package com.practice.algos.graphs;

import com.practice.alogs.graphs.GraphException;
import com.practice.alogs.graphs.GraphNode;
import com.practice.alogs.graphs.GraphTraversal;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sunilchandani on 23/01/17.
 */
public class GraphTraversalTest {

    /*
            Root    =>  1   2   3
            1       =>  5
            2       =>  5   4   Root
            3       =>  4
            4       =>  5
            5       =>
     */

    private GraphNode<String> rootNode;
    private List<GraphNode<String>> graphNodes;
    private GraphTraversal graphTraversal;

    @Before
    public void constructGraph() {
        rootNode = new GraphNode<String>("root");
        GraphNode<String> node1 = new GraphNode<String>("1");
        GraphNode<String> node2 = new GraphNode<String>("2");
        GraphNode<String> node3 = new GraphNode<String>("3");
        GraphNode<String> node4 = new GraphNode<String>("4");
        GraphNode<String> node5 = new GraphNode<String>("5");

        rootNode.addAdjacentNode(node1); rootNode.addAdjacentNode(node2); rootNode.addAdjacentNode(node3);
        node1.addAdjacentNode(node5); node2.addAdjacentNode(node5); node2.addAdjacentNode(node4);
        node3.addAdjacentNode(node4); node4.addAdjacentNode(node5);

        graphNodes = new ArrayList<GraphNode<String>>();
        graphNodes.add(node3); graphNodes.add(node4); graphNodes.add(node5);
        graphNodes.add(rootNode); graphNodes.add(node1); graphNodes.add(node2);

        graphTraversal = new GraphTraversal();
    }

    @Test
    public void shouldTestDFS() {
        List<GraphNode<String>> traversedNodes = graphTraversal.dfs(rootNode);
        assertDFSResultSet(traversedNodes);
    }

    @Test
    public void shouldTestDFSRecursive() throws GraphException {
        List<GraphNode<String>> traversedNodes = graphTraversal.dfsRecursive(rootNode);
        assertDFSResultSet(traversedNodes);
    }

    private void assertDFSResultSet(List<GraphNode<String>> traversedNodes) {
        assertEquals(6, traversedNodes.size());
        assertEquals("root", traversedNodes.get(0).getData());
        assertEquals("1", traversedNodes.get(1).getData());
        assertEquals("5", traversedNodes.get(2).getData());
        assertEquals("2", traversedNodes.get(3).getData());
        assertEquals("4", traversedNodes.get(4).getData());
        assertEquals("3", traversedNodes.get(5).getData());
    }

    @Test
    public void shouldTestBFS() throws GraphException {
        List<GraphNode<String>> traversedNodes = graphTraversal.bfs(rootNode);
        assertEquals(6, traversedNodes.size());

        assertEquals("root", traversedNodes.get(0).getData());
        assertEquals("1", traversedNodes.get(1).getData());
        assertEquals("2", traversedNodes.get(2).getData());
        assertEquals("3", traversedNodes.get(3).getData());
        assertEquals("5", traversedNodes.get(4).getData());
        assertEquals("4", traversedNodes.get(5).getData());
    }

    @Test
    public void shouldTestCycleInGraphWithNullNode() throws GraphException {
        graphTraversal.validateCycleInGraph(null);
    }

    @Test
    public void shouldTestCycleInGraphWithoutCycle() throws GraphException {
        graphTraversal.validateCycleInGraph(rootNode);
    }

    public void constructGraphForCycleValidation(boolean multipleCycle) {
        GraphNode<String> node1 = new GraphNode<String>("6");
        GraphNode<String> node2 = new GraphNode<String>("7");
        GraphNode<String> node3 = new GraphNode<String>("8");

        rootNode.addAdjacentNode(node1);
        node1.addAdjacentNode(node2);
        node2.addAdjacentNode(node3);
        node3.addAdjacentNode(rootNode);
        if(multipleCycle) node2.addAdjacentNode(node1);
    }

    @Test (expected = GraphException.class)
    public void shouldTestCycleInGraphWithSingleSelfCycleNode() throws GraphException {
        GraphNode<String> node = new GraphNode<String>("1");
        node.addAdjacentNode(node);
        graphTraversal.validateCycleInGraph(node);
    }

    @Test (expected = GraphException.class)
    public void shouldTestCycleInGraphWithSingleCycle() throws GraphException {
        constructGraphForCycleValidation(false);
        graphTraversal.validateCycleInGraph(rootNode);
    }

    @Test ( expected = GraphException.class )
    public void shouldTestCycleInGraphWithMultipleCycle() throws GraphException {
        constructGraphForCycleValidation(true);
        graphTraversal.validateCycleInGraph(rootNode);
    }

    @Test
    public void shouldTestStronglyConnectedComponents() {
        GraphNode<String> node1 = new GraphNode<String>("s");
        GraphNode<String> node2 = new GraphNode<String>("t");
        GraphNode<String> node3 = new GraphNode<String>("u");
        node1.addAdjacentNode(node2); node1.addAdjacentNode(node3);
        graphNodes.clear(); graphNodes.add(node2); graphNodes.add(node3); graphNodes.add(node1);
        List<Set<String>> scc = graphTraversal.getStronglyConnectedComponents(graphNodes);
        System.out.println(scc);
    }

    @Test (expected = GraphException.class)
    public void shouldTestTopologicalSortWithGraphMetricsHavingCycle() throws GraphException {
        boolean graph[][] = new boolean[][] {
                {false, true}, {true, false}
        };

        graphTraversal.topologicalSort(graph, 2);
    }

}
