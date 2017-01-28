package com.practice.algos.graphs;

import com.practice.alogs.graphs.TreeNode;
import com.practice.alogs.graphs.TreeTraversal;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class TreeTraversalTest {

    /*      Sample Tree
                        1
               2                3
           4      10                5
                11  12            6   7
     */

    TreeNode<Long> root;
    TreeTraversal<Long> treeTraversal;

    @Before
    public void setup() {
        TreeNode<Long> node5 = new TreeNode<Long>(5l, new TreeNode<Long>(6l), new TreeNode<Long>(7l));
        TreeNode<Long> node3 = new TreeNode<Long>(3l, null, node5);
        TreeNode<Long> node10 = new TreeNode<Long>(10l, new TreeNode<Long>(11l), new TreeNode<Long>(12l));
        TreeNode<Long> node2 = new TreeNode<Long>(2l, new TreeNode<Long>(4l), node10);
        root = new TreeNode<Long>(1l, node2, node3);
        treeTraversal = new TreeTraversal<Long>();
    }

    private void assertInOrderTraversal(List<Long> traversedNodes) {
        // 4 2 11 10 12 1 3 6 5 7
        assertEquals(10, traversedNodes.size());
        assertEquals((Long) 4l, traversedNodes.get(0));
        assertEquals((Long) 2l, traversedNodes.get(1));
        assertEquals((Long) 11l, traversedNodes.get(2));
        assertEquals((Long) 10l, traversedNodes.get(3));
        assertEquals((Long) 12l, traversedNodes.get(4));
        assertEquals((Long) 1l, traversedNodes.get(5));
        assertEquals((Long) 3l, traversedNodes.get(6));
        assertEquals((Long) 6l, traversedNodes.get(7));
        assertEquals((Long) 5l, traversedNodes.get(8));
        assertEquals((Long) 7l, traversedNodes.get(9));
    }
    private void assertPreOrderTraversal(List<Long> traversedNodes) {
        // 1 2 4 10 11 12 3 5 6 7
        assertEquals(10, traversedNodes.size());
        assertEquals((Long) 1l, traversedNodes.get(0));
        assertEquals((Long) 2l, traversedNodes.get(1));
        assertEquals((Long) 4l, traversedNodes.get(2));
        assertEquals((Long) 10l, traversedNodes.get(3));
        assertEquals((Long) 11l, traversedNodes.get(4));
        assertEquals((Long) 12l, traversedNodes.get(5));
        assertEquals((Long) 3l, traversedNodes.get(6));
        assertEquals((Long) 5l, traversedNodes.get(7));
        assertEquals((Long) 6l, traversedNodes.get(8));
        assertEquals((Long) 7l, traversedNodes.get(9));
    }

    private void assertPostOrderTraversal(List<Long> traversedNodes) {
        // 4 11 12 10 2 6 7 5 3 1
        assertEquals(10, traversedNodes.size());
        assertEquals((Long) 4l, traversedNodes.get(0));
        assertEquals((Long) 11l, traversedNodes.get(1));
        assertEquals((Long) 12l, traversedNodes.get(2));
        assertEquals((Long) 10l, traversedNodes.get(3));
        assertEquals((Long) 2l, traversedNodes.get(4));
        assertEquals((Long) 6l, traversedNodes.get(5));
        assertEquals((Long) 7l, traversedNodes.get(6));
        assertEquals((Long) 5l, traversedNodes.get(7));
        assertEquals((Long) 3l, traversedNodes.get(8));
        assertEquals((Long) 1l, traversedNodes.get(9));
    }

    @Test
    public void shouldTestRecursiveInOrderTraversal() {
        assertInOrderTraversal(treeTraversal.inorderRecursive(root));
    }

    @Test
    public void shouldTestIterativeInOrderTraversal() {
        assertInOrderTraversal(treeTraversal.inorderIterative(root));
    }

    @Test
    public void shouldTestRecursivePreOrderTraversal() {
        assertPreOrderTraversal(treeTraversal.preOrderRecursive(root));
    }

    @Test
    public void shouldTestIterativePreOrderTraversal() {
        assertPreOrderTraversal(treeTraversal.preOrderIterative(root));
    }

    @Test
    public void shouldTestRecursivePostOrderTraversal() {
        assertPostOrderTraversal(treeTraversal.postOrderRecursive(root));
    }

    @Test
    public void shouldTestIterativePostOrderTraversal() {
        assertPostOrderTraversal(treeTraversal.postOrderIterative(root));
    }

}
