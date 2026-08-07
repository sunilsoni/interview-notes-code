package com.interview.notes.code.year.y2026.august.assessments.test1;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class ThreeParrots {                          // Main class.

    public static String solve(String s1, String s2, String s3) { // Required method.

        int n = s1.length();                 // All strings have the same length.

        var diff = IntStream.range(0, n)     // Check every character position.
                .filter(i ->                 // Keep positions where parrots disagree.
                        s1.charAt(i) != s2.charAt(i) ||
                        s1.charAt(i) != s3.charAt(i))
                .boxed()                     // Convert int indexes to Integer.
                .toList();                   // Store differing positions.

        if (diff.size() > 3)                 // More than 3 positions need mistakes.
            return "Impossible";             // Three parrots cannot cover them.

        if (diff.size() <= 1)                // Zero/one differing position has many answers.
            return "Ambiguous";              // So the word cannot be uniquely identified.

        Set<String> valid = new LinkedHashSet<>(); // Stores valid answers without duplicates.

        addIfValid(valid, s1, s1, s2, s3);   // s1 itself may be the answer.

        char[] word = s1.toCharArray();       // Mutable copy makes character changes easy.

        for (int i : diff) {                 // Try each differing position.

            char old = word[i];              // Save original s1 character.

            word[i] = s2.charAt(i);           // Try s2's character.
            addIfValid(valid, new String(word), s1, s2, s3); // Validate this candidate.

            word[i] = s3.charAt(i);           // Try s3's character.
            addIfValid(valid, new String(word), s1, s2, s3); // Validate this candidate.

            word[i] = old;                    // Restore s1 before next position.

            if (valid.size() > 1)             // Two valid words mean no unique answer.
                return "Ambiguous";           // Stop early.
        }

        if (valid.isEmpty())                  // No candidate worked.
            return "Impossible";              // No possible original word.

        return valid.iterator().next();       // Exactly one candidate is the answer.
    }

    static void addIfValid(Set<String> valid, String word, // Helper checks a candidate.
                           String s1, String s2, String s3) {

        if (close(word, s1) &&                // Must be within one mistake of parrot 1.
            close(word, s2) &&                // Must be within one mistake of parrot 2.
            close(word, s3))                  // Must be within one mistake of parrot 3.
            valid.add(word);                  // Candidate is valid.
    }

    static boolean close(String a, String b) { // Checks Hamming distance <= 1.

        return IntStream.range(0, a.length()) // Visit every character.
                .filter(i ->                  // Keep mismatching positions.
                        a.charAt(i) != b.charAt(i))
                .limit(2)                     // We only care if mismatch reaches two.
                .count() <= 1;                // Zero or one mismatch is allowed.
    }

    static void test(String name, String s1, String s2, // Simple PASS/FAIL test helper.
                     String s3, String expected) {

        String actual = solve(s1, s2, s3);    // Execute solution.

        System.out.println(                   // Print test result.
                name + ": " +
                (actual.equals(expected)      // Compare actual with expected.
                        ? "PASS"               // Correct result.
                        : "FAIL -> " + actual) // Show wrong result.
        );
    }

    public static void main(String[] args) {  // No JUnit; simple main testing.

        test("Example 1",                     // Provided unique example.
                "aab", "aca", "kaa",
                "aaa");

        test("Example 2",                     // Provided impossible example.
                "abcdg", "fghij", "klmno",
                "Impossible");

        test("Example 3",                     // Provided ambiguous example.
                "abc", "aca", "abc",
                "Ambiguous");

        test("All Same",                      // Many words are possible.
                "abc", "abc", "abc",
                "Ambiguous");

        test("One Difference",                // One differing position gives many choices.
                "abc", "abc", "abd",
                "Ambiguous");

        test("Length One",                    // Smallest allowed input.
                "a", "b", "c",
                "Ambiguous");

        int n = 200_000;                      // Maximum constraint size.

        String answer = "a".repeat(n);        // Create expected large word.

        char[] a = answer.toCharArray();      // First parrot's large input.
        char[] b = answer.toCharArray();      // Second parrot's large input.
        char[] c = answer.toCharArray();      // Third parrot's large input.

        a[0] = 'b';                           // First parrot makes one mistake.
        b[n / 2] = 'c';                       // Second parrot makes one mistake.
        c[n - 1] = 'd';                       // Third parrot makes one mistake.

        test("Large 200000",                  // Verify maximum-size handling.
                new String(a),
                new String(b),
                new String(c),
                answer);
    }
}