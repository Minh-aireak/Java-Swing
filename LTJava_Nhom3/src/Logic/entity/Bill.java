package Logic.entity;

import java.time.LocalDateTime;

public class Bill {
    private String idBill;
    private String idTaiKhoan;
    private LocalDateTime thoiGianDat;
    private int tongTien;

    public Bill(String idBill, String idTaiKhoan, LocalDateTime thoiGianDat, int tongTien) {
        this.idBill = idBill;
        this.idTaiKhoan = idTaiKhoan;
        this.thoiGianDat = thoiGianDat;
        this.tongTien = tongTien;
    }

    public String getIdBill() {
        return idBill;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
    
}
