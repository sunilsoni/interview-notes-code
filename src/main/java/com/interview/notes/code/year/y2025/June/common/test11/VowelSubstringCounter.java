package com.interview.notes.code.year.y2025.June.common.test11;

import java.util.*;
import java.util.stream.IntStream;

public class VowelSubstringCounter {

    // quick vowel → index map
    private static final String VOWELS = "aeiou";
    private static final Set<Character> VOWEL_SET =
            new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    /**
     * O(n) – counts qualifying substrings
     */
    public static long vowelSubstring(String s) {
        long total = 0L;
        int start = 0;                     // left edge of current all-vowel block
        int[] last = {-1, -1, -1, -1, -1}; // last index of each vowel

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // consonant → break the window
            if (!VOWEL_SET.contains(c)) {
                start = i + 1;
                Arrays.fill(last, -1);
                continue;
            }

            // update last-seen index
            last[VOWELS.indexOf(c)] = i;

            // all five vowels seen?
            if (Arrays.stream(last).anyMatch(x -> x == -1)) continue;

            int minLast = Arrays.stream(last).min().getAsInt();
            total += (minLast - start + 1);
        }
        return total;
    }

    private static String bigRandom(int n) {
        Random rnd = new Random(42);
        StringBuilder sb = new StringBuilder(n);
        IntStream.range(0, n).forEach(i -> sb.append(VOWELS.charAt(rnd.nextInt(5))));
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Case> tests = Arrays.asList(
                new Case("aaeiouxa", 2),
                new Case("axyzaeiou", 1),
                new Case("aeiouaeiou", 21),
                // large 100 000-char random vowel string – expected value not pre-computed
                new Case(bigRandom(100_000), -1)
        );

        int id = 1;
        for (Case t : tests) {
            long got = vowelSubstring(t.in);
            if (t.exp >= 0) {
                System.out.printf("Test %02d : %s (expected %d, got %d)%n",
                        id, (got == t.exp ? "PASS" : "FAIL"), t.exp, got);
            } else {
                System.out.printf("Test %02d : large-case result = %d (verify manually)%n",
                        id, got);
            }
            id++;
        }
    }

    /* ---------- simple test harness (no JUnit) ---------- */
    private record Case(String in, long exp) {
    }
}