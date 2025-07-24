package com.interview.notes.code.year.y2025.july.amazon.test10;

import java.util.*;

public class BigramAnalyzer {
    // Main class to analyze bigrams in customer reviews
    
    // Method to process a single review and extract bigrams
    public static Set<String> processSingleReview(String review) {
        // Create a set to store unique bigrams from this review
        Set<String> bigrams = new HashSet<String>();
        
        // Split the review into words
        String[] words = review.toLowerCase().split("\\s+");
        
        // Process pairs of consecutive words to form bigrams
        for(int i = 0; i < words.length - 1; i++) {
            // Combine two consecutive words with a space
            String bigram = words[i] + " " + words[i + 1];
            // Add to set (automatically handles duplicates)
            bigrams.add(bigram);
        }
        
        return bigrams;
    }
    
    // Method to process multiple reviews and count bigram frequencies
    public static Map<String, Integer> analyzeBigrams(List<String> reviews) {
        // Map to store bigram counts across all reviews
        Map<String, Integer> bigramCounts = new HashMap<String, Integer>();
        
        // Process each review
        for(String review : reviews) {
            // Get unique bigrams from current review
            Set<String> reviewBigrams = processSingleReview(review);
            
            // Update counts in the main map
            for(String bigram : reviewBigrams) {
                // Increment count or initialize to 1 if not present
                Integer count = bigramCounts.get(bigram);
                bigramCounts.put(bigram, count == null ? 1 : count + 1);
            }
        }
        
        return bigramCounts;
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic functionality
        List<String> test1 = new ArrayList<String>();
        test1.add("this is good");
        test1.add("this is bad");
        Map<String, Integer> result1 = analyzeBigrams(test1);
        boolean pass1 = result1.get("this is") == 2 && result1.get("is good") == 1 && result1.get("is bad") == 1;
        System.out.println("Test 1 (Basic): " + (pass1 ? "PASS" : "FAIL"));
        
        // Test Case 2: Duplicate bigrams in same review
        List<String> test2 = new ArrayList<String>();
        test2.add("good good good good");
        Map<String, Integer> result2 = analyzeBigrams(test2);
        boolean pass2 = result2.get("good good") == 1;
        System.out.println("Test 2 (Duplicates): " + (pass2 ? "PASS" : "FAIL"));
        
        // Test Case 3: Large data test
        List<String> test3 = new ArrayList<String>();
        // Generate 10000 reviews
        for(int i = 0; i < 10000; i++) {
            test3.add("this is a very long review with many words " + i);
        }
        long startTime = System.currentTimeMillis();
        Map<String, Integer> result3 = analyzeBigrams(test3);
        long endTime = System.currentTimeMillis();
        boolean pass3 = (endTime - startTime) < 5000; // Should complete within 5 seconds
        System.out.println("Test 3 (Performance): " + (pass3 ? "PASS" : "FAIL"));
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        
        // Test Case 4: Empty input
        List<String> test4 = new ArrayList<String>();
        Map<String, Integer> result4 = analyzeBigrams(test4);
        boolean pass4 = result4.isEmpty();
        System.out.println("Test 4 (Empty input): " + (pass4 ? "PASS" : "FAIL"));
    }
}
