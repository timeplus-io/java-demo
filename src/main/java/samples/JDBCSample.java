package samples;

import java.sql.*;

public class JDBCSample {
    public static void main(String[] args) {
        try {
            Class.forName("timeplus.io.jdbc.TimeplusDriver");
            final String jdbcUrl = "jdbc:TimeplusDriver:test";
            final Connection conn = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected to Timeplus!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT time, speed_kmh, cid FROM car_live_data");
            while (rs.next()) {
                String cid = rs.getString("cid");
                System.out.println(cid + "\n");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
