package Logic.dto.response;

import java.util.List;

public class ChiTietLichChieuResponse {
    String tenPhong;
    List<String> idGhe;
    String idLichChieu;
    String idGia;
    List<Integer> listGias;

    public ChiTietLichChieuResponse(String tenPhong, List<String> idGhe, String idLichChieu, String idGia, List<Integer> listGias) {
        this.tenPhong = tenPhong;
        this.idGhe = idGhe;
        this.idLichChieu = idLichChieu;
        this.idGia = idGia;
        this.listGias = listGias;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public List<String> getIdGhe() {
        return idGhe;
    }

    public void setIdGhe(List<String> idGhe) {
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

    public List<Integer> getListGias() {
        return listGias;
    }

    public void setListGias(List<Integer> listGias) {
        this.listGias = listGias;
    }
    
}
