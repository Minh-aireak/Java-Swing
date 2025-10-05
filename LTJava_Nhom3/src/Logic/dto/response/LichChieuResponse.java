package Logic.dto.response;

public class LichChieuResponse {
    String tenPhong;
    String listIdGheDaDat;
    String idLichChieu;
    String idGia;
    int tieuChuan;
    int VIP;
    int Triple;

    public LichChieuResponse() {
    }

    public LichChieuResponse(String tenPhong, String listIdGheDaDat, String idLichChieu, String idGia, int tieuChuan, int VIP, int Triple) {
        this.tenPhong = tenPhong;
        this.listIdGheDaDat = listIdGheDaDat;
        this.idLichChieu = idLichChieu;
        this.idGia = idGia;
        this.tieuChuan = tieuChuan;
        this.VIP = VIP;
        this.Triple = Triple;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public String getListIdGheDaDat() {
        return listIdGheDaDat;
    }

    public String getIdLichChieu() {
        return idLichChieu;
    }

    public String getIdGia() {
        return idGia;
    }

    public int getTieuChuan() {
        return tieuChuan;
    }

    public int getVIP() {
        return VIP;
    }

    public int getTriple() {
        return Triple;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public void setListIdGheDaDat(String listIdGheDaDat) {
        this.listIdGheDaDat = listIdGheDaDat;
    }

    public void setIdLichChieu(String idLichChieu) {
        this.idLichChieu = idLichChieu;
    }

    public void setIdGia(String idGia) {
        this.idGia = idGia;
    }

    public void setTieuChuan(int tieuChuan) {
        this.tieuChuan = tieuChuan;
    }

    public void setVIP(int VIP) {
        this.VIP = VIP;
    }

    public void setTriple(int Triple) {
        this.Triple = Triple;
    }
    
}
