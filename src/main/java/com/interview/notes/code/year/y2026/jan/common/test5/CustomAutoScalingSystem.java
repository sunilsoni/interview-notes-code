package com.interview.notes.code.year.y2026.jan.common.test5;

public class CustomAutoScalingSystem {

    // 4. TEST HARNESS (Simple Main Method)
    public static void main(String[] args) {
        System.out.println("--- Custom Queue Auto-Scaling Tests ---");

        // Define Policy: Max 100ms latency, Max 5 items in queue
        ScalingPolicy policy = new ScalingPolicy(100, 5);

        // --- Test 1: Capacity Breach ---
        MyQueue q1 = new MyQueue();
        long now = System.currentTimeMillis();
        // Add 6 items (Limit is 5)
        for (int i = 0; i < 6; i++) q1.add(new Message("m" + i, now));

        runTest("Capacity Breach", true, q1, policy);

        // --- Test 2: Latency Breach ---
        MyQueue q2 = new MyQueue();
        // Add old message (500ms old)
        q2.add(new Message("old", now - 500));

        runTest("Latency Breach", true, q2, policy);

        // --- Test 3: Happy Path (No Scaling) ---
        MyQueue q3 = new MyQueue();
        q3.add(new Message("fresh", now)); // 0ms latency, size 1

        runTest("Normal Traffic", false, q3, policy);

        // --- Test 4: Large Data (Scale & Perf) ---
        MyQueue qHuge = new MyQueue();
        // Add 1 Million items
        // Since our 'add' and 'size' are O(1), this is very fast.
        for (int i = 0; i < 1_000_000; i++) {
            qHuge.add(new Message("load" + i, now));
        }

        runTest("1 Million Items", true, qHuge, policy);
    }

    // Helper for printing results clearly
    static void runTest(String name, boolean expected, MyQueue q, ScalingPolicy p) {
        long start = System.nanoTime();
        boolean actual = AutoScaler.shouldScale(q, p);
        long end = System.nanoTime();

        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] %-15s | Exp: %-5s Act: %-5s | Time: %d ns%n",
                status, name, expected, actual, (end - start));
    }

    // 1. DATA MODEL (Java 21 Record)
    // Simple carrier for message data.
    record Message(String id, long entryTime) {
    }

    // Configuration for scaling limits.
    record ScalingPolicy(long maxLatencyMs, int queueThreshold) {
    }

    // 2. CUSTOM QUEUE IMPLEMENTATION (No built-in libraries)
    // We implement a simple Singly Linked List.
    static class MyQueue {

        private Node head; // Points to the oldest message (for latency check)
        private Node tail; // Points to the newest message (for fast insertion)
        private int count; // Tracks size O(1)

        // Add message to the end (Enqueue)
        // Time Complexity: O(1) because we use a tail pointer.
        public void add(Message msg) {
            Node newNode = new Node(msg);
            if (tail != null) {
                tail.next = newNode;
            }
            tail = newNode;
            if (head == null) { // If first element
                head = newNode;
            }
            count++;
        }

        // Look at the oldest message without removing it
        // Time Complexity: O(1)
        public Message peek() {
            if (head == null) return null;
            return head.data;
        }

        // Get current size
        // Time Complexity: O(1)
        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        // Internal Node class to hold data
        private static class Node {
            Message data;
            Node next;

            Node(Message d) {
                this.data = d;
            }
        }
    }

    // 3. BUSINESS LOGIC
    static class AutoScaler {

        // The core scaling decision algorithm
        public static boolean shouldScale(MyQueue queue, ScalingPolicy policy) {
            // Case A: Empty Queue -> No scale
            if (queue.isEmpty()) return false;

            // Case B: Capacity Check (Filling up)
            // If current count > max allowed -> SCALE
            if (queue.size() > policy.queueThreshold()) {
                return true;
            }

            // Case C: Latency Check (Too slow)
            // Check the oldest message (at head)
            Message oldest = queue.peek();
            long latency = System.currentTimeMillis() - oldest.entryTime();

            // If waiting time > max allowed -> SCALE
            return latency > policy.maxLatencyMs();
        }
    }
}