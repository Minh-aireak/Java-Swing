package UI_Admin;

import java.sql.*;

public class DBConnection {
    public static Connection KetNoi() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/qlphim?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh";
        String username = "root";
        String pass = "Adhalao123@";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pass);
            return conn;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        return null;
    }
}
