package samples;

import java.sql.*;

public class JDBCSample {
    public static void main(String[] args) {
        try {
            Class.forName("timeplus.io.jdbc.TimeplusDriver");
            final String jdbcUrl = "jdbc:TimeplusDriver:test";
            final Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected to Timeplus!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
