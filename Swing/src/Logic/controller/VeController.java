package Logic.controller;

import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.ChiTietLichChieuResponse;
import Logic.dto.response.ListLichChieuResponse;
import Logic.entity.Phim;
import Logic.entity.Ve;
import Logic.service.VeService;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class VeController {
    private VeService veService;
    
    public VeController(VeService veService) {
        this.veService = veService;
    }
    
    public String holdSeats(String idTaiKhoan, String idLichChieu, List<String> listIdGhe, String idGia, String tongTien) throws SQLException {
        return veService.holdSeats(idTaiKhoan, idLichChieu, listIdGhe, idGia, tongTien);
    }
    
    public List<BillResponse> getListBill() throws SQLException{
        return veService.getListBill();
    }
    
    public ChiTietBillResponse getChiTietBill(String idBill) throws SQLException{
        return veService.getChiTietBill(idBill);
    }
    
    public ChiTietLichChieuResponse getChiTietLichChieu(Phim chosenPhim, Timestamp gioChieu) throws SQLException{
        return veService.getChiTietLichChieu(chosenPhim, gioChieu);
    }
    
    public List<String> getListGhe(Timestamp gioChieu) throws SQLException{
        return veService.getListGhe(gioChieu);
    }
    
    public ListLichChieuResponse getListLichChieu(String idPhim) throws SQLException {
        return veService.getListLichChieu(idPhim);
    }
   
    public List<Ve> getListVe() {
        return veService.getListVe();
    }
}
