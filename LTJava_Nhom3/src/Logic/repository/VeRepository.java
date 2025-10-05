package Logic.repository;

import Logic.entity.Ve;
import ConnectDatabase.DatabaseConnection;
import Logic.dto.request.DataCreateVeRequest;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.LichChieuResponse;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeRepository {
    Connection connection = DatabaseConnection.getConnection();
    
    public void taoVe(DataCreateVeRequest listVeRequest) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int index = 1;
        ps = connection.prepareStatement("INSERT INTO bill (idBill, idTaiKhoan, thoiGianDat, tongTien) VALUES (?, ?, ?, ?)");
        ps.setString(index++, listVeRequest.getIdBill());
        ps.setString(index++, listVeRequest.getIdTaiKhoan());
        ps.setString(index++, listVeRequest.getThoiGianDat().toString());
        ps.setInt(index, listVeRequest.getTongTien());
        ps.executeUpdate();
        
        for(int i = 0; i < listVeRequest.getListIdVe().size(); i++){
            ps = connection.prepareStatement("INSERT INTO ve (idVe, idLichChieu, idGhe, idGia, idBill) VALUES (?, ? ,? ,? ,?)");
            ps.setString(1, listVeRequest.getListIdVe().get(i));
            ps.setString(2, listVeRequest.getIdLichChieu());
            ps.setString(3, listVeRequest.getListIdGhe().get(i));
            ps.setString(4, listVeRequest.getListIdGia().get(i));
            ps.setString(5, listVeRequest.getIdBill());
            ps.executeUpdate();
            ps = connection.prepareStatement("INSERT INTO lichchieu_ghe (idLichChieu, idGhe, trangThai) VALUES (?, ?, ?)");
            ps.setString(1, listVeRequest.getIdLichChieu());
            ps.setString(2, listVeRequest.getListIdGhe().get(i));
            ps.setString(3, "Đã đặt");
            ps.executeUpdate();
        }
        
        ps = connection.prepareStatement("UPDATE lich_chieu SET soGheConLai = soGheConLai - ? WHERE idLichChieu = ?");
        ps.setString(1, String.valueOf(listVeRequest.getListIdGhe().size()));
        ps.setString(2, listVeRequest.getIdLichChieu());
        ps.executeUpdate();
    }
    
    public ArrayList<Ve> layDanhSachDatVe(){
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

    public boolean huyVe(String idVe){
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
            System.out.println("Lỗi hủy vé: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
    
    public List<BillResponse> getListBill(String idTaiKhoan) {
        List<BillResponse> response = new ArrayList<>();
            
        try {
            connection = DatabaseConnection.getConnection();
            String statement = "SELECT DISTINCT b.idBill, p.tenPhim, b.tongTien " +
                    "FROM phim p " +
                    "JOIN lich_chieu lc ON p.idPhim = lc.idPhim " +
                    "JOIN ve v ON lc.idLichChieu = v.idLichChieu " +
                    "JOIN bill b ON v.idBill = b.idBill " +
                    "JOIN tai_khoan tk ON b.idTaiKhoan = tk.idTaiKhoan " +
                    "WHERE tk.idTaiKhoan = ? ";
            PreparedStatement ps = connection.prepareStatement(statement);
            ps.setString(1, idTaiKhoan);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BillResponse billResponse = new BillResponse();
                billResponse.setIdBill(rs.getString("idBill"));
                billResponse.setTenPhim(rs.getString("tenPhim"));
                billResponse.setTongTien(rs.getInt("tongTien"));
                response.add(billResponse);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeRepository.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public LichChieuResponse getLichChieu(String idPhim, LocalDateTime gioChieu) {
        LichChieuResponse chieuResponse = new LichChieuResponse();
        
        try {
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
            ps.setString(1, idPhim);
            ps.setObject(2, gioChieu);
            ResultSet rs = ps.executeQuery();
            chieuResponse.setTenPhong(rs.getString("tenPhong"));
            chieuResponse.setListIdGheDaDat(rs.getString("listIdGheDaDat"));
            chieuResponse.setTenPhong(rs.getString("tenPhong"));
            chieuResponse.setTenPhong(rs.getString("tenPhong"));
            chieuResponse.setTenPhong(rs.getString("tenPhong"));
            chieuResponse.setTenPhong(rs.getString("tenPhong"));
        } catch (Exception e) {
            
        }
        return chieuResponse;
    }
}