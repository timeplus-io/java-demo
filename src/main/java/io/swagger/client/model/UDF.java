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
import io.swagger.client.model.Owner;
import io.swagger.client.model.UDFArgument;
import io.swagger.client.model.UDFAuthContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UDF
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class UDF {
  @SerializedName("arguments")
  private List<UDFArgument> arguments = null;

  @SerializedName("auth_context")
  private UDFAuthContext authContext = null;

  @SerializedName("auth_method")
  private String authMethod = null;

  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("created_by")
  private Owner createdBy = null;

  @SerializedName("last_updated_at")
  private String lastUpdatedAt = null;

  @SerializedName("last_updated_by")
  private Owner lastUpdatedBy = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("return_type")
  private String returnType = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("url")
  private String url = null;

  public UDF arguments(List<UDFArgument> arguments) {
    this.arguments = arguments;
    return this;
  }

  public UDF addArgumentsItem(UDFArgument argumentsItem) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<UDFArgument>();
    }
    this.arguments.add(argumentsItem);
    return this;
  }

   /**
   * Get arguments
   * @return arguments
  **/
  @ApiModelProperty(value = "")
  public List<UDFArgument> getArguments() {
    return arguments;
  }

  public void setArguments(List<UDFArgument> arguments) {
    this.arguments = arguments;
  }

  public UDF authContext(UDFAuthContext authContext) {
    this.authContext = authContext;
    return this;
  }

   /**
   * Get authContext
   * @return authContext
  **/
  @ApiModelProperty(value = "")
  public UDFAuthContext getAuthContext() {
    return authContext;
  }

  public void setAuthContext(UDFAuthContext authContext) {
    this.authContext = authContext;
  }

  public UDF authMethod(String authMethod) {
    this.authMethod = authMethod;
    return this;
  }

   /**
   * Get authMethod
   * @return authMethod
  **/
  @ApiModelProperty(value = "")
  public String getAuthMethod() {
    return authMethod;
  }

  public void setAuthMethod(String authMethod) {
    this.authMethod = authMethod;
  }

  public UDF createdAt(String createdAt) {
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

  public UDF createdBy(Owner createdBy) {
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

  public UDF lastUpdatedAt(String lastUpdatedAt) {
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

  public UDF lastUpdatedBy(Owner lastUpdatedBy) {
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

  public UDF name(String name) {
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

  public UDF returnType(String returnType) {
    this.returnType = returnType;
    return this;
  }

   /**
   * Get returnType
   * @return returnType
  **/
  @ApiModelProperty(value = "")
  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public UDF type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UDF url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(value = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UDF UDF = (UDF) o;
    return Objects.equals(this.arguments, UDF.arguments) &&
        Objects.equals(this.authContext, UDF.authContext) &&
        Objects.equals(this.authMethod, UDF.authMethod) &&
        Objects.equals(this.createdAt, UDF.createdAt) &&
        Objects.equals(this.createdBy, UDF.createdBy) &&
        Objects.equals(this.lastUpdatedAt, UDF.lastUpdatedAt) &&
        Objects.equals(this.lastUpdatedBy, UDF.lastUpdatedBy) &&
        Objects.equals(this.name, UDF.name) &&
        Objects.equals(this.returnType, UDF.returnType) &&
        Objects.equals(this.type, UDF.type) &&
        Objects.equals(this.url, UDF.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arguments, authContext, authMethod, createdAt, createdBy, lastUpdatedAt, lastUpdatedBy, name, returnType, type, url);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UDF {\n");
    
    sb.append("    arguments: ").append(toIndentedString(arguments)).append("\n");
    sb.append("    authContext: ").append(toIndentedString(authContext)).append("\n");
    sb.append("    authMethod: ").append(toIndentedString(authMethod)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    lastUpdatedAt: ").append(toIndentedString(lastUpdatedAt)).append("\n");
    sb.append("    lastUpdatedBy: ").append(toIndentedString(lastUpdatedBy)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    returnType: ").append(toIndentedString(returnType)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

