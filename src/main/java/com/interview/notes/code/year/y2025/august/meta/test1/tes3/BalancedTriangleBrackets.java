package com.interview.notes.code.year.y2025.august.meta.test1.tes3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
Here’s the **final combined question** from your images and explanation, structured clearly:

---

**Question:**
Lowest Common Ancestor (LCA) in a Binary Tree

You are given the following `TreeNode` class definition:

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

### Problem Statement:

Given the `root` of a binary tree and two nodes `p` and `q`, write a program to find their **Lowest Common Ancestor (LCA)**.

* The **Lowest Common Ancestor** of two nodes `p` and `q` is the lowest (i.e., deepest) node in the tree that has both `p` and `q` as descendants.

### Example Tree:

```
        3
       / \
      9   7
     / \    \
    2   6    4
```

### Examples:

1. **Input:** `p = 2, q = 6`
   **Output:** `9`
   (Because `9` is the lowest node that is an ancestor of both `2` and `6`.)

2. **Input:** `p = 7, q = 6`
   **Output:** `3`
   (Because `3` is the lowest node that is an ancestor of both `7` and `6`.)

---

👉 Do you want me to also **provide the Java code implementation** for the LCA solution along with explanation and test cases?

 */
public class BalancedTriangleBrackets {

    // Part A: Check if the string is valid
    public static boolean isValid(String s) {
        int open = 0;                          // counts unmatched '<' seen so far
        for (int i = 0; i < s.length(); i++) { // iterate each character left to right
            char c = s.charAt(i);              // current character
            if (c == '<') {                    // if we see an opening bracket
                open++;                        // we owe one more closing later
            } else if (c == '>') {             // if we see a closing bracket
                open--;                        // match it against an outstanding open
                if (open < 0) {                // if negative, too many '>' at this point
                    return false;              // invalid immediately
                }
            } else {                           // safety: if any other char appears
                return false;                  // not allowed per problem statement
            }
        }
        return open == 0;                      // valid only if all opens are matched
    }

    // Part B: Minimum number of brackets to add to make the string valid
    public static int minAdditionsToMakeValid(String s) {
        int open = 0;                          // current count of unmatched '<'
        int needOpen = 0;                      // number of '<' insertions required so far
        for (int i = 0; i < s.length(); i++) { // scan left to right
            char c = s.charAt(i);              // current character
            if (c == '<') {                    // opening bracket, increases debt of closes
                open++;                        // track one more required '>'
            } else if (c == '>') {             // closing bracket
                if (open > 0) {                // if we have an unmatched open to pair with
                    open--;                    // consume one open
                } else {                       // no open available -> this '>' is unmatched
                    needOpen++;                // we must insert a '<' before this position
                    // open remains 0, as the newly "inserted" '<' immediately pairs with this '>'
                }
            } else {                           // safety against invalid characters
                // If input can only be '<' or '>', treating any other char as error:
                // The minimal additions concept breaks with invalid alphabet.
                // We can either reject or count replacements — problem states only '<'/'>' provided,
                // so we'll treat others as impossible and return a big number.
                return Integer.MAX_VALUE;      // signals invalid input domain
            }
        }
        // After the scan, any remaining 'open' represent unmatched '<' that need trailing '>'
        return needOpen + open;                // total insertions = missing opens + missing closes
    }

    // Simple helper to mark PASS/FAIL text
    private static String passFail(boolean ok) {
        return ok ? "PASS" : "FAIL";
    }

    // Generate a large random test of given length for stress testing
    private static String randomBrackets(int n, long seed) {
        Random r = new Random(seed);           // seed to make runs reproducible
        StringBuilder sb = new StringBuilder(n); // efficient string building
        for (int i = 0; i < n; i++) {            // build n chars
            sb.append(r.nextBoolean() ? '<' : '>'); // random '<' or '>'
        }
        return sb.toString();                    // return the large random string
    }

    public static void main(String[] args) {
        // ---------------------------
        // Part A: Validity test cases
        // ---------------------------
        List<ValidCase> validCases = Arrays.asList(
                new ValidCase("", true),                // empty is valid (nothing to fix)
                new ValidCase("<>", true),              // basic balanced
                new ValidCase("<><>", true),            // two balanced pairs
                new ValidCase("<<>>>", true),           // nested then extra closes; overall balanced scan is ok
                new ValidCase("<<>", false),            // missing a closing
                new ValidCase("<<>><", false),          // ends with extra open
                new ValidCase(">>", false),             // starts with closing, invalid
                new ValidCase("<<<>>>", true),          // perfectly nested triple
                new ValidCase("<><><>", true),          // alternating pairs
                new ValidCase("><", false)              // close before open
        );

        // Run Part A tests and print results using Java 8 streams for compact formatting
        System.out.println("=== Part A: Validity Tests ===");
        validCases.stream()
                .map(tc -> {
                    boolean got = isValid(tc.s);       // compute validity
                    boolean ok = (got == tc.expect);   // compare to expected
                    return String.format("Input: %-10s Expected: %-5s Got: %-5s -> %s",
                            "\"" + tc.s + "\"", tc.expect, got, passFail(ok));
                })
                .forEach(System.out::println);

        // -------------------------------
        // Part B: Min additions test cases
        // -------------------------------
        // NOTE: Per the stated rules, "<>" is already valid => min additions = 0
        // (Prompt’s example "<> -> 1" conflicts with Part A rules; we follow the rules.)
        List<AddCase> addCases = Arrays.asList(
                new AddCase("", 0),                   // nothing to add
                new AddCase("<>", 0),                 // already valid
                new AddCase("<<<", 3),                // need three '>' to close
                new AddCase(">>>", 3),                // need three '<' upfront
                new AddCase("<<>", 1),                // need one '>' at end
                new AddCase("><", 2),                 // need one '<' before first '>' and one '>' after last '<'
                new AddCase("<<>><", 1),              // one trailing '>'
                new AddCase("<><><>", 0),             // valid alternating
                new AddCase(">>><<<", 6),             // three opens needed first, then three closes needed at end
                new AddCase("<<>>>", 0)               // given as valid in Part A example
        );

        System.out.println("\n=== Part B: Min Additions Tests ===");
        addCases.stream()
                .map(tc -> {
                    int got = minAdditionsToMakeValid(tc.s); // compute minimum insertions
                    boolean ok = (got == tc.expect);         // compare to expected
                    return String.format("Input: %-10s Expected: %-2d Got: %-2d -> %s",
                            "\"" + tc.s + "\"", tc.expect, got, passFail(ok));
                })
                .forEach(System.out::println);

        // --------------------------------
        // Extra: Cross-check Part A vs Part B coherence
        // If isValid(s) is true, min additions must be 0.
        // --------------------------------
        System.out.println("\n=== Consistency Check (Valid => 0 additions) ===");
        validCases.stream()
                .filter(tc -> tc.expect)                        // only those expected valid
                .map(tc -> {
                    int adds = minAdditionsToMakeValid(tc.s);   // compute additions
                    boolean ok = (adds == 0);                   // must be zero
                    return String.format("Input: %-10s minAdditions: %-2d -> %s",
                            "\"" + tc.s + "\"", adds, passFail(ok));
                })
                .forEach(System.out::println);

        // --------------------------------
        // Large data stress tests
        // --------------------------------
        System.out.println("\n=== Large Data Tests (performance sanity) ===");
        // Worst-case 1: many initial '>' then many '<' => forces lots of insertions and scanning
        int n = 1_000_000; // 1 million (tunable; still quick in O(n))
        String worst1 = new String(new char[n]).replace('\0', '>') +
                new String(new char[n]).replace('\0', '<');
        long t1 = System.nanoTime();
        int adds1 = minAdditionsToMakeValid(worst1);
        long t2 = System.nanoTime();

        // Worst-case 2: many '<' then many '>' => valid; checks linear pass time
        String worst2 = new String(new char[n]).replace('\0', '<') +
                new String(new char[n]).replace('\0', '>');
        long t3 = System.nanoTime();
        boolean valid2 = isValid(worst2);
        long t4 = System.nanoTime();

        // Random big
        String rnd = randomBrackets(n, 42L);
        long t5 = System.nanoTime();
        int addsRnd = minAdditionsToMakeValid(rnd);
        long t6 = System.nanoTime();

        // Print timings in milliseconds (approx)
        System.out.printf("Worst1 length=%d -> minAdditions=%d | time=%.3f ms%n",
                worst1.length(), adds1, (t2 - t1) / 1_000_000.0);
        System.out.printf("Worst2 length=%d -> isValid=%s   | time=%.3f ms%n",
                worst2.length(), valid2, (t4 - t3) / 1_000_000.0);
        System.out.printf("Random length=%d -> minAdditions=%d | time=%.3f ms%n",
                rnd.length(), addsRnd, (t6 - t5) / 1_000_000.0);
    }

    /**
     * @param s      input string
     * @param expect expected validity
     */ // Utility: A tiny record to hold a test case for Part A (validity)
        record ValidCase(String s, boolean expect) {
    }

    /**
     * @param s      input string
     * @param expect expected minimal insertions
     */ // Utility: A tiny record to hold a test case for Part B (min additions)
        record AddCase(String s, int expect) {
    }
}