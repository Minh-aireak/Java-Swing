/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.repository;

import UI_Admin.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PV
 */
public class BaoCaoRepository {
     public void capNhatSoGheConLai(String day, String month, String year, int soGheToiDa) throws SQLException{
         StringBuilder updateSql = new StringBuilder("""
            UPDATE lich_chieu lc
            SET lc.soGheConLai = ? - (
                SELECT COUNT(*) 
                FROM ve v 
                WHERE v.idLichChieu = lc.idLichChieu
            )
            WHERE 1=1
        """);

        List<Integer> updateParamValues = new ArrayList<>();

        if (!year.isEmpty()) {
            updateSql.append(" AND YEAR(lc.gioChieu) = ? ");
            updateParamValues.add(Integer.parseInt(year));
        }
        if (!month.isEmpty()) {
            updateSql.append(" AND MONTH(lc.gioChieu) = ? ");
            updateParamValues.add(Integer.parseInt(month));
        }
        if (!day.isEmpty()) {
            updateSql.append(" AND DAY(lc.gioChieu) = ? ");
            updateParamValues.add(Integer.parseInt(day));
        }

        try (Connection conn = DBConnection.KetNoi()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu!");
                return;
            }
            try (PreparedStatement updatePs = conn.prepareStatement(updateSql.toString())) {
                int index = 1;
                updatePs.setInt(index++, soGheToiDa);
                for (int val : updateParamValues) {
                    updatePs.setInt(index++, val);
                }
                updatePs.executeUpdate();
            }
        }
    }
     public void layBaoCao(String day, String month, String year, int soGheToiDa, DefaultTableModel modelReport) throws SQLException{
         StringBuilder reportSql = new StringBuilder("""
                SELECT
                    p.tenPhim,
                    GREATEST(0, (? * COUNT(*)) - SUM(lc.soGheConLai)) AS so_ghe_da_dat
                FROM
                    lich_chieu lc
                JOIN
                    phim p ON lc.idPhim = p.idPhim
                WHERE 1=1
            """);

            List<Integer> reportParamValues = new ArrayList<>();

            if (!year.isEmpty()) {
                reportSql.append(" AND YEAR(lc.gioChieu) = ? ");
                reportParamValues.add(Integer.parseInt(year));
            }
            if (!month.isEmpty()) {
                reportSql.append(" AND MONTH(lc.gioChieu) = ? ");
                reportParamValues.add(Integer.parseInt(month));
            }
            if (!day.isEmpty()) {
                reportSql.append(" AND DAY(lc.gioChieu) = ? ");
                reportParamValues.add(Integer.parseInt(day));
            }
            reportSql.append("""
                GROUP BY lc.idPhim, p.tenPhim
                ORDER BY p.tenPhim
            """);
            try (Connection conn = DBConnection.KetNoi(); 
                PreparedStatement reportPs = conn.prepareStatement(reportSql.toString())) {
                int index = 1;
                reportPs.setInt(index++, soGheToiDa);
                for (int val : reportParamValues) {
                    reportPs.setInt(index++, val);
                }
                ResultSet rs = reportPs.executeQuery();
                modelReport.setRowCount(0);
                while (rs.next()) {
                    String tenPhim = rs.getString("tenPhim");
                    int soGheDaDat = rs.getInt("so_ghe_da_dat");
                    modelReport.addRow(new Object[]{tenPhim, soGheDaDat});
                }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
