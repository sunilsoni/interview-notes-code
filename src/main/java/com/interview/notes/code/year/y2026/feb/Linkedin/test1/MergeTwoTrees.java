package com.interview.notes.code.year.y2026.feb.Linkedin.test1;

import java.util.LinkedHashMap;
import java.util.Map;

// Interface defining the structure of our tree nodes
interface TreeNode {
    String getKey(); // Get unique identifier for the node

    int getValue(); // Get the numeric value

    void setValue(int value); // Update the numeric value

    Map<String, TreeNode> getChildren(); // Get child nodes mapped by key
}

// Simple implementation of the TreeNode interface
class MyTreeNode implements TreeNode {
    private final String key; // Unique key among siblings
    private final Map<String, TreeNode> children = new LinkedHashMap<>(); // Maintain insertion order
    private int value; // Aggregated sum

    public MyTreeNode(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Map<String, TreeNode> getChildren() {
        return children;
    }
}

public class MergeTwoTrees {

    public static void main(String[] args) {
        MergeTwoTrees engine = new MergeTwoTrees();

        // TEST CASE 1: The example provided in the requirement image
        TreeNode src = new MyTreeNode("ROOT", 22);
        src.getChildren().put("W", new MyTreeNode("W", 2));
        src.getChildren().get("W").getChildren().put("V", new MyTreeNode("V", 3));
        src.getChildren().put("K", new MyTreeNode("K", 16));
        src.getChildren().get("K").getChildren().put("E", new MyTreeNode("E", 6));
        src.getChildren().get("K").getChildren().put("F", new MyTreeNode("F", 7));
        src.getChildren().put("C", new MyTreeNode("C", 4));

        TreeNode tgt = new MyTreeNode("ROOT", 20);
        tgt.getChildren().put("W", new MyTreeNode("W", 5));
        tgt.getChildren().get("W").getChildren().put("M", new MyTreeNode("M", 5));
        tgt.getChildren().put("K", new MyTreeNode("K", 2));
        tgt.getChildren().get("K").getChildren().put("M", new MyTreeNode("M", 2));
        tgt.getChildren().put("R", new MyTreeNode("R", 13));
        tgt.getChildren().get("R").getChildren().put("P", new MyTreeNode("P", 13));

        engine.merge(src, tgt);

        // Validation Logic
        boolean p1 = tgt.getValue() == 42; // ROOT: 22 + 20
        boolean p2 = tgt.getChildren().get("K").getValue() == 18; // K: 16 + 2
        boolean p3 = tgt.getChildren().get("K").getChildren().containsKey("E"); // New branch E created

        System.out.println("Test Case 1 (Standard Merge): " + (p1 && p2 && p3 ? "PASS" : "FAIL"));

        // TEST CASE 2: Large Data Input (Wide Tree)
        TreeNode bigSrc = new MyTreeNode("ROOT", 0);
        TreeNode bigTgt = new MyTreeNode("ROOT", 0);
        for (int i = 0; i < 10000; i++) {
            bigSrc.getChildren().put("Node" + i, new MyTreeNode("Node" + i, 1));
            bigTgt.getChildren().put("Node" + i, new MyTreeNode("Node" + i, 1));
        }
        engine.merge(bigSrc, bigTgt);
        System.out.println("Test Case 2 (Large Scale 10k nodes): " + (bigTgt.getValue() == 20000 ? "PASS" : "FAIL"));
    }

    public void merge(TreeNode source, TreeNode target) {
        if (source == null || target == null) return; // Safety check for null inputs

        // Iterate through source children using Stream API for brevity
        source.getChildren().forEach((key, srcChild) -> {
            // Check if target already has this specific child branch
            TreeNode tgtChild = target.getChildren().get(key);

            if (tgtChild != null) {
                // Scenario: Key exists in both. Sum values and recurse deeper.
                tgtChild.setValue(tgtChild.getValue() + srcChild.getValue()); // Update target value
                merge(srcChild, tgtChild); // Recursive call for nested branches
            } else {
                // Scenario: Branch is absent in target. Add the whole source branch.
                target.getChildren().put(key, srcChild);
            }
        });

        // After children are processed, target root value is updated to reflect the new sum
        target.setValue(target.getValue() + source.getValue());
    }
}