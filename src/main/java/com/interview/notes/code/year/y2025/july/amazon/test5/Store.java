package com.interview.notes.code.year.y2025.july.amazon.test5;

import java.util.LinkedList;
import java.util.Queue;

/*

Virtual Grocery-Store Queue

A grocery store can hold at most N patrons inside at once. Customers who want to enter join a virtual queue and are admitted one at a time as space frees up. Each customer, upon entering, tells us how many minutes they plan to spend in the store.

Goal:
	1.	Track exactly who is inside and when each will depart.
	2.	Expose an API enter(durationInMinutes) that records a new entrant’s planned stay.
	3.	Expose an API getEstimatedWaitTime() that returns, in whole minutes, how long the next waiting customer will have to wait before being admitted.

Assumptions & Clarifications
	•	Store capacity is fixed at N and it’s open 24/7.
	•	Customers leave exactly at their allotted time (no early exits unless an explicit exit API is added).
	•	First-in, first-out ordering for both entry and exit.
	•	If fewer than N are inside, getEstimatedWaitTime() returns zero.
	•	No external testing framework — just a main method that runs pass/fail checks.
	•	Use Java 8 and the Streams/Collections APIs.
	•	Add comments explaining any non-trivial logic in simple language.
	•	Provide a simple main-based test harness that checks all provided scenarios (empty store, partially filled, full store, equal durations, large inputs).

 */
/**
 * Store class manages virtual queue system for retail stores during COVID-19
 * Handles customer entry/exit tracking and wait time calculations
 * Time Complexity: O(1) for most operations, O(n) for store updates
 * Space Complexity: O(n) where n is store capacity
 */
public class Store {
    // Maximum number of customers allowed in store at any time (COVID-19 restriction)
    private final int capacity;

    // Queue implementation using LinkedList for O(1) add/remove operations
    // Maintains FIFO order of customers based on entry time
    private Queue<Customer> customersInStore;

    // Tracks current system time in milliseconds
    // Used for calculating customer entry/exit times and wait durations
    private long currentTime;

    /**
     * Initializes a new store with specified capacity limit
     * @param capacity Maximum number of customers allowed simultaneously
     * @throws IllegalArgumentException if capacity is negative or zero
     */
    public Store(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Store capacity must be positive");
        }
        this.capacity = capacity;
        this.customersInStore = new LinkedList<>();
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * Inner class representing a customer in the store
     * Encapsulates customer timing data for wait time calculations
     */
    private static class Customer {
        // Timestamp when customer entered store (in milliseconds)
        long entryTime;

        // How long customer plans to stay (in minutes)
        int plannedDuration;

        /**
         * Creates new customer with entry time and planned shopping duration
         * @param entryTime System time when customer enters
         * @param plannedDuration Expected shopping duration in minutes
         */
        Customer(long entryTime, int plannedDuration) {
            this.entryTime = entryTime;
            this.plannedDuration = plannedDuration;
        }
    }

    /**
     * Attempts to add a new customer to the store
     * @param plannedDurationMinutes Expected shopping duration for customer
     * @return true if customer can enter, false if store is at capacity
     * @throws IllegalArgumentException if planned duration is negative
     */
    public boolean enterStore(int plannedDurationMinutes) {
        if (plannedDurationMinutes <= 0) {
            throw new IllegalArgumentException("Shopping duration must be positive");
        }

        // Clean up any customers who should have left
        updateStore();

        // Check if store has reached capacity
        if (customersInStore.size() >= capacity) {
            return false; // Store is full
        }

        // Add new customer with current time and planned duration
        customersInStore.add(new Customer(currentTime, plannedDurationMinutes));
        return true;
    }

    /**
     * Calculates wait time for next customer trying to enter
     * @return Expected wait time in minutes, 0 if no wait
     */
    public int getWaitTimeMinutes() {
        // If store isn't at capacity, customer can enter immediately
        if (customersInStore.size() < capacity) {
            return 0;
        }

        // Update store state and recheck if space available
        updateStore();
        if (customersInStore.isEmpty()) {
            return 0;
        }

        // Calculate wait time based on when earliest customer will leave
        Customer earliestLeaving = customersInStore.peek();
        // Convert from milliseconds to minutes, rounding up
        return (int)((earliestLeaving.entryTime +
                (earliestLeaving.plannedDuration * 60000) - currentTime) / 60000);
    }

    /**
     * Updates store state by removing customers who have completed shopping
     * Called before any operation that needs current store state
     * Time Complexity: O(n) where n is number of finished customers
     */
    private void updateStore() {
        while (!customersInStore.isEmpty()) {
            Customer customer = customersInStore.peek();
            // Check if customer's planned shopping time has elapsed
            // 60000 = milliseconds in a minute
            if (currentTime >= customer.entryTime +
                    (customer.plannedDuration * 60000)) {
                customersInStore.poll(); // Remove finished customer
            } else {
                break; // Remaining customers still shopping
            }
        }
    }

    /**
     * Advances simulation time for testing purposes
     * @param minutes Number of minutes to advance time
     * @throws IllegalArgumentException if minutes is negative
     */
    public void advanceTime(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Cannot advance time backwards");
        }
        currentTime += minutes * 60000; // Convert minutes to milliseconds
    }
}
