package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class JDBCUtil {

    public static Connection getConnection() {
        Connection c = null;

        try {
            // đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/quanlysieuthileduylam";
            String username = "root";
            String password = "";

            c = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public static void closeConnection(Connection a) {
        try {
            if (a != null) {
                a.close();
            }
        } catch (Exception b) {
            b.printStackTrace();
        }
    }

    public static void printInfo(Connection c) {
        try {
            if (c != null) {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            } else {
                System.out.println("NULL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}