package com.practice.alogs.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class GraphNode<T> implements Comparable<GraphNode<T>> {
    private T data;
    private List<GraphNode<T>> adjacentNodes;

    // default one
    public GraphNode() {
        throw new RuntimeException("Do not use default constructor.");
    }
    public GraphNode(T data) {
        this.data = data;
        this.adjacentNodes = new ArrayList<GraphNode<T>>();
    }

    public GraphNode(T data, List<GraphNode<T>> adjacentNodes) {
        this.data = data;
        this.adjacentNodes = adjacentNodes;
    }

    public T getData() {
        return data;
    }

    public List<GraphNode<T>> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(GraphNode<T> node) {
        if(null != node) this.adjacentNodes.add(node);
    }

    @Override
    public String toString() {
        return "GraphNode{" + "data=" + data + ", adjacentNodes=" + adjacentNodes + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphNode)) return false;

        GraphNode<?> graphNode = (GraphNode<?>) o;

        if (!data.equals(graphNode.data)) return false;
        return adjacentNodes != null ? adjacentNodes.equals(graphNode.adjacentNodes) : graphNode.adjacentNodes == null;
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (adjacentNodes != null ? adjacentNodes.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(GraphNode<T> o) {
        return 0;
    }
}
