package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaiKhoanRepository {

    // Map một dòng ResultSet -> đối tượng TaiKhoan
    private TaiKhoan mapRow(ResultSet rs) throws SQLException {
        return new TaiKhoan(
                rs.getString("idTaiKhoan"),
                rs.getString("soDienThoai"),
                rs.getString("email"),
                rs.getString("matKhau"),
                rs.getString("hoDem"),
                rs.getString("ten"),
                rs.getString("ngaySinh"),
                rs.getString("diaChi"),
                rs.getString("gioiTinh")
        );
    }

    // Đăng nhập
    public Optional<TaiKhoan> findByPhoneAndPassword(String soDienThoai, String matKhau) throws SQLException {
        String sql = "SELECT idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh "
                + "FROM tai_khoan WHERE soDienThoai = ? AND matKhau = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soDienThoai);
            ps.setString(2, matKhau);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    // Lấy theo id
    public Optional<TaiKhoan> findById(String idTaiKhoan) throws SQLException {
        String sql = "SELECT idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh "
                + "FROM tai_khoan WHERE idTaiKhoan = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, idTaiKhoan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    //  check sdt dùng cho kiểm tra trùng khi đăng ký
    public Optional<TaiKhoan> findByPhone(String soDienThoai) throws SQLException {
        String sql = "SELECT idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh "
                + "FROM tai_khoan WHERE soDienThoai = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soDienThoai);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    // check theo mail dùng cho kiểm tra trùng khi đăng ký
    public Optional<TaiKhoan> findByEmail(String email) throws SQLException {
        String sql = "SELECT idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh "
                + "FROM tai_khoan WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    // LƯU TÀI KHOẢN MỚI (ĐĂNG KÝ)
    public TaiKhoan save(TaiKhoan tk) throws SQLException {
        String sql = "INSERT INTO tai_khoan "
                + "(idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tk.getIdTaiKhoan());
            ps.setString(2, tk.getSoDienThoai());
            ps.setString(3, tk.getEmail());
            ps.setString(4, tk.getMatKhau());
            ps.setString(5, tk.getHoDem());
            ps.setString(6, tk.getTen());

            // ngaySinh có thể NULL
            if (tk.getNgaySinh() == null || tk.getNgaySinh().trim().isEmpty()) {
                ps.setNull(7, java.sql.Types.VARCHAR);
            } else {
                ps.setString(7, tk.getNgaySinh());
            }

            ps.setString(8, tk.getDiaChi());
            ps.setString(9, tk.getGioiTinh());

            ps.executeUpdate();
        }
        return tk;
    }

    // Cập nhật thông tin
    public boolean updateInfo(TaiKhoan tk) throws SQLException {
        String sql = "UPDATE tai_khoan SET hoDem=?, ten=?, ngaySinh=?, diaChi=?, gioiTinh=? WHERE idTaiKhoan=?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tk.getHoDem());
            ps.setString(2, tk.getTen());
            ps.setString(3, tk.getNgaySinh());   // varchar nullable ok
            ps.setString(4, tk.getDiaChi());
            ps.setString(5, tk.getGioiTinh());
            ps.setString(6, tk.getIdTaiKhoan()); // ✅ chỉ để xác định dòng

            return ps.executeUpdate() > 0;
        }
    }

    // Đổi mật khẩu
    public boolean updatePassword(String idTaiKhoan, String oldPass, String newPass) throws SQLException {
        String sql = "UPDATE tai_khoan SET matKhau = ? WHERE idTaiKhoan = ? AND matKhau = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPass);
            ps.setString(2, idTaiKhoan);
            ps.setString(3, oldPass);

            int updated = ps.executeUpdate();
            return updated > 0;
        }
    }
    
    public List<TaiKhoan> listTaiKhoan() {
        List<TaiKhoan> ds = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT idTaiKhoan, soDienThoai,matKhau, email, hoDem, ten, ngaySinh, diaChi, gioiTinh FROM tai_khoan WHERE idTaiKhoan LIKE 'TK_%'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan nx = new TaiKhoan(
                    rs.getString("idTaiKhoan"),
                    rs.getString("soDienThoai"),
                    rs.getString("email"),
                    rs.getString("matKhau"),
                    rs.getString("hoDem"),
                    rs.getString("ten"),
                    rs.getString("ngaySinh"),
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
}
