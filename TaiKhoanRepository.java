package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.TaiKhoan;

import java.sql.*;
import java.util.Optional;

public class TaiKhoanRepository {

    // Đăng nhập: tìm theo sđt + mật khẩu
    public Optional<TaiKhoan> findByPhoneAndPassword(String soDienThoai, String matKhau) throws SQLException {
        String sql = "SELECT idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh " +
                     "FROM tai_khoan WHERE soDienThoai = ? AND matKhau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soDienThoai);
            ps.setString(2, matKhau);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan tk = new TaiKhoan(
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
                    return Optional.of(tk);
                }
            }
        }
        return Optional.empty();
    }

    // Kiểm tra trùng sđt hoặc email
    public boolean existsByPhoneOrEmail(String soDienThoai, String email) throws SQLException {
        String sql = "SELECT 1 FROM tai_khoan WHERE soDienThoai = ? OR email = ? LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soDienThoai);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Sinh id theo format KH0001, KH0002, ...
    private String generateNewId(Connection conn) throws SQLException {
        String sqlCheck = "SELECT idTaiKhoan FROM tai_khoan WHERE idTaiKhoan = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {
            int stt = 1;
            String newID;
            while (true) {
                newID = String.format("KH%04d", stt);
                checkStmt.setString(1, newID);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        return newID;
                    }
                }
                stt++;
            }
        }
    }

    // Đăng ký: chèn bản ghi mới; trả về TaiKhoan đã lưu (kèm id mới)
    public TaiKhoan insertTaiKhoan(TaiKhoan tk) throws SQLException {
        String sql = "INSERT INTO tai_khoan " +
                     "(idTaiKhoan, soDienThoai, email, matKhau, hoDem, ten, ngaySinh, diaChi, gioiTinh) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            String newId = generateNewId(conn);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newId);
                ps.setString(2, tk.getSoDienThoai());
                ps.setString(3, tk.getEmail());
                ps.setString(4, tk.getMatKhau());
                ps.setString(5, tk.getHoDem());
                ps.setString(6, tk.getTen());
                // giữ nguyên các thuộc tính là String như entity của bạn:
                ps.setString(7, tk.getNgaySinh()); // có thể null
                ps.setString(8, tk.getDiaChi());   // có thể null
                ps.setString(9, tk.getGioiTinh());

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    conn.commit();
                    TaiKhoan saved = new TaiKhoan(
                        newId,
                        tk.getSoDienThoai(),
                        tk.getEmail(),
                        tk.getMatKhau(),
                        tk.getHoDem(),
                        tk.getTen(),
                        tk.getNgaySinh(),
                        tk.getDiaChi(),
                        tk.getGioiTinh()
                    );
                    return saved;
                } else {
                    conn.rollback();
                    throw new SQLException("Không thể chèn tài khoản mới.");
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    // Đổi mật khẩu với kiểm tra mật khẩu cũ
    public boolean updatePassword(String idTaiKhoan, String oldPass, String newPass) throws SQLException {
        String sql = "UPDATE tai_khoan SET matKhau = ? WHERE idTaiKhoan = ? AND matKhau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPass);
            ps.setString(2, idTaiKhoan);
            ps.setString(3, oldPass);

            int updated = ps.executeUpdate();
            return updated > 0;
        }
    }
}
