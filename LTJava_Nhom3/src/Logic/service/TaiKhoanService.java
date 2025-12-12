package Logic.service;

import Global.Session;
import Logic.entity.TaiKhoan;
import Logic.repository.TaiKhoanRepository;

import java.sql.SQLException;
import java.util.Optional;

public class TaiKhoanService {

    private final TaiKhoanRepository repo = new TaiKhoanRepository();
    private TaiKhoan currentUser;
    private static Session session;

    // Lớp Result dùng chung
    public static class Result<T> {
        private final boolean success;
        private final String message;
        private final T data;

        public Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public T getData() { return data; }

        public static <T> Result<T> ok(String msg, T data) {
            return new Result<>(true, msg, data);
        }

        public static <T> Result<T> fail(String msg) {
            return new Result<>(false, msg, null);
        }
    }

    // login
    public Result<TaiKhoan> dangNhap(String soDienThoai, String matKhau) {
        try {
            Optional<TaiKhoan> opt = repo.findByPhoneAndPassword(soDienThoai, matKhau);
            if (opt.isPresent()) {
                currentUser = opt.get();
                session.setCurrentUser(currentUser);
                return Result.ok("Đăng nhập thành công!", currentUser);
            } else {
                return Result.fail("Sai số điện thoại hoặc mật khẩu!");
            }
        } catch (SQLException e) {
            return Result.fail("Lỗi DB: " + e.getMessage());
        }
    }

    // logup
    public Result<TaiKhoan> dangKy(String soDienThoai,
                                   String email,
                                   String matKhau,
                                   String hoDem,
                                   String ten,
                                   String gioiTinh,
                                   String ngaySinh,
                                   String diaChi) {
        try {
            // check sdt
            if (repo.findByPhone(soDienThoai).isPresent()) {
                return Result.fail("Số điện thoại đã được sử dụng!");
            }

            // check mail
            if (repo.findByEmail(email).isPresent()) {
                return Result.fail("Email đã được sử dụng!");
            }

            TaiKhoan tk = new TaiKhoan();
            tk.setSoDienThoai(soDienThoai);
            tk.setEmail(email);
            tk.setMatKhau(matKhau);
            tk.setHoDem(hoDem);
            tk.setTen(ten);
            tk.setGioiTinh(gioiTinh);
            tk.setNgaySinh(ngaySinh);
            tk.setDiaChi(diaChi);

            TaiKhoan saved = repo.save(tk);
            if (saved == null || saved.getIdTaiKhoan() == null) {
                return Result.fail("Đăng ký thất bại, không thể lưu vào CSDL!");
            }

            return Result.ok("Đăng ký thành công!", saved);

        } catch (SQLException e) {
            return Result.fail("Lỗi DB khi đăng ký: " + e.getMessage());
        }
    }

    // change pass
    public Result<Void> doiMatKhau(String idTaiKhoan, String oldPass, String newPass) {
        try {
            boolean ok = repo.updatePassword(idTaiKhoan, oldPass, newPass);
            if (ok) {
                return Result.ok("Đổi mật khẩu thành công!", null);
            } else {
                return Result.fail("Mật khẩu cũ không đúng!");
            }
        } catch (SQLException e) {
            return Result.fail("Lỗi DB: " + e.getMessage());
        }
    }

    // update inf
    public Result<TaiKhoan> capNhatThongTin(String idTaiKhoan,
                                            String hoDem,
                                            String ten,
                                            String ngaySinh,
                                            String diaChi,
                                            String gioiTinh) {
        try {
            Optional<TaiKhoan> opt = repo.findById(idTaiKhoan);
            if (opt.isEmpty()) {
                return Result.fail("Không tìm thấy tài khoản!");
            }

            TaiKhoan tk = opt.get();

            if (hoDem != null && !hoDem.trim().isEmpty()) tk.setHoDem(hoDem.trim());
            if (ten != null && !ten.trim().isEmpty()) tk.setTen(ten.trim());
            if (ngaySinh != null && !ngaySinh.trim().isEmpty()) tk.setNgaySinh(ngaySinh.trim());
            if (diaChi != null && !diaChi.trim().isEmpty()) tk.setDiaChi(diaChi.trim());
            if (gioiTinh != null && !gioiTinh.trim().isEmpty()) tk.setGioiTinh(gioiTinh.trim());

            boolean ok = repo.updateInfo(tk);
            if (!ok) {
                return Result.fail("Cập nhật thất bại!");
            }

            if (currentUser != null && currentUser.getIdTaiKhoan().equals(idTaiKhoan)) {
                currentUser = tk;
            }

            return Result.ok("Cập nhật thành công!", tk);

        } catch (SQLException e) {
            return Result.fail("Lỗi DB: " + e.getMessage());
        }
    }

    // 🔹 LOGOUT
    public void logout() {
        currentUser = null;
    }

    public TaiKhoan getCurrentUser() {
        return currentUser;
    }
}
