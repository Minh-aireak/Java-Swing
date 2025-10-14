package Logic.controller;

import Logic.dto.response.LichChieuResponse;
import Logic.entity.LichChieu;
import Logic.service.PhimService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PhimController {
    PhimService phimService;

    public PhimController() {
    }
    public LichChieuResponse updateLichChieu(String idLichChieu, String idPhim,
            String gioChieu, String idPhongChieu
            , String soGheConLai, String idGia) throws Exception{
        LichChieuResponse response = null;
        LichChieu lc = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (idPhim.isEmpty() || gioChieu.isEmpty() || idPhongChieu == null || soGheConLai.isEmpty()) {
            throw new Exception("Vui long nhap day du thong tin!");
        }
        
        try {
            lc.setIdLichChieu(idLichChieu);
            lc.setIdPhim(idPhim);
            lc.setGioChieu(LocalDateTime.parse(gioChieu, formatter));
            lc.setIdPhongChieu(idPhongChieu);
            lc.setSoGheConLai(Integer.parseInt(soGheConLai));
            lc.setIdGia(idGia);
            
            response = phimService.updateLichChieu(lc);
        } catch (NumberFormatException e) {
            throw new Exception("Số ghế còn lại phải là số!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

        return response;
    }
}