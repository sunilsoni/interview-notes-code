package com.interview.notes.code.year.y2025.july.amazon.test8;

import java.time.LocalDate;                             // For expiration dates
import java.util.*;                                     // For Map, List, etc.
import java.util.stream.*;                              // For Java 8 Stream API

public class SupermarketStorageSystem {
    
    // Internal class representing a batch of a product
    private static class Batch {
        private final LocalDate expirationDate;            // Expiration of this batch
        private int quantity;                              // Current quantity in this batch
        private final long timestamp;                      // Order timestamp for tie-breaking

        // Constructor to initialize all fields
        public Batch(LocalDate expirationDate, int quantity, long timestamp) {
            this.expirationDate = expirationDate;         // Set expiration date
            this.quantity = quantity;                     // Set initial quantity
            this.timestamp = timestamp;                   // Set insertion timestamp
        }

        // Getter for quantity
        public int getQuantity() {
            return quantity;                              // Return current quantity
        }

        // Setter for quantity
        public void setQuantity(int quantity) {
            this.quantity = quantity;                     // Update quantity
        }

        // Getter for timestamp
        public long getTimestamp() {
            return timestamp;                             // Return batch timestamp
        }
    }

    private final Map<Integer, List<Batch>> inventory;     // Map productId → list of batches
    private long timestampCounter = 0;                     // Monotonic counter for timestamps

    // Constructor: initialize the inventory map
    public SupermarketStorageSystem() {
        inventory = new HashMap<>();                        // Create empty HashMap
    }

    // Add a new batch of products
    public void purchase(int productId, LocalDate expirationDate, int quantity) {
        List<Batch> batches = inventory.computeIfAbsent(
            productId, k -> new ArrayList<>()               // Create new list if absent
        );                                                  
        batches.add(new Batch(expirationDate, quantity, timestampCounter++));
                                                          // Append batch with unique timestamp
    }

    // Remove quantity from a specific batch
    public void remove(int productId, LocalDate expirationDate, int quantity) {
        List<Batch> batches = inventory.get(productId);     // Fetch batches for this product
        if (batches == null) return;                        // No such product → nothing to do

        // Find first batch matching the expiration date
        for (Iterator<Batch> it = batches.iterator(); it.hasNext();) {
            Batch b = it.next();                           
            if (b.expirationDate.equals(expirationDate)) {
                int newQty = b.getQuantity() - quantity;    // Compute remaining quantity
                if (newQty > 0) {
                    b.setQuantity(newQty);                  // Update batch quantity
                } else {
                    it.remove();                            // Remove batch if depleted
                }
                break;                                      // Done removing
            }
        }
        // Clean up empty batch lists
        if (batches.isEmpty()) {
            inventory.remove(productId);                     // Remove product if no batches left
        }
    }

    // Determine which product to reorder next
    public int reorder() {
        return inventory.entrySet().stream()                // Stream over product entries
            // Keep only products whose total quantity is below threshold
            .filter(entry -> entry.getValue().stream()
                .mapToInt(Batch::getQuantity)
                .sum() < 20
            )
            // Among those, find the one whose oldest batch timestamp is minimal
            .min(Comparator.comparing(entry ->
                entry.getValue().stream()
                    .mapToLong(Batch::getTimestamp)
                    .min()
                    .orElse(Long.MAX_VALUE)
            ))
            .map(Map.Entry::getKey)                         // Extract the productId
            .orElse(-1);                                    // If none, return -1
    }

    // Simple main method for testing various scenarios
    public static void main(String[] args) {
        SupermarketStorageSystem sys = new SupermarketStorageSystem(); // Create system

        // ----- Test 1: No product below threshold → expect -1 -----
        sys.purchase(1, LocalDate.parse("2025-08-01"), 10);           // Add 10 units of prod 1
        sys.purchase(1, LocalDate.parse("2025-09-01"), 15);           // Add another 15 units
        int result1 = sys.reorder();                                  // Should be -1
        System.out.println("Test 1 " + (result1 == -1 ? "PASS" : "FAIL"));

        // ----- Test 2: After removal, total = 18 (<20) → expect 1 -----
        sys.remove(1, LocalDate.parse("2025-08-01"), 7);              // Remove 7 units from first batch
        int result2 = sys.reorder();                                  // Now total=3+15=18 → reorder=1
        System.out.println("Test 2 " + (result2 == 1 ? "PASS" : "FAIL"));

        // ----- Test 3: Add another low-stock product, but product 1 is older → expect 1 -----
        sys.purchase(2, LocalDate.parse("2025-07-30"), 5);            // Add prod 2 with qty 5
        int result3 = sys.reorder();                                  // Both <20, but prod 1 has older batch
        System.out.println("Test 3 " + (result3 == 1 ? "PASS" : "FAIL"));

        // ----- Large data test: 10 001 products, only product 0 qualifies → expect 0 -----
        SupermarketStorageSystem big = new SupermarketStorageSystem(); // New system
        big.purchase(0, LocalDate.parse("2025-12-31"), 5);            // Only this is low stock
        // Add 10 000 products with high stock
        for (int i = 1; i <= 10000; i++) {
            big.purchase(i, LocalDate.parse("2025-12-31"), 100);
        }
        int resultBig = big.reorder();                                // Should pick 0
        System.out.println("Large Data Test " + (resultBig == 0 ? "PASS" : "FAIL"));
    }
}