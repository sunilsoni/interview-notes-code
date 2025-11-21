package com.interview.notes.code.year.y2024.june24.test1;

public class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        root = null;
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        insert(newNode);
    }

    private void insert(Node newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = root;
        while (true) {
            if (newNode.key < parent.key) {
                if (parent.left == null) {
                    parent.left = newNode;
                    break;
                } else {
                    parent = parent.left;
                }
            } else {
                if (parent.right == null) {
                    parent.right = newNode;
                    break;
                } else {
                    parent = parent.right;
                }
            }
        }

        insertFixup(newNode);
    }

    private void insertFixup(Node newNode) {
        while (newNode != root && newNode.parent.color == Color.RED) {
            if (newNode.parent == newNode.parent.parent.left) {
                Node uncle = newNode.parent.parent.right;

                if (uncle != null && uncle.color == Color.RED) {
                    // Case 1: Uncle is red
                    newNode.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    newNode = newNode.parent.parent;
                } else {
                    // Case 2: Uncle is black and newNode is a right child
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }

                    // Case 3: Uncle is black and newNode is a left child
                    newNode.parent.color = Color.BLACK;
                    newNode.parent.parent.color = Color.RED;
                    rightRotate(newNode.parent.parent);
                }
            } else {
                // The code is symmetric to the above case
            }
        }

        root.color = Color.BLACK;
    }

    public void search(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                // Found the node
                // return node;
            }
        }

        // Node not found
        //   return null;
    }

    private void leftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(Node node) {
        // The code is symmetric to the leftRotate() method
    }

    private enum Color {
        RED,
        BLACK
    }

    private class Node {
        private final int key;
        private Color color;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int key) {
            this.key = key;
            this.color = Color.RED;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}