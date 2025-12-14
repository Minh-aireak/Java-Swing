package Logic.service;

import Logic.dto.response.LichChieuResponse;
import Logic.entity.Gia;
import Logic.entity.LichChieu;
import Logic.repository.PhimRepository;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PhimService {
    PhimRepository phimRepository;

    public PhimService(PhimRepository phimRepository) {
        this.phimRepository = phimRepository;
    }
    
    public List<LichChieu> getListLichChieu() {
        return phimRepository.getListLichChieu();
    }
    
    public List<Gia> layDanhSachGia() {
        return phimRepository.layDanhSachGia();
    }
    
    public boolean xoaGia(String idGia) {
        return phimRepository.xoaGia(idGia);
    }
    
    public boolean themGia(Gia gia) {
        return phimRepository.themGia(gia);
    }
    
    public boolean capNhatGia(Gia gia) {
        return phimRepository.capNhatGia(gia);
    }
    
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
    
    public LichChieuResponse addLichChieu(LichChieu lc) throws Exception {
        // Kiểm tra tồn tại (Generic style giống file bạn gửi)
        boolean check = phimRepository.kiemTraTonTai("lich_chieu", "idLichChieu", lc.getIdLichChieu());
        LichChieuResponse chieuResponse = new LichChieuResponse(); // Phải new để tránh NullPointer
        
        if (check) {
            chieuResponse.setMessage("Mã lịch chiếu đã tồn tại!");
            return chieuResponse; 
        }

        if (phimRepository.themLichChieu(lc)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            chieuResponse.setIdLichChieu(lc.getIdLichChieu());
            chieuResponse.setIdPhim(lc.getIdPhim());
            chieuResponse.setGioChieu(lc.getGioChieu().format(formatter));
            chieuResponse.setIdPhongChieu(lc.getIdPhongChieu());
            chieuResponse.setSoGheConLai(lc.getSoGheConLai());
            chieuResponse.setIdGia(lc.getIdGia());
            chieuResponse.setMessage("Thêm lịch chiếu thành công!");
        } else {
            throw new Exception("Thêm lịch chiếu thất bại!");
        }
        return chieuResponse;
    }

    public List<LichChieuResponse> searchLichChieu(String idLC, String idPhim, String idPhong) throws Exception {
        List<LichChieu> listEntity = phimRepository.timKiemLichChieu(idLC, idPhim, idPhong);
        List<LichChieuResponse> listResponse = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (LichChieu lc : listEntity) {
            LichChieuResponse res = new LichChieuResponse();
            res.setIdLichChieu(lc.getIdLichChieu());
            res.setIdPhim(lc.getIdPhim());
            res.setGioChieu(lc.getGioChieu().format(formatter));
            res.setIdPhongChieu(lc.getIdPhongChieu());
            res.setSoGheConLai(lc.getSoGheConLai());
            res.setIdGia(lc.getIdGia());
            listResponse.add(res);
        }
        return listResponse;
    }

    public List<String> getListPhongChieu() throws Exception {
        return phimRepository.getAllPhongChieu();
    }
    
    public String deleteLichChieu(String idLichChieu) throws Exception {
        // Kiểm tra tồn tại
        if (!phimRepository.kiemTraTonTai("lich_chieu", "idLichChieu", idLichChieu)) {
            throw new Exception("Lịch chiếu không tồn tại!");
        }

        // Gọi Repository để xóa
        if (phimRepository.xoaLichChieu(idLichChieu)) {
            return "Xóa lịch chiếu thành công!";
        } else {
            throw new Exception("Xóa lịch chiếu thất bại!");
        }
    }
}
