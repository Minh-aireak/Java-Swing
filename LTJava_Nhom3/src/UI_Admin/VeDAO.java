package UI_Admin;

import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class VeDAO {
    public static ArrayList<Ve> layDanhSachDatVe() {
        ArrayList<Ve> ds = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.KetNoi();
            String sql = "SELECT v.idVe, CONCAT(tk.hoDem, ' ', tk.ten) AS ten, " +
                         "p.tenPhim, DATE_FORMAT(lc.gioChieu, '%d/%m/%Y %H:%i') AS gioChieu, " +
                         "g.idGhe, pc.tenPhong, " +
                         "CASE g.loaiGhe " +
                         "WHEN 'Tiêu chuẩn' THEN gia.tieuChuan " +
                         "WHEN 'VIP' THEN gia.vip " +
                         "WHEN 'Triple' THEN gia.triple END AS giaVe " +
                         "FROM ve v " +
                         "JOIN bill b ON v.idBill = b.idBill " +
                         "JOIN tai_khoan tk ON b.idTaiKhoan = tk.idTaiKhoan " +
                         "JOIN lich_chieu lc ON v.idLichChieu = lc.idLichChieu " +
                         "JOIN phim p ON lc.idPhim = p.idPhim " +
                         "JOIN ghe g ON v.idGhe = g.idGhe " +
                         "JOIN phong_chieu pc ON lc.idPhongChieu = pc.idPhongChieu " +
                         "JOIN gia ON v.idGia = gia.idGia";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ve dv = new Ve(
                    rs.getString("idVe"),
                    rs.getString("ten"),
                    rs.getString("tenPhim"),
                    rs.getString("gioChieu"),
                    rs.getString("idGhe"),
                    rs.getString("tenPhong"),
                    rs.getInt("giaVe")
                );
                ds.add(dv);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách đặt vé: " + e.getMessage());
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return ds;
    }

    public static boolean huyVe(String idVe) {
        Connection conn = null;
        try {
            conn = DBConnection.KetNoi();
            String sqlVe = "DELETE FROM ve WHERE idVe = ?";
            PreparedStatement psVe = conn.prepareStatement(sqlVe);
            psVe.setString(1, idVe);
            int rows = psVe.executeUpdate();
            
            String sqlGhe = "UPDATE lichchieu_ghe SET trangThai = 'Còn trống' " +
                           "WHERE idLichChieu = (SELECT idLichChieu FROM ve WHERE idVe = ?) " +
                           "AND idGhe = (SELECT idGhe FROM ve WHERE idVe = ?)";
            PreparedStatement psGhe = conn.prepareStatement(sqlGhe);
            psGhe.setString(1, idVe);
            psGhe.setString(2, idVe);
            psGhe.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi hủy vé: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }
}