package com.timeplus.jdbc;

import java.sql.*;
import java.util.List;

import io.swagger.client.model.Column;

public class TimeplusResultsetMetadata implements ResultSetMetaData {
    private List<Column> columns;

    public TimeplusResultsetMetadata(List<Column> columns) {
        this.columns = columns;
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
    public int getColumnCount() throws SQLException {
        return this.columns.size();
    }

    @Override
    public boolean isAutoIncrement(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isCaseSensitive(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isSearchable(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean isCurrency(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int isNullable(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public boolean isSigned(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getColumnDisplaySize(int column) throws SQLException {
        return 10;
    }

    @Override
    public String getColumnLabel(int column) throws SQLException {
        return this.columns.get(column - 1).getName();
    }

    @Override
    public String getColumnName(int column) throws SQLException {
        return this.columns.get(column - 1).getName();
    }

    @Override
    public String getSchemaName(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getPrecision(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public int getScale(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public String getTableName(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    @Override
    public String getCatalogName(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

    private int toType(String typeName) {
        switch (typeName.toLowerCase()) {
            case "string":
                return java.sql.Types.VARCHAR;
            case "integer":
                return java.sql.Types.INTEGER;
            case "float":
                return java.sql.Types.FLOAT;
            case "bool":
                return java.sql.Types.BOOLEAN;
            case "date":
                return java.sql.Types.DATE;
            case "datetime":
            case "datetime64":
                return java.sql.Types.TIME;
            default:
                if (typeName.toLowerCase().startsWith("decimal")) {
                    return java.sql.Types.DECIMAL;
                } else if (typeName.toLowerCase().startsWith("array")) {
                    return java.sql.Types.ARRAY;
                } else if (typeName.toLowerCase().startsWith("map")) {
                    return java.sql.Types.JAVA_OBJECT;
                } else if (typeName.toLowerCase().startsWith("tuple")) {
                    return java.sql.Types.JAVA_OBJECT;
                } else {
                    return java.sql.Types.JAVA_OBJECT;
                }
        }
    }

    @Override
    public int getColumnType(int column) throws SQLException {
        return toType(this.columns.get(column - 1).getType());
    }

    @Override
    public String getColumnTypeName(int column) throws SQLException {
        return this.columns.get(column - 1).getType();
    }

    @Override
    public boolean isReadOnly(int column) throws SQLException {
        return true;
    }

    @Override
    public boolean isWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public boolean isDefinitelyWritable(int column) throws SQLException {
        return false;
    }

    @Override
    public String getColumnClassName(int column) throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented.");
    }

}
