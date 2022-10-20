package com.timeplus.jdbc;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.timeplus.TimeplusClient;

import io.swagger.client.ApiException;
import io.swagger.client.model.Column;
import io.swagger.client.model.CreateQueryRequest;
import io.swagger.client.model.Query;

public class TimeplusConnection implements Connection {
    static Logger logg = LoggerFactory.getLogger(TimeplusConnection.class);

    private String host;
    private String port;
    private String tenant;
    private String apikey;

    private TimeplusClient client = null;

    private boolean isClosed = false;

    public TimeplusConnection(String url, Properties loginProp) throws SQLException {
        logg.debug("the url is " + url);
        logg.debug("the loginProp is " + loginProp.toString());
        buildConnection(url, loginProp);
    }

    private void buildConnection(String url, Properties loginProp) throws SQLException {
        // jdbc:timeplus:[apikey]@//<host>[:<port>]/<tenant>
        Pattern pathParamsMatcher = Pattern.compile("^jdbc:timeplus:(.*)@\\/\\/([.\\w\\d]*):?(\\d*)\\/(.*)",
                Pattern.CASE_INSENSITIVE);
        Matcher pathParamsMatchData;
        try {
            pathParamsMatchData = pathParamsMatcher.matcher(URLDecoder.decode(url, "UTF-8"));
            if (pathParamsMatchData.find()) {
                this.apikey = pathParamsMatchData.group(1);
                this.host = pathParamsMatchData.group(2);
                this.port = pathParamsMatchData.group(3);
                this.tenant = pathParamsMatchData.group(4);
                buildClient();
            } else {
                throw new TimeplusSQLException("invalude jdbc url");
            }
        } catch (UnsupportedEncodingException e) {
            throw new SQLException(e);
        }
    }

    private void buildClient() throws TimeplusSQLException {
        String address;
        if (this.port.length() > 0) {
            address = String.format("https://%s:%s/", this.host, this.port);
        } else {
            address = String.format("https://%s/", this.host);
        }
        this.client = new TimeplusClient(address, this.tenant, this.apikey);
        check();
    }

    private void check() throws TimeplusSQLException {
        try {
            CreateQueryRequest request = new CreateQueryRequest()
                    .description("connection check query")
                    .sql("select 1");
            Query result = client.queryAPI().queriesPost(request);
            String queryId = result.getId();
            List<Column> header = result.getResult().getHeader();

            logg.debug("Query created with id " + queryId);
            logg.debug("Query header is " + header);
        } catch (ApiException e) {
            logg.error("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
            throw new TimeplusSQLException("failed to run query");
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Statement createStatement() throws SQLException {
        return new TimeplusStatement(this.client);
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        // do nothing now
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {
        // do nothing now
    }

    @Override
    public void rollback() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void close() throws SQLException {
        this.isClosed = true;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.isClosed;
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return new TimeplusDatabaseMetadata(this.client);
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        // cannot set catalog
    }

    @Override
    public String getCatalog() throws SQLException {
        return "default";
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Clob createClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Blob createBlob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public NClob createNClob() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        // TODO Auto-generated method stub
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setSchema(String schema) throws SQLException {

    }

    @Override
    public String getSchema() throws SQLException {
        return "default";
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }
}
