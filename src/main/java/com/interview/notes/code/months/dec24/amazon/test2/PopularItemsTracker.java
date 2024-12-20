package com.interview.notes.code.months.dec24.amazon.test2;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*

Amazon sells millions of products on its website and for better customer experience we like to show a
widget with the "most popular items bought" on the home page.
I'd like you to tell me how you'd go about calculating the top-k popular items sold on Amazon.
You can assume that you have a service and your service gets notified
in the form of a {customerId, itemId, timestamp} message whenever a customer purchases an item

 */
public class PopularItemsTracker {
    // Map to store item counts, where key is itemId and value is the count of purchases
    private final Map<String, Integer> itemCounts;
    // Queue to keep track of recent purchases within the given time window
    private final Queue<Purchase> recentPurchases;
    // Time window in hours for considering recent purchases
    private final int windowHours;
    // Number of top items to be retrieved
    private final int topK;

    // Constructor to initialize the tracker with a specified time window and number of top items
    public PopularItemsTracker(int windowHours, int topK) {
        this.itemCounts = new ConcurrentHashMap<>();
        this.recentPurchases = new LinkedList<>();
        this.windowHours = windowHours;
        this.topK = topK;
    }

    public static void main(String[] args) {
        // Test cases
        PopularItemsTracker tracker = new PopularItemsTracker(24, 3);
        Instant now = Instant.now();

        // Test 1: Basic functionality
        System.out.println("Test 1: Basic functionality");
        tracker.recordPurchase("user1", "item1", now);
        tracker.recordPurchase("user2", "item1", now);
        tracker.recordPurchase("user3", "item2", now);
        // Should print the most popular items based on recorded purchases
        System.out.println("Top items: " + tracker.getTopItems());
        System.out.println("Expected: [item1, item2]");

        // Test 2: Time window
        System.out.println("\nTest 2: Time window");
        tracker = new PopularItemsTracker(24, 3);
        // Record a purchase that is outside the time window (25 hours ago)
        tracker.recordPurchase("user1", "item1", now.minus(25, ChronoUnit.HOURS));
        tracker.recordPurchase("user2", "item2", now);
        // Only item2 should be in the top items as item1 is outside the time window
        System.out.println("Top items: " + tracker.getTopItems());
        System.out.println("Expected: [item2]");

        // Test 3: Large data
        System.out.println("\nTest 3: Large data");
        tracker = new PopularItemsTracker(24, 3);
        Random random = new Random();
        // Simulate 10,000 purchase events with random item IDs
        for (int i = 0; i < 10000; i++) {
            String itemId = "item" + (random.nextInt(100) + 1);
            tracker.recordPurchase("user" + i, itemId, now);
        }
        // Print the top 3 items after processing large data
        System.out.println("Top 3 items: " + tracker.getTopItems());
    }

    // Method to record a purchase event
    public void recordPurchase(String customerId, String itemId, Instant timestamp) {
        Purchase purchase = new Purchase(customerId, itemId, timestamp);
        synchronized (this) {
            // Add the new purchase to the queue
            recentPurchases.add(purchase);
            // Update the item count, incrementing by 1 or initializing to 1 if not present
            itemCounts.merge(itemId, 1, Integer::sum);
            // Remove old purchases that are outside the time window
            removeOldPurchases(timestamp);
        }
    }

    // Method to remove purchases that are outside the specified time window
    private void removeOldPurchases(Instant currentTime) {
        // Remove purchases from the queue if they are older than the time window
        while (!recentPurchases.isEmpty() &&
                ChronoUnit.HOURS.between(recentPurchases.peek().timestamp, currentTime) >= windowHours) {
            // Remove the oldest purchase from the queue
            Purchase oldPurchase = recentPurchases.poll();
            // Update the item count, decrementing by 1 or removing if count reaches 0
            itemCounts.compute(oldPurchase.itemId, (k, v) -> v == 1 ? null : v - 1);
        }
    }

    // Method to get the top-k popular items based on the purchase count
    public List<String> getTopItems() {
        // Sort the items by their count in descending order and return the top-k items
        return itemCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(topK)
                .map(Map.Entry::getKey)
                .toList();
    }

    // Inner class to represent a purchase event
    static class Purchase {
        String customerId; // Customer who made the purchase
        String itemId;     // Item that was purchased
        Instant timestamp; // Timestamp of the purchase

        Purchase(String customerId, String itemId, Instant timestamp) {
            this.customerId = customerId;
            this.itemId = itemId;
            this.timestamp = timestamp;
        }
    }
}
