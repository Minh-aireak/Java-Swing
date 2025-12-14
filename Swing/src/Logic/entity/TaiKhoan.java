package Logic.entity;

public class TaiKhoan {
    private String idTaiKhoan;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private String hoDem;
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String gioiTinh;

    public TaiKhoan(String idTaiKhoan, String soDienThoai, String email, String matKhau, String hoDem, String ten, String ngaySinh, String diaChi, String gioiTinh) {
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

    public TaiKhoan() {
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getHoDem() {
        return hoDem;
    }

    public String getTen() {
        return ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
}
