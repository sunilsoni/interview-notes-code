package com.interview.notes.code.year.y2025.october.oracle.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TweetAnalyzer {

    /**
     * Analyzes a tweet and returns various statistics about word frequency
     *
     * @param tweet The input tweet string to analyze
     * @return TweetAnalysisResult containing all analysis results
     */
    public static TweetAnalysisResult analyzeTweet(String tweet) {
        // Convert tweet to lowercase and split into words, removing punctuation
        // This line normalizes the input by making it case-insensitive
        String cleanTweet = tweet.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "");

        // Split the cleaned tweet into individual words using whitespace as delimiter
        // Filter out empty strings that might result from multiple spaces
        List<String> words = Arrays.stream(cleanTweet.split("\\s+"))
                .filter(word -> !word.isEmpty()) // Remove empty strings from splitting
                .collect(Collectors.toList()); // Collect into a list for further processing

        // Count unique words by converting to a Set which automatically removes duplicates
        // This gives us the total number of distinct words in the tweet
        long uniqueWordCount = words.stream()
                .distinct() // Remove duplicate words
                .count(); // Count the remaining unique words

        // Create frequency map using Stream API groupingBy collector
        // This groups words by their value and counts occurrences of each word
        Map<String, Long> wordFrequency = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(), // Group by the word itself
                        Collectors.counting() // Count occurrences of each word
                ));

        // Find the most frequent word by getting the entry with maximum count value
        // This uses Optional to handle cases where the map might be empty
        Optional<Map.Entry<String, Long>> mostFrequentEntry = wordFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()); // Compare entries by their frequency count

        // Extract the most frequent word from the Optional, default to empty string if not found
        String mostFrequentWord = mostFrequentEntry.map(Map.Entry::getKey).orElse("");

        // Get the frequency count of the most frequent word, default to 0 if not found
        long mostFrequentCount = mostFrequentEntry.map(Map.Entry::getValue).orElse(0L);

        // Create and return the analysis result object with all computed values
        return new TweetAnalysisResult(uniqueWordCount, wordFrequency, mostFrequentWord, mostFrequentCount);
    }

    /**
     * Gets the top N most frequent words from the tweet analysis
     *
     * @param wordFrequency Map containing word frequencies
     * @param n             Number of top words to retrieve
     * @return List of top N most frequent words with their counts
     */
    public static List<Map.Entry<String, Long>> getTopNWords(Map<String, Long> wordFrequency, int n) {
        // Sort the frequency map entries by count in descending order
        // Then limit to top N entries and collect to a list
        return wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Sort by frequency descending
                .limit(n) // Take only the top N entries
                .collect(Collectors.toList()); // Collect to list for return
    }

    /**
     * Main method for testing the tweet analyzer with various test cases
     * This method runs all test cases and reports pass/fail status
     */
    public static void main(String[] args) {
        // Initialize test counter to track total number of tests
        int testCount = 0;
        // Initialize pass counter to track successful tests
        int passCount = 0;

        System.out.println("=== TWEET ANALYZER TEST SUITE ===\n");

        // Test Case 1: Basic functionality with the provided sample
        testCount++; // Increment total test count
        System.out.println("Test Case 1: Basic Sample Tweet");
        String tweet1 = "this is a sample, sample is pretty easy. Sometimes easy is not pretty.";
        System.out.println("Input: \"" + tweet1 + "\"");

        // Analyze the tweet using our analyzer method
        TweetAnalysisResult result1 = analyzeTweet(tweet1);

        // Print the analysis results for verification
        System.out.println("Unique words: " + result1.uniqueWordCount());
        System.out.println("Word frequencies: " + result1.wordFrequency());
        System.out.println("Most frequent word: '" + result1.mostFrequentWord() + "' (appears " + result1.mostFrequentCount() + " times)");

        // Get top 3 most frequent words for additional analysis
        List<Map.Entry<String, Long>> top3 = getTopNWords(result1.wordFrequency(), 3);
        System.out.println("Top 3 frequent words: " + top3);

        // Verify expected results - checking if unique word count is correct
        boolean test1Pass = result1.uniqueWordCount() == 9; // Expected 9 unique words
        System.out.println("Result: " + (test1Pass ? "PASS" : "FAIL"));
        if (test1Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 2: Empty string edge case
        testCount++; // Increment test counter
        System.out.println("Test Case 2: Empty Tweet");
        String tweet2 = "";
        System.out.println("Input: \"" + tweet2 + "\"");

        // Analyze empty tweet to test edge case handling
        TweetAnalysisResult result2 = analyzeTweet(tweet2);
        System.out.println("Unique words: " + result2.uniqueWordCount());
        System.out.println("Most frequent word: '" + result2.mostFrequentWord() + "'");

        // Verify that empty tweet returns 0 unique words
        boolean test2Pass = result2.uniqueWordCount() == 0;
        System.out.println("Result: " + (test2Pass ? "PASS" : "FAIL"));
        if (test2Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 3: Single word repeated multiple times
        testCount++; // Increment test counter
        System.out.println("Test Case 3: Single Repeated Word");
        String tweet3 = "hello hello hello hello";
        System.out.println("Input: \"" + tweet3 + "\"");

        // Analyze tweet with single repeated word
        TweetAnalysisResult result3 = analyzeTweet(tweet3);
        System.out.println("Unique words: " + result3.uniqueWordCount());
        System.out.println("Most frequent word: '" + result3.mostFrequentWord() + "' (appears " + result3.mostFrequentCount() + " times)");

        // Verify single unique word with count of 4
        boolean test3Pass = result3.uniqueWordCount() == 1 && result3.mostFrequentCount() == 4;
        System.out.println("Result: " + (test3Pass ? "PASS" : "FAIL"));
        if (test3Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 4: All unique words (no repetition)
        testCount++; // Increment test counter
        System.out.println("Test Case 4: All Unique Words");
        String tweet4 = "every single word here appears once only";
        System.out.println("Input: \"" + tweet4 + "\"");

        // Analyze tweet where all words are unique
        TweetAnalysisResult result4 = analyzeTweet(tweet4);
        System.out.println("Unique words: " + result4.uniqueWordCount());
        System.out.println("Most frequent count: " + result4.mostFrequentCount());

        // Verify that all words are unique and most frequent count is 1
        boolean test4Pass = result4.uniqueWordCount() == 7 && result4.mostFrequentCount() == 1;
        System.out.println("Result: " + (test4Pass ? "PASS" : "FAIL"));
        if (test4Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 5: Special characters and punctuation handling
        testCount++; // Increment test counter
        System.out.println("Test Case 5: Special Characters");
        String tweet5 = "Hello! How are you? I'm fine, thanks! You're welcome.";
        System.out.println("Input: \"" + tweet5 + "\"");

        // Analyze tweet with punctuation to test cleaning functionality
        TweetAnalysisResult result5 = analyzeTweet(tweet5);
        System.out.println("Unique words: " + result5.uniqueWordCount());
        System.out.println("Word frequencies: " + result5.wordFrequency());

        // Verify that punctuation is properly removed
        boolean test5Pass = result5.uniqueWordCount() == 8; // Should have 8 unique words after cleaning
        System.out.println("Result: " + (test5Pass ? "PASS" : "FAIL"));
        if (test5Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 6: Large data input simulation
        testCount++; // Increment test counter
        System.out.println("Test Case 6: Large Data Input");

        // Create a large tweet by repeating words many times
        StringBuilder largeTweet = new StringBuilder();
        // Generate a tweet with 1000 words for performance testing
        for (int i = 0; i < 1000; i++) {
            largeTweet.append("word").append(i % 100).append(" "); // Create pattern of word0 to word99 repeated
        }

        System.out.println("Input: Large tweet with 1000 words (word0 to word99 pattern)");

        // Measure execution time for performance analysis
        long startTime = System.currentTimeMillis();
        TweetAnalysisResult result6 = analyzeTweet(largeTweet.toString());
        long endTime = System.currentTimeMillis();

        System.out.println("Unique words: " + result6.uniqueWordCount());
        System.out.println("Processing time: " + (endTime - startTime) + "ms");

        // Verify that large input is processed correctly (should have 100 unique words)
        boolean test6Pass = result6.uniqueWordCount() == 100;
        System.out.println("Result: " + (test6Pass ? "PASS" : "FAIL"));
        if (test6Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 7: Case sensitivity verification
        testCount++; // Increment test counter
        System.out.println("Test Case 7: Case Sensitivity");
        String tweet7 = "Hello HELLO hello HeLLo";
        System.out.println("Input: \"" + tweet7 + "\"");

        // Analyze tweet with different cases of same word
        TweetAnalysisResult result7 = analyzeTweet(tweet7);
        System.out.println("Unique words: " + result7.uniqueWordCount());
        System.out.println("Most frequent word: '" + result7.mostFrequentWord() + "' (appears " + result7.mostFrequentCount() + " times)");

        // Verify that case insensitive processing works (should be 1 unique word appearing 4 times)
        boolean test7Pass = result7.uniqueWordCount() == 1 && result7.mostFrequentCount() == 4;
        System.out.println("Result: " + (test7Pass ? "PASS" : "FAIL"));
        if (test7Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Test Case 8: Numbers and alphanumeric handling
        testCount++; // Increment test counter
        System.out.println("Test Case 8: Numbers and Alphanumeric");
        String tweet8 = "test123 test123 abc 123 abc 123";
        System.out.println("Input: \"" + tweet8 + "\"");

        // Analyze tweet with numbers and alphanumeric combinations
        TweetAnalysisResult result8 = analyzeTweet(tweet8);
        System.out.println("Unique words: " + result8.uniqueWordCount());
        System.out.println("Word frequencies: " + result8.wordFrequency());

        // Verify that numbers and alphanumeric are handled correctly
        boolean test8Pass = result8.uniqueWordCount() == 3; // Should have 3 unique: test123, abc, 123
        System.out.println("Result: " + (test8Pass ? "PASS" : "FAIL"));
        if (test8Pass) passCount++; // Increment pass count if test passed
        System.out.println();

        // Print final test summary with pass/fail statistics
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("Total Tests: " + testCount); // Display total number of tests run
        System.out.println("Passed: " + passCount); // Display number of tests that passed
        System.out.println("Failed: " + (testCount - passCount)); // Calculate and display failed tests
        System.out.println("Success Rate: " + String.format("%.1f", (passCount * 100.0 / testCount)) + "%"); // Calculate success percentage

        // Print overall result based on whether all tests passed
        if (passCount == testCount) {
            System.out.println("\n🎉 ALL TESTS PASSED! 🎉"); // Celebrate if all tests passed
        } else {
            System.out.println("\n❌ SOME TESTS FAILED ❌"); // Alert if any tests failed
        }
    }

    /**
     * Data class to hold the results of tweet analysis
     *
     * @param uniqueWordCount   Number of unique words in the tweet
     * @param wordFrequency     Map of word to frequency count
     * @param mostFrequentWord  The word that appears most often
     * @param mostFrequentCount How many times the most frequent word appears
     */
    record TweetAnalysisResult(long uniqueWordCount, Map<String, Long> wordFrequency, String mostFrequentWord,
                               long mostFrequentCount) {
        // Constructor to initialize all fields with analysis results
        // Set the unique word count
        // Set the frequency map
        // Set the most frequent word
        // Set the most frequent count
    }
}
