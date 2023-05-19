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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DashboardPanel
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-02-05T18:48:55.783Z")
public class DashboardPanel {
  @SerializedName("description")
  private String description = null;

  @SerializedName("id")
  private String id = null;

  @SerializedName("position")
  private Map<String, Object> position = null;

  @SerializedName("title")
  private String title = null;

  @SerializedName("viz_config")
  private Map<String, Object> vizConfig = null;

  @SerializedName("viz_content")
  private String vizContent = null;

  @SerializedName("viz_type")
  private String vizType = null;

  public DashboardPanel description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DashboardPanel id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DashboardPanel position(Map<String, Object> position) {
    this.position = position;
    return this;
  }

  public DashboardPanel putPositionItem(String key, Object positionItem) {
    if (this.position == null) {
      this.position = new HashMap<String, Object>();
    }
    this.position.put(key, positionItem);
    return this;
  }

   /**
   * e.g. {\&quot;x\&quot;:0,\&quot;y\&quot;:0,\&quot;w\&quot;:6,\&quot;h\&quot;:2,\&quot;nextX\&quot;:6,\&quot;nextY\&quot;:2}
   * @return position
  **/
  @ApiModelProperty(value = "e.g. {\"x\":0,\"y\":0,\"w\":6,\"h\":2,\"nextX\":6,\"nextY\":2}")
  public Map<String, Object> getPosition() {
    return position;
  }

  public void setPosition(Map<String, Object> position) {
    this.position = position;
  }

  public DashboardPanel title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public DashboardPanel vizConfig(Map<String, Object> vizConfig) {
    this.vizConfig = vizConfig;
    return this;
  }

  public DashboardPanel putVizConfigItem(String key, Object vizConfigItem) {
    if (this.vizConfig == null) {
      this.vizConfig = new HashMap<String, Object>();
    }
    this.vizConfig.put(key, vizConfigItem);
    return this;
  }

   /**
   * The JSON configuration of the viz For chart, it is &#x60;{ \&quot;chart_type\&quot;: \&quot;line\&quot;, ...  }&#x60; For markdown, it is &#x60;{ \&quot;favour\&quot;: \&quot;github\&quot;, ...  }&#x60;
   * @return vizConfig
  **/
  @ApiModelProperty(value = "The JSON configuration of the viz For chart, it is `{ \"chart_type\": \"line\", ...  }` For markdown, it is `{ \"favour\": \"github\", ...  }`")
  public Map<String, Object> getVizConfig() {
    return vizConfig;
  }

  public void setVizConfig(Map<String, Object> vizConfig) {
    this.vizConfig = vizConfig;
  }

  public DashboardPanel vizContent(String vizContent) {
    this.vizContent = vizContent;
    return this;
  }

   /**
   * For chart, the viz_content is the SQL For markdown, the viz_content is the markdown itself
   * @return vizContent
  **/
  @ApiModelProperty(value = "For chart, the viz_content is the SQL For markdown, the viz_content is the markdown itself")
  public String getVizContent() {
    return vizContent;
  }

  public void setVizContent(String vizContent) {
    this.vizContent = vizContent;
  }

  public DashboardPanel vizType(String vizType) {
    this.vizType = vizType;
    return this;
  }

   /**
   * e.g. &#x60;chart&#x60;, &#x60;markdown&#x60;
   * @return vizType
  **/
  @ApiModelProperty(value = "e.g. `chart`, `markdown`")
  public String getVizType() {
    return vizType;
  }

  public void setVizType(String vizType) {
    this.vizType = vizType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DashboardPanel dashboardPanel = (DashboardPanel) o;
    return Objects.equals(this.description, dashboardPanel.description) &&
        Objects.equals(this.id, dashboardPanel.id) &&
        Objects.equals(this.position, dashboardPanel.position) &&
        Objects.equals(this.title, dashboardPanel.title) &&
        Objects.equals(this.vizConfig, dashboardPanel.vizConfig) &&
        Objects.equals(this.vizContent, dashboardPanel.vizContent) &&
        Objects.equals(this.vizType, dashboardPanel.vizType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, id, position, title, vizConfig, vizContent, vizType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DashboardPanel {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    vizConfig: ").append(toIndentedString(vizConfig)).append("\n");
    sb.append("    vizContent: ").append(toIndentedString(vizContent)).append("\n");
    sb.append("    vizType: ").append(toIndentedString(vizType)).append("\n");
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
