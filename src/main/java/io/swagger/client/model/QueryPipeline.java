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
import io.swagger.client.model.QueryPipelineEdge;
import io.swagger.client.model.QueryPipelineNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryPipeline
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2022-08-27T00:55:54.866Z")
public class QueryPipeline {
  @SerializedName("edges")
  private List<QueryPipelineEdge> edges = null;

  @SerializedName("nodes")
  private List<QueryPipelineNode> nodes = null;

  public QueryPipeline edges(List<QueryPipelineEdge> edges) {
    this.edges = edges;
    return this;
  }

  public QueryPipeline addEdgesItem(QueryPipelineEdge edgesItem) {
    if (this.edges == null) {
      this.edges = new ArrayList<QueryPipelineEdge>();
    }
    this.edges.add(edgesItem);
    return this;
  }

   /**
   * Get edges
   * @return edges
  **/
  @ApiModelProperty(value = "")
  public List<QueryPipelineEdge> getEdges() {
    return edges;
  }

  public void setEdges(List<QueryPipelineEdge> edges) {
    this.edges = edges;
  }

  public QueryPipeline nodes(List<QueryPipelineNode> nodes) {
    this.nodes = nodes;
    return this;
  }

  public QueryPipeline addNodesItem(QueryPipelineNode nodesItem) {
    if (this.nodes == null) {
      this.nodes = new ArrayList<QueryPipelineNode>();
    }
    this.nodes.add(nodesItem);
    return this;
  }

   /**
   * Get nodes
   * @return nodes
  **/
  @ApiModelProperty(value = "")
  public List<QueryPipelineNode> getNodes() {
    return nodes;
  }

  public void setNodes(List<QueryPipelineNode> nodes) {
    this.nodes = nodes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryPipeline queryPipeline = (QueryPipeline) o;
    return Objects.equals(this.edges, queryPipeline.edges) &&
        Objects.equals(this.nodes, queryPipeline.nodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(edges, nodes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryPipeline {\n");
    
    sb.append("    edges: ").append(toIndentedString(edges)).append("\n");
    sb.append("    nodes: ").append(toIndentedString(nodes)).append("\n");
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

