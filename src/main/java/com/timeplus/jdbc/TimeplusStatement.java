package com.timeplus.jdbc;

import java.sql.*;
import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.timeplus.TimeplusClient;

import io.swagger.client.ApiException;
import io.swagger.client.model.*;

public class TimeplusStatement implements java.sql.Statement {
    static Logger logg = LoggerFactory.getLogger(TimeplusConnection.class);

    private TimeplusClient client = null;
    private boolean isClosed = false;

    private int maxRow = 0;
    private int maxFieldSize = 0;
    private int fetchSize = 0;

    private ResultSet currentResultset = null;

    public TimeplusStatement(TimeplusClient client) {
        super();
        this.client = client;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    private ResultSet executeTimeplusQuery(String sql) throws SQLException {
        try {
            CreateQueryRequest request = new CreateQueryRequest()
                    .sql(sql);
            Query result = client.queryAPI().queriesPost(request);
            String queryId = result.getId();
            List<Column> header = result.getResult().getHeader();

            logg.debug("Query created with id " + queryId);
            logg.debug("Query header is " + header);

            this.currentResultset = new TimeplusResultset(client, queryId, header);
            return this.currentResultset;
        } catch (ApiException e) {
            logg.error("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
            throw new TimeplusSQLException("failed to run query " + sql + " : " + e.getMessage());
        }
    }

    private String getQueryType(String sql) throws SQLException {
        try {
            AnalyzeSQLRequest request = new AnalyzeSQLRequest().sql(sql);
            SQLAnalyzeResult result = client.queryAPI().sqlanalyzePost(request);
            return result.getQueryType();
        } catch (ApiException e) {
            logg.error("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
            throw new TimeplusSQLException("failed to run AnalyzeSQLRequest " + e.getMessage());
        }
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        String queryType = this.getQueryType(sql);

        if (queryType.equals("SHOW_TABLE")) {
            String query = " SELECT name AS STREAM_NAME , engine AS ENGINE FROM system.tables WHERE not name like '.inner%' ";
            return executeTimeplusQuery(query);
        }

        return executeTimeplusQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void close() throws SQLException {
        this.isClosed = true;
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return this.maxFieldSize;
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        logg.info("setMaxFieldSize : " + max);
        this.maxFieldSize = max;
    }

    @Override
    public int getMaxRows() throws SQLException {
        return this.maxRow;
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        logg.info("setMaxRows : " + max);
        this.maxRow = max;
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public int getQueryTimeout() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void cancel() throws SQLException {
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
    public void setCursorName(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        ResultSet result = this.executeQuery(sql);
        this.currentResultset = result;
        return true;
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return this.currentResultset;
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return -1;
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return false;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        this.fetchSize = rows;
    }

    @Override
    public int getFetchSize() throws SQLException {
        return this.fetchSize;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getResultSetType() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return false;
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException(
                "Not implemented. execute(String sql, int autoGeneratedKeys) ");
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException(
                "Not implemented. execute(String sql, int[] columnIndexes) ");
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException(
                "Not implemented. execute(String sql, String[] columnNames)");
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isClosed() throws SQLException {
        return this.isClosed;
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }
}