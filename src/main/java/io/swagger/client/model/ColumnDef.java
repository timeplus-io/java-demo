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
import java.io.IOException;

/**
 * ColumnDef
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-02-05T18:48:55.783Z")
public class ColumnDef {
  @SerializedName("compression_codec")
  private String compressionCodec = null;

  @SerializedName("default")
  private String _default = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("skipping_index_expression")
  private String skippingIndexExpression = null;

  @SerializedName("ttl_expression")
  private String ttlExpression = null;

  @SerializedName("type")
  private String type = null;

  public ColumnDef compressionCodec(String compressionCodec) {
    this.compressionCodec = compressionCodec;
    return this;
  }

   /**
   * Get compressionCodec
   * @return compressionCodec
  **/
  @ApiModelProperty(value = "")
  public String getCompressionCodec() {
    return compressionCodec;
  }

  public void setCompressionCodec(String compressionCodec) {
    this.compressionCodec = compressionCodec;
  }

  public ColumnDef _default(String _default) {
    this._default = _default;
    return this;
  }

   /**
   * Get _default
   * @return _default
  **/
  @ApiModelProperty(value = "")
  public String getDefault() {
    return _default;
  }

  public void setDefault(String _default) {
    this._default = _default;
  }

  public ColumnDef name(String name) {
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

  public ColumnDef skippingIndexExpression(String skippingIndexExpression) {
    this.skippingIndexExpression = skippingIndexExpression;
    return this;
  }

   /**
   * Get skippingIndexExpression
   * @return skippingIndexExpression
  **/
  @ApiModelProperty(value = "")
  public String getSkippingIndexExpression() {
    return skippingIndexExpression;
  }

  public void setSkippingIndexExpression(String skippingIndexExpression) {
    this.skippingIndexExpression = skippingIndexExpression;
  }

  public ColumnDef ttlExpression(String ttlExpression) {
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

  public ColumnDef type(String type) {
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
    ColumnDef columnDef = (ColumnDef) o;
    return Objects.equals(this.compressionCodec, columnDef.compressionCodec) &&
        Objects.equals(this._default, columnDef._default) &&
        Objects.equals(this.name, columnDef.name) &&
        Objects.equals(this.skippingIndexExpression, columnDef.skippingIndexExpression) &&
        Objects.equals(this.ttlExpression, columnDef.ttlExpression) &&
        Objects.equals(this.type, columnDef.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(compressionCodec, _default, name, skippingIndexExpression, ttlExpression, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ColumnDef {\n");
    
    sb.append("    compressionCodec: ").append(toIndentedString(compressionCodec)).append("\n");
    sb.append("    _default: ").append(toIndentedString(_default)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    skippingIndexExpression: ").append(toIndentedString(skippingIndexExpression)).append("\n");
    sb.append("    ttlExpression: ").append(toIndentedString(ttlExpression)).append("\n");
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

