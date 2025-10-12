package Logic.repository.impl;

import Logic.entity.Phim;
import Logic.repository.PhimRepository;
import UI_Admin.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of PhimRepository. Ported from UI_Admin.PhimDAO.
 */
public class JdbcPhimRepository implements PhimRepository {

    @Override
    public boolean themPhim(Phim phim) throws FileNotFoundException, Exception {
        Connection conn = DBConnection.KetNoi();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Thêm vào bảng phim
            String sqlPhim = "INSERT INTO phim (idPhim, tenPhim, tacGia, thoiLuong, ngonNgu, dienVien, moTa, anhPhim) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psPhim = conn.prepareStatement(sqlPhim);
            psPhim.setString(1, phim.getIdPhim());
            psPhim.setString(2, phim.getTenPhim());
            psPhim.setString(3, phim.getTacGia());
            psPhim.setString(4, phim.getThoiLuong());
            psPhim.setString(5, phim.getNgonNgu());
            psPhim.setString(6, phim.getDienVien());
            psPhim.setString(7, phim.getMoTa());
            File selectedFile = new File(phim.getAnhPhim());
            FileInputStream fis = new FileInputStream(selectedFile);
            psPhim.setBinaryStream(8, fis, (int) selectedFile.length());
            int rowsAffected = psPhim.executeUpdate();

            if (rowsAffected == 0) {
                conn.rollback();
                return false;
            }

            // Thêm thể loại vào bảng phim_theloai
            String sqlTheLoai = "INSERT INTO phim_theloai (idPhim, idTheLoai) VALUES (?, ?)";
            PreparedStatement psTheLoai = conn.prepareStatement(sqlTheLoai);
            for (String tenTheLoai : phim.getTheLoai()) {
                String idTheLoai = layIdTheLoaiTuTen(tenTheLoai, conn);
                if (idTheLoai != null) {
                    psTheLoai.setString(1, phim.getIdPhim());
                    psTheLoai.setString(2, idTheLoai);
                    psTheLoai.executeUpdate();
                } else {
                    System.err.println("Không tìm thấy idTheLoai cho: " + tenTheLoai);
                }
            }

            conn.commit(); // Commit transaction
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi thêm phim: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean capNhatPhim(Phim phim) throws Exception {
        Connection conn = DBConnection.KetNoi();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật bảng phim
            String sqlPhim = "UPDATE phim SET tenPhim = ?, tacGia = ?, thoiLuong = ?, ngonNgu = ?, dienVien = ?, moTa = ?, anhPhim = ? WHERE idPhim = ?";
            PreparedStatement psPhim = conn.prepareStatement(sqlPhim);
            psPhim.setString(1, phim.getTenPhim());
            psPhim.setString(2, phim.getTacGia());
            psPhim.setString(3, phim.getThoiLuong());
            psPhim.setString(4, phim.getNgonNgu());
            psPhim.setString(5, phim.getDienVien());
            psPhim.setString(6, phim.getMoTa());
            // Nếu ảnh là đường dẫn mới, lưu như trước; nếu lưu khác, client cần đảm bảo
            File selectedFile = new File(phim.getAnhPhim());
            if (selectedFile.exists()) {
                FileInputStream fis = new FileInputStream(selectedFile);
                psPhim.setBinaryStream(7, fis, (int) selectedFile.length());
            } else {
                psPhim.setString(7, phim.getAnhPhim());
            }
            psPhim.setString(8, phim.getIdPhim());
            int rowsAffected = psPhim.executeUpdate();

            if (rowsAffected == 0) {
                conn.rollback();
                return false;
            }

            // Xóa thể loại cũ
            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDelete = conn.prepareStatement(sqlDeleteTheLoai);
            psDelete.setString(1, phim.getIdPhim());
            psDelete.executeUpdate();

            // Thêm lại thể loại mới
            String sqlInsertTheLoai = "INSERT INTO phim_theloai (idPhim, idTheLoai) VALUES (?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(sqlInsertTheLoai);
            for (String tenTheLoai : phim.getTheLoai()) {
                String idTheLoai = layIdTheLoaiTuTen(tenTheLoai, conn);
                if (idTheLoai != null) {
                    psInsert.setString(1, phim.getIdPhim());
                    psInsert.setString(2, idTheLoai);
                    psInsert.executeUpdate();
                } else {
                    System.err.println("Không tìm thấy idTheLoai cho: " + tenTheLoai);
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi cập nhật phim: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean xoaPhim(String idPhim) throws Exception {
        Connection conn = DBConnection.KetNoi();
        if (conn == null) return false;

        // Kiểm tra xem phim có lịch chiếu không
        if (phimDangCoLichChieu(idPhim, conn)) {
            System.out.println("Lỗi xóa phim: phim đang có lịch chiếu.");
            return false;
        }

        try {
            conn.setAutoCommit(false);

            // Xóa thể loại
            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDeleteTheLoai = conn.prepareStatement(sqlDeleteTheLoai);
            psDeleteTheLoai.setString(1, idPhim);
            psDeleteTheLoai.executeUpdate();

            // Xóa phim
            String sqlDeletePhim = "DELETE FROM phim WHERE idPhim = ?";
            PreparedStatement psDeletePhim = conn.prepareStatement(sqlDeletePhim);
            psDeletePhim.setString(1, idPhim);
            int rowsAffected = psDeletePhim.executeUpdate();

            if (rowsAffected == 0) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Lỗi xóa phim: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Phim> layDanhSachPhim() throws Exception {
        ArrayList<Phim> dsPhim = new ArrayList<>();
        Connection conn = DBConnection.KetNoi();
        if (conn == null) return dsPhim;

        try {
            String sql = "SELECT p.*, GROUP_CONCAT(tl.tenTheLoai) AS theLoai " +
                         "FROM phim p " +
                         "LEFT JOIN phim_theloai pt ON p.idPhim = pt.idPhim " +
                         "LEFT JOIN the_loai tl ON pt.idTheLoai = tl.idTheLoai " +
                         "GROUP BY p.idPhim";
            PreparedStatement ps = conn.prepareStatement(sql);
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
                conn.close();
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
}
