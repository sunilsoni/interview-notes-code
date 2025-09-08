package com.interview.notes.code.year.y2025.september.assesment.test6;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates different stream-based boolean checks for palindrome-like logic
 * and explains why only the half-range noneMatch inequality is the correct test.
 */
public class BooleanCheck {
    public static void main(String[] args) {
        // Option 1: anyMatch over full range -> true if at least one mirrored pair matches.
        // For a palindrome like "kayak", many mirrored pairs match, so this returns true, but it also
        // returns true for most non-palindromes as long as any single pair happens to match.
        String str1 = "kayak";
        boolean result1 = IntStream.range(0, str1.length())
                .anyMatch(i -> str1.charAt(i) == str1.charAt(str1.length() - i - 1));
        System.out.println("Option 1 (any pair equals) => " + result1 + " | Explanation: true if any mirrored pair matches; not a palindrome check.");

        // Option 2: anyMatch with inequality -> true if at least one mirrored pair differs.
        // For a palindrome, all mirrored pairs are equal, so this should be false when checked over the full range.
        // But since it scans the entire length (including comparing each index with its mirror twice), the logic still holds: any mismatch causes true.
        String str2 = "kayak";
        boolean result2 = IntStream.range(0, str2.length())
                .anyMatch(i -> str2.charAt(i) != str2.charAt(str2.length() - i - 1));
        System.out.println("Option 2 (any pair differs) => " + result2 + " | Explanation: true if any mirrored pair differs; palindrome yields false; still not optimal due to full scan.");

        // Option 3: noneMatch over half range with inequality -> true if no mismatches in first half.
        // This is the correct palindrome check: ensure every mirrored pair (checked once) is equal.
        String str3 = "kayak";
        boolean result3 = IntStream.range(0, str3.length() / 2)
                .noneMatch(i -> str3.charAt(i) != str3.charAt(str3.length() - i - 1));
        System.out.println("Option 3 (palindrome check) => " + result3 + " | Explanation: checks half and confirms no mismatches. Correct for palindrome.");

        // Option 4: noneMatch over half range with equality -> true if no mirrored pairs are equal.
        // For palindromes, mirrored pairs are equal, so this becomes false. It effectively asks: are all pairs different? Not a palindrome check.
        String str4 = "kayak";
        boolean result4 = IntStream.range(0, str4.length() / 2)
                .noneMatch(i -> str4.charAt(i) == str4.charAt(str4.length() - i - 1));
        System.out.println("Option 4 (no pair equal) => " + result4 + " | Explanation: true only if every mirrored pair differs; for palindromes it's false.");

        // Option 5: iterate indices over full length and anyMatch equality -> same semantics as Option 1 but with a different stream source.
        // True if at least one mirrored pair matches, which is almost always true; not a palindrome check.
        String str5 = "kayak";
        boolean result5 = Stream.iterate(0, i -> i + 1)
                .limit(str5.length())
                .anyMatch(i -> str5.charAt(i) == str5.charAt(str5.length() - i - 1));
        System.out.println("Option 5 (any pair equals via iterate) => " + result5 + " | Explanation: same as Option 1; not a palindrome check.");
    }
}
