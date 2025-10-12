package Logic.dto.response;

public class UpdateLichChieuResponse {
    private String idLichChieu;
    private String idPhim;
    private String gioChieu;
    private String idPhongChieu;
    private int soGheConLai;
    private String idGia;

    public UpdateLichChieuResponse(String idLichChieu, String idPhim, String gioChieu, String idPhongChieu, int soGheConLai, String idGia) {
        this.idLichChieu = idLichChieu;
        this.idPhim = idPhim;
        this.gioChieu = gioChieu;
        this.idPhongChieu = idPhongChieu;
        this.soGheConLai = soGheConLai;
        this.idGia = idGia;
    }

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

    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
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

    public String getIdGia() {
        return idGia;
    }

    public void setIdGia(String idGia) {
        this.idGia = idGia;
    }
    
}
