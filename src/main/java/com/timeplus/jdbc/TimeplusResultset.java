package com.timeplus.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.timeplus.Observer;
import com.timeplus.QueryResultWatcher;
import com.timeplus.TimeplusClient;

import io.swagger.client.ApiException;
import io.swagger.client.model.Column;
import io.swagger.client.model.Query;

class ResultsetQueryHandler implements Observer {
    static Logger logg = LoggerFactory.getLogger(ResultsetQueryHandler.class);
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();

    public ResultsetQueryHandler() {
    }

    public void handleMessage(String message) {
        try {
            this.messageQueue.put(message);
            logg.debug("put message into queur " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return this.messageQueue.size();
    }

    public String get() {
        try {
            String result = this.messageQueue.poll(1, TimeUnit.SECONDS);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Watcher extends Thread {
    static Logger logg = LoggerFactory.getLogger(Watcher.class);

    private QueryResultWatcher watcher;
    private TimeplusClient client;
    private String queryId;
    private ResultsetQueryHandler handler;

    private String current = null;

    public Watcher(TimeplusClient client, String queryId) {
        this.handler = new ResultsetQueryHandler();
        this.watcher = new QueryResultWatcher(client, queryId, this.handler);
        this.queryId = queryId;
        this.client = client;
    }

    public boolean next() throws TimeplusSQLException {
        this.current = this.handler.get();
        if (this.current != null) {
            return true;
        }

        while (this.status().equals("running")) {
            this.current = this.handler.get();
            if (this.current != null) {
                return true;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String value() {
        return this.current;
    }

    public String status() throws TimeplusSQLException {
        try {
            // QueryWithMetrics metrics = this.client.queryAPI().queriesIdGet(queryId);
            Query query = this.client.queryAPI().v1beta1QueriesIdGet(queryId);
            return query.getStatus();
        } catch (ApiException e) {
            e.printStackTrace();
            throw new TimeplusSQLException(e.getMessage());
        }
    }

    public void run() {
        watcher.watch();
        try {
            while (this.status().equals("running")) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (TimeplusSQLException e1) {
            e1.printStackTrace();
        }
        watcher.stop(); // query is still running but not been consumed
        logg.debug("watcher stopped!");
    }
}

public class TimeplusResultset implements java.sql.ResultSet {
    private TimeplusClient client;
    private List<Column> header;
    private String queryId;

    private Watcher watcher;

    public TimeplusResultset(TimeplusClient client, String queryId, List<Column> header) {
        this.client = client;
        this.header = header;
        this.queryId = queryId;

        this.watcher = new Watcher(client, queryId);
        this.watcher.start();
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
    public boolean next() throws SQLException {
        return this.watcher.next();
    }

    @Override
    public void close() throws SQLException {
        // cancel query using id
        try {
            client.queryAPI().v1beta1QueriesIdCancelPost(queryId);
            // client.queryAPI().queriesIdDelete(queryId);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        String message = this.watcher.value();
        JSONArray mJsonArray = new JSONArray(message);
        return mJsonArray.getString(columnIndex - 1);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        String message = this.watcher.value();
        JSONArray mJsonArray = new JSONArray(message);
        return mJsonArray.getBoolean(columnIndex - 1);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        float value = this.getFloat(columnIndex);
        return Math.round(value);
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        String message = this.watcher.value();
        JSONArray mJsonArray = new JSONArray(message);
        return mJsonArray.getFloat(columnIndex - 1);
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        int index = findColumn(columnLabel);
        return this.getString(index);
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        int index = findColumn(columnLabel);
        return this.getBoolean(index);
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        int index = findColumn(columnLabel);
        return this.getInt(index);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        int index = findColumn(columnLabel);
        return this.getFloat(index);
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
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
    public String getCursorName() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new TimeplusResultsetMetadata(this.header);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        String message = this.watcher.value();
        JSONArray mJsonArray = new JSONArray(message);
        return mJsonArray.get(columnIndex - 1).toString();
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        int index = findColumn(columnLabel);
        return this.getObject(index);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        for (int i = 0; i < this.header.size(); i++) {
            if (this.header.get(i).getName().equals(columnLabel)) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isFirst() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isLast() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void beforeFirst() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void afterLast() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean first() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean last() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean previous() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
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
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public int getType() throws SQLException {
        return ResultSet.TYPE_FORWARD_ONLY;
    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void insertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void deleteRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void refreshRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public Statement getStatement() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public int getHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());

    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented." + TimeplusUtilities.getMethodName());
    }

}
