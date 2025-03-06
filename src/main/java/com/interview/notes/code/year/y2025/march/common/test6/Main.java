package com.interview.notes.code.year.y2025.march.common.test6;

import java.util.ArrayList;
import java.util.List;

class Node {
    List<Node> subtrees = new ArrayList<>();
}

class Parser {
    private String s;
    private int index;

    public Node parse(String input) {
        s = input;
        index = 0;
        return parseNode();
    }

    private Node parseNode() {
        // Expect '('
        skipWhitespace();
        if (s.charAt(index) == '(') {
            index++;
            skipWhitespace();

            // Check for empty "()" => leaf
            if (s.charAt(index) == ')') {
                // Leaf node
                index++;
                return new Node();
            }

            // Otherwise parse subtrees separated by commas
            Node node = new Node();
            while (true) {
                node.subtrees.add(parseNode());
                skipWhitespace();
                if (s.charAt(index) == ')') {
                    index++;  // end of this node
                    break;
                } else if (s.charAt(index) == ',') {
                    index++;  // skip comma, parse next subtree
                    skipWhitespace();
                    continue;
                } else {
                    throw new RuntimeException("Parsing error at index " + index);
                }
            }
            return node;
        }

        throw new RuntimeException("Parsing error at index " + index);
    }

    private void skipWhitespace() {
        while (index < s.length() && Character.isWhitespace(s.charAt(index))) {
            index++;
        }
    }
}

class BalancedChecker {
    private int balancedCount = 0;

    public int checkBalanced(Node root) {
        balancedCount = 0;
        computeSize(root);
        return balancedCount;
    }

    private int computeSize(Node node) {
        if (node.subtrees.isEmpty()) {
            // Leaf is balanced
            balancedCount++;
            return 1;
        }
        int totalSize = 1;
        int[] sizes = new int[node.subtrees.size()];
        for (int i = 0; i < node.subtrees.size(); i++) {
            sizes[i] = computeSize(node.subtrees.get(i));
            totalSize += sizes[i];
        }

        // Check if all subtree sizes are the same
        boolean same = true;
        for (int i = 1; i < sizes.length; i++) {
            if (sizes[i] != sizes[0]) {
                same = false;
                break;
            }
        }
        if (same) {
            balancedCount++;
        }
        return totalSize;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example: parse a string, build the tree, check balanced count
        Parser parser = new Parser();
        BalancedChecker checker = new BalancedChecker();

        String treeStr = "((()(),(()())),(()()))"; // Example bracket notation
        Node root = parser.parse(treeStr);

        int balancedNodes = checker.checkBalanced(root);
        System.out.println("Balanced nodes: " + balancedNodes);
    }
}
