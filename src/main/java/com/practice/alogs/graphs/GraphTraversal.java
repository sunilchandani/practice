package com.practice.alogs.graphs;

import java.util.*;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class GraphTraversal<T> {

    public List<GraphNode<T>> bfs(GraphNode<T> rootNode) {
        List<GraphNode<T>> traversedNodes = new ArrayList<GraphNode<T>>();
        if(null == rootNode) return traversedNodes;

        Queue<GraphNode<T>> toBeTraversed = new LinkedList<GraphNode<T>>();
        toBeTraversed.add(rootNode);

        GraphNode<T> node;
        while(!toBeTraversed.isEmpty()) {
            node = toBeTraversed.poll();

            if(traversedNodes.contains(node)) continue;
            traversedNodes.add(node);

            for(GraphNode<T> adjacentNode : node.getAdjacentNodes()) {
                if(!traversedNodes.contains(adjacentNode)) {
                    toBeTraversed.add(adjacentNode);
                }
            }
        }

        return traversedNodes;
    }

    public List<GraphNode<T>> dfs(GraphNode<T> rootNode) {
        List<GraphNode<T>> traversedNodes = new ArrayList<GraphNode<T>>();
        if(null == rootNode) return traversedNodes;

        Stack<GraphNode<T>> toBeProcessed = new Stack<GraphNode<T>>();
        toBeProcessed.add(rootNode);

        GraphNode<T> node;

        while (!toBeProcessed.empty()) {
            node = toBeProcessed.pop();
            traversedNodes.add(node);

            for(int i=node.getAdjacentNodes().size() - 1 ; i >= 0; i--) {
                if(!traversedNodes.contains(node.getAdjacentNodes().get(i))) {
                    toBeProcessed.add(node.getAdjacentNodes().get(i));
                }
            }
        }

        return traversedNodes;
    }

    public List<GraphNode<T>> dfsRecursive(GraphNode<T> rootNode) throws GraphException {
        List<GraphNode<T>> traversedNodes = new ArrayList<GraphNode<T>>();
        dfs(rootNode, traversedNodes, null);
        return traversedNodes;
    }

    public void validateCycleInGraph(GraphNode<T> rootNode) throws GraphException {
        try {
            dfs(rootNode, new ArrayList<GraphNode<T>>(), new Stack<GraphNode<T>>());
        } catch (GraphException e) {
            if(e.getType() == GraphException.Type.GRAPH_CYCLE) throw e;
            else e.printStackTrace();
        }
    }

    private void dfs(GraphNode<T> node, List<GraphNode<T>> traversedNodes,
                     List<GraphNode<T>> processedNodeChain) throws GraphException {

        if(null == node) return;

        if(null != processedNodeChain) {
            if(processedNodeChain.contains(node)) {
                throw new GraphException(GraphException.Type.GRAPH_CYCLE, "CYCLE EXISTSNode already exists in the processed chain.");
            }
            processedNodeChain.add(node);
        }

        if(!traversedNodes.contains(node)) {
            traversedNodes.add(node);

            for(GraphNode<T> adjacentNode : node.getAdjacentNodes()) {
                dfs(adjacentNode, traversedNodes, processedNodeChain);
            }
        }

        if(null != processedNodeChain) processedNodeChain.remove(processedNodeChain.size() - 1);
    }

    public List<Integer> topologicalSort(boolean graphMetrics[][], int numNodes) throws GraphException {
        List<Integer> tpSortElements = new ArrayList<Integer>();
        int [][]inOutEdgeCount = new int[numNodes][]; // 1 => incoming edge count, 0 => outgoing edge count.
        for(int i=0; i<numNodes; i++) {
            inOutEdgeCount[i] = new int[]{0, 0};
        }

        for(int i=0; i<numNodes; i++) {
            for(int j=0; j<numNodes; j++) {
                if(i==j && graphMetrics[i][j])
                    throw new GraphException(GraphException.Type.GRAPH_CYCLE, "Cycle Exists in given Graph.");
                if(graphMetrics[i][j]) {
                    inOutEdgeCount[i][0]++;
                    inOutEdgeCount[j][1]++;
                }
            }
        }

        for(int i=0; i< numNodes; i++) {
            int nextNode = findNextTPNode(inOutEdgeCount, numNodes);
            tpSortElements.add(nextNode);
            for(int j=0; j<numNodes; j++) {
                if(graphMetrics[nextNode][j]) {
                    inOutEdgeCount[nextNode][0]--;
                    inOutEdgeCount[j][1]--;
                }
            }
        }

        return tpSortElements;
    }

    private int findNextTPNode(int inOutEdgeCount[][], int numNodes) throws GraphException {
        int nextNode = -1;
        for(int i=0; i<numNodes; i++) {
            if(inOutEdgeCount[i][1]==0) {
                nextNode = -1;
                break;
            }
        }
        if(nextNode==-1) throw new GraphException(GraphException.Type.GRAPH_CYCLE, "No Node exists with ZERO incoming nodes.");
        return nextNode;
    }

    // ??
    public List<GraphNode<T>> topologicalSort(List<List<GraphNode<T>>> stronglyConnectedComponents) throws GraphException {
        List<GraphNode<T>> topologicallySortNodes = new ArrayList<GraphNode<T>>();
        return topologicallySortNodes;
    }

    // 0 => incoming edge count, 1 => outgoing edge count.
    private void formNodeEdgesMap(GraphNode<T> node, GraphNode<T> parent, Map<GraphNode<T>, GraphNode[]> nodeEdgesMap) {
        if(!nodeEdgesMap.containsKey(node)) {
            nodeEdgesMap.put(node, new GraphNode[2]);
        }

        //if(null != parent) nodeEdgesMap.get(node)[0] = nodeEdgesMap.get(node)[0] + 1;
        for(GraphNode<T> adjacentNode : node.getAdjacentNodes()) {
            formNodeEdgesMap(adjacentNode, node, nodeEdgesMap);
        }
    }

    public List<Set<GraphNode<T>>> getStronglyConnectedComponents(List<GraphNode<T>> graphNodes) {
        List<Set<GraphNode<T>>> resultedScc = new ArrayList<Set<GraphNode<T>>>();
        Set<GraphNode<T>> traversedNodes = new HashSet<GraphNode<T>>();

        List<GraphNode<T>> componentsNodes;

        for(GraphNode<T> node : graphNodes) {
            if(!traversedNodes.contains(node)) {
                componentsNodes = dfs(node);
                traversedNodes.addAll(componentsNodes);
                Iterator<Set<GraphNode<T>>> iterator = resultedScc.iterator();

                while (iterator.hasNext()) {
                    Set<GraphNode<T>> scc = iterator.next();
                    List<GraphNode<T>> temp = new ArrayList<GraphNode<T>>(componentsNodes);
                    temp.retainAll(scc);
                    if(temp.size() > 0) {
                        componentsNodes.addAll(scc);
                        iterator.remove();
                    }
                }

                resultedScc.add(new HashSet<GraphNode<T>>(componentsNodes));
            }
        }

        return resultedScc;
    }
}
