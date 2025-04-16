package com.interview.notes.code.year.y2025.april.common.test7;

import java.util.*;

// Class representing a customer
class Customer {
    private final int serviceNumber;  // Unique sequential service number assigned upon check-in
    private final boolean isVIP;      // Flag to indicate if customer is VIP

    // Constructor to initialize customer with service number and VIP status.
    public Customer(int serviceNumber, boolean isVIP) {
        this.serviceNumber = serviceNumber;
        this.isVIP = isVIP;
    }

    // Getter for service number.
    public int getServiceNumber() {
        return serviceNumber;
    }

    // Getter to check if customer is VIP.
    public boolean isVIP() {
        return isVIP;
    }

    // Overriding toString for easier readability of Customer object.
    @Override
    public String toString() {
        return "Customer{" +
                "serviceNumber=" + serviceNumber +
                ", isVIP=" + isVIP +
                '}';
    }
}


// ServiceScheduler implements the scheduler for processing customers.
// It supports two modes:
// 1. Strict VIP-first (Part 2)
// 2. A 2:1 processing ratio of VIP to Regular customers (Part 3)
class ServiceScheduler {
    // Queues for storing VIP and Regular customers.
    private Queue<Customer> vipQueue = new LinkedList<>();
    private Queue<Customer> regularQueue = new LinkedList<>();

    // Counter to generate unique service numbers
    private int nextServiceNumber = 1;

    // Counter used for 2:1 ratio scheduling (Part 3).
    // It counts processed customers in the current cycle.
    private int cycleCount = 0;

    // Mode flag - if true use 2:1 ratio scheduling else use strict VIP-first scheduling.
    private boolean useRatioScheduling;

    // Constructor to set the scheduling mode.
    public ServiceScheduler(boolean useRatioScheduling) {
        this.useRatioScheduling = useRatioScheduling;
    }

    // Method for customer check-in.
    // It assigns a sequential service number and adds the customer to appropriate queue.
    public Customer checkIn(boolean isVIP) {
        // Create new Customer with next available service number and VIP status.
        Customer customer = new Customer(nextServiceNumber++, isVIP);
        // Add customer to the corresponding queue.
        if (isVIP) {
            vipQueue.add(customer);
        } else {
            regularQueue.add(customer);
        }
        return customer;
    }

    // Method to get the next customer based on the scheduling mode.
    public Customer getNextCustomer() {
        // If using ratio scheduling (2:1 VIP:Regular)
        if (useRatioScheduling) {
            // Determine choice based on current cycle count modulo 3.
            // For a cycle of 3:
            // cycleCount % 3 == 0 or 1: serve VIP if available. If VIP queue is empty, fall back to regular.
            // cycleCount % 3 == 2: serve Regular if available, otherwise serve VIP.
            int mod = cycleCount % 3;
            Customer nextCustomer = null;
            if (mod == 0 || mod == 1) {
                // Attempt to serve VIP customer
                nextCustomer = vipQueue.poll();
                if (nextCustomer == null) {
                    // Fallback: no VIP available, serve regular if possible.
                    nextCustomer = regularQueue.poll();
                }
            } else {
                // mod == 2; attempt to serve Regular customer.
                nextCustomer = regularQueue.poll();
                if (nextCustomer == null) {
                    // Fallback: if no Regular available, serve VIP.
                    nextCustomer = vipQueue.poll();
                }
            }
            // Increase cycle count if a customer was served.
            if (nextCustomer != null) {
                cycleCount++;
            }
            return nextCustomer;
        } else {
            // Strict VIP-first scheduling:
            // Serve a customer from the VIP queue first.
            Customer nextCustomer = vipQueue.poll();
            if (nextCustomer == null) {
                // If VIP queue is empty, serve from regular queue.
                nextCustomer = regularQueue.poll();
            }
            return nextCustomer;
        }
    }

    // Utility method to check if both queues are empty (useful for testing)
    public boolean isEmpty() {
        return vipQueue.isEmpty() && regularQueue.isEmpty();
    }
}


// Main class to run tests on the ServiceScheduler implementation.
public class ServiceSchedulerTest {

    // Helper method to print test results
    private static void printResult(String testName, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
    }

    // Test Part 2: Strict VIP-first scheduling.
    // All VIP customers should be served before Regular customers.
    public static void testStrictVIPFirst() {
        ServiceScheduler scheduler = new ServiceScheduler(false);
        // Check in customers (mix of VIP and Regular)
        scheduler.checkIn(false); // regular: service#1
        scheduler.checkIn(true);  // vip: service#2
        scheduler.checkIn(false); // regular: service#3
        scheduler.checkIn(true);  // vip: service#4
        scheduler.checkIn(false); // regular: service#5

        // Expected order: VIP customers first then regular
        List<Integer> expectedOrder = Arrays.asList(2, 4, 1, 3, 5);
        List<Integer> actualOrder = new ArrayList<>();

        Customer next;
        while ((next = scheduler.getNextCustomer()) != null) {
            actualOrder.add(next.getServiceNumber());
        }
        printResult("Strict VIP-First Test", expectedOrder.equals(actualOrder));
    }

    // Test Part 3: 2:1 VIP vs. Regular scheduling.
    // Expected pattern (assuming enough customers): Two VIP then one Regular repeatedly.
    public static void testTwoToOneRatio() {
        ServiceScheduler scheduler = new ServiceScheduler(true);
        // Check in customers (mix of VIP and Regular)
        // For clarity the check in order (service numbers):
        // Regular: 1, 3, 5, 7; VIP: 2, 4, 6, 8, 9, 10 (extra VIP customers)
        scheduler.checkIn(false); // 1 Regular
        scheduler.checkIn(true);  // 2 VIP
        scheduler.checkIn(false); // 3 Regular
        scheduler.checkIn(true);  // 4 VIP
        scheduler.checkIn(false); // 5 Regular
        scheduler.checkIn(true);  // 6 VIP
        scheduler.checkIn(false); // 7 Regular
        scheduler.checkIn(true);  // 8 VIP
        // Add extra VIP customers to cover ratio cycles
        scheduler.checkIn(true);  // 9 VIP
        scheduler.checkIn(true);  // 10 VIP

        // Expected order based on 2:1 scheduling:
        // Cycle 1: VIP 2, VIP 4, Regular 1
        // Cycle 2: VIP 6, VIP 8, Regular 3
        // Cycle 3: VIP 9, VIP 10, Regular 5
        // Cycle 4: Regular 7 (remaining regular, then no VIP left)
        List<Integer> expectedOrder = Arrays.asList(2, 4, 1, 6, 8, 3, 9, 10, 5, 7);
        List<Integer> actualOrder = new ArrayList<>();

        Customer next;
        while ((next = scheduler.getNextCustomer()) != null) {
            actualOrder.add(next.getServiceNumber());
        }
        printResult("2:1 Ratio Scheduling Test", expectedOrder.equals(actualOrder));
    }

    // Additional test: Test with only one type (e.g., only Regular customers).
    public static void testOnlyRegulars() {
        // Use ratio scheduling mode to check fallback behavior.
        ServiceScheduler scheduler = new ServiceScheduler(true);
        for (int i = 0; i < 5; i++) {
            scheduler.checkIn(false); // all regular
        }
        List<Integer> expectedOrder = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actualOrder = new ArrayList<>();
        Customer next;
        while ((next = scheduler.getNextCustomer()) != null) {
            actualOrder.add(next.getServiceNumber());
        }
        printResult("Only Regulars Test", expectedOrder.equals(actualOrder));
    }

    // Additional test: Large data input test.
    public static void testLargeDataInput() {
        ServiceScheduler scheduler = new ServiceScheduler(true);
        int totalCustomers = 10000;  // simulate large number of check-ins
        Random rand = new Random();

        // Randomly check in a mix of customers
        for (int i = 0; i < totalCustomers; i++) {
            // Randomly decide if the customer is VIP with 30% chance (for instance)
            scheduler.checkIn(rand.nextDouble() < 0.3);
        }

        // Just count how many customers are served.
        int count = 0;
        while (scheduler.getNextCustomer() != null) {
            count++;
        }
        printResult("Large Data Input Test", count == totalCustomers);
    }

    // Main method to run all tests.
    public static void main(String[] args) {
        System.out.println("Starting ServiceScheduler tests...");

        // Run tests for strict VIP-first scheduling.
        testStrictVIPFirst();

        // Run tests for 2:1 ratio scheduling.
        testTwoToOneRatio();

        // Run additional edge tests.
        testOnlyRegulars();

        // Run large data input test.
        testLargeDataInput();

        System.out.println("All tests complete.");
    }
}
