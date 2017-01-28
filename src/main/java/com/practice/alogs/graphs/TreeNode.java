package com.practice.alogs.graphs;

/**
 * Created by sunilchandani on 22/01/17.
 */
public class TreeNode<T> {
    private T data;
    private TreeNode<T> leftChild, rightChild;

    public TreeNode(T data) {
        this.data = data;
        leftChild = null;
        rightChild = null;
    }

    public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;

        TreeNode<?> treeNode = (TreeNode<?>) o;

        if (!data.equals(treeNode.data)) return false;
        if (leftChild != null ? !leftChild.equals(treeNode.leftChild) : treeNode.leftChild != null) return false;
        return rightChild != null ? rightChild.equals(treeNode.rightChild) : treeNode.rightChild == null;
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (leftChild != null ? leftChild.hashCode() : 0);
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        return result;
    }
}
