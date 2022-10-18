package timeplus.io.jdbc;

import java.sql.*;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeplusDriver implements java.sql.Driver {
    /** Instance log4j.Logger */
    static Logger logg = LoggerFactory.getLogger(TimeplusDriver.class);
    /** Url_Prefix for using this driver */
    private static final String URL_PREFIX = "jdbc:TimeplusDriver:";
    /** MAJOR Version of the driver */
    private static final int MAJOR_VERSION = 1;
    /** Minor Version of the driver */
    private static final int MINOR_VERSION = 0;

    /** Registers the driver with the driver manager */
    static {
        try {
            TimeplusDriver driverInst = new TimeplusDriver();
            DriverManager.registerDriver(driverInst);
            logg.debug("Registered the driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Major Version of the Driver as static
     *
     * @return Major Version of the Driver as static
     */
    public static int getMajorVersionAsStatic() {
        return TimeplusDriver.MAJOR_VERSION;
    }

    /**
     * Gets Minor Version of the Driver as static
     *
     * @return Minor Version of the Driver as static
     */
    public static int getMinorVersionAsStatic() {
        return TimeplusDriver.MINOR_VERSION;
    }

    /** It returns the URL prefix for using BQDriver */
    public static String getURLPrefix() {
        return TimeplusDriver.URL_PREFIX;
    }

    /** {@inheritDoc} */
    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(TimeplusDriver.URL_PREFIX);
    }

    /**
     *
     *
     * <h1>Implementation Details:</h1>
     *
     * <br>
     * This method create a new BQconnection and then returns it
     */
    @Override
    public Connection connect(String url, Properties loginProp) throws SQLException {
        TimeplusConnection localConInstance = null;
        logg.debug("Creating Connection With url: " + url);

        if (this.acceptsURL(url)) {
            localConInstance = new TimeplusConnection(url, loginProp);
        }

        return localConInstance;
    }

    /** {@inheritDoc} */
    @Override
    public int getMajorVersion() {
        return TimeplusDriver.MAJOR_VERSION;
    }

    /** {@inheritDoc} */
    @Override
    public int getMinorVersion() {
        return TimeplusDriver.MINOR_VERSION;
    }

    /**
     *
     *
     * <h1>Implementation Details:</h1>
     *
     * <br>
     * Gets information about the possible properties for this driver.
     *
     * @return a default DriverPropertyInfo
     */
    @Override
    public java.sql.DriverPropertyInfo[] getPropertyInfo(String url, Properties loginProps)
            throws SQLException {
        return new DriverPropertyInfo[0];
    }

    /**
     *
     *
     * <h1>Implementation Details:</h1>
     *
     * <br>
     * Always returns false, since the driver is not jdbcCompliant
     */
    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

}
