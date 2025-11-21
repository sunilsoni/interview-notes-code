package com.interview.notes.code.year.y2025.april.common.tst6;// Part 1: Basic Classes

import java.util.LinkedList;
import java.util.Queue;

// Customer class representing a service center visitor
class Customer {
    private static int idCounter = 0;  // Static counter for generating unique IDs
    private final int id;                    // Unique identifier for each customer
    private final boolean isVip;             // VIP status flag
    private final long arrivalTime;          // Timestamp when customer checked in

    public Customer(boolean isVip) {
        this.id = ++idCounter;
        this.isVip = isVip;
        this.arrivalTime = System.currentTimeMillis();
    }

    // Getters
    public int getId() {
        return id;
    }

    public boolean isVip() {
        return isVip;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }
}

// ServiceScheduler class managing customer queue and processing
class ServiceScheduler {
    private final Queue<Customer> regularQueue;     // Queue for regular customers
    private final Queue<Customer> vipQueue;         // Queue for VIP customers
    private int processedVipCount;            // Counter for processed VIP customers
    private int processedRegularCount;        // Counter for processed regular customers

    public ServiceScheduler() {
        this.regularQueue = new LinkedList<>();
        this.vipQueue = new LinkedList<>();
        this.processedVipCount = 0;
        this.processedRegularCount = 0;
    }

    // Add customer to appropriate queue
    public void checkIn(Customer customer) {
        if (customer.isVip()) {
            vipQueue.offer(customer);
        } else {
            regularQueue.offer(customer);
        }
    }

    // Part 2: Serve all VIP customers first
    public Customer getNextCustomerVipFirst() {
        return vipQueue.isEmpty() ? regularQueue.poll() : vipQueue.poll();
    }

    // Part 3: Implement 2:1 VIP vs Regular ratio
    public Customer getNextCustomer() {
        // If VIP queue is empty, serve regular customer
        if (vipQueue.isEmpty()) {
            return regularQueue.poll();
        }

        // If regular queue is empty, serve VIP customer
        if (regularQueue.isEmpty()) {
            return vipQueue.poll();
        }

        // Maintain 2:1 ratio (VIP:Regular)
        if (processedVipCount * 1.0 / (processedRegularCount + 1) < 2.0) {
            processedVipCount++;
            return vipQueue.poll();
        } else {
            processedRegularCount++;
            return regularQueue.poll();
        }
    }
}

// Main class for testing
class ServiceCenterTest {
    public static void main(String[] args) {
        ServiceScheduler scheduler = new ServiceScheduler();

        // Test case 1: Basic VIP priority
        System.out.println("Test Case 1: VIP Priority");
        scheduler.checkIn(new Customer(false));  // Regular customer
        scheduler.checkIn(new Customer(true));   // VIP customer
        scheduler.checkIn(new Customer(false));  // Regular customer

        Customer next = scheduler.getNextCustomerVipFirst();
        System.out.println("First customer served is VIP: " + next.isVip());  // Should be true

        // Test case 2: 2:1 Ratio
        System.out.println("\nTest Case 2: 2:1 Ratio");
        ServiceScheduler scheduler2 = new ServiceScheduler();

        // Add mix of customers
        for (int i = 0; i < 3; i++) {
            scheduler2.checkIn(new Customer(true));   // VIP
            scheduler2.checkIn(new Customer(false));  // Regular
        }

        // Process and verify ratio
        for (int i = 0; i < 6; i++) {
            Customer served = scheduler2.getNextCustomer();
            System.out.println("Customer " + (i + 1) + " is VIP: " + served.isVip());
        }
    }
}
