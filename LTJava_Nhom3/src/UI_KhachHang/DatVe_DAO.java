package UI_KhachHang;

import ConnectDatabase.DatabaseConnection;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatVe_DAO {
    public static boolean getLichChieu(String idPhim){
        boolean flag = false;
        Connection connection;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT GROUP_CONCAT(lc.gioChieu SEPARATOR ', ') AS gioChieu " + 
                    "FROM phim p " +
                    "JOIN lich_chieu lc ON p.idPhim = lc.idPhim " +
                    "WHERE p.idPhim = ? ");
            ps.setString(1, idPhim);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String gioChieu = rs.getString("gioChieu");
                if(gioChieu != null && !gioChieu.isEmpty()){
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatVe_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
