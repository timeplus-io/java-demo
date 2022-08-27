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
 * QueryPipelineNodeMetric
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class QueryPipelineNodeMetric {
  @SerializedName("processed_bytes")
  private Integer processedBytes = null;

  @SerializedName("processing_time_ns")
  private Integer processingTimeNs = null;

  public QueryPipelineNodeMetric processedBytes(Integer processedBytes) {
    this.processedBytes = processedBytes;
    return this;
  }

   /**
   * Get processedBytes
   * @return processedBytes
  **/
  @ApiModelProperty(value = "")
  public Integer getProcessedBytes() {
    return processedBytes;
  }

  public void setProcessedBytes(Integer processedBytes) {
    this.processedBytes = processedBytes;
  }

  public QueryPipelineNodeMetric processingTimeNs(Integer processingTimeNs) {
    this.processingTimeNs = processingTimeNs;
    return this;
  }

   /**
   * Get processingTimeNs
   * @return processingTimeNs
  **/
  @ApiModelProperty(value = "")
  public Integer getProcessingTimeNs() {
    return processingTimeNs;
  }

  public void setProcessingTimeNs(Integer processingTimeNs) {
    this.processingTimeNs = processingTimeNs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryPipelineNodeMetric queryPipelineNodeMetric = (QueryPipelineNodeMetric) o;
    return Objects.equals(this.processedBytes, queryPipelineNodeMetric.processedBytes) &&
        Objects.equals(this.processingTimeNs, queryPipelineNodeMetric.processingTimeNs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processedBytes, processingTimeNs);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryPipelineNodeMetric {\n");
    
    sb.append("    processedBytes: ").append(toIndentedString(processedBytes)).append("\n");
    sb.append("    processingTimeNs: ").append(toIndentedString(processingTimeNs)).append("\n");
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

