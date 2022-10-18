package timeplus.io;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import java.sql.*;

//import com.timeplus.jdbc.*;

/**
 * Unit test for simple App.
 */
public class DriverTest {
    /**
     * Connection Test
     * 
     * @throws SQLException
     */
    @Test
    public void shouldConnect() {

        try {
            Class.forName("timeplus.io.jdbc.TimeplusDriver");
            final String jdbcUrl = "jdbc:TimeplusDriver:test";
            final Connection con = DriverManager.getConnection(jdbcUrl);
            assertNotNull(con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
