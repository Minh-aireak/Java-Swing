package Logic.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class DataCreateVeRequest {
    String idBill;
    String idTaiKhoan;
    LocalDateTime thoiGianDat;
    int tongTien;
    List<String> listIdVe;
    List<String> listIdGhe;
    List<String> listIdGia;
    String idLichChieu;
    String trangThai;

    public DataCreateVeRequest(String idBill, String idTaiKhoan, LocalDateTime thoiGianDat, int tongTien, List<String> listIdVe, List<String> listIdGhe, List<String> listIdGia, String idLichChieu, String trangThai) {
        this.idBill = idBill;
        this.idTaiKhoan = idTaiKhoan;
        this.thoiGianDat = thoiGianDat;
        this.tongTien = tongTien;
        this.listIdVe = listIdVe;
        this.listIdGhe = listIdGhe;
        this.listIdGia = listIdGia;
        this.idLichChieu = idLichChieu;
        this.trangThai = trangThai;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public int getTongTien() {
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

    public void setTongTien(int tongTien) {
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

    public List<String> getListIdVe() {
        return listIdVe;
    }

    public List<String> getListIdGhe() {
        return listIdGhe;
    }

    public List<String> getListIdGia() {
        return listIdGia;
    }

    public void setListIdVe(List<String> listIdVe) {
        this.listIdVe = listIdVe;
    }

    public void setListIdGhe(List<String> listIdGhe) {
        this.listIdGhe = listIdGhe;
    }

    public void setListIdGia(List<String> listIdGia) {
        this.listIdGia = listIdGia;
    }
    
}
