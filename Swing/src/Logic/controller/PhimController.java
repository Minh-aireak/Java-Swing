package Logic.controller;

import Logic.dto.response.LichChieuResponse;
import Logic.entity.Gia;
import Logic.entity.LichChieu;
import Logic.service.PhimService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Logic.entity.Phim;
import Logic.repository.PhimRepository;
import java.io.FileNotFoundException;
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

//    if (lc.getIdLichChieu().isEmpty() || lc.getIdPhim().isEmpty() || lc.getIdPhongChieu().isEmpty()) {
//        return new LichChieuResponse("Lỗi: Vui lòng nhập đầy đủ thông tin!");
//    }
//    
//    if (lc.getGioChieu() == null) {
//        return new LichChieuResponse("Lỗi: Ngày giờ chiếu không hợp lệ!");
//    }
//
//    try {
//        // Gọi xuống Service hoặc Repository để xử lý
//        // Lưu ý: Nếu bạn gọi thẳng Repository thì dùng repo.update...
//        // Nếu qua Service thì dùng phimService.update...
//        
//        boolean result = repo.updateLichChieu(lc); // Hoặc phimService.updateLichChieu(lc)
//
//        if (result) {
//            return new LichChieuResponse("Cập nhật thành công!");
//        } else {
//            return new LichChieuResponse("Cập nhật thất bại (Có thể do sai ID hoặc trùng lịch)!");
//        }
//    } catch (Exception e) {
//        return new LichChieuResponse("Lỗi hệ thống: " + e.getMessage());
//    }
return null;
}
    
    public LichChieuResponse addLichChieu(String idLichChieu, String idPhim,
            String gioChieu, String idPhongChieu
            , String soGheConLai, String idGia) throws Exception {
        
        LichChieuResponse response = null;
        LichChieu lc = new LichChieu(); // Phải khởi tạo
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
        // Tìm kiếm có thể null hoặc rỗng, không cần validate chặt
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
}