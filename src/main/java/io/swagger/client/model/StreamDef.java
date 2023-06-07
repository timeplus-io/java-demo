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
import io.swagger.client.model.ColumnDef;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * StreamDef
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-06-06T23:45:14.171Z")
public class StreamDef {
  @SerializedName("columns")
  private List<ColumnDef> columns = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("event_time_column")
  private String eventTimeColumn = null;

  @SerializedName("event_time_timezone")
  private String eventTimeTimezone = null;

  @SerializedName("logstore_retention_bytes")
  private Integer logstoreRetentionBytes = null;

  @SerializedName("logstore_retention_ms")
  private Integer logstoreRetentionMs = null;

  @SerializedName("mode")
  private String mode = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("order_by_expression")
  private String orderByExpression = null;

  @SerializedName("order_by_granularity")
  private String orderByGranularity = null;

  @SerializedName("partition_by_granularity")
  private String partitionByGranularity = null;

  @SerializedName("primary_key")
  private String primaryKey = null;

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

  public StreamDef description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "my first stream", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public StreamDef eventTimeColumn(String eventTimeColumn) {
    this.eventTimeColumn = eventTimeColumn;
    return this;
  }

   /**
   * This column will be used as the event time if specified
   * @return eventTimeColumn
  **/
  @ApiModelProperty(example = "time", value = "This column will be used as the event time if specified")
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
   * The timezone of the &#x60;TimestampColumn&#x60;
   * @return eventTimeTimezone
  **/
  @ApiModelProperty(example = "UTC", value = "The timezone of the `TimestampColumn`")
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
   * The max size a stream can grow. Defaulted to 10 GiB
   * @return logstoreRetentionBytes
  **/
  @ApiModelProperty(example = "10737418240", value = "The max size a stream can grow. Defaulted to 10 GiB")
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
   * The max time the data can be retained in the stream. Defaulted to 7 days
   * @return logstoreRetentionMs
  **/
  @ApiModelProperty(example = "604800000", value = "The max time the data can be retained in the stream. Defaulted to 7 days")
  public Integer getLogstoreRetentionMs() {
    return logstoreRetentionMs;
  }

  public void setLogstoreRetentionMs(Integer logstoreRetentionMs) {
    this.logstoreRetentionMs = logstoreRetentionMs;
  }

  public StreamDef mode(String mode) {
    this.mode = mode;
    return this;
  }

   /**
   * Storage mode of stream. Possible values: &#x60;append&#x60;, &#x60;changelog&#x60;, &#x60;changelog_kv&#x60;, &#x60;versioned_kv&#x60;
   * @return mode
  **/
  @ApiModelProperty(example = "append", value = "Storage mode of stream. Possible values: `append`, `changelog`, `changelog_kv`, `versioned_kv`")
  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public StreamDef name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Stream name should only contain a maximum of 64 letters, numbers, or _, and start with a letter
   * @return name
  **/
  @ApiModelProperty(example = "test_stream", required = true, value = "Stream name should only contain a maximum of 64 letters, numbers, or _, and start with a letter")
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

  public StreamDef primaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
    return this;
  }

   /**
   * Expression of primary key, required in &#x60;changelog_kv&#x60;&#x60; and &#x60;versioned_kv&#x60;&#x60; mode
   * @return primaryKey
  **/
  @ApiModelProperty(value = "Expression of primary key, required in `changelog_kv`` and `versioned_kv`` mode")
  public String getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
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
        Objects.equals(this.description, streamDef.description) &&
        Objects.equals(this.eventTimeColumn, streamDef.eventTimeColumn) &&
        Objects.equals(this.eventTimeTimezone, streamDef.eventTimeTimezone) &&
        Objects.equals(this.logstoreRetentionBytes, streamDef.logstoreRetentionBytes) &&
        Objects.equals(this.logstoreRetentionMs, streamDef.logstoreRetentionMs) &&
        Objects.equals(this.mode, streamDef.mode) &&
        Objects.equals(this.name, streamDef.name) &&
        Objects.equals(this.orderByExpression, streamDef.orderByExpression) &&
        Objects.equals(this.orderByGranularity, streamDef.orderByGranularity) &&
        Objects.equals(this.partitionByGranularity, streamDef.partitionByGranularity) &&
        Objects.equals(this.primaryKey, streamDef.primaryKey) &&
        Objects.equals(this.replicationFactor, streamDef.replicationFactor) &&
        Objects.equals(this.shards, streamDef.shards) &&
        Objects.equals(this.ttlExpression, streamDef.ttlExpression);
  }

  @Override
  public int hashCode() {
    return Objects.hash(columns, description, eventTimeColumn, eventTimeTimezone, logstoreRetentionBytes, logstoreRetentionMs, mode, name, orderByExpression, orderByGranularity, partitionByGranularity, primaryKey, replicationFactor, shards, ttlExpression);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StreamDef {\n");
    
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    eventTimeColumn: ").append(toIndentedString(eventTimeColumn)).append("\n");
    sb.append("    eventTimeTimezone: ").append(toIndentedString(eventTimeTimezone)).append("\n");
    sb.append("    logstoreRetentionBytes: ").append(toIndentedString(logstoreRetentionBytes)).append("\n");
    sb.append("    logstoreRetentionMs: ").append(toIndentedString(logstoreRetentionMs)).append("\n");
    sb.append("    mode: ").append(toIndentedString(mode)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    orderByExpression: ").append(toIndentedString(orderByExpression)).append("\n");
    sb.append("    orderByGranularity: ").append(toIndentedString(orderByGranularity)).append("\n");
    sb.append("    partitionByGranularity: ").append(toIndentedString(partitionByGranularity)).append("\n");
    sb.append("    primaryKey: ").append(toIndentedString(primaryKey)).append("\n");
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

