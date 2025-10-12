package Logic.dto.request;

import Logic.entity.Phim;
import java.sql.Timestamp;

public class DataGetLichChieuRequest {
    Phim chossePhim;
    Timestamp timestamp;

    public DataGetLichChieuRequest(Phim chossePhim, Timestamp timestamp) {
        this.chossePhim = chossePhim;
        this.timestamp = timestamp;
    }

    public Phim getChossePhim() {
        return chossePhim;
    }

    public void setChossePhim(Phim chossePhim) {
        this.chossePhim = chossePhim;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
}
