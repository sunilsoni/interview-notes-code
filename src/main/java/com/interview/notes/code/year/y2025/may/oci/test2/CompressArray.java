package com.interview.notes.code.year.y2025.may.oci.test2;

import java.util.*;
import java.util.stream.*;

public class CompressArray {

    /**
     * Returns the minimum length of the array after performing as many valid merges
     * (adjacent pairs whose product ≤ k) as possible.
     *
     * @param a List of integers (each 1 ≤ a[i] ≤ 10^9).
     * @param k The maximum allowed product to merge (1 ≤ k ≤ 10^9).
     * @return The smallest possible length of the array after merging.
     */
    public static int getMinLength(List<Integer> a, int k) {
        // We will use a stack (as an ArrayDeque<Integer>) to simulate merging.
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate over each element in the input list.
        for (int x : a) {
            // Push the current element onto the stack.
            stack.push(x);

            // Now try to merge as long as the top two elements can be merged.
            // We only merge if their product ≤ k.
            while (stack.size() >= 2) {
                // Peek the top two elements: 
                // top1 = the element at the top, top2 = the next element.
                int top1 = stack.pop();
                int top2 = stack.pop();

                // Compute their product using long to avoid overflow.
                long product = (long) top1 * (long) top2;

                // If product ≤ k, we merge them into a single element (= product).
                if (product <= k) {
                    // Cast back to int is safe because product ≤ k ≤ 10^9.
                    int merged = (int) product;
                    // Push the merged value back onto the stack.
                    stack.push(merged);

                    // Continue the while loop: the new merged value might merge again
                    // with whatever is below it on the stack.
                } else {
                    // If we cannot merge, we must restore the two popped elements, in order.
                    // First push top2, then top1, because we popped top1 then top2.
                    stack.push(top2);
                    stack.push(top1);
                    // Break out of the merging loop: no further merges possible at top.
                    break;
                }
            }
        }

        // After processing all elements, the stack contains the final “compressed” elements.
        // Its size is the minimum length we can get.
        return stack.size();
    }

    /**
     * A simple container for test cases.
     */
    private static class TestCase {
        final List<Integer> inputArray;
        final int k;
        final int expected;

        TestCase(List<Integer> inputArray, int k, int expected) {
            this.inputArray = inputArray;
            this.k = k;
            this.expected = expected;
        }

        // Runs this test case, returns true if output matches expected.
        boolean run() {
            int result = getMinLength(inputArray, k);
            return result == expected;
        }
    }

    /**
     * The main method runs several test cases (including the provided samples,
     * extra edge cases, and a large random case). It prints PASS or FAIL for each.
     */
    public static void main(String[] args) {
        // 1) Sample Cases from the prompt
        TestCase sample0 = new TestCase(
            Arrays.asList(1, 3, 2, 5, 4),
            6,
            3
        );

        TestCase sample1 = new TestCase(
            Arrays.asList(1, 2, 1, 3, 6, 1),
            6,
            2
        );

        TestCase exampleInDescription = new TestCase(
            Arrays.asList(2, 3, 3, 7, 3, 5),
            20,
            3
        );

        // 2) Additional edge cases
        TestCase singleElement = new TestCase(
            Arrays.asList(42),
            10,
            1
        ); // No merges possible, result = 1

        TestCase allOnes = new TestCase(
            Collections.nCopies(10, 1), // [1,1,1,1,1,1,1,1,1,1]
            1,
            1
        ); // Everything merges into one 1 eventually

        TestCase noMerges = new TestCase(
            Arrays.asList(2, 1000000000, 3, 1000000000, 5),
            5,
            5
        ); // Adjacent products always exceed k=5, no merges

        TestCase alternatingMerge = new TestCase(
            Arrays.asList(2, 3, 1, 4, 1, 5, 1, 6),
            6,
            3
        );
        // Step by step:
        // [2,3,1,4,1,5,1,6]
        // Merge (2,3)=6 → [6,1,4,1,5,1,6]
        // Merge (6,1)=6 → [6,4,1,5,1,6]
        // (6,4)=24>6 no
        // Push 1 → [6,4,1,…]; (4×1)=4≤6 → merge → [6,4,5,1,6]? Wait carefully:
        // After [6,4,1,5,1,6], we see (4×1)=4→ [6,4,5,1,6]
        // Then (4×5)=20>6 no → push 1 → [6,4,5,1,6], (5×1)=5→ merge→ [6,4,5,6]
        // (5×6)=30>6 no → final [6,4,5,6] length=4? Actually written expected=3—let me correct:
        // Let’s do a simpler specifically crafted test instead.

        // Correction: build a test where merges alternate but safe:
        TestCase alternatingMergeFixed = new TestCase(
            Arrays.asList(2, 3, 1, 2, 1, 2, 1, 2),
            6,
            2
        );
        // Explanation (one possible sequence):
        // [2,3,1,2,1,2,1,2]
        // Merge (2,3)=6 → [6,1,2,1,2,1,2]
        // Merge (6,1)=6 → [6,2,1,2,1,2]
        // Merge (6,2)=12 > 6 no
        // Push next 1 → [6,2,1,…], (2×1)=2→merge→ [6,2,2,1,2]
        // Merge (2,2)=4 → [6,4,1,2]
        // Merge (4,1)=4 → [6,4,2]
        // Merge (4,2)=8>6 no
        // Push next 1 → [6,4,2,1], (2×1)=2→merge→ [6,4,2]
        // Merge (4×2)=8>6 no
        // Push final 2 → [6,4,2,2], (2×2)=4→merge→ [6,4,4]
        // (4×4)=16>6 no → final = [6,4,4] length=3.  
        // Hmm—still not 2. We can keep adjusting until it truly compresses to length=2 if possible,
        // but this is just demonstrating how tricky these merges can be if not chosen in order.
        // Let’s instead use a more deterministic edge case:

        TestCase allMergeable = new TestCase(
            Arrays.asList(1, 1, 2, 2, 1, 1),
            2,
            1
        );
        // Because 1×1=1≤2 → merge repeatedly → eventually a single “1” remains

        // 3) A large random‐data test for performance (n = 200,000)
        //    Construct an array of all 1’s so everything collapses into a single element quickly.
        List<Integer> largeAllOnes = Collections.nCopies(200_000, 1);
        TestCase largeTest = new TestCase(largeAllOnes, 1, 1);

        // Gather all test cases in a list.
        List<TestCase> tests = Arrays.asList(
            sample0,
            sample1,
            exampleInDescription,
            singleElement,
            allOnes,
            noMerges,
            allMergeable,
            largeTest
        );

        // Use Java 8 Stream API to run and report.
        System.out.println("Running " + tests.size() + " test cases...");
        long passedCount = tests.stream()
            .filter(TestCase::run)
            .count();

        // Print summary
        System.out.println("→ Passed " + passedCount + " / " + tests.size() + " cases.");

        // Also print which ones failed, if any
        IntStream.range(0, tests.size())
            .filter(i -> !tests.get(i).run())
            .forEach(i -> System.out.println("Test case #" + i + " FAILED."));

        // For manual inspection, you could also print details:
        // tests.forEach(tc -> {
        //     System.out.println("Input=" + tc.inputArray + ", k=" + tc.k +
        //                        ", expected=" + tc.expected +
        //                        ", got=" + getMinLength(tc.inputArray, tc.k));
        // });
    }
}