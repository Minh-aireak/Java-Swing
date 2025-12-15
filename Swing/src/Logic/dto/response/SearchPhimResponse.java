package Logic.dto.response;

public class SearchPhimResponse {
    private String idPhim;
    private String tenPhim;
    private String thoiLuong;

    public SearchPhimResponse() {
    }

    public SearchPhimResponse(String idPhim, String tenPhim, String thoiLuong) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }
    
}
