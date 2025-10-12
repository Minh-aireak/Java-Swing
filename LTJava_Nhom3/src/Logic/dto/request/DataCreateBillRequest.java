package Logic.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class DataCreateBillRequest {
String idTaiKhoan;
    String idLichChieu;
    String idGia;
    LocalDateTime dateTime;
    List<String> listGheForPay;
    String tongTien;

    public DataCreateBillRequest(String idTaiKhoan, String idLichChieu, String idGia, LocalDateTime dateTime, List<String> listGheForPay, String tongTien) {
        this.idTaiKhoan = idTaiKhoan;
        this.idLichChieu = idLichChieu;
        this.idGia = idGia;
        this.dateTime = dateTime;
        this.listGheForPay = listGheForPay;
        this.tongTien = tongTien;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getIdLichChieu() {
        return idLichChieu;
    }

    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public String getIdGia() {
        return idGia;
    }

    public void setIdGia(String idGia) {
        this.idGia = idGia;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getListGheForPay() {
        return listGheForPay;
    }

    public void setListGheForPay(List<String> listGheForPay) {
        this.listGheForPay = listGheForPay;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
