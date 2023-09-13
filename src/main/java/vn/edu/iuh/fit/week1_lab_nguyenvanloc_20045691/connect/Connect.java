package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.connect;

import java.sql.*;


public class Connect {
    private static Connection connection;

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/mydb?createDatabaseIfNotExist=true";
            connection = DriverManager.getConnection(url);
        }catch (ClassNotFoundException| SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
