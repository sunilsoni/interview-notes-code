package com.interview.notes.code.year.y2025.august.oracle.test5;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

---

**Question:**

Given a string representation of a number and an integer `k`, return the smallest possible number that can be formed by removing exactly `k` digits from the number.

### Examples:

* **Input:** `num = "1432219"`, `k = 3`
  **Output:** `"1219"`

* **Input:** `num = "2222"`, `k = 2`
  **Output:** `"22"`

---

 */
public class RemoveKDigitsSolution {

    // ---- Core method: Greedy monotonic stack solution ----
    public static String removeKdigits(String num, int k) {
        // Validate trivial case: if we remove all digits or input empty, result is "0"
        if (num == null || num.isEmpty() || k >= num.length()) return "0"; // simple guard

        StringBuilder stack = new StringBuilder(); // acts as a stack of chosen digits (monotonic non-decreasing)
        int remainingRemovals = k; // number of digits we still need to remove

        // Iterate each character (digit) in the given numeric string
        for (int i = 0; i < num.length(); i++) { // loop through all digits
            char c = num.charAt(i);             // current digit

            // While we can remove more digits, and the stack is non-empty,
            // and the top of the stack is greater than current digit,
            // pop from the stack to make the number smaller
            while (remainingRemovals > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > c) {
                stack.deleteCharAt(stack.length() - 1); // remove the last (top) digit from stack
                remainingRemovals--;                    // we used one removal
            }

            stack.append(c); // push current digit to the stack
        }

        // If we still have removals left, remove from the end (these are the largest remaining positions)
        while (remainingRemovals > 0) {                 // if still need to drop digits
            stack.deleteCharAt(stack.length() - 1);     // remove from the end
            remainingRemovals--;                        // count removal
        }

        // Remove leading zeros for the final result
        int idx = 0;                                    // pointer to skip leading zeros
        while (idx < stack.length() && stack.charAt(idx) == '0') idx++; // skip zeros

        String result = (idx == stack.length()) ? "0" : stack.substring(idx); // if all zeros, return "0"; else substring

        return result; // final minimal number as string
    }

    // ---- Slow brute-force oracle for small strings: tries all combinations of k deletions ----
    // Used in tests only to verify correctness on small random inputs.
    private static String removeKdigitsBruteForce(String num, int k) {
        // If k >= length, the result must be "0"
        if (k >= num.length()) return "0"; // trivial fast path
        // Generate all index combinations of size k and pick minimal string
        int n = num.length(); // length of number string
        int targetLen = n - k; // length after removing k digits
        String[] best = new String[]{null}; // holder for best string found

        // Backtracking over choices: choose which positions to keep (targetLen of them)
        backtrackKeep(num, 0, targetLen, new StringBuilder(), best); // fill best with minimal result

        // Strip leading zeros for fairness (same as main method behavior)
        String res = stripLeadingZeros(best[0]); // remove leading zeros
        return res.isEmpty() ? "0" : res; // normalize to "0" if empty
    }

    // Helper to backtrack: choose exactly 'remain' characters to keep from position 'i'
    private static void backtrackKeep(String num, int i, int remain, StringBuilder cur, String[] best) {
        // If we chose enough digits, evaluate candidate
        if (remain == 0) { // picked enough digits
            String candidate = cur.toString(); // build candidate
            if (best[0] == null || compareNumericStrings(candidate, best[0]) < 0) { // keep minimal numeric string
                best[0] = candidate; // update best
            }
            return; // done with this path
        }
        // If not enough characters left to reach 'remain', prune early
        if (num.length() - i < remain) return; // cannot complete this path

        // Choice 1: keep num[i]
        cur.append(num.charAt(i)); // choose to keep current char
        backtrackKeep(num, i + 1, remain - 1, cur, best); // recurse with one less to keep
        cur.deleteCharAt(cur.length() - 1); // undo choice

        // Choice 2: skip num[i] (i.e., delete it)
        backtrackKeep(num, i + 1, remain, cur, best); // move on without keeping current char
    }

    // Compare two numeric strings by numeric value with same leading-zero handling as final output (but here we compare raw)
    private static int compareNumericStrings(String a, String b) {
        // Strip leading zeros for proper numeric comparison
        String aa = stripLeadingZeros(a);
        String bb = stripLeadingZeros(b);
        // Compare lengths
        if (aa.length() != bb.length()) return Integer.compare(aa.length(), bb.length());
        // Same length → lexicographic compare
        return aa.compareTo(bb);
    }

    // Strip leading zeros; return empty string if all zeros
    private static String stripLeadingZeros(String s) {
        int i = 0; // pointer to first non-zero
        while (i < s.length() && s.charAt(i) == '0') i++; // skip zeros
        return (i == s.length()) ? "" : s.substring(i); // return tail or empty
    }

    // ---- Test Harness: simple main (no JUnit), prints PASS/FAIL ----
    public static void main(String[] args) {
        // Fixed tests: [input, k, expected]
        List<TestCase> fixed = Arrays.asList(
                new TestCase("1432219", 3, "1219"),  // given example
                new TestCase("2222", 2, "22"),       // given example
                new TestCase("10", 1, "0"),          // removing '1' => "0"
                new TestCase("10200", 1, "200"),     // classic case: smallest after removing 1
                new TestCase("9", 1, "0"),           // remove single digit -> "0"
                new TestCase("123456", 3, "123"),    // removing 3 from increasing -> chop tail
                new TestCase("987654", 2, "7654"),   // removing 2 from decreasing -> drop leading big digits
                new TestCase("100200300", 1, "00200300".replaceFirst("^0+(?!$)", "")), // remove '1' -> "00200300" -> "200300"
                new TestCase("100200300", 1, "200300"), // explicit same as above for clarity
                new TestCase("1000", 1, "0"),        // becomes "000" -> "0"
                new TestCase("112", 1, "11"),        // remove '2' -> "11"
                new TestCase("1212", 2, "11")        // best is "11"
        );

        System.out.println("=== Fixed Tests ===");
        fixed.forEach(tc -> {
            String got = removeKdigits(tc.num, tc.k); // run solution
            boolean pass = got.equals(tc.expected);   // compare result
            System.out.printf("Input: num=%s, k=%d | Expected: %s | Got: %s | %s%n",
                    tc.num, tc.k, tc.expected, got, pass ? "PASS" : "FAIL");
        });

        // Random small tests with brute-force oracle
        System.out.println("\n=== Random Small Tests (Brute-force cross-check) ===");
        Random rnd = new Random(42); // seed for repeatability
        int randomTests = 50; // number of randomized small tests
        for (int t = 0; t < randomTests; t++) {
            int n = 6 + rnd.nextInt(5); // length 6..10
            String num = rnd.ints(n, 0, 10) // random digits 0..9
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining());
            int k = rnd.nextInt(Math.max(1, n)); // 0..n-1
            String expected = removeKdigitsBruteForce(num, k); // oracle result
            String got = removeKdigits(num, k); // our result
            boolean pass = expected.equals(got); // compare
            System.out.printf("Rand #%02d: num=%s, k=%d | Expected: %s | Got: %s | %s%n",
                    t + 1, num, k, expected, got, pass ? "PASS" : "FAIL");
        }

        // Large data performance test (sanity checks: length, zero handling)
        System.out.println("\n=== Large Data Test (Performance/Sanity) ===");
        int bigN = 200_000; // 200k digits
        String bigNum = Stream.generate(() -> "9") // start with many 9s to trigger lots of pops
                .limit(bigN / 2)
                .collect(Collectors.joining())
                + Stream.generate(() -> "0") // and many 0s
                .limit(bigN / 2)
                .collect(Collectors.joining());
        int bigK = bigN / 3; // remove a third
        long start = System.currentTimeMillis(); // start timing
        String bigRes = removeKdigits(bigNum, bigK); // run solution
        long end = System.currentTimeMillis(); // end timing
        boolean lengthOk = bigRes.length() == Math.max(1, bigN - bigK); // length sanity (or "0" length 1)
        boolean zeroNormOk = !(bigRes.startsWith("0") && bigRes.length() > 1); // no leading zeros unless single "0"
        System.out.printf("Large input n=%d, k=%d | len ok: %s | zeros ok: %s | Time: %d ms | %s%n",
                bigN, bigK, lengthOk, zeroNormOk, (end - start),
                (lengthOk && zeroNormOk) ? "PASS" : "FAIL");
    }

    // Simple holder for fixed tests
    private static class TestCase {
        String num; // input number string
        int k;      // number of digits to remove
        String expected; // expected output

        TestCase(String num, int k, String expected) { // constructor
            this.num = num; // assign
            this.k = k; // assign
            this.expected = expected; // assign
        }
    }
}