package Logic.service;

import Logic.entity.TaiKhoan;
import Logic.repository.TaiKhoanRepository;

import java.util.Objects;
import java.util.Optional;

public class TaiKhoanService {

    private final TaiKhoanService repo;

    public TaiKhoanService() {
        this.repo = new TaiKhoanService();
    }

    public TaiKhoanService(TaiKhoanService repo) {
        this.repo = Objects.requireNonNull(repo);
    }

    // Đăng nhập
    public Result<TaiKhoan> dangNhap(String soDienThoai, String matKhau) throws Exception {
        if (soDienThoai == null || soDienThoai.isEmpty() ||
            matKhau == null || matKhau.isEmpty()) {
            return Result.fail("Vui lòng nhập đầy đủ thông tin đăng nhập.");
        }
        Optional<TaiKhoan> tk = repo.findByPhoneAndPassword(soDienThoai, matKhau);
        if (tk.isPresent()) {
            return Result.ok("Đăng nhập thành công!", tk.get());
        }
        return Result.fail("Số điện thoại hoặc mật khẩu không đúng.");
    }

    // Đăng ký (giữ nguyên các thuộc tính dạng String của TaiKhoan)
    public Result<TaiKhoan> dangKy(String soDienThoai,
                                   String email,
                                   String matKhau,
                                   String hoDem,
                                   String ten,
                                   String gioiTinh,
                                   String ngaySinh,  // có thể null
                                   String diaChi     // có thể null
    ) throws Exception {
        if (soDienThoai == null || soDienThoai.isEmpty() ||
            email == null || email.isEmpty() ||
            matKhau == null || matKhau.isEmpty() ||
            hoDem == null || hoDem.isEmpty() ||
            ten == null || ten.isEmpty() ||
            gioiTinh == null || gioiTinh.isEmpty()) {
            return Result.fail("Vui lòng nhập đầy đủ thông tin bắt buộc.");
        }

        if (repo.existsByPhoneOrEmail(soDienThoai, email)) {
            return Result.fail("Số điện thoại hoặc email đã tồn tại.");
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setSoDienThoai(soDienThoai);
        tk.setEmail(email);
        tk.setMatKhau(matKhau);
        tk.setHoDem(hoDem);
        tk.setTen(ten);
        tk.setGioiTinh(gioiTinh);
        tk.setNgaySinh(ngaySinh); // có thể null
        tk.setDiaChi(diaChi);     // có thể null

        TaiKhoan saved = repo.insertTaiKhoan(tk);
        return Result.ok("Đăng ký thành công!", saved);
    }

    // Đổi mật khẩu
    public Result<Void> doiMatKhau(String idTaiKhoan, String oldPass, String newPass) throws Exception {
        if (idTaiKhoan == null || idTaiKhoan.isEmpty() ||
            oldPass == null || oldPass.isEmpty() ||
            newPass == null || newPass.isEmpty()) {
            return Result.fail("Thiếu thông tin đổi mật khẩu.");
        }
        if (oldPass.equals(newPass)) {
            return Result.fail("Mật khẩu mới phải khác mật khẩu cũ.");
        }
        boolean ok = repo.updatePassword(idTaiKhoan, oldPass, newPass);
        if (ok) {
            return Result.ok("Đổi mật khẩu thành công!", null);
        }
        return Result.fail("Đổi mật khẩu thất bại! Kiểm tra lại mật khẩu cũ.");
    }

    private Optional<TaiKhoan> findByPhoneAndPassword(String soDienThoai, String matKhau) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean existsByPhoneOrEmail(String soDienThoai, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private TaiKhoan insertTaiKhoan(TaiKhoan tk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean updatePassword(String idTaiKhoan, String oldPass, String newPass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Lớp Result gọn nhẹ cho Service trả về message + data (không thêm thuộc tính entity)
    public static class Result<T> {
        private final boolean success;
        private final String message;
        private final T data;

        private Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public static <T> Result<T> ok(String message, T data) {
            return new Result<>(true, message, data);
        }

        public static <T> Result<T> fail(String message) {
            return new Result<>(false, message, null);
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public T getData() { return data; }
    }
}