package Logic.dto.response;

import Logic.entity.TaiKhoan;

public class LoginResponse {
    TaiKhoan taiKhoan;
    String message;

    public LoginResponse(TaiKhoan taiKhoan, String message) {
        this.taiKhoan = taiKhoan;
        this.message = message;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
