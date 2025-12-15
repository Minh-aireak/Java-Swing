package Logic.controller;

import Logic.dto.response.LichChieuResponse;
import Logic.dto.response.SearchPhimResponse;
import Logic.entity.Gia;
import Logic.entity.LichChieu;
import Logic.service.PhimService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Logic.entity.Phim;
import Logic.repository.PhimRepository;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhimController {
    private PhimService phimService;
    private PhimRepository repo;

    public PhimController(PhimService phimService) {
        this.phimService = phimService;
        if (this.repo == null) this.repo = new PhimRepository();
    }

    public PhimController(PhimRepository repo) {
        this.repo = repo;
    }
    
    public List<LichChieu> getListLichChieu() {
        return phimService.getListLichChieu();
    }
    
    public List<Gia> layDanhSachGia() {
        return phimService.layDanhSachGia();
    }
    
    public boolean xoaGia(String idGia) {
        return phimService.xoaGia(idGia);
    }
    
    public boolean themGia(Gia gia) {
        return phimService.themGia(gia);
    }
    
    public boolean capNhatGia(Gia gia) {
        return phimService.capNhatGia(gia);
    }
    
    public boolean themPhim(Phim phim) throws FileNotFoundException, Exception {
        if (phim == null) throw new IllegalArgumentException("Phim null");
        if (phim.getIdPhim() == null || phim.getIdPhim().trim().isEmpty())
            throw new IllegalArgumentException("idPhim required");
        return repo.themPhim(phim);
    }

    public boolean capNhatPhim(Phim phim) throws Exception {
        if (phim == null || phim.getIdPhim() == null || phim.getIdPhim().trim().isEmpty())
            throw new IllegalArgumentException("Invalid phim");
        return repo.capNhatPhim(phim);
    }

    public boolean xoaPhim(String idPhim) throws Exception {
        if (idPhim == null || idPhim.trim().isEmpty()) throw new IllegalArgumentException("idPhim required");
        return repo.xoaPhim(idPhim);
    }

    public ArrayList<Phim> layDanhSachPhim() throws Exception {
        return repo.layDanhSachPhim();
    }
    
    public LichChieuResponse updateLichChieu(LichChieu lc) {
    LichChieuResponse res = new LichChieuResponse();

        if (lc.getGioChieu() == null) {
            res.setMessage("Lỗi: Ngày giờ chiếu không hợp lệ hoặc để trống!");
            return res;
        }

        try {
            boolean result = repo.updateLichChieu(lc); 

            if (result) {
                res.setMessage("Cập nhật thành công!");
            } else {
                res.setMessage("Cập nhật thất bại! (Kiểm tra lại Mã Lịch Chiếu hoặc dữ liệu nhập)");
            }
        } catch (Exception e) {
            res.setMessage("Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    
    return res;
}
    
    public LichChieuResponse addLichChieu(String idLichChieu, String idPhim,
            String gioChieu, String idPhongChieu
            , String soGheConLai, String idGia) throws Exception {
        
        LichChieuResponse response = null;
        LichChieu lc = new LichChieu(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (idLichChieu.isEmpty() || idPhim.isEmpty() || gioChieu.isEmpty() || idPhongChieu == null || soGheConLai.isEmpty()) {
            throw new Exception("Vui lòng nhập đầy đủ thông tin!");
        }

        try {
            lc.setIdLichChieu(idLichChieu);
            lc.setIdPhim(idPhim);
            lc.setGioChieu(LocalDateTime.parse(gioChieu, formatter));
            lc.setIdPhongChieu(idPhongChieu);
            lc.setSoGheConLai(Integer.parseInt(soGheConLai));
            lc.setIdGia(idGia);

            response = phimService.addLichChieu(lc);
        } catch (NumberFormatException e) {
            throw new Exception("Số ghế còn lại phải là số!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return response;
    }

    public List<LichChieuResponse> searchLichChieu(String idLichChieu, String idPhim, String idPhongChieu) throws Exception {
        return phimService.searchLichChieu(idLichChieu, idPhim, idPhongChieu);
    }

    public List<String> getPhongChieu() {
        try {
            return phimService.getListPhongChieu();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public String deleteLichChieu(String idLichChieu) throws Exception {
        if (idLichChieu == null || idLichChieu.trim().isEmpty()) {
            throw new Exception("Vui lòng chọn lịch chiếu để xóa!");
        }
        return phimService.deleteLichChieu(idLichChieu);
    }
    
    public Phim getChiTietPhim(String idPhim) throws Exception {
        return phimService.getChiTietPhim(idPhim);
    }
    
    public List<SearchPhimResponse> search(String tenPhim, String tacGia, List<String> list_chosen) throws SQLException, Exception {
        return phimService.search(tenPhim, tacGia, list_chosen);
    }
}