package UI_Admin;

import java.sql.*;
import java.util.ArrayList;

public class LichChieuDAO {
    private static boolean kiemTraTonTai(String table, String column, String value) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.KetNoi();
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra tồn tại: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static ArrayList<LichChieu> layDanhSachLichChieu() {
        ArrayList<LichChieu> ds = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.KetNoi();
            if (conn == null) {
                System.out.println("Không thể kết nối tới cơ sở dữ liệu!");
                return ds;
            }
            String sql = "SELECT * FROM lich_chieu";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                LichChieu lc = new LichChieu(
                    rs.getString("idLichChieu"),
                    rs.getString("idPhim"),
                    rs.getTimestamp("gioChieu"),
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
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
            }
        }
        return ds;
    }

    public static boolean themLichChieu(LichChieu lc, String idPhongChieu) {
        Connection conn = null;
        PreparedStatement psGia = null;
        PreparedStatement psLichChieu = null;
        try {
            conn = DBConnection.KetNoi();
            if (conn == null) {
                System.out.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }

            // Kiểm tra idPhim và idPhongChieu tồn tại
            if (!kiemTraTonTai("phim", "idPhim", lc.getIdPhim())) {
                System.out.println("Mã phim " + lc.getIdPhim() + " không tồn tại!");
                return false;
            }
            if (!kiemTraTonTai("phong_chieu", "idPhongChieu", idPhongChieu)) {
                System.out.println("Mã phòng chiếu " + idPhongChieu + " không tồn tại!");
                return false;
            }

            conn.setAutoCommit(false);


            String sqlLichChieu = "INSERT INTO lich_chieu (idLichChieu, gioChieu, soGheConLai, idPhim, idPhongChieu, idGia) " +
                                 "VALUES (?, ?, ?, ?, ?, ?)";
            psLichChieu = conn.prepareStatement(sqlLichChieu);
            psLichChieu.setString(1, lc.getIdLichChieu());
            psLichChieu.setTimestamp(2, lc.getGioChieu());
            psLichChieu.setInt(3, lc.getSoGheConLai());
            psLichChieu.setString(4, lc.getIdPhim());
            psLichChieu.setString(5, idPhongChieu);
            psLichChieu.setString(6, lc.getIdGia());
            int rows = psLichChieu.executeUpdate();

            conn.commit();
            return rows > 0;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Lỗi rollback: " + ex.getMessage());
            }
            System.out.println("Lỗi thêm lịch chiếu: " + e.getMessage());
            return false;
        } finally {
            try {
                if (psGia != null) psGia.close();
                if (psLichChieu != null) psLichChieu.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    public static boolean xoaLichChieu(String idLichChieu) {
    Connection conn = null;
    PreparedStatement psLichChieuGhe = null;
    PreparedStatement psLichChieu = null;
    try {
        conn = DBConnection.KetNoi();
        if (conn == null) {
            System.out.println("Không thể kết nối tới cơ sở dữ liệu!");
            return false;
        }
        conn.setAutoCommit(false); // Bắt đầu giao dịch

        // Xóa các bản ghi trong bảng lichchieu_ghe liên quan đến idLichChieu
        String sqlLichChieuGhe = "DELETE FROM lichchieu_ghe WHERE idLichChieu = ?";
        psLichChieuGhe = conn.prepareStatement(sqlLichChieuGhe);
        psLichChieuGhe.setString(1, idLichChieu);
        psLichChieuGhe.executeUpdate();

        // Xóa lịch chiếu trong bảng lich_chieu
        String sqlLichChieu = "DELETE FROM lich_chieu WHERE idLichChieu = ?";
        psLichChieu = conn.prepareStatement(sqlLichChieu);
        psLichChieu.setString(1, idLichChieu);
        int rows = psLichChieu.executeUpdate();

        conn.commit(); // Xác nhận giao dịch
        return rows > 0;
    } catch (SQLException e) {
        try {
            if (conn != null) conn.rollback(); // Hoàn tác nếu có lỗi
        } catch (SQLException ex) {
            System.out.println("Lỗi rollback: " + ex.getMessage());
        }
        System.out.println("Lỗi xóa lịch chiếu: " + e.getMessage());
        return false;
    } finally {
        try {
            if (psLichChieuGhe != null) psLichChieuGhe.close();
            if (psLichChieu != null) psLichChieu.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
        }
    }
}

    public static boolean capNhatLichChieu(LichChieu lc) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.KetNoi();
            if (conn == null) {
                System.out.println("Không thể kết nối tới cơ sở dữ liệu!");
                return false;
            }
            // Kiểm tra idPhim và idPhongChieu tồn tại
            if (!kiemTraTonTai("phim", "idPhim", lc.getIdPhim())) {
                System.out.println("Mã phim " + lc.getIdPhim() + " không tồn tại!");
                return false;
            }
            if (!kiemTraTonTai("phong_chieu", "idPhongChieu", lc.getIdPhongChieu())) {
                System.out.println("Mã phòng chiếu " + lc.getIdPhongChieu() + " không tồn tại!");
                return false;
            }

            String sql = "UPDATE lich_chieu SET idPhim = ?, gioChieu = ?, soGheConLai = ?, idPhongChieu = ?, idGia = ? WHERE idLichChieu = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, lc.getIdPhim());
            ps.setTimestamp(2, lc.getGioChieu());
            ps.setInt(3, lc.getSoGheConLai());
            ps.setString(4, lc.getIdPhongChieu());
            ps.setString(5, lc.getIdGia());
            ps.setString(6, lc.getIdLichChieu());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật lịch chiếu: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
}