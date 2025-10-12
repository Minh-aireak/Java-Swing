package Logic.service;

import Logic.dto.response.LichChieuResponse;
import Logic.entity.LichChieu;
import Logic.repository.PhimRepository;
import java.time.format.DateTimeFormatter;

public class PhimService {
    PhimRepository phimRepository;
    
    public LichChieuResponse updateLichChieu(LichChieu lc) throws Exception {
        var check = phimRepository.kiemTraTonTai("lich_chieu", "idLichChieu", lc.getIdLichChieu());
        LichChieuResponse chieuResponse = null;
        if (!check) {
            chieuResponse.setMessage("Lich chieu khong ton tai!");
        }
        
        if (phimRepository.updateLichChieu(lc)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
            chieuResponse.setIdLichChieu(lc.getIdLichChieu());
            chieuResponse.setIdPhim(lc.getIdPhim());
            chieuResponse.setGioChieu(lc.getGioChieu().format(formatter));
            chieuResponse.setIdPhongChieu(lc.getIdPhongChieu());
            chieuResponse.setSoGheConLai(lc.getSoGheConLai());
            chieuResponse.setIdGia(lc.getIdGia());
            chieuResponse.setMessage("Cập nhật lịch chiếu thành công!");
        } else {
            throw new Exception("Cập nhật lịch chiếu thất bại!");
        }
        return chieuResponse;
    }
}
