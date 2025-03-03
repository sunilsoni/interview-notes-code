package com.interview.notes.code.year.y2025.jan.amazon.test3;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/*

"Let's imagine you're tasked with designing a system to determine the most popular items on Amazon.
I'd like you to tell me how you'd go about calculating what the most popular items on a website are.

You can assume that there's a service you can subscribe to that
sends you a {customerID, itemID, timestamp} message whenever a customer purchases an item."


 */
public class PopularItemTracker {
    private final Map<String, Integer> itemCounts;
    private final Map<String, Entry> itemEntries;
    private final PriorityQueue<Entry> topItemsHeap;
    private final int k;
    private final ReentrantLock lock;
    private final boolean isThreadSafe;

    /**
     * Constructor for PopularItemTracker
     *
     * @param k          The number of top items to track
     * @param threadSafe Whether to make operations thread-safe
     */
    public PopularItemTracker(int k, boolean threadSafe) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        this.k = k;
        this.isThreadSafe = threadSafe;
        this.itemCounts = new HashMap<>();
        this.itemEntries = new HashMap<>();
        this.topItemsHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.count, b.count));
        this.lock = threadSafe ? new ReentrantLock() : null;
    }

    /**
     * Constructor defaulting to non-thread-safe operation
     *
     * @param k The number of top items to track
     */
    public PopularItemTracker(int k) {
        this(k, false);
    }

    // Main method with usage example and performance test
    public static void main(String[] args) {
        // Example usage
        PopularItemTracker tracker = new PopularItemTracker(3);

        // Update some items
        String[] items = {"A", "B", "A", "C", "A", "B", "D", "E", "A", "B"};
        for (String item : items) {
            tracker.updateItem(item);
        }

        // Print top k items
        System.out.println("Top items: " + tracker.getTopK());
        System.out.println("Count of 'A': " + tracker.getCount("A"));
        System.out.println("Total unique items: " + tracker.getTotalItems());

        // Performance test
        System.out.println("\nPerformance Test:");
        int k = 10;
        int numOperations = 1_000_000;
        PopularItemTracker perfTracker = new PopularItemTracker(k, true);
        Random rand = new Random();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numOperations; i++) {
            String itemId = "item" + rand.nextInt(1000);
            perfTracker.updateItem(itemId);
        }
        long endTime = System.currentTimeMillis();

        System.out.printf("Processed %d updates in %d ms%n",
                numOperations, (endTime - startTime));
        System.out.println("Top " + k + " items: " + perfTracker.getTopK());
    }

    /**
     * Updates the count for an item and maintains the top-k heap
     * Time Complexity: O(log k)
     *
     * @param itemId The item to update
     */
    public void updateItem(String itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId cannot be null");
        }

        if (isThreadSafe) {
            lock.lock();
        }
        try {
            // Update count in the main map - O(1)
            int newCount = itemCounts.merge(itemId, 1, Integer::sum);

            Entry entry = itemEntries.get(itemId);

            if (entry == null) {
                // Item not in heap
                if (topItemsHeap.size() < k) {
                    // Heap not full - O(log k)
                    entry = new Entry(itemId, newCount);
                    topItemsHeap.offer(entry);
                    itemEntries.put(itemId, entry);
                } else {
                    // Heap full, check if new count beats minimum
                    Entry minEntry = topItemsHeap.peek();
                    if (newCount > minEntry.count) {
                        // Replace minimum - O(log k)
                        topItemsHeap.poll();
                        itemEntries.remove(minEntry.itemId);

                        entry = new Entry(itemId, newCount);
                        topItemsHeap.offer(entry);
                        itemEntries.put(itemId, entry);
                    }
                }
            } else {
                // Item already in heap - O(log k)
                topItemsHeap.remove(entry);
                entry.count = newCount;
                topItemsHeap.offer(entry);
            }
        } finally {
            if (isThreadSafe) {
                lock.unlock();
            }
        }
    }

    /**
     * Gets the top k items sorted by count in descending order
     * Time Complexity: O(k log k)
     *
     * @return List of top k items
     */
    public List<String> getTopK() {
        if (isThreadSafe) {
            lock.lock();
        }
        try {
            PriorityQueue<Entry> tempHeap = new PriorityQueue<>(
                    (a, b) -> Integer.compare(b.count, a.count)
            );
            tempHeap.addAll(topItemsHeap);

            List<String> result = new ArrayList<>();
            while (!tempHeap.isEmpty() && result.size() < k) {
                result.add(tempHeap.poll().itemId);
            }
            return result;
        } finally {
            if (isThreadSafe) {
                lock.unlock();
            }
        }
    }

    /**
     * Gets the count for a specific item
     * Time Complexity: O(1)
     *
     * @param itemId The item to get the count for
     * @return The count of the item, or 0 if not found
     */
    public int getCount(String itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId cannot be null");
        }

        if (isThreadSafe) {
            lock.lock();
        }
        try {
            return itemCounts.getOrDefault(itemId, 0);
        } finally {
            if (isThreadSafe) {
                lock.unlock();
            }
        }
    }

    /**
     * Gets the total number of unique items being tracked
     * Time Complexity: O(1)
     *
     * @return Total number of unique items
     */
    public int getTotalItems() {
        if (isThreadSafe) {
            lock.lock();
        }
        try {
            return itemCounts.size();
        } finally {
            if (isThreadSafe) {
                lock.unlock();
            }
        }
    }

    /**
     * Custom Entry class to maintain item data in the heap
     */
    private static class Entry {
        String itemId;
        int count;

        Entry(String itemId, int count) {
            this.itemId = itemId;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("%s:%d", itemId, count);
        }
    }
}
