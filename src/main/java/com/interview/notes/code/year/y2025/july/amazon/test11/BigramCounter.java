package com.interview.notes.code.year.y2025.july.amazon.test11;

import java.util.*;

/*

You've been tasked with building a system to analyze sentiment in customer reviews on Amazon.com.
As a first step, you need to preprocess the customer reviews into bigrams (two consecutive words), such that you can count the number of times each bigram occurs across all customer reviews.

**Some notes:**

* The customer reviews will be provided as a list of strings.
* If a bigram is found in a review, it counts as one unique use no matter how many times the bigram appears in the review.
* On average, each review contains 30-40 words and 150-200 characters.

 */
public class BigramCounter {

    /**
     * Count how many times each bigram (two consecutive words) occurs across the list of reviews.
     * A bigram only counts once per review, even if it appears multiple times in that review.
     */
    public static Map<String, Integer> countBigrams(List<String> reviews) {
        // This map will hold the final counts of each bigram
        Map<String, Integer> bigramCounts = new HashMap<String, Integer>();

        // Process each review one by one
        for (String review : reviews) {
            // Use a set to remember which bigrams we already counted in this review
            Set<String> seenInThisReview = new HashSet<String>();

            // Split the review on whitespace to get individual words
            String[] words = review.split("\\s+");

            // Walk through the words and form bigrams
            for (int i = 0; i < words.length - 1; i++) {
                // Build the bigram by joining word[i] and word[i+1] with a space
                String bigram = words[i] + " " + words[i + 1];

                // If we haven't counted this bigram yet in this review
                if (!seenInThisReview.contains(bigram)) {
                    // Mark it as seen
                    seenInThisReview.add(bigram);

                    // Increase its global count by 1 (or start at 1 if new)
                    Integer count = bigramCounts.get(bigram);
                    if (count == null) {
                        bigramCounts.put(bigram, 1);
                    } else {
                        bigramCounts.put(bigram, count + 1);
                    }
                }
            }
        }

        // Return the completed counts
        return bigramCounts;
    }

    /**
     * Simple main method to run a bunch of test cases and print PASS/FAIL.
     */
    public static void main(String[] args) {
        // List to hold all the test review lists
        List<List<String>> testInputs = new ArrayList<List<String>>();
        // Corresponding list of expected counts
        List<Map<String, Integer>> expectedOutputs = new ArrayList<Map<String, Integer>>();

        // ----- Test Case 1: basic small example -----
        List<String> reviews1 = new ArrayList<String>();
        reviews1.add("hello world");              // yields "hello world"
        reviews1.add("hello");                    // no bigrams
        reviews1.add("hello world hello");        // "hello world" and "world hello"
        testInputs.add(reviews1);
        Map<String, Integer> expect1 = new HashMap<String, Integer>();
        expect1.put("hello world", 2);            // appears in review1 & review3
        expect1.put("world hello", 1);            // appears only in review3
        expectedOutputs.add(expect1);

        // ----- Test Case 2: empty reviews list -----
        testInputs.add(new ArrayList<String>());
        expectedOutputs.add(new HashMap<String, Integer>());  // expect empty map

        // ----- Test Case 3: reviews with no possible bigrams -----
        List<String> reviews3 = new ArrayList<String>();
        reviews3.add("");
        reviews3.add("single");
        testInputs.add(reviews3);
        expectedOutputs.add(new HashMap<String, Integer>());  // still empty

        // ----- Test Case 4: large data input -----
        List<String> reviews4 = new ArrayList<String>();
        // We'll repeat the same 4-word review 10,000 times
        for (int i = 0; i < 10000; i++) {
            reviews4.add("a b c d");
        }
        testInputs.add(reviews4);
        Map<String, Integer> expect4 = new HashMap<String, Integer>();
        // Bigram "a b", "b c", "c d" each appears once per review × 10000 reviews
        expect4.put("a b", 10000);
        expect4.put("b c", 10000);
        expect4.put("c d", 10000);
        expectedOutputs.add(expect4);

        // Run all test cases
        for (int i = 0; i < testInputs.size(); i++) {
            // Compute actual counts
            Map<String, Integer> actual = countBigrams(testInputs.get(i));
            // Check equality
            boolean pass = actual.equals(expectedOutputs.get(i));
            // Print result
            System.out.println("Test case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            // If it failed, show the differences
            if (!pass) {
                System.out.println("  Expected: " + expectedOutputs.get(i));
                System.out.println("  Actual:   " + actual);
            }
        }
    }
}