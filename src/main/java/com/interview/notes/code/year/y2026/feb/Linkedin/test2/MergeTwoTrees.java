package com.interview.notes.code.year.y2026.feb.Linkedin.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

// Interface definition as requested in the prompt
interface TreeNode {
    // Standard getters and setters for the node's data
    String getKey(); // Get the unique identifier of the node

    int getValue(); // Get the numerical value

    void setValue(int v); // Update the numerical value

    Map<String, TreeNode> getChildren(); // Get the map of child nodes

    void addChild(TreeNode node); // Helper to add a child
}

// Concrete implementation of the tree node
class Node implements TreeNode {
    private final String key; // Stores the node identifier (e.g., "W", "K")
    private final Map<String, TreeNode> children = new HashMap<>(); // Stores children mapped by their Key for fast access
    private int value; // Stores the node value

    // Constructor to initialize a node with key and value
    public Node(String key, int value) {
        this.key = key; // Set the key
        this.value = value; // Set the value
    }

    // Implementing interface methods
    public String getKey() {
        return key;
    } // Return key

    public int getValue() {
        return value;
    } // Return value

    public void setValue(int v) {
        this.value = v;
    } // Set value

    public Map<String, TreeNode> getChildren() {
        return children;
    } // Return child map

    // Helper to add a child node to the map using its key
    public void addChild(TreeNode node) {
        children.put(node.getKey(), node); // Put node in map
    }

    // toString for easier debugging and printing results
    public String toString() {
        return key + ":" + value; // Format: Key:Value
    }
}

public class MergeTwoTrees {

    public static void main(String[] args) {
        MergeTwoTrees solver = new MergeTwoTrees(); // Create instance of solver
        System.out.println("--- Starting Tests ---"); // Log start

        // TEST CASE 1: The Example from the Screenshot
        System.out.println("\nTest 1: Standard Example from Image..."); // Log test

        // Construct Source Tree
        Node srcRoot = new Node("ROOT", 22); // Create Source Root
        Node sw = new Node("W", 2);
        srcRoot.addChild(sw); // Add W to Source
        Node sk = new Node("K", 16);
        srcRoot.addChild(sk); // Add K to Source
        Node sc = new Node("C", 4);
        srcRoot.addChild(sc); // Add C to Source
        sw.addChild(new Node("V", 3)); // Add V under W
        Node skE = new Node("E", 6);
        sk.addChild(skE); // Add E under K
        Node skF = new Node("F", 7);
        sk.addChild(skF); // Add F under K

        // Construct Target Tree
        Node tgtRoot = new Node("ROOT", 20); // Create Target Root
        Node tw = new Node("W", 5);
        tgtRoot.addChild(tw); // Add W to Target
        Node tk = new Node("K", 2);
        tgtRoot.addChild(tk); // Add K to Target
        Node tr = new Node("R", 13);
        tgtRoot.addChild(tr); // Add R to Target
        tw.addChild(new Node("M", 5)); // Add M under W
        tk.addChild(new Node("M", 2)); // Add M under K
        tk.addChild(new Node("P", 13)); // Add P under K

        // Execute Merge
        solver.merge(srcRoot, tgtRoot); // Run the merge logic

        // Verification Logic
        boolean pass = true; // Flag to track pass/fail
        pass &= check(tgtRoot.getValue() == 42, "Root Value 42"); // 22+20

        // Check Child W
        Node resW = (Node) tgtRoot.getChildren().get("W"); // Get W
        pass &= check(resW != null && resW.getValue() == 7, "W Value 7"); // 2+5
        pass &= check(resW.getChildren().containsKey("V"), "W has V"); // Source only
        pass &= check(resW.getChildren().containsKey("M"), "W has M"); // Target only

        // Check Child K
        Node resK = (Node) tgtRoot.getChildren().get("K"); // Get K
        pass &= check(resK != null && resK.getValue() == 18, "K Value 18"); // 16+2

        // Check Child C (Only in Source)
        Node resC = (Node) tgtRoot.getChildren().get("C"); // Get C
        pass &= check(resC != null && resC.getValue() == 4, "C exists/val"); // Source copy

        // Check Child R (Only in Target)
        Node resR = (Node) tgtRoot.getChildren().get("R"); // Get R
        pass &= check(resR != null && resR.getValue() == 13, "R exists/val"); // Target keep

        printResult(pass); // Print final result for Test 1


        // TEST CASE 2: Large Data Input (Stress Test)
        System.out.println("\nTest 2: Large Data (100,000 nodes)..."); // Log test
        Node largeSrc = new Node("ROOT", 0); // Root for stress test
        Node largeTgt = new Node("ROOT", 0); // Target for stress test

        // Java 21 Stream to generate 100,000 nodes quickly
        IntStream.range(0, 100_000).forEach(i -> {
            largeSrc.addChild(new Node("Child" + i, 1)); // Add to source
            if (i % 2 == 0) largeTgt.addChild(new Node("Child" + i, 1)); // Add half to target
        });

        long start = System.currentTimeMillis(); // Start timer
        solver.merge(largeSrc, largeTgt); // Run merge
        long end = System.currentTimeMillis(); // End timer

        // Validation for large data
        // Even indices were in both (1+1=2), Odd were only in source (1)
        Node child0 = (Node) largeTgt.getChildren().get("Child0"); // Check common node
        Node child1 = (Node) largeTgt.getChildren().get("Child1"); // Check source-only node

        boolean stressPass = true; // Reset flag
        stressPass &= check(largeTgt.getChildren().size() == 100_000, "Size is 100k"); // Check count
        stressPass &= check(child0.getValue() == 2, "Merged value correct"); // Check sum
        stressPass &= check(child1.getValue() == 1, "New value correct"); // Check insert

        printResult(stressPass); // Print result
        System.out.println("Time taken: " + (end - start) + "ms"); // Print performance
    }

    // --- TESTING SUITE (No JUnit, just main method) ---

    // Helper method to print test step results
    private static boolean check(boolean condition, String msg) {
        if (!condition) System.out.println(" [FAIL] " + msg); // Print fail
        return condition; // Return status
    }

    // Helper method to print final Pass/Fail status
    private static void printResult(boolean pass) {
        if (pass) System.out.println("RESULT: PASS"); // Print Pass
        else System.out.println("RESULT: FAIL"); // Print Fail
    }

    // The core merge logic requested in the problem
    public void merge(TreeNode source, TreeNode target) {
        // Base case validation: if either is null, we can't merge
        if (source == null || target == null) return; // Exit method

        // 1. Sum the values: Target = Target + Source
        target.setValue(target.getValue() + source.getValue()); // Add source value to target value

        // 2. Process children using Java Streams and Map.merge
        // Iterate over every child in the Source tree
        source.getChildren().forEach((key, sourceChild) -> {
            // Map.merge is a Java 8+ feature that simplifies logic:
            // If 'key' does NOT exist in target -> add sourceChild.
            // If 'key' DOES exist in target -> execute the lambda block (remapping function).
            target.getChildren().merge(key, sourceChild, (targetChild, val) -> {
                // Recursive step: Both have this key, so we must merge these sub-trees
                merge(sourceChild, targetChild); // Recurse down
                return targetChild; // Return the modified target child to remain in the map
            });
        });
    }
}