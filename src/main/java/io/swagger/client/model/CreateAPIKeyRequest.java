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
 * CreateAPIKeyRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class CreateAPIKeyRequest {
  @SerializedName("expire_at")
  private String expireAt = null;

  @SerializedName("expire_in")
  private String expireIn = null;

  @SerializedName("name")
  private String name = null;

  public CreateAPIKeyRequest expireAt(String expireAt) {
    this.expireAt = expireAt;
    return this;
  }

   /**
   * define the expiration time of the API key by specifying the exact date time, cannot use with expire_in
   * @return expireAt
  **/
  @ApiModelProperty(example = "2022-06-07T12:00:00Z08:00", value = "define the expiration time of the API key by specifying the exact date time, cannot use with expire_in")
  public String getExpireAt() {
    return expireAt;
  }

  public void setExpireAt(String expireAt) {
    this.expireAt = expireAt;
  }

  public CreateAPIKeyRequest expireIn(String expireIn) {
    this.expireIn = expireIn;
    return this;
  }

   /**
   * define the expiration time of the API key by specifying the amount of time to count from now, cannot use with expire_at
   * @return expireIn
  **/
  @ApiModelProperty(example = "24h", value = "define the expiration time of the API key by specifying the amount of time to count from now, cannot use with expire_at")
  public String getExpireIn() {
    return expireIn;
  }

  public void setExpireIn(String expireIn) {
    this.expireIn = expireIn;
  }

  public CreateAPIKeyRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * the name of the API key
   * @return name
  **/
  @ApiModelProperty(required = true, value = "the name of the API key")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAPIKeyRequest createAPIKeyRequest = (CreateAPIKeyRequest) o;
    return Objects.equals(this.expireAt, createAPIKeyRequest.expireAt) &&
        Objects.equals(this.expireIn, createAPIKeyRequest.expireIn) &&
        Objects.equals(this.name, createAPIKeyRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expireAt, expireIn, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAPIKeyRequest {\n");
    
    sb.append("    expireAt: ").append(toIndentedString(expireAt)).append("\n");
    sb.append("    expireIn: ").append(toIndentedString(expireIn)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

