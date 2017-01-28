package com.practice.alogs.graphs;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class GraphException extends Exception {

    private String message;
    private Type type;

    public enum Type {
        GRAPH_CYCLE,
        DEFAULT
    }

    public GraphException(String message) {
        super(message);
        this.message = message;
        this.type = Type.DEFAULT;
    }

    public GraphException(Type type, String message) {
        super(message);
        this.message = message;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GraphException{" + "message='" + message + '\'' + ", type=" + type + '}';
    }
}
