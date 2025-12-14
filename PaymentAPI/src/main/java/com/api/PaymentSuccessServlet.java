package com.api;

import com.configuration.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@WebServlet("/payment_success")
public class PaymentSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("vnp_TxnRef");
        Connection conn = null;

        String idTaiKhoan;
        String idLichChieu;
        String listIdGhe;
        String idGia;
        String tongTien;
        String idBill = "Bill_" + UUID.randomUUID();
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps = null;

            ps = conn.prepareStatement("SELECT idTaiKhoan, idLichChieu, listIdGhe, idGia, tongTien " +
                            "FROM payment_pending " +
                            "WHERE id = ? AND status = 'Đang xử lý'"
            );
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                resp.getWriter().println("Giao dịch không hợp lệ!");
                return;
            }

            idTaiKhoan  = rs.getString("idTaiKhoan");
            idLichChieu = rs.getString("idLichChieu");
            listIdGhe   = rs.getString("listIdGhe");
            idGia       = rs.getString("idGia");
            tongTien    = rs.getString("tongTien");

            ps = conn.prepareStatement("INSERT INTO bill (idBill, idTaiKhoan, thoiGianDat, tongTien) VALUES (?, ?, NOW(), ?)");
            ps.setString(1, idBill);
            ps.setString(2, idTaiKhoan);
            ps.setInt(3, Integer.parseInt(tongTien));
            ps.executeUpdate();

            List<String> list = Arrays.asList(listIdGhe.split(", "));
            for (String idGhe : list){
                ps = conn.prepareStatement("INSERT INTO ve (idVe, idLichChieu, idGhe, idGia, idBill) VALUES (?, ? ,? ,? ,?)");
                ps.setString(1, "Ve_" + UUID.randomUUID());
                ps.setString(2, idLichChieu);
                ps.setString(3, idGhe);
                ps.setString(4, idGia);
                ps.setString(5, idBill);
                ps.executeUpdate();

                ps = conn.prepareStatement("UPDATE lichchieu_ghe SET trangThai = 'Đã đặt' " +
                                            " WHERE idLichChieu = ? AND idGhe = ? AND trangThai = 'Đang xử lý'");
                ps.setString(1, idLichChieu);
                ps.setString(2, idGhe);
                int updated = ps.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Ghế đã được đặt");
                }

            }

            ps = conn.prepareStatement("UPDATE lich_chieu SET soGheConLai = soGheConLai - ? WHERE idLichChieu = ?");
            ps.setInt(1, list.size());
            ps.setString(2, idLichChieu);
            ps.executeUpdate();

            conn.commit();
            resp.getWriter().println("Thanh toán thành công!");
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
