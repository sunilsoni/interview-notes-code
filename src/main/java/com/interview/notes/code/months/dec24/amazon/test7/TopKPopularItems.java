package com.interview.notes.code.months.dec24.amazon.test7;

import java.util.*;

/*

Amazon sells millions of products on its website and for better customer experience we like to show a
widget with the "most popular items bought" on the home page.
I'd like you to tell me how you'd go about calculating the top-k popular items sold on Amazon.
You can assume that you have a service and your service gets notified
in the form of a {customerId, itemId, timestamp} message whenever a customer purchases an item


 */
public class TopKPopularItems {

    public static List<String> getTopKPopularItems(List<String[]> purchases, int k) {
        // Map to count the purchases of each item
        Map<String, Integer> itemCountMap = new HashMap<>();

        // Process each purchase
        for (String[] purchase : purchases) {
            String itemId = purchase[1];
            itemCountMap.put(itemId, itemCountMap.getOrDefault(itemId, 0) + 1);
        }

        // Use a min-heap to keep the top-k items
        PriorityQueue<Item> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.count));

        // Add items to the heap
        for (Map.Entry<String, Integer> entry : itemCountMap.entrySet()) {
            String itemId = entry.getKey();
            int count = entry.getValue();
            Item item = new Item(itemId, count);

            // If heap size is less than k, add the item
            if (minHeap.size() < k) {
                minHeap.offer(item);
            } else if (minHeap.peek().count < count) {
                minHeap.poll();
                minHeap.offer(item);
            }
        }

        // Extract the top-k popular items
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().itemId);
        }

        // Reverse the list to get the items in descending order
        Collections.reverse(result);
        return result;
    }

    // Method to test the solution
    public static void main(String[] args) {
        // Test cases
        List<String[]> purchases1 = Arrays.asList(
                new String[]{"1", "itemA", "2024-12-01T10:00:00"},
                new String[]{"2", "itemB", "2024-12-01T11:00:00"},
                new String[]{"3", "itemA", "2024-12-01T12:00:00"},
                new String[]{"4", "itemC", "2024-12-01T13:00:00"},
                new String[]{"5", "itemA", "2024-12-01T14:00:00"}
        );

        List<String[]> purchases2 = Arrays.asList(
                new String[]{"1", "itemA", "2024-12-01T10:00:00"},
                new String[]{"2", "itemB", "2024-12-01T11:00:00"},
                new String[]{"3", "itemC", "2024-12-01T12:00:00"},
                new String[]{"4", "itemD", "2024-12-01T13:00:00"}
        );

        // Define k (top-k popular items)
        int k = 2;

        // Execute and print results
        System.out.println("Top " + k + " popular items for purchases1: " + getTopKPopularItems(purchases1, k));
        System.out.println("Top " + k + " popular items for purchases2: " + getTopKPopularItems(purchases2, k));
    }

    // A helper class to represent an item and its count
    static class Item {
        String itemId;
        int count;

        Item(String itemId, int count) {
            this.itemId = itemId;
            this.count = count;
        }
    }
}
