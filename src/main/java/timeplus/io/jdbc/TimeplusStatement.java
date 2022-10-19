package timeplus.io.jdbc;

import java.sql.*;
import java.util.List;

import timeplus.io.TimeplusClient;

import io.swagger.client.ApiException;
import io.swagger.client.model.Column;
import io.swagger.client.model.CreateQueryRequest;
import io.swagger.client.model.Query;

public class TimeplusStatement implements java.sql.Statement {
    private TimeplusClient client = null;

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

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            CreateQueryRequest request = new CreateQueryRequest()
                    .sql(sql);
            Query result = client.queryAPI().queriesPost(request);
            String queryId = result.getId();
            List<Column> header = result.getResult().getHeader();

            System.out.println("Query created with id " + queryId);
            System.out.println("Query header is " + header);
            return new TimeplusResultset(client, queryId, header);
        } catch (ApiException e) {
            System.err.println("Exception when calling QueriesApi#queriesPost");
            e.printStackTrace();
            throw new TimeplusSQLException("failed to run query");
        }
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void close() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getMaxRows() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public int getQueryTimeout() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public void cancel() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public void setCursorName(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public boolean execute(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getUpdateCount() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getResultSetType() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");

    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }
}