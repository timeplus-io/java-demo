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
 * SourceMetrics
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class SourceMetrics {
  @SerializedName("failed_write_count")
  private Integer failedWriteCount = null;

  @SerializedName("read_count")
  private Integer readCount = null;

  @SerializedName("success_write_count")
  private Integer successWriteCount = null;

  public SourceMetrics failedWriteCount(Integer failedWriteCount) {
    this.failedWriteCount = failedWriteCount;
    return this;
  }

   /**
   * Get failedWriteCount
   * @return failedWriteCount
  **/
  @ApiModelProperty(value = "")
  public Integer getFailedWriteCount() {
    return failedWriteCount;
  }

  public void setFailedWriteCount(Integer failedWriteCount) {
    this.failedWriteCount = failedWriteCount;
  }

  public SourceMetrics readCount(Integer readCount) {
    this.readCount = readCount;
    return this;
  }

   /**
   * Get readCount
   * @return readCount
  **/
  @ApiModelProperty(value = "")
  public Integer getReadCount() {
    return readCount;
  }

  public void setReadCount(Integer readCount) {
    this.readCount = readCount;
  }

  public SourceMetrics successWriteCount(Integer successWriteCount) {
    this.successWriteCount = successWriteCount;
    return this;
  }

   /**
   * Get successWriteCount
   * @return successWriteCount
  **/
  @ApiModelProperty(value = "")
  public Integer getSuccessWriteCount() {
    return successWriteCount;
  }

  public void setSuccessWriteCount(Integer successWriteCount) {
    this.successWriteCount = successWriteCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceMetrics sourceMetrics = (SourceMetrics) o;
    return Objects.equals(this.failedWriteCount, sourceMetrics.failedWriteCount) &&
        Objects.equals(this.readCount, sourceMetrics.readCount) &&
        Objects.equals(this.successWriteCount, sourceMetrics.successWriteCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(failedWriteCount, readCount, successWriteCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceMetrics {\n");
    
    sb.append("    failedWriteCount: ").append(toIndentedString(failedWriteCount)).append("\n");
    sb.append("    readCount: ").append(toIndentedString(readCount)).append("\n");
    sb.append("    successWriteCount: ").append(toIndentedString(successWriteCount)).append("\n");
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

