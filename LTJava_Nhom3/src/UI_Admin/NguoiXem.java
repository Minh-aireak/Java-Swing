package UI_Admin;

import java.sql.Date;

public class NguoiXem {
    private String idTaiKhoan;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private String hoDem;
    private String ten;
    private Date ngaySinh;
    private String diaChi;
    private String gioiTinh;

    public NguoiXem(String idTaiKhoan, String soDienThoai, String email,String matKhau, String hoDem, String ten,
                    Date ngaySinh, String diaChi, String gioiTinh) {
        this.idTaiKhoan = idTaiKhoan;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.matKhau = matKhau;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public String getIdTaiKhoan() { return idTaiKhoan; }
    public void setIdTaiKhoan(String idTaiKhoan) { this.idTaiKhoan = idTaiKhoan; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatKhau() { return matKhau;}
    public void setMatKhau(String matKhau) {this.matKhau = matKhau;}
    public String getHoDem() { return hoDem; }
    public void setHoDem(String hoDem) { this.hoDem = hoDem; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
}