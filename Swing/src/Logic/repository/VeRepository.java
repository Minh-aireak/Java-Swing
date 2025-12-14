package Logic.repository;

import Logic.entity.Ve;
import ConnectDatabase.DatabaseConnection;
import Logic.dto.request.DataCreateVeRequest;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.ChiTietLichChieuResponse;
import Logic.entity.Phim;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeRepository {
    Connection connection = DatabaseConnection.getConnection();
    
    public String holdSeats(String idTaiKhoan, String idLichChieu, List<String> listIdGhe, String idGia, String tongTien) throws SQLException{
        connection.setAutoCommit(false);
        PreparedStatement ps = null;
        String id = null;
        try {
            for (String idGhe : listIdGhe) {
                ps = connection.prepareStatement(
                    "INSERT INTO lichchieu_ghe (idLichChieu, idGhe, trangThai) " +
                    "VALUES (?, ?, 'Đang xử lý')");
                ps.setString(1, idLichChieu);
                ps.setString(2, idGhe);
                ps.executeUpdate();
            }
            
            String listGhe = String.join(", ", listIdGhe);
            id = "PAY_" + UUID.randomUUID();
            ps = connection.prepareStatement("INSERT INTO payment_pending " +
                            "(id, idTaiKhoan, idLichChieu, listIdGhe, idGia, tongTien, status) " +
                            "VALUES (?, ?, ?, ?, ?, ?, 'Đang xử lý')");
            ps.setString(1, id);
            ps.setString(2, idTaiKhoan);
            ps.setString(3, idLichChieu);
            ps.setString(4, listGhe);
            ps.setString(5, idGia);
            ps.setString(6, tongTien);
            ps.executeUpdate();
            
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return null;
        }
        return id;
    }
    
//    public void saveVe(DataCreateVeRequest listVeRequest) {
//        
//        connection.setAutoCommit(false);
//        int index = 1;
//        try {
//            PreparedStatement ps = null;
//            ps = connection.prepareStatement("INSERT INTO bill (idBill, idTaiKhoan, thoiGianDat, tongTien) VALUES (?, ?, ?, ?)");
//            ps.setString(index++, listVeRequest.getIdBill());
//            ps.setString(index++, listVeRequest.getIdTaiKhoan());
//            ps.setTimestamp(index++, Timestamp.valueOf(listVeRequest.getThoiGianDat()));
//            ps.setInt(index, Integer.valueOf(listVeRequest.getTongTien()));
//            ps.executeUpdate();
//
//            for (String idGhe : listVeRequest.getListIdGhe()){
//                ps = connection.prepareStatement("INSERT INTO ve (idVe, idLichChieu, idGhe, idGia, idBill) VALUES (?, ? ,? ,? ,?)");
//                ps.setString(1, UUID.randomUUID().toString());
//                ps.setString(2, listVeRequest.getIdLichChieu());
//                ps.setString(3, idGhe);
//                ps.setString(4, listVeRequest.getIdGia());
//                ps.setString(5, listVeRequest.getIdBill());
//                ps.executeUpdate();
//                ps = connection.prepareStatement("INSERT INTO lichchieu_ghe (idLichChieu, idGhe, trangThai) VALUES (?, ?, ?)");
//                ps.setString(1, listVeRequest.getIdLichChieu());
//                ps.setString(2, idGhe);
//                ps.setString(3, listVeRequest.getTrangThai());
//                ps.executeUpdate();
//            }
//
//            ps = connection.prepareStatement("UPDATE lich_chieu SET soGheConLai = soGheConLai - ? WHERE idLichChieu = ?");
//            ps.setString(1, String.valueOf(listVeRequest.getListIdGhe().size()));
//            ps.setString(2, listVeRequest.getIdLichChieu());
//            ps.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            connection.rollback();
//            Logger.getLogger(VeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public ArrayList<Ve> getDanhSachDatVe(){
        ArrayList<Ve> ds = new ArrayList<>();
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT v.idVe, CONCAT(tk.hoDem, ' ', tk.ten) AS ten, " +
                         "p.tenPhim, DATE_FORMAT(lc.gioChieu, '%d/%m/%Y %H:%i') AS gioChieu, " +
                         "g.idGhe, pc.tenPhong, " +
                         "CASE g.loaiGhe " +
                         "WHEN 'Tiêu chuẩn' THEN gia.tieuChuan " +
                         "WHEN 'VIP' THEN gia.vip " +
                         "WHEN 'Triple' THEN gia.triple END AS giaVe " +
                         "FROM ve v " +
                         "JOIN bill b ON v.idBill = b.idBill " +
                         "JOIN tai_khoan tk ON b.idTaiKhoan = tk.idTaiKhoan " +
                         "JOIN lich_chieu lc ON v.idLichChieu = lc.idLichChieu " +
                         "JOIN phim p ON lc.idPhim = p.idPhim " +
                         "JOIN ghe g ON v.idGhe = g.idGhe " +
                         "JOIN phong_chieu pc ON lc.idPhongChieu = pc.idPhongChieu " +
                         "JOIN gia ON v.idGia = gia.idGia";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Ve dv = new Ve(
                    rs.getString("idVe"),
                    rs.getString("ten"),
                    rs.getString("tenPhim"),
                    rs.getString("gioChieu"),
                    rs.getString("idGhe"),
                    rs.getString("tenPhong"),
                    rs.getInt("giaVe")
                );
                ds.add(dv);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách đặt vé: " + e.getMessage());
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
        return ds;
    }

    public boolean deleteVe(String idVe) throws SQLException{
        try {
            connection = DatabaseConnection.getConnection();
            String sqlVe = "DELETE FROM ve WHERE idVe = ?";
            PreparedStatement psVe = connection.prepareStatement(sqlVe);
            psVe.setString(1, idVe);
            int rows = psVe.executeUpdate();
            
            String sqlGhe = "UPDATE lichchieu_ghe SET trangThai = 'Còn trống' " +
                           "WHERE idLichChieu = (SELECT idLichChieu FROM ve WHERE idVe = ?) " +
                           "AND idGhe = (SELECT idGhe FROM ve WHERE idVe = ?)";
            PreparedStatement psGhe = connection.prepareStatement(sqlGhe);
            psGhe.setString(1, idVe);
            psGhe.setString(2, idVe);
            psGhe.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
    
    public List<BillResponse> getListBill(String idTaiKhoan) throws SQLException {
        
        List<BillResponse> response = new ArrayList<>();
            
        connection = DatabaseConnection.getConnection();
        String statement =
            "SELECT " +
            "b.idBill, " +
            "GROUP_CONCAT(DISTINCT p.tenPhim SEPARATOR ', ') AS tenPhim, " +
            "b.tongTien, " +
            "GROUP_CONCAT(DISTINCT lcg.trangThai SEPARATOR ', ') AS trangThai " +
            "FROM bill b " +
            "JOIN ve v ON b.idBill = v.idBill " +
            "JOIN lich_chieu lc ON v.idLichChieu = lc.idLichChieu " +
            "JOIN phim p ON lc.idPhim = p.idPhim " +
            "JOIN lichchieu_ghe lcg ON lc.idLichChieu = lcg.idLichChieu " +
            "WHERE b.idTaiKhoan = ? " +
            "GROUP BY b.idBill, b.tongTien";
        PreparedStatement ps = connection.prepareStatement(statement);
        ps.setString(1, idTaiKhoan);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            BillResponse billResponse = new BillResponse();
            billResponse.setIdBill(rs.getString("idBill"));
            billResponse.setTenPhim(rs.getString("tenPhim"));
            billResponse.setTongTien(rs.getInt("tongTien"));
            billResponse.setTrangThai(rs.getString("trangThai"));
            response.add(billResponse);
        }
        
        return response;
    }
    
    public ChiTietBillResponse getChiTietBill(String idBill, String idTaiKhoan) throws SQLException {
        ChiTietBillResponse billResponse = new ChiTietBillResponse();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            connection = DatabaseConnection.getConnection();
            String statement = "SELECT DISTINCT lc.idLichChieu, p.tenPhim, lc.gioChieu, GROUP_CONCAT(v.idGhe SEPARATOR ', ') AS idGhe, pc.tenPhong, b.thoiGianDat , b.tongTien " +
            "FROM phim p " +
            "JOIN lich_chieu lc ON p.idPhim = lc.idPhim " +
            "JOIN ve v ON lc.idLichChieu = v.idLichChieu " +
            "JOIN bill b ON b.idBill = v.idBill " +
            "JOIN tai_khoan tk ON b.idTaiKhoan = tk.idTaiKhoan " +
            "JOIN phong_chieu pc ON lc.idPhongChieu = pc.idPhongChieu " +
            "WHERE tk.idTaiKhoan = ? " +
            "AND b.idBill = ? " +
            "GROUP BY lc.idLichChieu, p.tenPhim, lc.gioChieu, p.thoiLuong, pc.tenPhong, b.thoiGianDat, b.tongTien ";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setString(1, idTaiKhoan);
            ps.setString(2, idBill);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                billResponse.setIdBill(idBill);
                billResponse.setTenPhim(rs.getString("tenPhim"));
                billResponse.setXuatChieu(dateFormat.format(rs.getTimestamp("gioChieu")));
                
                StringBuilder builder = new StringBuilder();
                builder.append(rs.getString("idGhe"));
                billResponse.setSoGheDaDat(builder.toString());
                
                billResponse.setPhongChieu(rs.getString("tenPhong"));
                billResponse.setThoiGianDat(dateFormat.format(rs.getTimestamp("thoiGianDat")));
                billResponse.setTongTien(rs.getInt("tongTien"));
            }
            
            return billResponse;
    }
    
    public ChiTietLichChieuResponse getChiTietLichChieu(Phim chosenPhim, Timestamp gioChieu) throws SQLException {
        ChiTietLichChieuResponse response = new ChiTietLichChieuResponse(null, null, null, null, null);
        
        PreparedStatement ps = connection.prepareStatement(
            "SELECT pc.tenPhong, GROUP_CONCAT(lcg.idGhe SEPARATOR ', ') AS idGhe, lc.idLichChieu, ga.idGia, ga.tieuChuan, ga.VIP, ga.Triple " +
            "FROM phim p " +
            "JOIN lich_chieu lc ON p.idPhim = lc.idPhim " +
            "JOIN phong_chieu pc ON lc.idPhongChieu = pc.idPhongChieu " +
            "LEFT JOIN lichchieu_ghe lcg ON lc.idLichChieu = lcg.idLichChieu " +
            "LEFT JOIN ghe ge ON lcg.idGhe = ge.idGhe " +
            "JOIN gia ga ON lc.idGia = ga.idGia " +
            "WHERE lc.idPhim = ? " +
            "AND lc.gioChieu = ? " +
            "GROUP BY pc.tenPhong, lc.idLichChieu, ga.idGia, ga.tieuChuan, ga.VIP, ga.Triple");
        ps.setString(1, chosenPhim.getIdPhim());
        ps.setTimestamp(2, gioChieu);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            response.setTenPhong(rs.getString("tenPhong"));
            response.setIdLichChieu(rs.getString("idLichChieu"));
            response.setIdGia(rs.getString("idGia"));
            response.setTenPhong(rs.getString("tenPhong"));

            if(rs.getString("idGhe") != null && !rs.getString("idGhe").isEmpty()){
                String[] list = rs.getString("idGhe").split(", ");
                response.setIdGhe(Arrays.asList(list));
            }

            List<Integer> list = new ArrayList<>();
            list.add(rs.getInt("tieuChuan"));
            list.add(rs.getInt("VIP"));
            list.add(rs.getInt("Triple"));
            response.setListGias(list);
        }

        return response;
    }
    
    public List<String> getListGhe(Timestamp gioChieu) throws SQLException {
        List<String> listGhe = null;
        PreparedStatement ps = connection.prepareStatement("SELECT GROUP_CONCAT(lcg.idGhe SEPARATOR ', ') AS idGhe " +
                    "FROM lich_chieu lc " +
                    "LEFT JOIN lichchieu_ghe lcg ON lc.idLichChieu = lcg.idLichChieu " +
                    "WHERE lc.gioChieu = ?");
        ps.setTimestamp(1, gioChieu);
        ResultSet rs = ps.executeQuery();
        if(rs.getString("idGhe") != null && !rs.getString("idGhe").isEmpty()){
            String[] dsach = rs.getString("idGhe").split(", ");
            listGhe.addAll(Arrays.asList(dsach));
        }
        return listGhe;
    }
    
    public List<Timestamp> getListLichChieu(String idPhim) throws SQLException {
        List<Timestamp> listGioChieu = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT gioChieu " +
                                        "FROM lich_chieu lc " +
                                        "JOIN phim p ON lc.idPhim = p.idPhim " +
                                        "WHERE lc.idPhim = ? " +
                                        "AND lc.gioChieu >= DATE_ADD(NOW(), INTERVAL 1 HOUR) " +
                                        "ORDER BY gioChieu ASC");
        ps.setString(1, idPhim);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Timestamp ts = rs.getTimestamp("gioChieu");     
            listGioChieu.add(ts);
        }
        return listGioChieu;
    }
}