package Logic.controller;

import Logic.entity.TaiKhoan;
import Logic.service.TaiKhoanService;

public class LoginController {

    private final TaiKhoanService service;

    public LoginController() {
        this.service = new TaiKhoanService();
    }

    // ĐĂNG NHẬP
    public LoginResponse dangNhap(String soDienThoai, String matKhau) throws Exception {
        var rs = service.dangNhap(soDienThoai, matKhau);
        LoginResponse resp = new LoginResponse();
        resp.setMessage(rs.getMessage());
        if (rs.isSuccess()) {
            resp.setTaiKhoan(rs.getData());
        }
        return resp;
    }

    // ĐĂNG KÝ 
    public RegisterResponse dangKy(String soDienThoai,
                                   String email,
                                   String matKhau,
                                   String hoDem,
                                   String ten,
                                   String gioiTinh,
                                   String ngaySinh, // có thể null
                                   String diaChi    // có thể null
    ) throws Exception {
        var rs = service.dangKy(soDienThoai, email, matKhau, hoDem, ten, gioiTinh, ngaySinh, diaChi);
        RegisterResponse resp = new RegisterResponse();
        resp.setMessage(rs.getMessage());
        resp.setTaiKhoan(rs.getData()); // có thể null nếu fail
        return resp;
    }

    // ==== ĐỔI MẬT KHẨU ====
    public ChangePasswordResponse doiMatKhau(String idTaiKhoan, String oldPass, String newPass) throws Exception {
        var rs = service.doiMatKhau(idTaiKhoan, oldPass, newPass);
        ChangePasswordResponse resp = new ChangePasswordResponse();
        resp.setMessage(rs.getMessage());
        resp.setSuccess(rs.isSuccess());
        return resp;
    }

    // ======== Các DTO phản hồi gọn nhẹ (nằm ngay trong Controller để bạn không phải tạo thêm file) ========

    public static class LoginResponse {
        private String message;
        private TaiKhoan taiKhoan;

        public String getMessage() { return message; }
        public TaiKhoan getTaiKhoan() { return taiKhoan; }
        public void setMessage(String message) { this.message = message; }
        public void setTaiKhoan(TaiKhoan taiKhoan) { this.taiKhoan = taiKhoan; }
    }

    public static class RegisterResponse {
        private String message;
        private TaiKhoan taiKhoan; // trả về tài khoản mới tạo (có id)

        public String getMessage() { return message; }
        public TaiKhoan getTaiKhoan() { return taiKhoan; }
        public void setMessage(String message) { this.message = message; }
        public void setTaiKhoan(TaiKhoan taiKhoan) { this.taiKhoan = taiKhoan; }
    }

    public static class ChangePasswordResponse {
        private String message;
        private boolean success;

        public String getMessage() { return message; }
        public boolean isSuccess() { return success; }
        public void setMessage(String message) { this.message = message; }
        public void setSuccess(boolean success) { this.success = success; }
    }
}