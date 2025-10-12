package Logic.entity;

import java.sql.Timestamp;

public class LichChieu {
    private String idLichChieu;
    private String idPhim;
    private Timestamp gioChieu;
    private String idPhongChieu;
    private int soGheConLai;
    private String idGia;

    public LichChieu(String idLichChieu, String idPhim, Timestamp gioChieu, String idPhongChieu, int soGheConLai, String idGia) {
        this.idLichChieu = idLichChieu;
        this.idPhim = idPhim;
        this.gioChieu = gioChieu;
        this.idPhongChieu = idPhongChieu;
        this.soGheConLai = soGheConLai;
        this.idGia = idGia;
    }

    // Getters và Setters
    public String getIdLichChieu() {
        return idLichChieu;
    }

    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public Timestamp getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(Timestamp gioChieu) {
        this.gioChieu = gioChieu;
    }

    public String getIdPhongChieu() {
        return idPhongChieu;
    }

    public void setIdPhongChieu(String idPhongChieu) {
        this.idPhongChieu = idPhongChieu;
    }

    public int getSoGheConLai() {
        return soGheConLai;
    }

    public void setSoGheConLai(int soGheConLai) {
        this.soGheConLai = soGheConLai;
    }
    
    public String getIdGia(){
        return idGia;
    }
    
    public void setIdGia(String idGia){
        this.idGia = idGia;
    }
}