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
import io.swagger.client.model.Latency;
import io.swagger.client.model.Throughput;
import java.io.IOException;

/**
 * QueryMetrics
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class QueryMetrics {
  @SerializedName("count")
  private Integer count = null;

  @SerializedName("latency")
  private Latency latency = null;

  @SerializedName("throughput")
  private Throughput throughput = null;

  public QueryMetrics count(Integer count) {
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @ApiModelProperty(value = "")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public QueryMetrics latency(Latency latency) {
    this.latency = latency;
    return this;
  }

   /**
   * Get latency
   * @return latency
  **/
  @ApiModelProperty(value = "")
  public Latency getLatency() {
    return latency;
  }

  public void setLatency(Latency latency) {
    this.latency = latency;
  }

  public QueryMetrics throughput(Throughput throughput) {
    this.throughput = throughput;
    return this;
  }

   /**
   * Get throughput
   * @return throughput
  **/
  @ApiModelProperty(value = "")
  public Throughput getThroughput() {
    return throughput;
  }

  public void setThroughput(Throughput throughput) {
    this.throughput = throughput;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryMetrics queryMetrics = (QueryMetrics) o;
    return Objects.equals(this.count, queryMetrics.count) &&
        Objects.equals(this.latency, queryMetrics.latency) &&
        Objects.equals(this.throughput, queryMetrics.throughput);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, latency, throughput);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryMetrics {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    latency: ").append(toIndentedString(latency)).append("\n");
    sb.append("    throughput: ").append(toIndentedString(throughput)).append("\n");
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

