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
import io.swagger.client.model.Connection;
import io.swagger.client.model.Owner;
import io.swagger.client.model.SourceMetrics;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SourceWithMetrics
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-02-05T18:48:55.783Z")
public class SourceWithMetrics {
  @SerializedName("connection")
  private Connection connection = null;

  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("created_by")
  private Owner createdBy = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("last_updated_at")
  private String lastUpdatedAt = null;

  @SerializedName("last_updated_by")
  private Owner lastUpdatedBy = null;

  @SerializedName("metrics")
  private SourceMetrics metrics = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("properties")
  private Map<String, Object> properties = new HashMap<String, Object>();

  @SerializedName("start_time")
  private Integer startTime = null;

  @SerializedName("type")
  private String type = null;

  public SourceWithMetrics connection(Connection connection) {
    this.connection = connection;
    return this;
  }

   /**
   * Get connection
   * @return connection
  **/
  @ApiModelProperty(required = true, value = "")
  public Connection getConnection() {
    return connection;
  }

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  public SourceWithMetrics createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public SourceWithMetrics createdBy(Owner createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * Get createdBy
   * @return createdBy
  **/
  @ApiModelProperty(value = "")
  public Owner getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Owner createdBy) {
    this.createdBy = createdBy;
  }

  public SourceWithMetrics description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public SourceWithMetrics id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SourceWithMetrics lastUpdatedAt(String lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
    return this;
  }

   /**
   * Get lastUpdatedAt
   * @return lastUpdatedAt
  **/
  @ApiModelProperty(value = "")
  public String getLastUpdatedAt() {
    return lastUpdatedAt;
  }

  public void setLastUpdatedAt(String lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
  }

  public SourceWithMetrics lastUpdatedBy(Owner lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
    return this;
  }

   /**
   * Get lastUpdatedBy
   * @return lastUpdatedBy
  **/
  @ApiModelProperty(value = "")
  public Owner getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(Owner lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public SourceWithMetrics metrics(SourceMetrics metrics) {
    this.metrics = metrics;
    return this;
  }

   /**
   * Get metrics
   * @return metrics
  **/
  @ApiModelProperty(required = true, value = "")
  public SourceMetrics getMetrics() {
    return metrics;
  }

  public void setMetrics(SourceMetrics metrics) {
    this.metrics = metrics;
  }

  public SourceWithMetrics name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SourceWithMetrics properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  public SourceWithMetrics putPropertiesItem(String key, Object propertiesItem) {
    this.properties.put(key, propertiesItem);
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(required = true, value = "")
  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  public SourceWithMetrics startTime(Integer startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getStartTime() {
    return startTime;
  }

  public void setStartTime(Integer startTime) {
    this.startTime = startTime;
  }

  public SourceWithMetrics type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceWithMetrics sourceWithMetrics = (SourceWithMetrics) o;
    return Objects.equals(this.connection, sourceWithMetrics.connection) &&
        Objects.equals(this.createdAt, sourceWithMetrics.createdAt) &&
        Objects.equals(this.createdBy, sourceWithMetrics.createdBy) &&
        Objects.equals(this.description, sourceWithMetrics.description) &&
        Objects.equals(this.id, sourceWithMetrics.id) &&
        Objects.equals(this.lastUpdatedAt, sourceWithMetrics.lastUpdatedAt) &&
        Objects.equals(this.lastUpdatedBy, sourceWithMetrics.lastUpdatedBy) &&
        Objects.equals(this.metrics, sourceWithMetrics.metrics) &&
        Objects.equals(this.name, sourceWithMetrics.name) &&
        Objects.equals(this.properties, sourceWithMetrics.properties) &&
        Objects.equals(this.startTime, sourceWithMetrics.startTime) &&
        Objects.equals(this.type, sourceWithMetrics.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connection, createdAt, createdBy, description, id, lastUpdatedAt, lastUpdatedBy, metrics, name, properties, startTime, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceWithMetrics {\n");
    
    sb.append("    connection: ").append(toIndentedString(connection)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastUpdatedAt: ").append(toIndentedString(lastUpdatedAt)).append("\n");
    sb.append("    lastUpdatedBy: ").append(toIndentedString(lastUpdatedBy)).append("\n");
    sb.append("    metrics: ").append(toIndentedString(metrics)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

