package UI_Admin;

import java.sql.*;
import java.util.ArrayList;

public class GiaDAO {
    public static ArrayList<Gia> layDanhSachGia() {
        ArrayList<Gia> ds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.KetNoi();
            String sql = "SELECT * FROM gia";
            Statement st = conn.createStatement();
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
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return ds;
    }

    public static boolean themGia(Gia gia) {
        Connection conn = null;
        try {
            conn = DBConnection.KetNoi();
            String sql = "INSERT INTO gia (idGia, tieuChuan, vip, triple) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
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
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }

    public static boolean capNhatGia(Gia gia) {
        Connection conn = null;
        try {
            conn = DBConnection.KetNoi();
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
            conn = DBConnection.KetNoi();
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Lấy danh sách idLichChieu liên quan đến idGia
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

            // Xóa các bản ghi trong ve liên quan đến idLichChieu
            String sqlDeleteVe = "DELETE FROM ve WHERE idLichChieu = ?";
            PreparedStatement psVe = conn.prepareStatement(sqlDeleteVe);
            for (String idLichChieu : lichChieuIds) {
                psVe.setString(1, idLichChieu);
                psVe.executeUpdate();
            }
            psVe.close();

            // Xóa các bản ghi trong lichchieu_ghe liên quan đến idLichChieu
            String sqlDeleteLichChieuGhe = "DELETE FROM lichchieu_ghe WHERE idLichChieu = ?";
            PreparedStatement psLichChieuGhe = conn.prepareStatement(sqlDeleteLichChieuGhe);
            for (String idLichChieu : lichChieuIds) {
                psLichChieuGhe.setString(1, idLichChieu);
                psLichChieuGhe.executeUpdate();
            }
            psLichChieuGhe.close();

            // Xóa các lịch chiếu liên quan
            String sqlDeleteLichChieu = "DELETE FROM lich_chieu WHERE idGia = ?";
            PreparedStatement psLichChieu = conn.prepareStatement(sqlDeleteLichChieu);
            psLichChieu.setString(1, idGia);
            psLichChieu.executeUpdate();
            psLichChieu.close();

            // Xóa giá
            String sqlDeleteGia = "DELETE FROM gia WHERE idGia = ?";
            PreparedStatement psGia = conn.prepareStatement(sqlDeleteGia);
            psGia.setString(1, idGia);
            int rows = psGia.executeUpdate();
            psGia.close();

            conn.commit(); // Commit transaction
            return rows > 0;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Rollback nếu có lỗi
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
            conn = DBConnection.KetNoi();
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
}