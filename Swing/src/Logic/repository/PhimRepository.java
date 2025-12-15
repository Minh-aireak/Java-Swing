package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.dto.response.SearchPhimResponse;
import Logic.entity.Gia;
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
import java.util.Arrays;
import java.util.Collections;

public class PhimRepository {
    Connection connection = null;
    
    public boolean kiemTraTonTai(String table, String column, String value) throws SQLException {
        connection = DatabaseConnection.getConnection();
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
        if (lc.getGioChieu() == null) {
            System.out.println("Lỗi: Giờ chiếu bị Null");
            return false;
        }

        String sql = "UPDATE lich_chieu SET idPhim = ?, gioChieu = ?, soGheConLai = ?, idPhongChieu = ?, idGia = ? WHERE idLichChieu = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, lc.getIdPhim());
            ps.setTimestamp(2, Timestamp.valueOf(lc.getGioChieu()));
            ps.setInt(3, lc.getSoGheConLai());
            ps.setString(4, lc.getIdPhongChieu());
            ps.setString(5, lc.getIdGia());
            ps.setString(6, lc.getIdLichChieu());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            System.out.println("Lỗi update: Mã Phim, Phòng hoặc Giá không tồn tại!");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi hệ thống khi update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean themPhim(Phim phim) throws Exception {
        connection = DatabaseConnection.getConnection();

        long t0 = System.currentTimeMillis();
        try {
            connection.setAutoCommit(false); 

            String sqlPhim = "INSERT INTO phim (idPhim, tenPhim, tacGia, thoiLuong, ngonNgu, dienVien, moTa, anhPhim) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psPhim = connection.prepareStatement(sqlPhim)) {
                psPhim.setString(1, phim.getIdPhim());
                psPhim.setString(2, phim.getTenPhim());
                psPhim.setString(3, phim.getTacGia());
                psPhim.setString(4, phim.getThoiLuong());
                psPhim.setString(5, phim.getNgonNgu());
                psPhim.setString(6, phim.getDienVien());
                psPhim.setString(7, phim.getMoTa());

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

            connection.commit(); 
            long tEnd = System.currentTimeMillis();
            System.out.println("[themPhim] total time: " + (tEnd - t0) + "ms");
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); 
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
        connection = DatabaseConnection.getConnection();
        
        try {
            connection.setAutoCommit(false); 

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

            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDelete = connection.prepareStatement(sqlDeleteTheLoai);
            psDelete.setString(1, phim.getIdPhim());
            psDelete.executeUpdate();

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
        connection = DatabaseConnection.getConnection();

        if (phimDangCoLichChieu(idPhim, connection)) {
            System.out.println("Lỗi xóa phim: phim đang có lịch chiếu.");
            return false;
        }

        try {
            connection.setAutoCommit(false);

            String sqlDeleteTheLoai = "DELETE FROM phim_theloai WHERE idPhim = ?";
            PreparedStatement psDeleteTheLoai = connection.prepareStatement(sqlDeleteTheLoai);
            psDeleteTheLoai.setString(1, idPhim);
            psDeleteTheLoai.executeUpdate();

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
        connection = DatabaseConnection.getConnection();

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
        return true; 
    }
    
    public boolean themLichChieu(LichChieu lc) throws SQLException {
        connection = DatabaseConnection.getConnection();
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

    public List<LichChieu> timKiemLichChieu(String idLC, String idPhim, String idPhong) throws SQLException {
        List<LichChieu> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM lich_chieu WHERE 1=1");
        connection = DatabaseConnection.getConnection();
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

    public List<String> getAllPhongChieu() throws SQLException {
        connection = DatabaseConnection.getConnection();
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
       connection = DatabaseConnection.getConnection();
        PreparedStatement psLichChieuGhe = null;
        PreparedStatement psLichChieu = null;
        boolean result = false;

        try {
            connection.setAutoCommit(false); 

            String sqlLichChieuGhe = "DELETE FROM lichchieu_ghe WHERE idLichChieu = ?";
            psLichChieuGhe = connection.prepareStatement(sqlLichChieuGhe);
            psLichChieuGhe.setString(1, idLichChieu);
            psLichChieuGhe.executeUpdate();

            String sqlLichChieu = "DELETE FROM lich_chieu WHERE idLichChieu = ?";
            psLichChieu = connection.prepareStatement(sqlLichChieu);
            psLichChieu.setString(1, idLichChieu);
            int rows = psLichChieu.executeUpdate();

            connection.commit();
            result = rows > 0;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; 
        } finally {
            if (psLichChieuGhe != null) psLichChieuGhe.close();
            if (psLichChieu != null) psLichChieu.close();
            connection.setAutoCommit(true); 
        }
        return result;
    }
    
    public List<LichChieu> getListLichChieu() {
        List<LichChieu> ds = new ArrayList<>();
        connection = DatabaseConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("Không thể kết nối tới cơ sở dữ liệu!");
                return ds;
            }
            String sql = "SELECT * FROM lich_chieu";
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                LichChieu lc = new LichChieu(
                    rs.getString("idLichChieu"),
                    rs.getString("idPhim"),
                    rs.getTimestamp("gioChieu").toLocalDateTime(),
                    rs.getString("idPhongChieu"),
                    rs.getInt("soGheConLai"),
                    rs.getString("idGia")
                );
                ds.add(lc);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách lịch chiếu: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
            }
        }
        return ds; 
    }
    
    public List<Gia> layDanhSachGia() {
        List<Gia> ds = new ArrayList<>();
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM gia";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Gia gia = new Gia(
                    rs.getString("idGia"),
                    rs.getInt("tieuChuan"),
                    rs.getInt("vip"),
                    rs.getInt("triple")
                );
                ds.add(gia);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách giá: " + e.getMessage());
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
        return ds;
    }
    
    public boolean themGia(Gia gia) {
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO gia (idGia, tieuChuan, vip, triple) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gia.getIdGia());
            ps.setInt(2, gia.getTieuChuan());
            ps.setInt(3, gia.getVip());
            ps.setInt(4, gia.getTriple());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm giá: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
    
    public static boolean capNhatGia(Gia gia) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE gia SET tieuChuan = ?, vip = ?, triple = ? WHERE idGia = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, gia.getTieuChuan());
            ps.setInt(2, gia.getVip());
            ps.setInt(3, gia.getTriple());
            ps.setString(4, gia.getIdGia());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật giá: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }

    public static boolean xoaGia(String idGia) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            String sqlSelectLichChieu = "SELECT idLichChieu FROM lich_chieu WHERE idGia = ?";
            PreparedStatement psSelectLichChieu = conn.prepareStatement(sqlSelectLichChieu);
            psSelectLichChieu.setString(1, idGia);
            ResultSet rs = psSelectLichChieu.executeQuery();
            ArrayList<String> lichChieuIds = new ArrayList<>();
            while (rs.next()) {
                lichChieuIds.add(rs.getString("idLichChieu"));
            }
            rs.close();
            psSelectLichChieu.close();

            String sqlDeleteVe = "DELETE FROM ve WHERE idLichChieu = ?";
            PreparedStatement psVe = conn.prepareStatement(sqlDeleteVe);
            for (String idLichChieu : lichChieuIds) {
                psVe.setString(1, idLichChieu);
                psVe.executeUpdate();
            }
            psVe.close();

            String sqlDeleteLichChieuGhe = "DELETE FROM lichchieu_ghe WHERE idLichChieu = ?";
            PreparedStatement psLichChieuGhe = conn.prepareStatement(sqlDeleteLichChieuGhe);
            for (String idLichChieu : lichChieuIds) {
                psLichChieuGhe.setString(1, idLichChieu);
                psLichChieuGhe.executeUpdate();
            }
            psLichChieuGhe.close();

            String sqlDeleteLichChieu = "DELETE FROM lich_chieu WHERE idGia = ?";
            PreparedStatement psLichChieu = conn.prepareStatement(sqlDeleteLichChieu);
            psLichChieu.setString(1, idGia);
            psLichChieu.executeUpdate();
            psLichChieu.close();

            String sqlDeleteGia = "DELETE FROM gia WHERE idGia = ?";
            PreparedStatement psGia = conn.prepareStatement(sqlDeleteGia);
            psGia.setString(1, idGia);
            int rows = psGia.executeUpdate();
            psGia.close();

            conn.commit(); 
            return rows > 0;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Lỗi rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Lỗi xóa giá: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            }
        }
    }

    public static Gia timKiemGia(String idGia) {
        Connection conn = null;
        Gia gia = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM gia WHERE idGia = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idGia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                gia = new Gia(
                    rs.getString("idGia"),
                    rs.getInt("tieuChuan"),
                    rs.getInt("vip"),
                    rs.getInt("triple")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm giá: " + e.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return gia;
    }
    
    public Phim getChiTietPhim(String idPhim) {
        
        try {
            Phim phim = new Phim();
            connection = DatabaseConnection.getConnection();
            String statement = "SELECT p.idPhim, p.tenPhim, p.tacGia, p.dienVien, p.thoiLuong, GROUP_CONCAT(tl.tenTheLoai SEPARATOR ', ') AS theLoai, p.ngonNgu, p.moTa, p.anhPhim " +
            "FROM phim p " +
            "JOIN phim_theloai ptl ON p.idPhim = ptl.idPhim " +
            "JOIN the_loai tl ON ptl.idTheLoai = tl.idTheLoai " +
            "WHERE p.idPhim = ? " +
            "GROUP BY p.idPhim, p.tenPhim, p.tacGia, p.dienVien, p.thoiLuong, p.ngonNgu, p.moTa ";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setString(1, idPhim);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                phim.setIdPhim(rs.getString("idPhim"));
                phim.setTenPhim(rs.getString("tenPhim"));
                phim.setTacGia(rs.getString("tacGia"));
                phim.setDienVien(rs.getString("dienVien"));
                phim.setThoiLuong(rs.getString("thoiLuong"));
                List<String> listChosenPhim = Arrays.asList(rs.getString("theLoai").split(","));
                phim.setTheLoai(listChosenPhim);
                phim.setNgonNgu(rs.getString("ngonNgu"));
                phim.setMoTa(rs.getString("moTa"));
                phim.setAnhPhim(rs.getString("anhPhim"));
            }
            
            return phim;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<SearchPhimResponse> search(String tenPhim, String tacGia, List<String> list_chosen) throws SQLException {
        
        try {
            List<SearchPhimResponse> list = new ArrayList<SearchPhimResponse>();
            connection = DatabaseConnection.getConnection();
            String statement = "SELECT DISTINCT p.idPhim, p.tenPhim, p.thoiLuong " +
            "FROM phim p " +
            "JOIN phim_theloai ptl ON p.idPhim = ptl.idPhim " +
            "JOIN the_loai tl ON ptl.idTheLoai = tl.idTheLoai " +
            "WHERE 1=1";
            if(!tenPhim.isEmpty()){
                statement += " AND p.tenPhim LIKE ?";
            }

            if(!tacGia.isEmpty()){
                statement += " AND p.tacGia LIKE ?";
            }

            if(!list_chosen.isEmpty()){
                statement += " AND tl.idTheLoai IN (" + String.join(",", Collections.nCopies(list_chosen.size(), "?")) + ") " +
                "GROUP BY p.idPhim, p.tenPhim, p.thoiLuong " +
                "HAVING COUNT(DISTINCT tl.idTheLoai) = ?";
            }
            
            PreparedStatement ps = connection.prepareStatement(statement);
            int index = 1;
            
            if(!tenPhim.isEmpty()){
                ps.setString(index++, "%" + tenPhim + "%");
            }
            
            if(!tacGia.isEmpty()){
                ps.setString(index++, "%" + tacGia + "%");
            }
            
            if(!list_chosen.isEmpty()){
                for (String str : list_chosen){
                    ps.setString(index++, str);
                }
                ps.setInt(index, list_chosen.size());
            }
            
            ResultSet rs = ps.executeQuery();

            boolean hasResult = false;
            while(rs.next()){
                hasResult = true;
                SearchPhimResponse spr = new SearchPhimResponse();
                spr.setIdPhim(rs.getString("idPhim"));
                spr.setTenPhim(rs.getString("tenPhim"));
                spr.setThoiLuong(rs.getString("thoiLuong"));
                list.add(spr);
            }
            
            if (!hasResult)
                return null;
            
            return list;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}