package Logic.entity;

public class Ve {
    private String idVe;
    private String tenKhachHang;
    private String tenPhim;
    private String gioChieu; 
    private String ghe;
    private String tenPhong;
    private int giaVe;

    public Ve(String idVe, String tenKhachHang, String tenPhim, String gioChieu, String ghe, String tenPhong, int giaVe) {
        this.idVe = idVe;
        this.tenKhachHang = tenKhachHang;
        this.tenPhim = tenPhim;
        this.gioChieu = gioChieu;
        this.ghe = ghe;
        this.tenPhong = tenPhong;
        this.giaVe = giaVe;
    }

    // Getters và Setters
    public String getIdVe() { return idVe; }
    public void setIdVe(String idVe) { this.idVe = idVe; }
    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
    public String getTenPhim() { return tenPhim; }
    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public String getGioChieu() { return gioChieu; }
    public void setGioChieu(String gioChieu) { this.gioChieu = gioChieu; }
    public String getGhe() { return ghe; }
    public void setGhe(String ghe) { this.ghe = ghe; }
    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }
    public int getGiaVe() { return giaVe; }
    public void setGiaVe(int giaVe) { this.giaVe = giaVe; }
}