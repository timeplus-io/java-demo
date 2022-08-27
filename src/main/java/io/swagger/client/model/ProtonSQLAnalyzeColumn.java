/*
 * Timeplus
 * Welcome to the Timeplus HTTP REST API specification.
 *
 * OpenAPI spec version: v1beta1
 * Contact: eng@timeplus.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ProtonSQLAnalyzeColumn
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class ProtonSQLAnalyzeColumn {
  @SerializedName("column")
  private String column = null;

  @SerializedName("column_type")
  private String columnType = null;

  @SerializedName("database")
  private String database = null;

  @SerializedName("is_view")
  private Boolean isView = null;

  @SerializedName("table")
  private String table = null;

  public ProtonSQLAnalyzeColumn column(String column) {
    this.column = column;
    return this;
  }

   /**
   * Get column
   * @return column
  **/
  @ApiModelProperty(value = "")
  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public ProtonSQLAnalyzeColumn columnType(String columnType) {
    this.columnType = columnType;
    return this;
  }

   /**
   * Get columnType
   * @return columnType
  **/
  @ApiModelProperty(value = "")
  public String getColumnType() {
    return columnType;
  }

  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }

  public ProtonSQLAnalyzeColumn database(String database) {
    this.database = database;
    return this;
  }

   /**
   * Get database
   * @return database
  **/
  @ApiModelProperty(value = "")
  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public ProtonSQLAnalyzeColumn isView(Boolean isView) {
    this.isView = isView;
    return this;
  }

   /**
   * Get isView
   * @return isView
  **/
  @ApiModelProperty(value = "")
  public Boolean isIsView() {
    return isView;
  }

  public void setIsView(Boolean isView) {
    this.isView = isView;
  }

  public ProtonSQLAnalyzeColumn table(String table) {
    this.table = table;
    return this;
  }

   /**
   * Get table
   * @return table
  **/
  @ApiModelProperty(value = "")
  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtonSQLAnalyzeColumn protonSQLAnalyzeColumn = (ProtonSQLAnalyzeColumn) o;
    return Objects.equals(this.column, protonSQLAnalyzeColumn.column) &&
        Objects.equals(this.columnType, protonSQLAnalyzeColumn.columnType) &&
        Objects.equals(this.database, protonSQLAnalyzeColumn.database) &&
        Objects.equals(this.isView, protonSQLAnalyzeColumn.isView) &&
        Objects.equals(this.table, protonSQLAnalyzeColumn.table);
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, columnType, database, isView, table);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtonSQLAnalyzeColumn {\n");
    
    sb.append("    column: ").append(toIndentedString(column)).append("\n");
    sb.append("    columnType: ").append(toIndentedString(columnType)).append("\n");
    sb.append("    database: ").append(toIndentedString(database)).append("\n");
    sb.append("    isView: ").append(toIndentedString(isView)).append("\n");
    sb.append("    table: ").append(toIndentedString(table)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

