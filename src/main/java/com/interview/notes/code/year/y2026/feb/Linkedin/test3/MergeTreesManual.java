package com.interview.notes.code.year.y2026.feb.Linkedin.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

// 1. Interface (Simple)
interface TreeNode {
    int getValue(); // Get numeric value

    void setValue(int v); // Set numeric value

    Map<String, TreeNode> getChildren(); // Get child map
}

// 2. Concrete Node Implementation
class Node implements TreeNode {
    final Map<String, TreeNode> children = new HashMap<>(); // Stores children. HashMap is O(1).
    int val; // Stores the value

    public Node(int v) {
        this.val = v;
    } // Constructor

    public int getValue() {
        return val;
    } // Getter

    public void setValue(int v) {
        this.val = v;
    } // Setter

    public Map<String, TreeNode> getChildren() {
        return children;
    } // Getter for map
}

public class MergeTreesManual {

    // --- TESTING (Main Method) ---
    public static void main(String[] args) {
        MergeTreesManual solver = new MergeTreesManual(); // Instance
        System.out.println("--- Running Tests (No Map.merge) ---");

        // TEST 1: Standard Scenario from Image
        var src = new Node(22); // Source Root
        var sW = new Node(2);
        src.getChildren().put("W", sW); // Add W
        src.getChildren().put("K", new Node(16)); // Add K
        src.getChildren().put("C", new Node(4)); // Add C
        sW.getChildren().put("V", new Node(3)); // Add V to W

        var tgt = new Node(20); // Target Root
        var tW = new Node(5);
        tgt.getChildren().put("W", tW); // Add W
        tgt.getChildren().put("K", new Node(2)); // Add K
        tgt.getChildren().put("R", new Node(13)); // Add R
        tW.getChildren().put("M", new Node(5)); // Add M to W

        solver.merge(src, tgt); // Execute Logic

        // Verify Results manually
        boolean pass = true;
        pass &= check(tgt.getValue() == 42, "Root Value is 42"); // 22 + 20
        pass &= check(tgt.getChildren().get("W").getValue() == 7, "W Value is 7"); // 2 + 5

        // Verify structural merge (W should have both V and M)
        var wKids = tgt.getChildren().get("W").getChildren();
        pass &= check(wKids.containsKey("V"), "W has V (from Source)");
        pass &= check(wKids.containsKey("M"), "W has M (from Target)");

        System.out.println("Test 1 Result: " + (pass ? "PASS" : "FAIL"));

        // TEST 2: Large Data (Performance & Stack Depth)
        System.out.println("\n--- Large Data Test (100k Nodes) ---");
        var lSrc = new Node(0);
        var lTgt = new Node(0);

        // Generate 100,000 nodes using Stream
        IntStream.range(0, 100_000).forEach(i -> {
            lSrc.getChildren().put("n" + i, new Node(1)); // Source has everything
            if (i % 2 == 0) lTgt.getChildren().put("n" + i, new Node(1)); // Target has evens
        });

        long start = System.currentTimeMillis(); // Timer Start
        solver.merge(lSrc, lTgt); // Run Merge
        long time = System.currentTimeMillis() - start; // Timer End

        // Validation:
        // Even index 'n0': Source(1) + Target(1) = 2
        // Odd index 'n1': Source(1) + Target(0) = 1 (Copied)
        boolean pass2 = lTgt.getChildren().get("n0").getValue() == 2
                && lTgt.getChildren().get("n1").getValue() == 1
                && lTgt.getChildren().size() == 100_000;

        System.out.println("Test 2 Result: " + (pass2 ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + time + "ms");
    }

    // Helper to print PASS/FAIL status
    static boolean check(boolean condition, String msg) {
        if (!condition) System.out.println(" [FAIL] " + msg);
        return condition;
    }

    // --- MERGE LOGIC (No Map.merge) ---
    public TreeNode merge(TreeNode src, TreeNode tgt) {
        // Validation: If either node is null, return the target as is
        if (src == null || tgt == null) return tgt;

        // 1. Sum the values of the current nodes
        tgt.setValue(tgt.getValue() + src.getValue()); // Target Value += Source Value

        // 2. Iterate over Source children to merge structures
        src.getChildren().forEach((key, srcChild) -> {

            // Check if Target already has a child with this key
            var tgtChild = tgt.getChildren().get(key);

            if (tgtChild == null) {
                // CASE 1: Target does NOT have this key.
                // Simply copy the reference from Source to Target.
                tgt.getChildren().put(key, srcChild);
            } else {
                // CASE 2: Target DOES have this key.
                // We must merge the two existing branches recursively.
                merge(srcChild, tgtChild);
            }
        });

        return tgt; // Return the updated target
    }
}