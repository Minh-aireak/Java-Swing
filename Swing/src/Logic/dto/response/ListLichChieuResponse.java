package Logic.dto.response;

import java.sql.Timestamp;
import java.util.List;

public class ListLichChieuResponse {
    List<Timestamp> listGioChieu;
    String message;

    public ListLichChieuResponse(List<Timestamp> listGioChieu, String message) {
        this.listGioChieu = listGioChieu;
        this.message = message;
    }

    public List<Timestamp> getListGioChieu() {
        return listGioChieu;
    }

    public void setListGioChieu(List<Timestamp> listGioChieu) {
        this.listGioChieu = listGioChieu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
