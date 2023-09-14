package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connection connection;

    private static void createConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/mydb?createDatabaseIfNotExist=true";
            connection = DriverManager.getConnection(url,"root", "0986045812a");
        } catch (ClassNotFoundException | SQLException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }
    }

    // Hàm lấy kết nối (sẽ tạo kết nối nếu chưa có)
    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    // Hàm đóng kết nối
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Xử lý lỗi nếu có
                e.printStackTrace();
            }
        }
    }
}
