package ConnectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/qlphim";
    private static final String user = "root";
    private static final String password = "1801062012";
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(url, user, password);
            return c;
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        return null;
    }
}
