package com.interview.notes.code.year.y2025.july.amazon.test5;

public class StoreTest {
    public static void main(String[] args) {
        // Test Case 1: Basic functionality
        Store store = new Store(2);
        System.out.println("Test 1: Empty store wait time: " + 
                          (store.getWaitTimeMinutes() == 0 ? "PASS" : "FAIL"));

        // Test Case 2: Store at capacity
        store.enterStore(30); // First customer: 30 minutes
        store.enterStore(45); // Second customer: 45 minutes
        System.out.println("Test 2: Full store wait time: " + 
                          (store.getWaitTimeMinutes() > 0 ? "PASS" : "FAIL"));

        // Test Case 3: Customer leaving
        store.advanceTime(31); // First customer should leave
        System.out.println("Test 3: After customer leaves: " + 
                          (store.getWaitTimeMinutes() == 0 ? "PASS" : "FAIL"));

        // Test Case 4: Large number of customers
        Store largeStore = new Store(100);
        for (int i = 0; i < 100; i++) {
            largeStore.enterStore(30);
        }
        System.out.println("Test 4: Large store handling: " + 
                          (largeStore.getWaitTimeMinutes() > 0 ? "PASS" : "FAIL"));
    }
}
