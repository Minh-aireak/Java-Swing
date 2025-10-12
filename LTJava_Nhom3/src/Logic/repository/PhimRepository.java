package Logic.repository;

import ConnectDatabase.DatabaseConnection;
import Logic.entity.LichChieu;
import java.sql.*;

public class PhimRepository {
    Connection connection = DatabaseConnection.getConnection();
    
    public boolean kiemTraTonTai(String table, String column, String value) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        return false;
    }
    
    public boolean updateLichChieu(LichChieu lc) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
            String sql = "UPDATE lich_chieu SET idPhim = ?, gioChieu = ?, soGheConLai = ?, idPhongChieu = ?, idGia = ? WHERE idLichChieu = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, lc.getIdPhim());
            ps.setTimestamp(2, Timestamp.valueOf(lc.getGioChieu()));
            ps.setInt(3, lc.getSoGheConLai());
            ps.setString(4, lc.getIdPhongChieu());
            ps.setString(5, lc.getIdGia());
            ps.setString(6, lc.getIdLichChieu());
            int rows = ps.executeUpdate();
        return rows > 0;
    }
    
}