package com.practice.alogs.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class TreeTraversal<T> {

    public List<T> inorderRecursive(TreeNode<T> root) {
        List<T> traversedNodes = new ArrayList<T>();

        if(null==root) return traversedNodes;

        traversedNodes.addAll(inorderRecursive(root.getLeftChild()));
        traversedNodes.add(root.getData());
        traversedNodes.addAll(inorderRecursive(root.getRightChild()));
        return traversedNodes;
    }

    public List<T> inorderIterative(TreeNode<T> root) {
        List<T> traversedNodes = new ArrayList<T>();
        if(null==root) return traversedNodes;

        Stack<TreeNode<T>> toBeTraversedNodes = new Stack<TreeNode<T>>();
        toBeTraversedNodes.push(root);
        TreeNode<T> node = root.getLeftChild();

        while(null!=node || !toBeTraversedNodes.empty()) {
            if(null == node) {
                node = toBeTraversedNodes.pop();
                traversedNodes.add(node.getData());
                node = node.getRightChild();
            } else {
                toBeTraversedNodes.push(node);
                node = node.getLeftChild();
            }
        }

        return traversedNodes;
    }

    public List<T> preOrderRecursive(TreeNode<T> root) {
        List<T> traversedTreeNodes = new ArrayList<T>();

        if(null==root) return traversedTreeNodes;
        traversedTreeNodes.add(root.getData());
        traversedTreeNodes.addAll(preOrderRecursive(root.getLeftChild()));
        traversedTreeNodes.addAll(preOrderRecursive(root.getRightChild()));
        return traversedTreeNodes;
    }

    public List<T> preOrderIterative(TreeNode<T> root) {
        List<T> traversedNodes = new ArrayList<T>();
        if(null==root) return traversedNodes;

        Stack<TreeNode<T>> toBeProcessedNodes = new Stack<TreeNode<T>>();

        traversedNodes.add(root.getData());

        toBeProcessedNodes.push(root.getRightChild());
        toBeProcessedNodes.push(root.getLeftChild());

        while (!toBeProcessedNodes.empty()) {
            TreeNode<T> node = toBeProcessedNodes.pop();

            if(null==node) continue;
            traversedNodes.add(node.getData());

            toBeProcessedNodes.push(node.getRightChild());
            toBeProcessedNodes.push(node.getLeftChild());
        }

        return traversedNodes;
    }

    public List<T> postOrderRecursive(TreeNode<T> root) {
        List<T> traversedTreeNodes = new ArrayList<T>();

        if(null==root) return traversedTreeNodes;
        traversedTreeNodes.addAll(postOrderRecursive(root.getLeftChild()));
        traversedTreeNodes.addAll(postOrderRecursive(root.getRightChild()));
        traversedTreeNodes.add(root.getData());
        return traversedTreeNodes;
    }

    public List<T> postOrderIterative(TreeNode<T> root) {
        List<T> traversedNodes = new ArrayList<T>();
        if(null==root) return traversedNodes;

        Stack<TreeNode<T>> rightToBeProcessed = new Stack<TreeNode<T>>();
        Stack<TreeNode<T>> postRoot = new Stack<TreeNode<T>>();

        rightToBeProcessed.add(root);
        postRoot.add(root);
        TreeNode<T> node = root.getLeftChild();

        while(null!=node || !rightToBeProcessed.empty() || !postRoot.empty()) {
            if(null==node) {
                if(rightToBeProcessed.empty()) {
                    traversedNodes.add(postRoot.pop().getData());
                } else {
                    if(rightToBeProcessed.peek().equals(postRoot.peek())) {
                        node = rightToBeProcessed.pop().getRightChild();
                    } else {
                        traversedNodes.add(postRoot.pop().getData());
                    }
                }
            } else {
                rightToBeProcessed.push(node);
                postRoot.push(node);
                node = node.getLeftChild();
            }
        }
        return traversedNodes;
    }

}
