package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.LichChieu;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Logic.entity.Phim;

public class PhimRepository {
    Connection connection = DatabaseConnection.getConnection();
    
    public boolean kiemTraTonTai(String table, String column, String value) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        return false;
    }
    
    public boolean updateLichChieu(LichChieu lc) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
            String sql = "UPDATE lich_chieu SET idPhim = ?, gioChieu = ?, soGheConLai = ?, idPhongChieu = ?, idGia = ? WHERE idLichChieu = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, lc.getIdPhim());
            ps.setTimestamp(2, Timestamp.valueOf(lc.getGioChieu()));
            ps.setInt(3, lc.getSoGheConLai());
            ps.setString(4, lc.getIdPhongChieu());
            ps.setString(5, lc.getIdGia());
            ps.setString(6, lc.getIdLichChieu());
            int rows = ps.executeUpdate();
        return rows > 0;
    }
    
    public boolean themPhim(Phim phim) throws Exception {
        if (connection == null) return false;

        long t0 = System.currentTimeMillis();
        try {
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Thêm vào bảng phim
            String sqlPhim = "INSERT INTO phim (idPhim, tenPhim, tacGia, thoiLuong, ngonNgu, dienVien, moTa, anhPhim) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psPhim = connection.prepareStatement(sqlPhim)) {
                psPhim.setString(1, phim.getIdPhim());
                psPhim.setString(2, phim.getTenPhim());
                psPhim.setString(3, phim.getTacGia());
                psPhim.setString(4, phim.getThoiLuong());
                psPhim.setString(5, phim.getNgonNgu());
                psPhim.setString(6, phim.getDienVien());
                psPhim.setString(7, phim.getMoTa());

                // Lưu URL hoặc đường dẫn file dưới dạng string
                psPhim.setString(8, phim.getAnhPhim() != null ? phim.getAnhPhim() : null);
                
                long t1 = System.currentTimeMillis();
                int rowsAffected = psPhim.executeUpdate();
                long t2 = System.currentTimeMillis();
                System.out.println("[themPhim] executeUpdate (insert phim) took " + (t2 - t1) + "ms");

                if (rowsAffected == 0) {
                    connection.rollback();
                    return false;
                }
            }

            long tInsert = System.currentTimeMillis();
            System.out.println("[themPhim] inserted phim in " + (tInsert - t0) + "ms, now inserting genres");

            // Prefetch genre name->id map to avoid repeated selects
            Map<String, String> genreMap = fetchTheLoaiMap(connection);

            String sqlTheLoai = "INSERT INTO phim_theloai (idPhim, idTheLoai) VALUES (?, ?)";
            try (PreparedStatement psTheLoai = connection.prepareStatement(sqlTheLoai)) {
                if (phim.getTheLoai() != null) {
                    for (String tenTheLoai : phim.getTheLoai()) {
                        String idTheLoai = genreMap.get(tenTheLoai.trim());
                        if (idTheLoai != null) {
                            psTheLoai.setString(1, phim.getIdPhim());
                            psTheLoai.setString(2, idTheLoai);
                            psTheLoai.addBatch();
                        } else {
                            System.err.println("Không tìm thấy idTheLoai cho: " + tenTheLoai);
                        }
                    }
                    long tBatchStart = System.currentTimeMillis();
                    psTheLoai.executeBatch();
                    long tBatchEnd = System.currentTimeMillis();
                    System.out.println("[themPhim] batch insert genres took " + (tBatchEnd - tBatchStart) + "ms");
                }
            }

            connection.commit(); // Commit transaction
            long tEnd = System.currentTimeMillis();
            System.out.println("[themPhim] total time: " + (tEnd - t0) + "ms");
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi thêm phim: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean capNhatPhim(Phim phim) throws Exception {
        if (connection == null) return false;

        try {
            connection.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật bảng phim
            String sqlPhim = "UPDATE phim SET tenPhim = ?, tacGia = ?, thoiLuong = ?, ngonNgu = ?, dienVien = ?, moTa = ?, anhPhim = ? WHERE idPhim = ?";
            PreparedStatement psPhim = connection.prepareStatement(sqlPhim);
            psPhim.setString(1, phim.getTenPhim());
            psPhim.setString(2, phim.getTacGia());
            psPhim.setString(3, phim.getThoiLuong());
            psPhim.setString(4, phim.getNgonNgu());
            psPhim.setString(5, phim.getDienVien());
            psPhim.setString(6, phim.getMoTa());
            psPhim.setString(7, phim.getAnhPhim() != null ? phim.getAnhPhim() : null);
            psPhim.setString(8, phim.getIdPhim());
            int rowsAffected = psPhim.executeUpdate();
            if (rowsAffected == 0) {
                connection.rollback();
                return false;
            }

            // Xóa thể loại cũ
            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDelete = connection.prepareStatement(sqlDeleteTheLoai);
            psDelete.setString(1, phim.getIdPhim());
            psDelete.executeUpdate();

            // Thêm lại thể loại mới
            String sqlInsertTheLoai = "INSERT INTO phim_theloai (idPhim, idTheLoai) VALUES (?, ?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlInsertTheLoai);
            for (String tenTheLoai : phim.getTheLoai()) {
                String idTheLoai = layIdTheLoaiTuTen(tenTheLoai, connection);
                if (idTheLoai != null) {
                    psInsert.setString(1, phim.getIdPhim());
                    psInsert.setString(2, idTheLoai);
                    psInsert.executeUpdate();
                } else {
                    System.err.println("Không tìm thấy idTheLoai cho: " + tenTheLoai);
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi cập nhật phim: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean xoaPhim(String idPhim) throws Exception {
        if (connection == null) return false;

        // Kiểm tra xem phim có lịch chiếu không
        if (phimDangCoLichChieu(idPhim, connection)) {
            System.out.println("Lỗi xóa phim: phim đang có lịch chiếu.");
            return false;
        }

        try {
            connection.setAutoCommit(false);

            // Xóa thể loại
            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDeleteTheLoai = connection.prepareStatement(sqlDeleteTheLoai);
            psDeleteTheLoai.setString(1, idPhim);
            psDeleteTheLoai.executeUpdate();

            // Xóa phim
            String sqlDeletePhim = "DELETE FROM phim WHERE idPhim = ?";
            PreparedStatement psDeletePhim = connection.prepareStatement(sqlDeletePhim);
            psDeletePhim.setString(1, idPhim);
            int rowsAffected = psDeletePhim.executeUpdate();

            if (rowsAffected == 0) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi xóa phim: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Phim> layDanhSachPhim() throws Exception {
        ArrayList<Phim> dsPhim = new ArrayList<>();
        if (connection == null) return dsPhim;

        try {
            String sql = "SELECT p.*, GROUP_CONCAT(tl.tenTheLoai) AS theLoai " +
                         "FROM phim p " +
                         "LEFT JOIN phim_theloai pt ON p.idPhim = pt.idPhim " +
                         "LEFT JOIN the_loai tl ON pt.idTheLoai = tl.idTheLoai " +
                         "GROUP BY p.idPhim";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                List<String> theLoai = new ArrayList<>();
                String theLoaiStr = rs.getString("theLoai");
                if (theLoaiStr != null && !theLoaiStr.isEmpty()) {
                    String[] theLoaiArray = theLoaiStr.split(",");
                    for (String tenTheLoai : theLoaiArray) {
                        theLoai.add(tenTheLoai.trim());
                    }
                }
                Phim phim = new Phim(
                    rs.getString("idPhim"),
                    rs.getString("tenPhim"),
                    rs.getString("tacGia"),
                    theLoai,
                    rs.getString("thoiLuong"),
                    rs.getString("ngonNgu"),
                    rs.getString("dienVien"),
                    rs.getString("moTa"),
                    rs.getString("anhPhim")
                );
                dsPhim.add(phim);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phim: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dsPhim;
    }

    // Trả về idTheLoai từ tên thể loại
    private String layIdTheLoaiTuTen(String ten, Connection conn) {
        String sql = "SELECT idTheLoai FROM the_loai WHERE tenTheLoai = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten.trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("idTheLoai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> fetchTheLoaiMap(Connection conn) {
        Map<String, String> map = new HashMap<>();
        String sql = "SELECT idTheLoai, tenTheLoai FROM the_loai";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("idTheLoai");
                    String ten = rs.getString("tenTheLoai");
                    if (ten != null) map.put(ten.trim(), id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // Kiểm tra xem phim có trong lịch chiếu hay không
    private boolean phimDangCoLichChieu(String idPhim, Connection conn) {
        String sql = "SELECT COUNT(*) FROM lich_chieu WHERE idPhim = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPhim);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; // giả định có nếu lỗi
    }
    
    // --- Bổ sung cho AddLC ---
    public boolean themLichChieu(LichChieu lc) throws SQLException {
        String sql = "INSERT INTO lich_chieu(idLichChieu, idPhim, gioChieu, idPhongChieu, soGheConLai, idGia) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, lc.getIdLichChieu());
        ps.setString(2, lc.getIdPhim());
        ps.setTimestamp(3, Timestamp.valueOf(lc.getGioChieu()));
        ps.setString(4, lc.getIdPhongChieu());
        ps.setInt(5, lc.getSoGheConLai());
        ps.setString(6, lc.getIdGia());
        int rows = ps.executeUpdate();
        return rows > 0;
    }

    // --- Bổ sung cho SearchLC ---
    public List<LichChieu> timKiemLichChieu(String idLC, String idPhim, String idPhong) throws SQLException {
        List<LichChieu> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM lich_chieu WHERE 1=1");
        
        if (!idLC.isEmpty()) sql.append(" AND idLichChieu LIKE ?");
        if (!idPhim.isEmpty()) sql.append(" AND idPhim LIKE ?");
        if (idPhong != null && !idPhong.isEmpty()) sql.append(" AND idPhongChieu = ?");

        PreparedStatement ps = connection.prepareStatement(sql.toString());
        int index = 1;
        if (!idLC.isEmpty()) ps.setString(index++, "%" + idLC + "%");
        if (!idPhim.isEmpty()) ps.setString(index++, "%" + idPhim + "%");
        if (idPhong != null && !idPhong.isEmpty()) ps.setString(index++, idPhong);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LichChieu lc = new LichChieu();
            lc.setIdLichChieu(rs.getString("idLichChieu"));
            lc.setIdPhim(rs.getString("idPhim"));
            lc.setGioChieu(rs.getTimestamp("gioChieu").toLocalDateTime());
            lc.setIdPhongChieu(rs.getString("idPhongChieu"));
            lc.setSoGheConLai(rs.getInt("soGheConLai"));
            lc.setIdGia(rs.getString("idGia"));
            list.add(lc);
        }
        return list;
    }

    // --- Bổ sung load ComboBox ---
    public List<String> getAllPhongChieu() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT idPhongChieu FROM phong_chieu";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString("idPhongChieu"));
        }
        return list;
    }
    
    public boolean xoaLichChieu(String idLichChieu) throws SQLException {
        PreparedStatement psLichChieuGhe = null;
        PreparedStatement psLichChieu = null;
        boolean result = false;

        try {
            // 1. Tắt tự động lưu để quản lý Transaction
            connection.setAutoCommit(false); 

            // 2. Xóa các bản ghi trong bảng con (lichchieu_ghe) trước
            String sqlLichChieuGhe = "DELETE FROM lichchieu_ghe WHERE idLichChieu = ?";
            psLichChieuGhe = connection.prepareStatement(sqlLichChieuGhe);
            psLichChieuGhe.setString(1, idLichChieu);
            psLichChieuGhe.executeUpdate();

            // 3. Xóa lịch chiếu trong bảng cha (lich_chieu)
            String sqlLichChieu = "DELETE FROM lich_chieu WHERE idLichChieu = ?";
            psLichChieu = connection.prepareStatement(sqlLichChieu);
            psLichChieu.setString(1, idLichChieu);
            int rows = psLichChieu.executeUpdate();

            // 4. Nếu chạy đến đây không lỗi thì Commit (Lưu thật)
            connection.commit();
            result = rows > 0;

        } catch (SQLException e) {
            // 5. Nếu có lỗi thì Rollback (Hoàn tác)
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; // Ném lỗi ra để Service/Controller biết
        } finally {
            // 6. Đóng PreparedStatement và bật lại AutoCommit
            if (psLichChieuGhe != null) psLichChieuGhe.close();
            if (psLichChieu != null) psLichChieu.close();
            connection.setAutoCommit(true); 
        }
        return result;
    }
}