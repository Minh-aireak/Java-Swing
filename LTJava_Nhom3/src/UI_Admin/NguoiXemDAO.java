package UI_Admin;

import java.sql.*;
import java.util.ArrayList;

public class NguoiXemDAO {
    public static ArrayList<NguoiXem> layDanhSachNguoiXem() {
        ArrayList<NguoiXem> ds = new ArrayList<>();
        Connection conn = DBConnection.KetNoi();
        String sql = "SELECT idTaiKhoan, soDienThoai,matKhau, email, hoDem, ten, ngaySinh, diaChi, gioiTinh FROM tai_khoan WHERE idTaiKhoan LIKE 'KH%'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                NguoiXem nx = new NguoiXem(
                    rs.getString("idTaiKhoan"),
                    rs.getString("soDienThoai"),
                    rs.getString("email"),
                    rs.getString("matKhau"),
                    rs.getString("hoDem"),
                    rs.getString("ten"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getString("gioiTinh")
                );
                ds.add(nx);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách người xem: " + e.getMessage());
        }
        return ds;
    }

    public static boolean themNguoiXem(NguoiXem nx) {
        Connection conn = DBConnection.KetNoi();
        String sql = "INSERT INTO tai_khoan (idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nx.getIdTaiKhoan());
            ps.setString(2, nx.getSoDienThoai());
            ps.setString(3, nx.getEmail());
            ps.setString(4, "1");
            ps.setString(5, nx.getHoDem());
            ps.setString(6, nx.getTen());
            ps.setDate(7, nx.getNgaySinh());
            ps.setString(8, nx.getDiaChi());
            ps.setString(9, nx.getGioiTinh());
            int rows = ps.executeUpdate();
            ps.close();
            conn.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm người xem: " + e.getMessage());
            return false;
        }
    }

    public static boolean suaNguoiXem(NguoiXem nx) {
        Connection conn = DBConnection.KetNoi();
        String sql = "UPDATE tai_khoan SET soDienThoai = ?, email = ?, matKhau = ?,hoDem = ?, ten = ?, ngaySinh = ?, diaChi = ?, gioiTinh = ? WHERE idTaiKhoan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nx.getSoDienThoai());
            ps.setString(2, nx.getEmail());
            ps.setString(3, nx.getMatKhau());
            ps.setString(4, nx.getHoDem());
            ps.setString(5, nx.getTen());
            ps.setDate(6, nx.getNgaySinh());
            ps.setString(7, nx.getDiaChi());
            ps.setString(8, nx.getGioiTinh());
            ps.setString(9, nx.getIdTaiKhoan());
            int rows = ps.executeUpdate();
            ps.close();
            conn.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi sửa người xem: " + e.getMessage());
            return false;
        }
    }

    public static boolean xoaNguoiXem(String idTaiKhoan) {
        Connection conn = DBConnection.KetNoi();
        String sql = "DELETE FROM tai_khoan WHERE idTaiKhoan = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idTaiKhoan);
            int rows = ps.executeUpdate();
            ps.close();
            conn.close();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa người xem: " + e.getMessage());
            return false;
        }
    }
}