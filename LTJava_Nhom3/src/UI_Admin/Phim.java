package UI_Admin;

import java.util.ArrayList;
import java.util.List;

public class Phim {
    private String idPhim;
    private String tenPhim;
    private String tacGia;
    private List<String> theLoai; // Danh sách id thể loại
    private String thoiLuong;
    private String ngonNgu;
    private String dienVien;
    private String moTa;
    private String anhPhim;

    public Phim(String idPhim, String tenPhim, String tacGia, List<String> theLoai, 
                String thoiLuong, String ngonNgu, String dienVien, String moTa, String anhPhim) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.ngonNgu = ngonNgu;
        this.dienVien = dienVien;
        this.moTa = moTa;
        this.anhPhim = anhPhim;
    }
    
    public Phim() {
        theLoai = new ArrayList<>();
    }

    // Getters và Setters
    public String getIdPhim() { return idPhim; }
    public void setIdPhim(String idPhim) { this.idPhim = idPhim; }
    public String getTenPhim() { return tenPhim; }
    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public List<String> getTheLoai() { return theLoai; }
    public void setTheLoai(List<String> theLoai) { this.theLoai = theLoai; }
    public String getThoiLuong() { return thoiLuong; }
    public void setThoiLuong(String thoiLuong) { this.thoiLuong = thoiLuong; }
    public String getNgonNgu() { return ngonNgu; }
    public void setNgonNgu(String ngonNgu) { this.ngonNgu = ngonNgu; }
    public String getDienVien() { return dienVien; }
    public void setDienVien(String dienVien) { this.dienVien = dienVien; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getAnhPhim() { return anhPhim; }
    public void setAnhPhim(String anhPhim) { this.anhPhim = anhPhim; }
}
