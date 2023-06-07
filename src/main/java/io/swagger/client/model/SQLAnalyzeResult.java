/*
 * Timeplus
 * Welcome to the Timeplus HTTP REST API specification.
 *
 * OpenAPI spec version: v1
 * Contact: support@timeplus.com
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
import io.swagger.client.model.SQLAnalyzeColumn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLAnalyzeResult
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-06-06T23:45:14.171Z")
public class SQLAnalyzeResult {
  @SerializedName("group_by_columns")
  private List<String> groupByColumns = null;

  @SerializedName("has_aggr")
  private Boolean hasAggr = null;

  @SerializedName("has_subquery")
  private Boolean hasSubquery = null;

  @SerializedName("has_table_join")
  private Boolean hasTableJoin = null;

  @SerializedName("has_union")
  private Boolean hasUnion = null;

  @SerializedName("is_streaming")
  private Boolean isStreaming = null;

  @SerializedName("original_query")
  private String originalQuery = null;

  @SerializedName("query_type")
  private String queryType = null;

  @SerializedName("required_columns")
  private List<SQLAnalyzeColumn> requiredColumns = null;

  @SerializedName("result_columns")
  private List<SQLAnalyzeColumn> resultColumns = null;

  @SerializedName("rewritten_query")
  private String rewrittenQuery = null;

  public SQLAnalyzeResult groupByColumns(List<String> groupByColumns) {
    this.groupByColumns = groupByColumns;
    return this;
  }

  public SQLAnalyzeResult addGroupByColumnsItem(String groupByColumnsItem) {
    if (this.groupByColumns == null) {
      this.groupByColumns = new ArrayList<String>();
    }
    this.groupByColumns.add(groupByColumnsItem);
    return this;
  }

   /**
   * Get groupByColumns
   * @return groupByColumns
  **/
  @ApiModelProperty(value = "")
  public List<String> getGroupByColumns() {
    return groupByColumns;
  }

  public void setGroupByColumns(List<String> groupByColumns) {
    this.groupByColumns = groupByColumns;
  }

  public SQLAnalyzeResult hasAggr(Boolean hasAggr) {
    this.hasAggr = hasAggr;
    return this;
  }

   /**
   * Get hasAggr
   * @return hasAggr
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasAggr() {
    return hasAggr;
  }

  public void setHasAggr(Boolean hasAggr) {
    this.hasAggr = hasAggr;
  }

  public SQLAnalyzeResult hasSubquery(Boolean hasSubquery) {
    this.hasSubquery = hasSubquery;
    return this;
  }

   /**
   * Get hasSubquery
   * @return hasSubquery
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasSubquery() {
    return hasSubquery;
  }

  public void setHasSubquery(Boolean hasSubquery) {
    this.hasSubquery = hasSubquery;
  }

  public SQLAnalyzeResult hasTableJoin(Boolean hasTableJoin) {
    this.hasTableJoin = hasTableJoin;
    return this;
  }

   /**
   * Get hasTableJoin
   * @return hasTableJoin
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasTableJoin() {
    return hasTableJoin;
  }

  public void setHasTableJoin(Boolean hasTableJoin) {
    this.hasTableJoin = hasTableJoin;
  }

  public SQLAnalyzeResult hasUnion(Boolean hasUnion) {
    this.hasUnion = hasUnion;
    return this;
  }

   /**
   * Get hasUnion
   * @return hasUnion
  **/
  @ApiModelProperty(value = "")
  public Boolean isHasUnion() {
    return hasUnion;
  }

  public void setHasUnion(Boolean hasUnion) {
    this.hasUnion = hasUnion;
  }

  public SQLAnalyzeResult isStreaming(Boolean isStreaming) {
    this.isStreaming = isStreaming;
    return this;
  }

   /**
   * Get isStreaming
   * @return isStreaming
  **/
  @ApiModelProperty(value = "")
  public Boolean isIsStreaming() {
    return isStreaming;
  }

  public void setIsStreaming(Boolean isStreaming) {
    this.isStreaming = isStreaming;
  }

  public SQLAnalyzeResult originalQuery(String originalQuery) {
    this.originalQuery = originalQuery;
    return this;
  }

   /**
   * Get originalQuery
   * @return originalQuery
  **/
  @ApiModelProperty(value = "")
  public String getOriginalQuery() {
    return originalQuery;
  }

  public void setOriginalQuery(String originalQuery) {
    this.originalQuery = originalQuery;
  }

  public SQLAnalyzeResult queryType(String queryType) {
    this.queryType = queryType;
    return this;
  }

   /**
   * Get queryType
   * @return queryType
  **/
  @ApiModelProperty(value = "")
  public String getQueryType() {
    return queryType;
  }

  public void setQueryType(String queryType) {
    this.queryType = queryType;
  }

  public SQLAnalyzeResult requiredColumns(List<SQLAnalyzeColumn> requiredColumns) {
    this.requiredColumns = requiredColumns;
    return this;
  }

  public SQLAnalyzeResult addRequiredColumnsItem(SQLAnalyzeColumn requiredColumnsItem) {
    if (this.requiredColumns == null) {
      this.requiredColumns = new ArrayList<SQLAnalyzeColumn>();
    }
    this.requiredColumns.add(requiredColumnsItem);
    return this;
  }

   /**
   * Get requiredColumns
   * @return requiredColumns
  **/
  @ApiModelProperty(value = "")
  public List<SQLAnalyzeColumn> getRequiredColumns() {
    return requiredColumns;
  }

  public void setRequiredColumns(List<SQLAnalyzeColumn> requiredColumns) {
    this.requiredColumns = requiredColumns;
  }

  public SQLAnalyzeResult resultColumns(List<SQLAnalyzeColumn> resultColumns) {
    this.resultColumns = resultColumns;
    return this;
  }

  public SQLAnalyzeResult addResultColumnsItem(SQLAnalyzeColumn resultColumnsItem) {
    if (this.resultColumns == null) {
      this.resultColumns = new ArrayList<SQLAnalyzeColumn>();
    }
    this.resultColumns.add(resultColumnsItem);
    return this;
  }

   /**
   * Get resultColumns
   * @return resultColumns
  **/
  @ApiModelProperty(value = "")
  public List<SQLAnalyzeColumn> getResultColumns() {
    return resultColumns;
  }

  public void setResultColumns(List<SQLAnalyzeColumn> resultColumns) {
    this.resultColumns = resultColumns;
  }

  public SQLAnalyzeResult rewrittenQuery(String rewrittenQuery) {
    this.rewrittenQuery = rewrittenQuery;
    return this;
  }

   /**
   * Get rewrittenQuery
   * @return rewrittenQuery
  **/
  @ApiModelProperty(value = "")
  public String getRewrittenQuery() {
    return rewrittenQuery;
  }

  public void setRewrittenQuery(String rewrittenQuery) {
    this.rewrittenQuery = rewrittenQuery;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SQLAnalyzeResult sqLAnalyzeResult = (SQLAnalyzeResult) o;
    return Objects.equals(this.groupByColumns, sqLAnalyzeResult.groupByColumns) &&
        Objects.equals(this.hasAggr, sqLAnalyzeResult.hasAggr) &&
        Objects.equals(this.hasSubquery, sqLAnalyzeResult.hasSubquery) &&
        Objects.equals(this.hasTableJoin, sqLAnalyzeResult.hasTableJoin) &&
        Objects.equals(this.hasUnion, sqLAnalyzeResult.hasUnion) &&
        Objects.equals(this.isStreaming, sqLAnalyzeResult.isStreaming) &&
        Objects.equals(this.originalQuery, sqLAnalyzeResult.originalQuery) &&
        Objects.equals(this.queryType, sqLAnalyzeResult.queryType) &&
        Objects.equals(this.requiredColumns, sqLAnalyzeResult.requiredColumns) &&
        Objects.equals(this.resultColumns, sqLAnalyzeResult.resultColumns) &&
        Objects.equals(this.rewrittenQuery, sqLAnalyzeResult.rewrittenQuery);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupByColumns, hasAggr, hasSubquery, hasTableJoin, hasUnion, isStreaming, originalQuery, queryType, requiredColumns, resultColumns, rewrittenQuery);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SQLAnalyzeResult {\n");
    
    sb.append("    groupByColumns: ").append(toIndentedString(groupByColumns)).append("\n");
    sb.append("    hasAggr: ").append(toIndentedString(hasAggr)).append("\n");
    sb.append("    hasSubquery: ").append(toIndentedString(hasSubquery)).append("\n");
    sb.append("    hasTableJoin: ").append(toIndentedString(hasTableJoin)).append("\n");
    sb.append("    hasUnion: ").append(toIndentedString(hasUnion)).append("\n");
    sb.append("    isStreaming: ").append(toIndentedString(isStreaming)).append("\n");
    sb.append("    originalQuery: ").append(toIndentedString(originalQuery)).append("\n");
    sb.append("    queryType: ").append(toIndentedString(queryType)).append("\n");
    sb.append("    requiredColumns: ").append(toIndentedString(requiredColumns)).append("\n");
    sb.append("    resultColumns: ").append(toIndentedString(resultColumns)).append("\n");
    sb.append("    rewrittenQuery: ").append(toIndentedString(rewrittenQuery)).append("\n");
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

