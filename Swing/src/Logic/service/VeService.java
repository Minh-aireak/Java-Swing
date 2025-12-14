package Logic.service;

import Global.Session;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.ChiTietLichChieuResponse;
import Logic.dto.response.ListLichChieuResponse;
import Logic.entity.Phim;
import Logic.entity.Ve;
import Logic.repository.VeRepository;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeService {
    VeRepository veRepository;
    Session session = new Session();
    String idTaiKhoan = session.getCurrentUser().getIdTaiKhoan();

    public VeService(VeRepository veRepository) {
        this.veRepository = veRepository;
    }
    
    public String holdSeats(String idTaiKhoan, String idLichChieu, List<String> listIdGhe, String idGia, String tongTien) throws SQLException {
        return veRepository.holdSeats(idTaiKhoan, idLichChieu, listIdGhe, idGia, tongTien);
    }
    
    public boolean deleteVe(String idVe) {
        try {
            veRepository.deleteVe(idVe);
            return true;
        } catch (SQLException ex) {
            System.out.println("Lỗi hủy vé: " + ex.getMessage());
        }
        return false;
    }
    
    public List<BillResponse> getListBill() throws SQLException{
        try {
            return veRepository.getListBill(idTaiKhoan);
        } catch (SQLException ex) {
            throw new SQLException("getListBill error!");
        }
    }
    
    public ChiTietBillResponse getChiTietBill(String idBill) throws SQLException{
        try {
            var reponse = veRepository.getChiTietBill(idBill, idTaiKhoan);
            return reponse;
        } catch (SQLException ex) {
            throw new SQLException("getChiTietBill error!");
        }
    }
    
    public ChiTietLichChieuResponse getChiTietLichChieu(Phim chosenPhim, Timestamp gioChieu) throws SQLException {
        ChiTietLichChieuResponse response = new ChiTietLichChieuResponse(null, null, null, null, null);
        try {
            response = veRepository.getChiTietLichChieu(chosenPhim, gioChieu);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }
        
        return response;
    }
    
    public List<String> getListGhe(Timestamp gioChieu) throws SQLException{
        List<String> listGhe = new ArrayList<>();
        try {
            listGhe = veRepository.getListGhe(gioChieu);
        } catch (SQLException ex) {
            throw new SQLException("getListGhe error!");
        }
        
        return listGhe;
    }
    
    public ListLichChieuResponse getListLichChieu(String idPhim) throws SQLException {
        ListLichChieuResponse response = new ListLichChieuResponse(null, null);
        try {
            List<Timestamp> listGioChieu = veRepository.getListLichChieu(idPhim);
            System.out.println(listGioChieu);
            if(listGioChieu.isEmpty()){
                response.setMessage("Phim hiện tại chưa cập nhật lịch chiếu");
            }
            response.setListGioChieu(listGioChieu);
        } catch (SQLException ex) {
            throw new SQLException("getListLichChieu error!");
        }
        
        return response;
    }
    
    public List<Ve> getListVe() {
        return veRepository.getListVe();
    }
}
