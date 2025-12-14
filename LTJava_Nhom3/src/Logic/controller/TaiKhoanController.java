package Logic.controller;

import Logic.dto.response.LoginResponse;
import Logic.entity.TaiKhoan;
import Logic.service.TaiKhoanService;
import java.util.List;

public class TaiKhoanController {

    private TaiKhoanService service;

    public TaiKhoanController(TaiKhoanService service) {
        this.service = service;
    }
    
    // login
    public LoginResponse dangNhap(String soDienThoai, String matKhau) {
        var rs = service.dangNhap(soDienThoai, matKhau);
        return new LoginResponse(rs.isSuccess(), rs.getMessage(), rs.getData());
    }

    // logout
    public void logout() {
        service.logout();
    }

    // change pass
    public ChangePasswordResponse doiMatKhau(String idTaiKhoan, String oldPass, String newPass) {
        var rs = service.doiMatKhau(idTaiKhoan, oldPass, newPass);
        return new ChangePasswordResponse(rs.isSuccess(), rs.getMessage());
    }

    // update if
    public LoginResponse capNhatThongTin(String idTaiKhoan,
                                         String hoDem,
                                         String ten,
                                         String ngaySinh,
                                         String diaChi,
                                         String gioiTinh) {
        var rs = service.capNhatThongTin(idTaiKhoan, hoDem, ten, ngaySinh, diaChi, gioiTinh);
        return new LoginResponse(rs.isSuccess(), rs.getMessage(), rs.getData());
    }

    // dki
    public RegisterResponse dangKy(String soDienThoai,
                                   String email,
                                   String matKhau,
                                   String hoDem,
                                   String ten,
                                   String gioiTinh,
                                   String ngaySinh,
                                   String diaChi) {

        var rs = service.dangKy(soDienThoai, email, matKhau, hoDem, ten, gioiTinh, ngaySinh, diaChi);
        return new RegisterResponse(rs.isSuccess(), rs.getMessage(), rs.getData());
    }
    
    public List<TaiKhoan> ListlayDanhSachNguoiXem() {
        return service.listTaiKhoan();
    }

    // Đổi mật khẩu
    public static class ChangePasswordResponse {
        private boolean success;
        private String message;

        public ChangePasswordResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public void setSuccess(boolean success) { this.success = success; }
        public void setMessage(String message) { this.message = message; }
    }

    // Đăng ký
    public static class RegisterResponse {
        private boolean success;
        private String message;
        private TaiKhoan taiKhoan;

        public RegisterResponse(boolean success, String message, TaiKhoan taiKhoan) {
            this.success = success;
            this.message = message;
            this.taiKhoan = taiKhoan;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public TaiKhoan getTaiKhoan() { return taiKhoan; }

        public void setSuccess(boolean success) { this.success = success; }
        public void setMessage(String message) { this.message = message; }
        public void setTaiKhoan(TaiKhoan taiKhoan) { this.taiKhoan = taiKhoan; }
    }
    
}
