package com.interview.notes.code.year.y2024.july24.test12;

class Node {
    private final Node leftChild;
    private final Node rightChild;

    public Node(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public int height() {
        if (this.leftChild == null && this.rightChild == null) {
            return 0;
        }

        int leftHeight = (this.leftChild != null) ? this.leftChild.height() : -1;
        int rightHeight = (this.rightChild != null) ? this.rightChild.height() : -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}

public class Main {
    public static void main(String[] args) {
        Node leaf1 = new Node(null, null);
        Node leaf2 = new Node(null, null);
        Node node = new Node(leaf1, null);
        Node root = new Node(node, leaf2);

        System.out.println(root.height());
    }
}
