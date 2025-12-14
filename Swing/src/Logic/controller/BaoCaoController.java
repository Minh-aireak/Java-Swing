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
            
    public boolean ktraHopLe(String day, String month, String year, String errorMessage){
        if (!day.isEmpty() && !day.matches("\\d+")) {
            errorMessage = "Ngày phải là số hợp lệ!";
            return false;
        }
        if (!month.isEmpty() && !month.matches("\\d+")) {
            errorMessage = "Tháng phải là số hợp lệ!";
            return false;
        }
        if (!year.isEmpty() && !year.matches("\\d+")) {
            errorMessage = "Năm phải là số hợp lệ!";
            return false;
        }
        int ngay = !day.isEmpty() ? Integer.parseInt(day) : -1;
        int thang = !month.isEmpty() ? Integer.parseInt(month) : -1;
        int nam = !month.isEmpty() ? Integer.parseInt(month) : -1;
        
        if(nam != -1 && nam < 1900){
            errorMessage = "Năm phải lớn hơn 1900!";
            return false;
        }

        if (thang != -1 && (thang < 1 || thang > 12)) {
            errorMessage = "Tháng phải nằm trong khoảng 1-12!";
            return false;
        }
        if (ngay != -1) {
            if (thang == -1) {
                errorMessage = "Bạn cần nhập tháng để kiểm tra ngày!";
                return false;
            }

            List<Integer> thang31Ngay = List.of(1, 3, 5, 7, 8, 10, 12);
            List<Integer> thang30Ngay = List.of(4, 6, 9, 11);

            if (thang31Ngay.contains(thang)) {
                if (ngay > 31) {
                    errorMessage = "Ngày không hợp lệ!";
                    return false;
                }
            } else if (thang30Ngay.contains(thang)) {
                if (ngay > 30) {
                    errorMessage = "Ngày không hợp lệ!";
                    return false;
                }
            } else if (thang == 2) {
                if (nam == -1) {
                    errorMessage = "Cần nhập năm để kiểm tra tháng 2!";
                    return false;
                }
                boolean isLeap = (nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0);
                int maxDay = isLeap ? 29 : 28;
                if (ngay > maxDay) {
                    errorMessage = "Ngày không hợp lệ với tháng 2 năm " + nam + "!";
                    return false;
                }
            }
        }
        return true;
    }
    public void taoBaoCao(String day, String month, String year, DefaultTableModel modelReport) throws Exception{  
        try {
            reportRepo.capNhatSoGheConLai(day, month, year, SO_GHE_TOI_DA);
            reportRepo.layBaoCao(day, month, year, SO_GHE_TOI_DA, modelReport);
        }catch (Exception e) {
            throw new Exception("Lỗi tạo báo cáo!");
        }
//        StringBuilder updateSql = new StringBuilder("""
//            UPDATE lich_chieu lc
//            SET lc.soGheConLai = ? - (
//                SELECT COUNT(*)
//                FROM ve v
//                WHERE v.idLichChieu = lc.idLichChieu
//            )
//            WHERE 1=1
//        """);
//
//        List<Integer> updateParamValues = new ArrayList<>();
//
//        if (!year.isEmpty()) {
//            updateSql.append(" AND YEAR(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(year));
//        }
//        if (!month.isEmpty()) {
//            updateSql.append(" AND MONTH(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(month));
//        }
//        if (!day.isEmpty()) {
//            updateSql.append(" AND DAY(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(day));
//        }
//
//        try (Connection conn = DBConnection.KetNoi()) {
//            if (conn == null) {
//                JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!");
//                return;
//            }
//            try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
//                int index = 1;
//                updatePs.setInt(index++, SO_GHE_TOI_DA);
//                for (int val : updateParamValues) {
//                    updatePs.setInt(index++, val);
//                }
//                updatePs.executeUpdate();
//            }
//            StringBuilder reportSql = new StringBuilder("""
//                SELECT
//                    p.tenPhim,
//                    GREATEST(0, (? * COUNT(*)) - SUM(lc.soGheConLai)) AS so_ghe_da_dat
//                FROM
//                    lich_chieu lc
//                JOIN
//                    phim p ON lc.idPhim = p.idPhim
//                WHERE 1=1
//            """);
//
//            List<Integer> reportParamValues = new ArrayList<>();
//
//            if (!year.isEmpty()) {
//                reportSql.append(" AND YEAR(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(year));
//            }
//            if (!month.isEmpty()) {
//                reportSql.append(" AND MONTH(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(month));
//            }
//            if (!day.isEmpty()) {
//                reportSql.append(" AND DAY(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(day));
//            }
//            reportSql.append("""
//                GROUP BY lc.idPhim, p.tenPhim
//                ORDER BY p.tenPhim
//            """);
//            try (PreparedStatement reportPs = conn.prepareStatement(reportSql.toString())) {
//                int index = 1;
//                reportPs.setInt(index++, SO_GHE_TOI_DA);
//                for (int val : reportParamValues) {
//                    reportPs.setInt(index++, val);
//                }
//                ResultSet rs = reportPs.executeQuery();
//                modelReport.setRowCount(0);
//                while (rs.next()) {
//                    String tenPhim = rs.getString("tenPhim");
//                    int soGheDaDat = rs.getInt("so_ghe_da_dat");
//                    modelReport.addRow(new Object[]{tenPhim, soGheDaDat});
//                }
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage());
//            ex.printStackTrace();
//        }
        
//        StringBuilder updateSql = new StringBuilder("""
//            UPDATE lich_chieu lc
//            SET lc.soGheConLai = ? - (
//                SELECT COUNT(*) 
//                FROM ve v 
//                WHERE v.idLichChieu = lc.idLichChieu
//            )
//            WHERE 1=1
//        """);
//
//        List<Integer> updateParamValues = new ArrayList<>();
//
//        if (!year.isEmpty()) {
//            updateSql.append(" AND YEAR(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(year));
//        }
//        if (!month.isEmpty()) {
//            updateSql.append(" AND MONTH(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(month));
//        }
//        if (!day.isEmpty()) {
//            updateSql.append(" AND DAY(lc.gioChieu) = ? ");
//            updateParamValues.add(Integer.parseInt(day));
//        }
//
//        try (Connection conn = DBConnection.KetNoi()) {
//            if (conn == null) {
//                JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!");
//                return;
//            }
//            try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
//                int index = 1;
//                updatePs.setInt(index++, SO_GHE_TOI_DA);
//                for (int val : updateParamValues) {
//                    updatePs.setInt(index++, val);
//                }
//                updatePs.executeUpdate();
//            }
//            StringBuilder reportSql = new StringBuilder("""
//                SELECT
//                    p.tenPhim,
//                    GREATEST(0, (? * COUNT(*)) - SUM(lc.soGheConLai)) AS so_ghe_da_dat
//                FROM
//                    lich_chieu lc
//                JOIN
//                    phim p ON lc.idPhim = p.idPhim
//                WHERE 1=1
//            """);
//
//            List<Integer> reportParamValues = new ArrayList<>();
//
//            if (!year.isEmpty()) {
//                reportSql.append(" AND YEAR(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(year));
//            }
//            if (!month.isEmpty()) {
//                reportSql.append(" AND MONTH(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(month));
//            }
//            if (!day.isEmpty()) {
//                reportSql.append(" AND DAY(lc.gioChieu) = ? ");
//                reportParamValues.add(Integer.parseInt(day));
//            }
//            reportSql.append("""
//                GROUP BY lc.idPhim, p.tenPhim
//                ORDER BY p.tenPhim
//            """);
//            try (PreparedStatement reportPs = conn.prepareStatement(reportSql.toString())) {
//                int index = 1;
//                reportPs.setInt(index++, SO_GHE_TOI_DA);
//                for (int val : reportParamValues) {
//                    reportPs.setInt(index++, val);
//                }
//                ResultSet rs = reportPs.executeQuery();
//                modelReport.setRowCount(0);
//                while (rs.next()) {
//                    String tenPhim = rs.getString("tenPhim");
//                    int soGheDaDat = rs.getInt("so_ghe_da_dat");
//                    modelReport.addRow(new Object[]{tenPhim, soGheDaDat});
//                }
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }
    
    public void taoBaoCaoFromTo(Date dateFrom, Date dateTo, DefaultTableModel modelReport) throws Exception {
        try{
            reportRepo.capNhatSoGheConLaiFromTo(dateFrom, dateTo, SO_GHE_TOI_DA);
            reportRepo.layBaoCaoFromTo(dateFrom, dateTo, SO_GHE_TOI_DA, modelReport);
        }catch(Exception e){
            throw new Exception("Lỗi tạo báo cáo!");
        }
    }
}
