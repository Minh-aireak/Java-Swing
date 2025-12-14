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

@WebServlet("/payment_return")
public class PaymentSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String responseCode = req.getParameter("vnp_ResponseCode");
        String txnRef = req.getParameter("vnp_TxnRef");

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT idTaiKhoan, idLichChieu, listIdGhe, idGia, tongTien " +
                            "FROM payment_pending WHERE id = ? AND status = 'Đang xử lý'"
            );
            ps.setString(1, txnRef);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                resp.getWriter().println("Giao dịch không hợp lệ!");
                return;
            }

            String idTaiKhoan  = rs.getString("idTaiKhoan");
            String idLichChieu = rs.getString("idLichChieu");
            String listIdGhe   = rs.getString("listIdGhe");
            String idGia       = rs.getString("idGia");
            int tongTien       = rs.getInt("tongTien");

            List<String> list = Arrays.asList(listIdGhe.split(", "));

            if (!"00".equals(responseCode)) {

                ps = conn.prepareStatement(
                        "DELETE lichchieu_ghe " +
                                "WHERE idLichChieu = ? AND idGhe = ? AND trangThai = 'Đang xử lý'"
                );

                for (String idGhe : list) {
                    ps.setString(1, idLichChieu);
                    ps.setString(2, idGhe);
                    ps.executeUpdate();
                }

                ps = conn.prepareStatement(
                        "UPDATE payment_pending SET status = 'Thất bại' WHERE id = ?"
                );
                ps.setString(1, txnRef);
                ps.executeUpdate();

                conn.commit();
                resp.getWriter().println("Thanh toán thất bại!");
                return;
            }

            String idBill = "Bill_" + UUID.randomUUID();

            ps = conn.prepareStatement(
                    "INSERT INTO bill (idBill, idTaiKhoan, thoiGianDat, tongTien) " +
                            "VALUES (?, ?, NOW(), ?)"
            );
            ps.setString(1, idBill);
            ps.setString(2, idTaiKhoan);
            ps.setInt(3, tongTien);
            ps.executeUpdate();

            for (String idGhe : list) {
                ps = conn.prepareStatement(
                        "INSERT INTO ve (idVe, idLichChieu, idGhe, idGia, idBill) " +
                                "VALUES (?, ?, ?, ?, ?)"
                );
                ps.setString(1, "Ve_" + UUID.randomUUID());
                ps.setString(2, idLichChieu);
                ps.setString(3, idGhe);
                ps.setString(4, idGia);
                ps.setString(5, idBill);
                ps.executeUpdate();

                ps = conn.prepareStatement("UPDATE lichchieu_ghe SET trangThai = 'Đã đặt' " +
                                "WHERE idLichChieu = ? AND idGhe = ? AND trangThai = 'Đang xử lý'"
                );
                ps.setString(1, idLichChieu);
                ps.setString(2, idGhe);

                if (ps.executeUpdate() == 0) {
                    throw new SQLException("Ghế đã bị người khác đặt");
                }
            }

            ps = conn.prepareStatement(
                    "UPDATE lich_chieu SET soGheConLai = soGheConLai - ? WHERE idLichChieu = ?"
            );
            ps.setInt(1, list.size());
            ps.setString(2, idLichChieu);
            ps.executeUpdate();

            ps = conn.prepareStatement(
                    "UPDATE payment_pending SET status = 'Thành công' WHERE id = ?"
            );
            ps.setString(1, txnRef);
            ps.executeUpdate();

            conn.commit();
            resp.getWriter().println("Thanh toán thành công!");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ignored) {}
            throw new RuntimeException(e);
        }
    }
}
