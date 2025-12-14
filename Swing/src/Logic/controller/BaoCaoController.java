package Logic.controller;

import java.sql.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Logic.repository.BaoCaoRepository;

public class BaoCaoController {
    
    public static final int SO_GHE_TOI_DA = 30;
    BaoCaoRepository reportRepo = new BaoCaoRepository();
    public BaoCaoController(BaoCaoRepository reportRepo) {
        this.reportRepo = reportRepo;
    }
            
    public String ktraHopLe(String day, String month, String year, String errorMessage){
        if (!day.isEmpty() && !day.matches("\\d+")) {
            errorMessage = "Ngày phải là số hợp lệ!";
            return errorMessage;
        }
        if (!month.isEmpty() && !month.matches("\\d+")) {
            errorMessage = "Tháng phải là số hợp lệ!";
            return errorMessage;
        }
        if (!year.isEmpty() && !year.matches("\\d+")) {
            errorMessage = "Năm phải là số hợp lệ!";
            return errorMessage;
        }
        int ngay = !day.isEmpty() ? Integer.parseInt(day) : -1;
        int thang = !month.isEmpty() ? Integer.parseInt(month) : -1;
        int nam = !year.isEmpty() ? Integer.parseInt(year) : -1;
        
        if(nam != -1 && nam < 1900){
            errorMessage = "Năm phải lớn hơn 1900!";
            return errorMessage;
        }

        if (thang != -1 && (thang < 1 || thang > 12)) {
            errorMessage = "Tháng phải nằm trong khoảng 1-12!";
            return errorMessage;
        }
        if (ngay != -1) {
            if (thang == -1) {
                errorMessage = "Bạn cần nhập tháng để kiểm tra ngày!";
                return errorMessage;
            }

            List<Integer> thang31Ngay = List.of(1, 3, 5, 7, 8, 10, 12);
            List<Integer> thang30Ngay = List.of(4, 6, 9, 11);

            if (thang31Ngay.contains(thang)) {
                if (ngay > 31) {
                    errorMessage = "Ngày không hợp lệ!";
                    return errorMessage;
                }
            } else if (thang30Ngay.contains(thang)) {
                if (ngay > 30) {
                    errorMessage = "Ngày không hợp lệ!";
                    return errorMessage;
                }
            } else if (thang == 2) {
                if (nam == -1) {
                    errorMessage = "Cần nhập năm để kiểm tra tháng 2!";
                    return errorMessage;
                }
                boolean isLeap = (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
                int maxDay = isLeap ? 29 : 28;
                if (ngay > maxDay) {
                    errorMessage = "Ngày không hợp lệ với tháng 2 năm " + nam + "!";
                    return errorMessage;
                }
            }
        }
        return null;
    }
    
    public void taoBaoCao(String day, String month, String year, DefaultTableModel modelReport) throws Exception{  
        try {
            reportRepo.capNhatSoGheConLai(day, month, year, SO_GHE_TOI_DA);
            reportRepo.layBaoCao(day, month, year, SO_GHE_TOI_DA, modelReport);
        }catch (Exception e) {
            throw new Exception("Lỗi tạo báo cáo!");
        }
    }
    
    public void taoBaoCaoFromTo(Date dateFrom, Date dateTo, DefaultTableModel modelReport) throws Exception {
        try{
            reportRepo.capNhatSoGheConLaiFromTo(dateFrom, dateTo, SO_GHE_TOI_DA);
            reportRepo.layBaoCaoFromTo(dateFrom, dateTo, SO_GHE_TOI_DA, modelReport);
        }catch(Exception e){
            throw new Exception("Lỗi: " + e.getMessage());
        }
    }
}
