package Logic.dto.response;

public class BillResponse {
    String idBill;
    String tenPhim;
    int tongTien;
    String xuatChieu;
    int soGheDaDat;
    String phongChieu;
    String thoiGianDat;

    public BillResponse() {
    }

    public BillResponse(String idBill, String tenPhim, int tongTien, String xuatChieu, int soGheDaDat, String phongChieu, String thoiGianDat) {
        this.idBill = idBill;
        this.tenPhim = tenPhim;
        this.tongTien = tongTien;
        this.xuatChieu = xuatChieu;
        this.soGheDaDat = soGheDaDat;
        this.phongChieu = phongChieu;
        this.thoiGianDat = thoiGianDat;
    }

    public String getIdBill() {
        return idBill;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getXuatChieu() {
        return xuatChieu;
    }

    public int getSoGheDaDat() {
        return soGheDaDat;
    }

    public String getPhongChieu() {
        return phongChieu;
    }

    public String getThoiGianDat() {
        return thoiGianDat;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setXuatChieu(String xuatChieu) {
        this.xuatChieu = xuatChieu;
    }

    public void setSoGheDaDat(int soGheDaDat) {
        this.soGheDaDat = soGheDaDat;
    }

    public void setPhongChieu(String phongChieu) {
        this.phongChieu = phongChieu;
    }

    public void setThoiGianDat(String thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }
    
}
