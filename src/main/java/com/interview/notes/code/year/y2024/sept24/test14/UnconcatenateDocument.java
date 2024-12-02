package com.interview.notes.code.year.y2024.sept24.test14;

import java.util.*;

public class UnconcatenateDocument {
    // Set to hold dictionary words for O(1) look-up
    private static Set<String> dictionary = new HashSet<>();

    // Memoization map to store computed results
    private static Map<Integer, Result> memo = new HashMap<>();

    public static void main(String[] args) {
        // Initialize the dictionary
        dictionary.addAll(Arrays.asList("did", "the", "code", "review", "for", "pull", "request"));

        // Document to unconcatenate
        String document = "pauldidthecodereviewforsimonspullrequest";

        // Process the document
        String result = reconstruct(document);

        // Output the result
        System.out.println(result);
    }

    // Method to reconstruct the original text
    private static String reconstruct(String s) {
        Result r = split(s, 0);
        return r == null ? "" : r.parsed;
    }

    // Helper method for dynamic programming
    private static Result split(String s, int start) {
        if (start >= s.length()) {
            return new Result(0, "");
        }

        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        int minUnrecognized = Integer.MAX_VALUE;
        String bestParsed = null;

        StringBuilder currentWord = new StringBuilder();

        for (int i = start; i < s.length(); i++) {
            currentWord.append(s.charAt(i));
            String current = currentWord.toString();

            int unrecognized = dictionary.contains(current) ? 0 : current.length();

            Result next = split(s, i + 1);

            if (next != null) {
                int totalUnrecognized = unrecognized + next.unrecognized;
                if (totalUnrecognized < minUnrecognized) {
                    minUnrecognized = totalUnrecognized;
                    String optionalSpace = (next.parsed.isEmpty()) ? "" : " ";
                    bestParsed = current + optionalSpace + next.parsed;
                }
            }
        }

        Result result = new Result(minUnrecognized, bestParsed);
        memo.put(start, result);
        return result;
    }

    // Class to store the result of each subproblem
    private static class Result {
        int unrecognized;
        String parsed;

        Result(int unrecognized, String parsed) {
            this.unrecognized = unrecognized;
            this.parsed = parsed;
        }
    }
}
