package com.interview.notes.code.year.y2025.july.codility.test4;

import java.util.Arrays;
import java.util.Iterator;

public class Codec {

    /**
     * Definition for a binary tree node, using Java 17 record.
     */
    public static record TreeNode(int val, TreeNode left, TreeNode right) {}

    /**
     * Encodes a tree to a single string.
     * Pre-order traversal, nulls as "#,".
     */
    public String serialize(TreeNode root) {
        return root == null
            ? "#,"
            : root.val() + "," +
              serialize(root.left()) +
              serialize(root.right());
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        Iterator<String> it = Arrays.asList(data.split(",")).iterator();
        return build(it);
    }

    // Helper: rebuilds the tree in pre-order from tokens
    private TreeNode build(Iterator<String> it) {
        String token = it.next();
        if (token.equals("#")) {
            return null;
        }
        int v = Integer.parseInt(token);
        return new TreeNode(
            v,
            build(it),    // left subtree
            build(it)     // right subtree
        );
    }
}