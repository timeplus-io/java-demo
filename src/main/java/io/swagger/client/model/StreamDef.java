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
import io.swagger.client.model.ColumnDef;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * StreamDef
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class StreamDef {
  @SerializedName("columns")
  private List<ColumnDef> columns = null;

  @SerializedName("event_time_column")
  private String eventTimeColumn = null;

  @SerializedName("event_time_timezone")
  private String eventTimeTimezone = null;

  @SerializedName("logstore_retention_bytes")
  private Integer logstoreRetentionBytes = null;

  @SerializedName("logstore_retention_ms")
  private Integer logstoreRetentionMs = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("order_by_expression")
  private String orderByExpression = null;

  @SerializedName("order_by_granularity")
  private String orderByGranularity = null;

  @SerializedName("partition_by_granularity")
  private String partitionByGranularity = null;

  @SerializedName("replication_factor")
  private Integer replicationFactor = null;

  @SerializedName("shards")
  private Integer shards = null;

  @SerializedName("ttl_expression")
  private String ttlExpression = null;

  public StreamDef columns(List<ColumnDef> columns) {
    this.columns = columns;
    return this;
  }

  public StreamDef addColumnsItem(ColumnDef columnsItem) {
    if (this.columns == null) {
      this.columns = new ArrayList<ColumnDef>();
    }
    this.columns.add(columnsItem);
    return this;
  }

   /**
   * Get columns
   * @return columns
  **/
  @ApiModelProperty(value = "")
  public List<ColumnDef> getColumns() {
    return columns;
  }

  public void setColumns(List<ColumnDef> columns) {
    this.columns = columns;
  }

  public StreamDef eventTimeColumn(String eventTimeColumn) {
    this.eventTimeColumn = eventTimeColumn;
    return this;
  }

   /**
   * Get eventTimeColumn
   * @return eventTimeColumn
  **/
  @ApiModelProperty(value = "")
  public String getEventTimeColumn() {
    return eventTimeColumn;
  }

  public void setEventTimeColumn(String eventTimeColumn) {
    this.eventTimeColumn = eventTimeColumn;
  }

  public StreamDef eventTimeTimezone(String eventTimeTimezone) {
    this.eventTimeTimezone = eventTimeTimezone;
    return this;
  }

   /**
   * Get eventTimeTimezone
   * @return eventTimeTimezone
  **/
  @ApiModelProperty(value = "")
  public String getEventTimeTimezone() {
    return eventTimeTimezone;
  }

  public void setEventTimeTimezone(String eventTimeTimezone) {
    this.eventTimeTimezone = eventTimeTimezone;
  }

  public StreamDef logstoreRetentionBytes(Integer logstoreRetentionBytes) {
    this.logstoreRetentionBytes = logstoreRetentionBytes;
    return this;
  }

   /**
   * Get logstoreRetentionBytes
   * @return logstoreRetentionBytes
  **/
  @ApiModelProperty(value = "")
  public Integer getLogstoreRetentionBytes() {
    return logstoreRetentionBytes;
  }

  public void setLogstoreRetentionBytes(Integer logstoreRetentionBytes) {
    this.logstoreRetentionBytes = logstoreRetentionBytes;
  }

  public StreamDef logstoreRetentionMs(Integer logstoreRetentionMs) {
    this.logstoreRetentionMs = logstoreRetentionMs;
    return this;
  }

   /**
   * Get logstoreRetentionMs
   * @return logstoreRetentionMs
  **/
  @ApiModelProperty(value = "")
  public Integer getLogstoreRetentionMs() {
    return logstoreRetentionMs;
  }

  public void setLogstoreRetentionMs(Integer logstoreRetentionMs) {
    this.logstoreRetentionMs = logstoreRetentionMs;
  }

  public StreamDef name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StreamDef orderByExpression(String orderByExpression) {
    this.orderByExpression = orderByExpression;
    return this;
  }

   /**
   * Get orderByExpression
   * @return orderByExpression
  **/
  @ApiModelProperty(value = "")
  public String getOrderByExpression() {
    return orderByExpression;
  }

  public void setOrderByExpression(String orderByExpression) {
    this.orderByExpression = orderByExpression;
  }

  public StreamDef orderByGranularity(String orderByGranularity) {
    this.orderByGranularity = orderByGranularity;
    return this;
  }

   /**
   * Get orderByGranularity
   * @return orderByGranularity
  **/
  @ApiModelProperty(value = "")
  public String getOrderByGranularity() {
    return orderByGranularity;
  }

  public void setOrderByGranularity(String orderByGranularity) {
    this.orderByGranularity = orderByGranularity;
  }

  public StreamDef partitionByGranularity(String partitionByGranularity) {
    this.partitionByGranularity = partitionByGranularity;
    return this;
  }

   /**
   * Get partitionByGranularity
   * @return partitionByGranularity
  **/
  @ApiModelProperty(value = "")
  public String getPartitionByGranularity() {
    return partitionByGranularity;
  }

  public void setPartitionByGranularity(String partitionByGranularity) {
    this.partitionByGranularity = partitionByGranularity;
  }

  public StreamDef replicationFactor(Integer replicationFactor) {
    this.replicationFactor = replicationFactor;
    return this;
  }

   /**
   * Get replicationFactor
   * @return replicationFactor
  **/
  @ApiModelProperty(value = "")
  public Integer getReplicationFactor() {
    return replicationFactor;
  }

  public void setReplicationFactor(Integer replicationFactor) {
    this.replicationFactor = replicationFactor;
  }

  public StreamDef shards(Integer shards) {
    this.shards = shards;
    return this;
  }

   /**
   * Get shards
   * @return shards
  **/
  @ApiModelProperty(value = "")
  public Integer getShards() {
    return shards;
  }

  public void setShards(Integer shards) {
    this.shards = shards;
  }

  public StreamDef ttlExpression(String ttlExpression) {
    this.ttlExpression = ttlExpression;
    return this;
  }

   /**
   * Get ttlExpression
   * @return ttlExpression
  **/
  @ApiModelProperty(value = "")
  public String getTtlExpression() {
    return ttlExpression;
  }

  public void setTtlExpression(String ttlExpression) {
    this.ttlExpression = ttlExpression;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StreamDef streamDef = (StreamDef) o;
    return Objects.equals(this.columns, streamDef.columns) &&
        Objects.equals(this.eventTimeColumn, streamDef.eventTimeColumn) &&
        Objects.equals(this.eventTimeTimezone, streamDef.eventTimeTimezone) &&
        Objects.equals(this.logstoreRetentionBytes, streamDef.logstoreRetentionBytes) &&
        Objects.equals(this.logstoreRetentionMs, streamDef.logstoreRetentionMs) &&
        Objects.equals(this.name, streamDef.name) &&
        Objects.equals(this.orderByExpression, streamDef.orderByExpression) &&
        Objects.equals(this.orderByGranularity, streamDef.orderByGranularity) &&
        Objects.equals(this.partitionByGranularity, streamDef.partitionByGranularity) &&
        Objects.equals(this.replicationFactor, streamDef.replicationFactor) &&
        Objects.equals(this.shards, streamDef.shards) &&
        Objects.equals(this.ttlExpression, streamDef.ttlExpression);
  }

  @Override
  public int hashCode() {
    return Objects.hash(columns, eventTimeColumn, eventTimeTimezone, logstoreRetentionBytes, logstoreRetentionMs, name, orderByExpression, orderByGranularity, partitionByGranularity, replicationFactor, shards, ttlExpression);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StreamDef {\n");
    
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    eventTimeColumn: ").append(toIndentedString(eventTimeColumn)).append("\n");
    sb.append("    eventTimeTimezone: ").append(toIndentedString(eventTimeTimezone)).append("\n");
    sb.append("    logstoreRetentionBytes: ").append(toIndentedString(logstoreRetentionBytes)).append("\n");
    sb.append("    logstoreRetentionMs: ").append(toIndentedString(logstoreRetentionMs)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    orderByExpression: ").append(toIndentedString(orderByExpression)).append("\n");
    sb.append("    orderByGranularity: ").append(toIndentedString(orderByGranularity)).append("\n");
    sb.append("    partitionByGranularity: ").append(toIndentedString(partitionByGranularity)).append("\n");
    sb.append("    replicationFactor: ").append(toIndentedString(replicationFactor)).append("\n");
    sb.append("    shards: ").append(toIndentedString(shards)).append("\n");
    sb.append("    ttlExpression: ").append(toIndentedString(ttlExpression)).append("\n");
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

