package samples;

import java.sql.*;

public class JDBCSample {
    public static void main(String[] args) {
        try {
            String apiKey = System.getenv("TIMEPLUS_API_KEY");
            String tenant = System.getenv("TIMEPLUS_TENANT");
            String host = "dev.timeplus.cloud";

            Class.forName("com.timeplus.jdbc.TimeplusDriver");
            // jdbc:timeplus:[apikey]@//<host>[:<port>]/<tenant>
            String jdbcUrl = String.format("jdbc:timeplus:%s@//%s/%s", apiKey, host, tenant);

            final Connection conn = DriverManager.getConnection(jdbcUrl);
            System.out.println("Connected to Timeplus!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT time, speed_kmh, cid FROM car_live_data");
            int count = 0;
            while (rs.next()) {
                String cid = rs.getString("cid");
                String time = rs.getString("time");
                float speed = rs.getFloat("speed_kmh");
                System.out.println("cid is " + cid);
                System.out.println("time is " + time);
                System.out.println("speed is " + speed);
                count += 1;
                if (count > 10) {
                    break;
                }
            }
            rs.close();
            System.out.println("query completed! ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
