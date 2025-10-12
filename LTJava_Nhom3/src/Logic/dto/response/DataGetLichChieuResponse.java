package Logic.dto.response;

import java.util.List;

public class DataGetLichChieuResponse {
    String tenPhong;
    String idGhe;
    String idLichChieu;
    String idGia;
    int tieuChuan;
    int VIP;
    int Triple;

    public DataGetLichChieuResponse(String tenPhong, String idGhe, String idLichChieu, String idGia, int tieuChuan, int VIP, int Triple) {
        this.tenPhong = tenPhong;
        this.idGhe = idGhe;
        this.idLichChieu = idLichChieu;
        this.idGia = idGia;
        this.tieuChuan = tieuChuan;
        this.VIP = VIP;
        this.Triple = Triple;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(String idGhe) {
        this.idGhe = idGhe;
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

    public int getTieuChuan() {
        return tieuChuan;
    }

    public void setTieuChuan(int tieuChuan) {
        this.tieuChuan = tieuChuan;
    }

    public int getVIP() {
        return VIP;
    }

    public void setVIP(int VIP) {
        this.VIP = VIP;
    }

    public int getTriple() {
        return Triple;
    }

    public void setTriple(int Triple) {
        this.Triple = Triple;
    }
    
}
