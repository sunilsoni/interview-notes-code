package com.interview.notes.code.year.y2025.july.amazon.test5;

import java.time.LocalDate;
import java.util.*;

/*
Here is the complete, properly formatted question based on the image:

---

### **Problem Statement: Supermarket Storage System**

We are building a **supermarket storage system** where each product is identified by a **product ID**. Products are managed in **batches**, with each batch uniquely identified by its **product ID** and **expiration date**.

---

### **Implement the following functions:**

#### 1. `purchase/add(productId, expirationDate, quantity)`

* Add a **new batch** of products by specifying the product ID, expiration date, and quantity.

#### 2. `remove(productId, expirationDate, quantity)`

* Remove a **specified quantity** of products from a specific batch identified by product ID and expiration date.

#### 3. `reorder() → productId`

* Return the **single most urgent product ID** to reorder next, prioritized by:

  * Products whose **total quantity is less than 20** have **highest reorder priority**.
  * If there is a **tie**, reorder the product **whose oldest batch** has the **earliest order timestamp**.

---


 */
public class SupermarketStorage {

    private final Map<Integer, List<Batch>> inventory = new HashMap<>();
    private long sequence;

    /**
     * Simple main to verify correctness on small tests
     */
    public static void main(String[] args) {
        SupermarketStorage s = new SupermarketStorage();

        // Test 1: no low-stock → –1
        s.addBatch(1, LocalDate.parse("2025-08-01"), 10);
        s.addBatch(1, LocalDate.parse("2025-09-01"), 15);
        System.out.println(s.getReorderProduct() == -1 ? "PASS" : "FAIL");

        // Test 2: now total=18 → 1
        s.removeBatch(1, LocalDate.parse("2025-08-01"), 7);
        System.out.println(s.getReorderProduct() == 1 ? "PASS" : "FAIL");

        // Test 3: add product 2 with 5 units → still 1 (older batch wins)
        s.addBatch(2, LocalDate.parse("2025-07-30"), 5);
        System.out.println(s.getReorderProduct() == 1 ? "PASS" : "FAIL");
    }

    /**
     * Add a new batch of `quantity` for `productId` expiring on `expiryDate`.
     */
    public void addBatch(int productId, LocalDate expiryDate, int quantity) {
        inventory
                .computeIfAbsent(productId, k -> new ArrayList<>())
                .add(new Batch(expiryDate, quantity, sequence++));
    }

    /**
     * Remove `quantity` from the batch matching `productId` + `expiryDate`.
     */
    public void removeBatch(int productId, LocalDate expiryDate, int quantity) {
        List<Batch> batches = inventory.get(productId);
        if (batches == null) return;

        // Find the batch and decrement its qty
        batches.stream()
                .filter(b -> b.expiry.equals(expiryDate))
                .findFirst()
                .ifPresent(b -> b.qty = Math.max(0, b.qty - quantity));

        // Purge any empty batches; if none remain, drop the product entirely
        batches.removeIf(b -> b.qty == 0);
        if (batches.isEmpty()) inventory.remove(productId);
    }

    /**
     * Return the single product ID whose total stock < 20
     * and whose oldest batch was added earliest.
     * Returns –1 if none qualify.
     */
    public int getReorderProduct() {
        return inventory.entrySet().stream()
                // only products below threshold
                .filter(e -> e.getValue().stream().mapToInt(b -> b.qty).sum() < 20)
                // pick the one whose minimal batch-ts is smallest
                .min(Comparator.comparingLong(e ->
                        e.getValue().stream()
                                .mapToLong(b -> b.ts)
                                .min()
                                .orElse(Long.MAX_VALUE)))
                .map(Map.Entry::getKey)
                .orElse(-1);
    }

    private static class Batch {
        LocalDate expiry;
        int qty;
        long ts;

        Batch(LocalDate e, int q, long t) {
            expiry = e;
            qty = q;
            ts = t;
        }
    }
}