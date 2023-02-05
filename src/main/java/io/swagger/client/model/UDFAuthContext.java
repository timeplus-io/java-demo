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
 * UDFAuthContext
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-02-05T18:48:55.783Z")
public class UDFAuthContext {
  @SerializedName("key_name")
  private String keyName = null;

  @SerializedName("key_value")
  private String keyValue = null;

  public UDFAuthContext keyName(String keyName) {
    this.keyName = keyName;
    return this;
  }

   /**
   * Get keyName
   * @return keyName
  **/
  @ApiModelProperty(value = "")
  public String getKeyName() {
    return keyName;
  }

  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  public UDFAuthContext keyValue(String keyValue) {
    this.keyValue = keyValue;
    return this;
  }

   /**
   * Get keyValue
   * @return keyValue
  **/
  @ApiModelProperty(value = "")
  public String getKeyValue() {
    return keyValue;
  }

  public void setKeyValue(String keyValue) {
    this.keyValue = keyValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UDFAuthContext udFAuthContext = (UDFAuthContext) o;
    return Objects.equals(this.keyName, udFAuthContext.keyName) &&
        Objects.equals(this.keyValue, udFAuthContext.keyValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyName, keyValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UDFAuthContext {\n");
    
    sb.append("    keyName: ").append(toIndentedString(keyName)).append("\n");
    sb.append("    keyValue: ").append(toIndentedString(keyValue)).append("\n");
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

