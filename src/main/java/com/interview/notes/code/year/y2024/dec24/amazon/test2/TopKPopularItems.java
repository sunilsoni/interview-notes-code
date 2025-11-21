package com.interview.notes.code.year.y2024.dec24.amazon.test2;

import java.util.*;

/**
 * This class calculates the top-k popular items sold based on purchase notifications.
 */
public class TopKPopularItems {

    // Map to store the count of purchases for each itemId
    private final Map<String, Integer> itemPurchaseCounts = new HashMap<>();

    /**
     * Main method to run test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        TopKPopularItems topKPopularItems = new TopKPopularItems();

        // Test Case 1: Basic functionality test
        topKPopularItems.processPurchase("C1", "ItemA", 1000);
        topKPopularItems.processPurchase("C2", "ItemB", 1001);
        topKPopularItems.processPurchase("C3", "ItemA", 1002);
        topKPopularItems.processPurchase("C4", "ItemC", 1003);
        topKPopularItems.processPurchase("C5", "ItemB", 1004);
        topKPopularItems.processPurchase("C6", "ItemA", 1005);

        List<String> result = topKPopularItems.getTopKItems(2);
        List<String> expected = Arrays.asList("ItemA", "ItemB");

        if (result.equals(expected)) {
            System.out.println("Test Case 1 Passed.");
        } else {
            System.out.println("Test Case 1 Failed. Expected: " + expected + ", Got: " + result);
        }

        // Test Case 2: Edge case with ties in popularity
        topKPopularItems = new TopKPopularItems();
        topKPopularItems.processPurchase("C1", "ItemA", 1000);
        topKPopularItems.processPurchase("C2", "ItemB", 1001);
        topKPopularItems.processPurchase("C3", "ItemC", 1002);
        topKPopularItems.processPurchase("C4", "ItemD", 1003);

        result = topKPopularItems.getTopKItems(2);
        expected = Arrays.asList("ItemD", "ItemC"); // Any two items since all have same count

        if (result.size() == 2 && itemCountsMatch(result, expected)) {
            System.out.println("Test Case 2 Passed.");
        } else {
            System.out.println("Test Case 2 Failed. Got: " + result);
        }

        // Test Case 3: Large data input
        topKPopularItems = new TopKPopularItems();
        int largeDataSize = 1000000;
        for (int i = 0; i < largeDataSize; i++) {
            topKPopularItems.processPurchase("C" + i, "Item" + (i % 100), System.currentTimeMillis());
        }

        result = topKPopularItems.getTopKItems(5);
        if (result.size() == 5) {
            System.out.println("Test Case 3 Passed.");
        } else {
            System.out.println("Test Case 3 Failed. Expected top 5 items, Got: " + result.size());
        }
    }

    /**
     * Helper method to check if two lists have the same items regardless of order.
     *
     * @param list1 The first list.
     * @param list2 The second list.
     * @return True if both lists contain the same items.
     */
    private static boolean itemCountsMatch(List<String> list1, List<String> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    /**
     * Processes a purchase event.
     *
     * @param customerId The ID of the customer making the purchase.
     * @param itemId     The ID of the item being purchased.
     * @param timestamp  The timestamp of the purchase.
     */
    public void processPurchase(String customerId, String itemId, long timestamp) {
        itemPurchaseCounts.put(itemId, itemPurchaseCounts.getOrDefault(itemId, 0) + 1);
    }

    /**
     * Retrieves the top-k popular items based on purchase counts.
     *
     * @param k The number of top items to retrieve.
     * @return A list of the top-k item IDs.
     */
    public List<String> getTopKItems(int k) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        for (Map.Entry<String, Integer> entry : itemPurchaseCounts.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> topKItems = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topKItems.add(minHeap.poll().getKey());
        }
        Collections.reverse(topKItems); // Optional: To have highest counts first
        return topKItems;
    }
}
