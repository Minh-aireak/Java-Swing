package Logic.service;

import Logic.dto.response.LoginResponse;
import Logic.entity.TaiKhoan;
import Logic.repository.TaiKhoanRepository;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TaiKhoanService {
    private TaiKhoanRepository taiKhoanRepo;
    private static final Logger LOGGER = Logger.getLogger(TaiKhoanService.class.getName());
    
    public TaiKhoanService() {
        this.taiKhoanRepo = new TaiKhoanRepository();
    }
    
    public LoginResponse dangNhap(String soDienThoai, String matKhau) {
        LoginResponse response = null;
        
        try {
            TaiKhoan taiKhoan = taiKhoanRepo.dangNhap(soDienThoai, matKhau);
            if (taiKhoan != null) {
                response.setTaiKhoan(taiKhoan);
                response.setMessage("<html>Đăng nhập thành công<br>Xin chào, " + taiKhoan.getTen());
            } else {
                response.setMessage("Tài khoản, mật khẩu không chính xác!");
            }
            
        } catch (SQLException ex) {
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setMessage("Loi khong xac dinh khi dang nhap!");
        }
        
        return response;
    }
    
    public boolean dangKyTaiKhoan(String soDienThoai, String email, String matKhau, String xacNhanMatKhau, 
                                 String hoDem, String ten, String gioiTinh) {
        try {
            // Validation
            if (soDienThoai.isEmpty() || email.isEmpty() || matKhau.isEmpty() || 
                xacNhanMatKhau.isEmpty() || hoDem.isEmpty() || ten.isEmpty() || gioiTinh.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (!soDienThoai.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại phải là số và đủ 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!email.matches("^[\\w.-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
                JOptionPane.showMessageDialog(null, "Email không đúng định dạng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (matKhau.length() < 8 || !matKhau.matches(".*[^a-zA-Z0-9].*")) {
                JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 8 ký tự và 1 ký tự đặc biệt.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (!matKhau.equals(xacNhanMatKhau)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Kiểm tra trùng
            if (taiKhoanRepo.kiemTraTrungSoDienThoaiHoacEmail(soDienThoai, email)) {
                JOptionPane.showMessageDialog(null, "Số điện thoại hoặc email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Tạo tài khoản mới
            String idMoi = taiKhoanRepo.taoMaTaiKhoanMoi();
            TaiKhoan.setIdTaiKhoan(idMoi);
            TaiKhoan taiKhoanMoi = new TaiKhoan(idMoi, soDienThoai, email, matKhau, hoDem, ten, null, null, gioiTinh);
            
            boolean result = taiKhoanRepo.dangKyTaiKhoan(taiKhoanMoi);
            if (result) {
                JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Đăng ký thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return result;
            
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi database khi đăng ký", ex);
            JOptionPane.showMessageDialog(null, "Lỗi database: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi đăng ký", ex);
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String xacNhanMatKhau) {
        try {
            // Validation
            if (matKhauCu.isEmpty() || matKhauMoi.isEmpty() || xacNhanMatKhau.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Kiểm tra mật khẩu cũ
            if (!taiKhoanRepo.kiemTraMatKhauCu(TaiKhoan.getIdTaiKhoan(), matKhauCu)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (matKhauMoi.equals(matKhauCu)) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới phải khác mật khẩu cũ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (matKhauMoi.length() < 8) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới phải có ít nhất 8 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!matKhauMoi.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*")) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới phải chứa ít nhất 1 ký tự đặc biệt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!matKhauMoi.equals(xacNhanMatKhau)) {
                JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Đổi mật khẩu
            boolean result = taiKhoanRepo.doiMatKhau(TaiKhoan.getIdTaiKhoan(), matKhauMoi);
            if (result) {
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return result;
            
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi database khi đổi mật khẩu", ex);
            JOptionPane.showMessageDialog(null, "Lỗi database: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi đổi mật khẩu", ex);
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean capNhatThongTin(String ten, String hoDem, String ngaySinhStr, String diaChi, String gioiTinh) {
        try {
            TaiKhoan taiKhoanHienTai = taiKhoanRepo.getThongTinTaiKhoan(TaiKhoan.getIdTaiKhoan());
            if (taiKhoanHienTai == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Sử dụng giá trị hiện tại nếu người dùng không nhập
            if (ten.isEmpty()) ten = taiKhoanHienTai.getTen();
            if (hoDem.isEmpty()) hoDem = taiKhoanHienTai.getHoDem();
            if (diaChi.isEmpty()) diaChi = taiKhoanHienTai.getDiaChi();
            if (gioiTinh.isEmpty()) gioiTinh = taiKhoanHienTai.getGioiTinh();
            
            Date ngaySinh = taiKhoanHienTai.getNgaySinh();
            
            // Xử lý ngày sinh nếu có nhập
            if (!ngaySinhStr.isEmpty()) {
                if (!ngaySinhStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    JOptionPane.showMessageDialog(null, "Định dạng ngày sinh phải là dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                
                try {
                    Date parsedDate = sdf.parse(ngaySinhStr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(parsedDate);
                    int year = cal.get(Calendar.YEAR);

                    if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    ngaySinh = parsedDate;
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
            // Cập nhật thông tin
            taiKhoanHienTai.setTen(ten);
            taiKhoanHienTai.setHoDem(hoDem);
            taiKhoanHienTai.setNgaySinh(ngaySinh);
            taiKhoanHienTai.setDiaChi(diaChi);
            taiKhoanHienTai.setGioiTinh(gioiTinh);
            
            boolean result = taiKhoanRepo.capNhatThongTin(taiKhoanHienTai);
            if (result) {
                TaiKhoan.setTenHienThi(ten);
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return result;
            
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi database khi cập nhật thông tin", ex);
            JOptionPane.showMessageDialog(null, "Lỗi database: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Lỗi không xác định khi cập nhật thông tin", ex);
            JOptionPane.showMessageDialog(null, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}