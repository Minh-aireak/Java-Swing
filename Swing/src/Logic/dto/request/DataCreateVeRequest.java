package Logic.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class DataCreateVeRequest {
    String idBill;
    String idTaiKhoan;
    LocalDateTime thoiGianDat;
    String tongTien;
    List<String> listIdGhe;
    String idGia;
    String idLichChieu;
    String trangThai;

    public DataCreateVeRequest(String idBill, String idTaiKhoan, LocalDateTime thoiGianDat, String tongTien, List<String> listIdGhe, String idGia, String idLichChieu, String trangThai) {
        this.idBill = idBill;
        this.idTaiKhoan = idTaiKhoan;
        this.thoiGianDat = thoiGianDat;
        this.tongTien = tongTien;
        this.listIdGhe = listIdGhe;
        this.idGia = idGia;
        this.idLichChieu = idLichChieu;
        this.trangThai = trangThai;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public String getTongTien() {
        return tongTien;
    }

    public String getIdLichChieu() {
        return idLichChieu;
    }

    public String getTrangThai() {
        return trangThai;
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

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
    
    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdBill() {
        return idBill;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public List<String> getListIdGhe() {
        return listIdGhe;
    }

    public String getIdGia() {
        return idGia;
    }

    public void setListIdGhe(List<String> listIdGhe) {
        this.listIdGhe = listIdGhe;
    }

    public void setIdGia(String idGia) {
        this.idGia = idGia;
    }
    
}
