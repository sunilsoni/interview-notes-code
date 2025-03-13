package com.interview.notes.code.year.y2025.march.amazon.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Amazon Product Search Autocomplete implementation using a Trie data structure
 */
public class AmazonProductSearch {

    private TrieNode root; // Root of our Trie

    public AmazonProductSearch() {
        root = new TrieNode(); // Initialize the Trie with an empty root
    }

    /**
     * Main method to demonstrate and test the product search functionality
     */
    public static void main(String[] args) {
        // Create and populate the search engine with sample products
        AmazonProductSearch searchEngine = new AmazonProductSearch();

        // Add sample products
        String[] products = {
                "Handsoap", "Hairbrush", "Headphones", "Hand sanitizer",
                "Hammer", "Hat", "Handbag", "Highlighter", "HDMI cable",
                "Honey", "Harry Potter book", "Hiking boots", "Hand cream",
                "Humidifier", "Hockey stick", "Hair dryer", "Hoodie"
        };

        for (String product : products) {
            searchEngine.addProduct(product);
        }

        // Test cases
        testSearch(searchEngine, "ha", 5);
        testSearch(searchEngine, "hand", 10);
        testSearch(searchEngine, "he", 3);
        testSearch(searchEngine, "z", 5); // No matches expected
        testSearch(searchEngine, "", 5); // Empty search
        testSearch(searchEngine, "h", 20); // Many matches

        // Test with large dataset
        testLargeDataset();
    }

    /**
     * Helper method to test and display search results
     */
    private static void testSearch(AmazonProductSearch engine, String prefix, int limit) {
        System.out.println("\nSearch for '" + prefix + "' (limit: " + limit + "):");
        List<String> results = engine.searchProducts(prefix, limit);

        if (results.isEmpty()) {
            System.out.println("  No matches found");
        } else {
            for (String result : results) {
                System.out.println("  → " + result);
            }
        }

        // Validate the results
        boolean pass = validateSearchResults(results, prefix, limit);
        System.out.println("  Test: " + (pass ? "PASS" : "FAIL"));
    }

    /**
     * Validates search results against expected behavior
     */
    private static boolean validateSearchResults(List<String> results, String prefix, int limit) {
        // Check if results don't exceed the limit
        if (results.size() > limit) {
            return false;
        }

        // For non-empty prefix, check if all results start with the prefix (case-insensitive)
        if (!prefix.isEmpty()) {
            for (String result : results) {
                if (!result.toLowerCase().startsWith(prefix.toLowerCase())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Tests the solution with a large dataset to verify performance
     */
    private static void testLargeDataset() {
        System.out.println("\n=== Large Dataset Test ===");

        AmazonProductSearch largeEngine = new AmazonProductSearch();

        // Generate a large set of products (10,000 products)
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            largeEngine.addProduct("Product" + i);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Added 10,000 products in " + (endTime - startTime) + "ms");

        // Test search performance
        startTime = System.currentTimeMillis();
        List<String> results = largeEngine.searchProducts("pro", 20);
        endTime = System.currentTimeMillis();

        System.out.println("Found " + results.size() + " matches for 'pro' in " + (endTime - startTime) + "ms");
        System.out.println("First few results: " + results.subList(0, Math.min(5, results.size())));

        boolean pass = validateSearchResults(results, "pro", 20);
        System.out.println("Large dataset test: " + (pass ? "PASS" : "FAIL"));
    }

    /**
     * Adds a product to the search index
     *
     * @param product The product name to add
     */
    public void addProduct(String product) {
        if (product == null || product.isEmpty()) {
            return; // Skip empty or null products
        }

        TrieNode current = root;

        // Insert the product character by character into the Trie
        for (char c : product.toLowerCase().toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode()); // Create node if it doesn't exist
            current = current.children.get(c); // Move to the next node
        }

        // Mark the end of the product name and store the full product
        current.isEndOfWord = true;
        current.product = product;
    }

    /**
     * Searches for products that match the given prefix
     *
     * @param prefix The search prefix typed by the user
     * @param limit  Maximum number of suggestions to return
     * @return List of matching product suggestions
     */
    public List<String> searchProducts(String prefix, int limit) {
        List<String> suggestions = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()) {
            return suggestions; // Return empty list for empty search
        }

        TrieNode current = root;
        prefix = prefix.toLowerCase(); // Case-insensitive search

        // Navigate to the node representing the prefix
        for (char c : prefix.toCharArray()) {
            if (!current.children.containsKey(c)) {
                return suggestions; // Prefix not found, return empty list
            }
            current = current.children.get(c);
        }

        // Collect all products starting with this prefix using DFS
        collectSuggestions(current, suggestions, limit);
        return suggestions;
    }

    /**
     * Helper method to collect all product suggestions using DFS
     */
    private void collectSuggestions(TrieNode node, List<String> suggestions, int limit) {
        // Base case: if we've reached our suggestion limit
        if (suggestions.size() >= limit) {
            return;
        }

        // If this node is a complete product, add it to suggestions
        if (node.isEndOfWord) {
            suggestions.add(node.product);
        }

        // Explore all child nodes in alphabetical order for consistent results
        node.children.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort alphabetically
                .forEach(entry -> collectSuggestions(entry.getValue(), suggestions, limit));
    }

    // Trie node class to store product names efficiently for prefix searches
    private static class TrieNode {
        Map<Character, TrieNode> children; // Maps characters to child nodes
        boolean isEndOfWord; // Marks if this node represents the end of a product name
        String product; // Stores the complete product name at terminal nodes

        public TrieNode() {
            children = new HashMap<>(); // Initialize the children map
            isEndOfWord = false; // Default: not end of word
            product = null; // No product stored initially
        }
    }
}