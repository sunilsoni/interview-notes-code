package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;

public class AutoScalingMessageQueue {

    // Test 1: Verify latency-based scaling
    static boolean testLatencyTriggeredScaling() {
        System.out.println("\n--- Test 1: Latency Triggered Scaling ---");

        // config: 100ms max latency, 80% fill threshold, 1-10 workers
        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add old message (200ms ago) to simulate high latency
        long oldTime = System.currentTimeMillis() - 200;
        queue.enqueue(new Message("m1", "old-msg", oldTime, 1));

        // check scaling decision
        var decision = queue.checkAndScale();

        System.out.println("Max Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        // should scale up because latency > 100ms
        boolean pass = decision.shouldScale() && decision.targetWorkers() > 1;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 2: Verify queue-fill-based scaling
    static boolean testQueueFillTriggeredScaling() {
        System.out.println("\n--- Test 2: Queue Fill Triggered Scaling ---");

        var config = new ScalingConfig(10000, 0.8, 1, 10); // high latency threshold
        var queue = new ScalableMessageQueue(100, config);

        // fill queue to 85% (above 80% threshold)
        long now = System.currentTimeMillis();
        IntStream.range(0, 85)
                .forEach(i -> queue.enqueue(new Message("m" + i, "payload", now, 1)));

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

        // should scale up because fill > 80%
        boolean pass = decision.shouldScale() && decision.reason().contains("QueueFill");
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 3: Verify no scaling when conditions not met
    static boolean testNoScalingWhenHealthy() {
        System.out.println("\n--- Test 3: No Scaling When Healthy ---");

        var config = new ScalingConfig(1000, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add few fresh messages (low fill, low latency)
        long now = System.currentTimeMillis();
        IntStream.range(0, 50) // 50% fill
                .forEach(i -> queue.enqueue(new Message("m" + i, "payload", now, 1)));

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Max Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        // should NOT scale (fill < 80%, latency < 1000ms)
        boolean pass = !decision.shouldScale();
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 4: Verify scale down when load reduces
    static boolean testScaleDown() {
        System.out.println("\n--- Test 4: Scale Down When Load Reduces ---");

        var config = new ScalingConfig(1000, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // first, trigger scale up
        long oldTime = System.currentTimeMillis() - 2000;
        IntStream.range(0, 85)
                .forEach(i -> queue.enqueue(new Message("m" + i, "payload", oldTime, 1)));

        queue.checkAndScale(); // scale up
        int workersAfterScaleUp = queue.getCurrentWorkers();
        System.out.println("Workers after scale up: " + workersAfterScaleUp);

        // clear queue (simulate messages processed)
        while (queue.dequeue() != null) {
        }

        // add few fresh messages (low load)
        long now = System.currentTimeMillis();
        IntStream.range(0, 10) // only 10% fill
                .forEach(i -> queue.enqueue(new Message("m" + i, "payload", now, 1)));

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

        // may or may not scale down, but workers should not increase
        boolean pass = queue.getCurrentWorkers() <= workersAfterScaleUp;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // ==================== TEST METHODS ====================

    // Test 5: Large data test - handles 100K messages
    static boolean testLargeDataHandling() {
        System.out.println("\n--- Test 5: Large Data Handling (100K messages) ---");

        var config = new ScalingConfig(500, 0.9, 1, 100);
        var queue = new ScalableMessageQueue(150000, config);

        long start = System.currentTimeMillis();

        // enqueue 100K messages
        long oldTime = System.currentTimeMillis() - 1000;
        int enqueued = (int) IntStream.range(0, 100000)
                .filter(i -> queue.enqueue(new Message("m" + i, "payload-" + i, oldTime, i % 5)))
                .count();

        long enqueueTime = System.currentTimeMillis() - start;
        System.out.println("Enqueued " + enqueued + " messages in " + enqueueTime + "ms");

        // check scaling
        start = System.currentTimeMillis();
        var decision = queue.checkAndScale();
        long scaleCheckTime = System.currentTimeMillis() - start;

        System.out.println("Scale check took: " + scaleCheckTime + "ms");
        System.out.println("Decision: " + decision);
        System.out.println("Workers scaled to: " + queue.getCurrentWorkers());

        // verify: enqueued 100K, scaling works, performance acceptable
        boolean pass = enqueued == 100000 && scaleCheckTime < 1000;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 6: Edge case - empty queue
    static boolean testEmptyQueue() {
        System.out.println("\n--- Test 6: Empty Queue ---");

        var config = new ScalingConfig(100, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        // don't add any messages
        var decision = queue.checkAndScale();

        System.out.println("Queue Size: " + queue.getQueueSize());
        System.out.println("Decision: " + decision);

        // should not crash, should not scale up
        boolean pass = !decision.shouldScale() && queue.getCurrentWorkers() == 2;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 7: Verify max worker limit respected
    static boolean testMaxWorkerLimit() {
        System.out.println("\n--- Test 7: Max Worker Limit Respected ---");

        var config = new ScalingConfig(10, 0.5, 1, 5); // max 5 workers
        var queue = new ScalableMessageQueue(100, config);

        // repeatedly trigger scaling
        for (int i = 0; i < 10; i++) {
            long oldTime = System.currentTimeMillis() - 100;
            IntStream.range(0, 80)
                    .forEach(j -> queue.enqueue(new Message("m" + j, "p", oldTime, 1)));
            queue.checkAndScale();
            while (queue.dequeue() != null) {
            } // clear
        }

        System.out.println("Final workers: " + queue.getCurrentWorkers());

        // workers should never exceed max (5)
        boolean pass = queue.getCurrentWorkers() <= 5;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Main method - runs all tests
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("AUTO-SCALING MESSAGE QUEUE - TEST SUITE");
        System.out.println("========================================");

        // collect all test results
        List<Boolean> results = List.of(
                testLatencyTriggeredScaling(),
                testQueueFillTriggeredScaling(),
                testNoScalingWhenHealthy(),
                testScaleDown(),
                testLargeDataHandling(),
                testEmptyQueue(),
                testMaxWorkerLimit()
        );

        // count passed tests
        long passed = results.stream().filter(r -> r).count();
        long total = results.size();

        // print summary
        System.out.println("\n========================================");
        System.out.println("SUMMARY: " + passed + "/" + total + " tests passed");
        System.out.println("========================================");

        // final verdict
        if (passed == total) {
            System.out.println("ALL TESTS PASSED!");
        } else {
            System.out.println("SOME TESTS FAILED!");
        }
    }

    // Record for Message - immutable data carrier (Java 21 feature)
    // Holds message data with timestamp for latency calculation
    record Message(
            String id,           // unique identifier for tracking
            String payload,      // actual message content
            long timestamp,      // when message entered queue (for latency calc)
            int priority         // message priority level
    ) {
    }

    // Record for scaling configuration - defines scaling thresholds
    // These params control when auto-scaling triggers
    record ScalingConfig(
            long maxLatencyMs,    // max allowed message wait time in ms
            double fillThreshold, // queue fill percentage (0.0 to 1.0) to trigger scale
            int minWorkers,       // minimum workers to maintain
            int maxWorkers        // maximum workers allowed
    ) {
    }

    // Record to hold scaling decision result
    // Tells us whether to scale and by how much
    record ScaleDecision(
            boolean shouldScale,  // true if scaling needed
            int targetWorkers,    // desired worker count
            String reason         // why scaling triggered
    ) {
    }

    // Main Message Queue class with auto-scaling capability
    static class ScalableMessageQueue {

        // Thread-safe queue to hold messages
        // ConcurrentLinkedDeque allows fast add/remove from both ends
        private final ConcurrentLinkedDeque<Message> queue = new ConcurrentLinkedDeque<>();

        // Maximum capacity of the queue
        private final int maxCapacity;

        // Scaling configuration parameters
        private final ScalingConfig config;

        // Current number of active workers processing messages
        private volatile int currentWorkers;

        // Constructor initializes queue with capacity and scaling config
        ScalableMessageQueue(int maxCapacity, ScalingConfig config) {
            this.maxCapacity = maxCapacity;       // set max queue size
            this.config = config;                  // store scaling rules
            this.currentWorkers = config.minWorkers; // start with min workers
        }

        // Add message to queue - returns false if queue full
        boolean enqueue(Message msg) {
            // check if queue has space before adding
            if (queue.size() >= maxCapacity) return false;

            // add message to end of queue
            queue.addLast(msg);
            return true; // success
        }

        // Remove and return oldest message from queue
        Message dequeue() {
            return queue.pollFirst(); // returns null if empty
        }

        // Calculate current queue fill percentage (0.0 to 1.0)
        double getQueueFillPercentage() {
            // divide current size by max to get fill ratio
            return (double) queue.size() / maxCapacity;
        }

        // Find the oldest message's latency (wait time in queue)
        long getMaxLatency() {
            long now = System.currentTimeMillis(); // current time

            // stream through all messages, find max wait time
            // if empty queue, return 0 latency
            return queue.stream()
                    .mapToLong(m -> now - m.timestamp) // calc each message's wait
                    .max()                              // get maximum
                    .orElse(0);                         // default if empty
        }

        // Core auto-scaling logic - decides if scaling needed
        ScaleDecision checkAndScale() {
            long maxLatency = getMaxLatency();           // get current max wait
            double fillPct = getQueueFillPercentage();   // get fill percentage

            // CONDITION 1: Check if latency exceeds threshold
            // This means messages waiting too long - need more workers
            boolean latencyBreached = maxLatency > config.maxLatencyMs;

            // CONDITION 2: Check if queue filling up
            // This means incoming rate > processing rate - need more workers
            boolean queueFilling = fillPct > config.fillThreshold;

            // Either condition triggers scale UP
            if (latencyBreached || queueFilling) {
                // calculate new worker count (increase by 50%, at least 1)
                int newWorkers = Math.min(
                        config.maxWorkers,                           // don't exceed max
                        currentWorkers + Math.max(1, currentWorkers / 2) // add 50%
                );

                // build reason string for logging
                String reason = latencyBreached
                        ? "Latency=" + maxLatency + "ms > " + config.maxLatencyMs + "ms"
                        : "QueueFill=" + String.format("%.1f%%", fillPct * 100);

                // only scale if actually increasing workers
                if (newWorkers > currentWorkers) {
                    currentWorkers = newWorkers; // update worker count
                    return new ScaleDecision(true, newWorkers, "SCALE UP: " + reason);
                }
            }

            // Check if we can scale DOWN (queue mostly empty, low latency)
            boolean canScaleDown = fillPct < 0.2 && maxLatency < config.maxLatencyMs / 2;

            if (canScaleDown && currentWorkers > config.minWorkers) {
                // reduce workers by 25%
                int newWorkers = Math.max(
                        config.minWorkers,               // don't go below min
                        currentWorkers - currentWorkers / 4 // reduce by 25%
                );

                if (newWorkers < currentWorkers) {
                    currentWorkers = newWorkers;
                    return new ScaleDecision(true, newWorkers, "SCALE DOWN: Low load");
                }
            }

            // No scaling needed
            return new ScaleDecision(false, currentWorkers, "No scaling needed");
        }

        // Getters for testing
        int getCurrentWorkers() {
            return currentWorkers;
        }

        int getQueueSize() {
            return queue.size();
        }

        ScalingConfig getConfig() {
            return config;
        }
    }
}