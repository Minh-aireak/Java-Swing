package Logic.service;

import Logic.dto.request.DataCreatePaymentRequest;
import Logic.dto.request.DataCreateVeRequest;
import Logic.dto.response.BillResponse;
import Logic.dto.response.ChiTietBillResponse;
import Logic.dto.response.ChiTietLichChieuResponse;
import Logic.dto.response.ListLichChieuResponse;
import Logic.entity.Phim;
import Logic.repository.VeRepository;
import configuration.Config;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeService {
    VeRepository veRepository;
    String idTaiKhoan = null;

    public VeService(VeRepository veRepository) {
        this.veRepository = veRepository;
    }
    
    
    public void saveVe(DataCreateVeRequest request) throws SQLException{
         try {
             veRepository.saveVe(request);
         } catch (SQLException ex) {
             throw new SQLException("saveVe error!");
         }
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
            var response = veRepository.getListBill(idTaiKhoan);
            System.out.println("123");
            return response;
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
        ChiTietLichChieuResponse response = null;
        try {
            response = veRepository.getChiTietLichChieu(chosenPhim, gioChieu);
        } catch (SQLException ex) {
            throw new SQLException("getChiTietLichChieu error!");
        }
        
        return response;
    }
    
    public List<String> getListGhe(Timestamp gioChieu) throws SQLException{
        List<String> listGhe = null;
        try {
            listGhe = veRepository.getListGhe(gioChieu);
        } catch (SQLException ex) {
            throw new SQLException("getListGhe error!");
        }
        
        return listGhe;
    }
    
    public ListLichChieuResponse getListLichChieu(String idPhim) throws SQLException {
        ListLichChieuResponse response = null;
        try {
            List<Timestamp> listGioChieu = veRepository.getListLichChieu(idPhim);
            if(Objects.isNull(listGioChieu)){
                response.setMessage("Phim hiện tại chưa cập nhật lịch chiếu");
            }
            response.setListGioChieu(listGioChieu);
        } catch (SQLException ex) {
            throw new SQLException("getListLichChieu error!");
        }
        
        return response;
    }
    
    public String createPaymentUrl(DataCreatePaymentRequest request) throws Exception {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String vnp_TxnRef = Config.getRandomNumber(8);
//        String ipAddr = Config.getIpAddress();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", Config.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(request.getAmount() * 100)); 
        vnp_Params.put("vnp_CurrCode", "VND");
        if (request.getBackCode() != null && !request.getBackCode().isEmpty()) {
            vnp_Params.put("vnp_BankCode", request.getBackCode());
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        if (request.getLanguage() != null && !request.getLanguage().isEmpty()) {
            vnp_Params.put("vnp_Locale", request.getLanguage());
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
//        vnp_Params.put("vnp_IpAddr", ipAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                hashData.append(fieldName).append("=").append(fieldValue);
                query.append(fieldName).append("=").append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                if (itr.hasNext()) {
                    hashData.append("&");
                    query.append("&");
                }
            }
        }
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        String paymentUrl = Config.vnp_PayUrl + "?" + query.toString()
                            + "&vnp_SecureHash=" + vnp_SecureHash;
        return paymentUrl;
    }
}
