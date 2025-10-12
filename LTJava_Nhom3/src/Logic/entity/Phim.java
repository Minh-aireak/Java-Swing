package Logic.entity;

public class Phim {
    private String idPhim, tenPhim, tacGia, dienVien, thoiLuong, theLoai, ngonNgu, moTa, anhPhim;

    public Phim(String idPhim, String tenPhim, String tacGia, String dienVien, String thoiLuong, String theLoai, String ngonNgu, String moTa, String anhPhim) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.tacGia = tacGia;
        this.dienVien = dienVien;
        this.thoiLuong = thoiLuong;
        this.theLoai = theLoai;
        this.ngonNgu = ngonNgu;
        this.moTa = moTa;
        this.anhPhim = anhPhim;
    }

    public Phim() {
    }

    public String getIdPhim() {
        return idPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getDienVien() {
        return dienVien;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public String getAnhPhim() {
        return anhPhim;
    }
}
