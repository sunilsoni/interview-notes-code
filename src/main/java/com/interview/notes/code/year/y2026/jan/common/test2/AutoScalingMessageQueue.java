package com.interview.notes.code.year.y2026.jan.common.test2;

public class AutoScalingMessageQueue {
    
    // Test 1: latency triggers scaling
    static boolean testLatencyTriggeredScaling() {
        System.out.println("\n--- Test 1: Latency Triggered Scaling ---");

        // config: 100ms max latency, 80% fill, 1-10 workers
        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add OLD message (200ms ago) to simulate high latency
        long oldTime = System.currentTimeMillis() - 200;
        queue.enqueue(new Message("m1", "old-message", oldTime, 1));

        // check what scaler decides
        var decision = queue.checkAndScale();

        // print results
        System.out.println("Max Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        // verify: should scale up due to latency > 100ms
        boolean pass = decision.shouldScale() &&
                       decision.targetWorkers() > 1 &&
                       decision.reason().contains("Latency");

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 2: queue fill triggers scaling
    static boolean testQueueFillTriggeredScaling() {
        System.out.println("\n--- Test 2: Queue Fill Triggered Scaling ---");

        // config: very high latency threshold so only fill matters
        var config = new ScalingConfig(100000, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // fill queue to 85% (above 80% threshold)
        long now = System.currentTimeMillis();
        for (int i = 0; i < 85; i++) {
            queue.enqueue(new Message("m" + i, "payload", now, 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

        // verify: should scale up due to fill > 80%
        boolean pass = decision.shouldScale() &&
                       decision.reason().contains("QueueFill");

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 3: no scaling when healthy
    static boolean testNoScalingWhenHealthy() {
        System.out.println("\n--- Test 3: No Scaling When Healthy ---");

        var config = new ScalingConfig(1000, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add moderate messages (50% fill, fresh timestamps)
        long now = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            queue.enqueue(new Message("m" + i, "payload", now, 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Max Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        // verify: should NOT scale (fill < 80%, latency < 1000ms)
        boolean pass = !decision.shouldScale();

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 4: scale down when load reduces
    static boolean testScaleDown() {
        System.out.println("\n--- Test 4: Scale Down When Load Reduces ---");

        var config = new ScalingConfig(1000, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // first trigger scale up with high load
        long oldTime = System.currentTimeMillis() - 2000;
        for (int i = 0; i < 85; i++) {
            queue.enqueue(new Message("m" + i, "payload", oldTime, 1));
        }

        queue.checkAndScale();  // should scale up
        int workersAfterUp = queue.getCurrentWorkers();
        System.out.println("Workers after scale up: " + workersAfterUp);

        // clear queue (simulate processing)
        queue.clear();

        // add few fresh messages (low load)
        long now = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(new Message("m" + i, "payload", now, 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

        // verify: workers should reduce or stay same
        boolean pass = queue.getCurrentWorkers() <= workersAfterUp;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 5: large data handling
    static boolean testLargeDataHandling() {
        System.out.println("\n--- Test 5: Large Data Handling (100K messages) ---");

        var config = new ScalingConfig(500, 0.9, 1, 100);
        var queue = new ScalableMessageQueue(150000, config);

        long start = System.currentTimeMillis();

        // enqueue 100K messages
        long oldTime = System.currentTimeMillis() - 1000;
        int enqueued = 0;
        for (int i = 0; i < 100000; i++) {
            if (queue.enqueue(new Message("m" + i, "payload-" + i, oldTime, i % 5))) {
                enqueued++;
            }
        }

        long enqueueTime = System.currentTimeMillis() - start;
        System.out.println("Enqueued " + enqueued + " messages in " + enqueueTime + "ms");

        // check scaling performance
        start = System.currentTimeMillis();
        var decision = queue.checkAndScale();
        long scaleTime = System.currentTimeMillis() - start;

        System.out.println("Scale check took: " + scaleTime + "ms");
        System.out.println("Decision: " + decision);
        System.out.println("Workers: " + queue.getCurrentWorkers());

        // verify: all enqueued, reasonable performance
        boolean pass = enqueued == 100000 && scaleTime < 2000;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 6: empty queue handling
    static boolean testEmptyQueue() {
        System.out.println("\n--- Test 6: Empty Queue ---");

        var config = new ScalingConfig(100, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        // don't add any messages
        var decision = queue.checkAndScale();

        System.out.println("Queue Size: " + queue.getQueueSize());
        System.out.println("Max Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        // verify: no crash, no unnecessary scaling
        boolean pass = !decision.shouldScale() &&
                       queue.getCurrentWorkers() == 2;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 7: max worker limit respected
    static boolean testMaxWorkerLimit() {
        System.out.println("\n--- Test 7: Max Worker Limit Respected ---");

        // config with low max (5 workers)
        var config = new ScalingConfig(10, 0.5, 1, 5);
        var queue = new ScalableMessageQueue(100, config);

        // repeatedly trigger scaling
        for (int round = 0; round < 10; round++) {
            long oldTime = System.currentTimeMillis() - 100;
            for (int i = 0; i < 80; i++) {
                queue.enqueue(new Message("m" + i, "p", oldTime, 1));
            }
            queue.checkAndScale();
            queue.clear();  // reset for next round
        }

        System.out.println("Final workers: " + queue.getCurrentWorkers());

        // verify: never exceed max of 5
        boolean pass = queue.getCurrentWorkers() <= 5;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // ==================== TEST METHODS ====================
    
    // Test 8: custom queue basic operations
    static boolean testCustomQueueOperations() {
        System.out.println("\n--- Test 8: Custom Queue Basic Operations ---");

        CustomQueue<Integer> q = new CustomQueue<>(5);

        // test enqueue
        boolean e1 = q.enqueue(1);  // should succeed
        boolean e2 = q.enqueue(2);  // should succeed
        boolean e3 = q.enqueue(3);  // should succeed
        boolean e4 = q.enqueue(4);  // should succeed
        boolean e5 = q.enqueue(5);  // should succeed
        boolean e6 = q.enqueue(6);  // should FAIL (full)

        System.out.println("Enqueue results: " + e1 + "," + e2 + "," + e3 + "," + e4 + "," + e5 + "," + e6);
        System.out.println("Queue size: " + q.size());

        // test dequeue (FIFO order)
        Integer d1 = q.dequeue();  // should be 1
        Integer d2 = q.dequeue();  // should be 2

        System.out.println("Dequeued: " + d1 + ", " + d2);
        System.out.println("Queue size after dequeue: " + q.size());

        // verify FIFO and capacity limits
        boolean pass = e1 && e2 && e3 && e4 && e5 && !e6 &&  // capacity check
                       d1 == 1 && d2 == 2 &&                   // FIFO check
                       q.size() == 3;                          // size check

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 9: multiple scale cycles
    static boolean testMultipleScaleCycles() {
        System.out.println("\n--- Test 9: Multiple Scale Cycles ---");

        var config = new ScalingConfig(50, 0.7, 1, 20);
        var queue = new ScalableMessageQueue(100, config);

        CustomArrayList<Integer> workerHistory = new CustomArrayList<>();
        workerHistory.add(queue.getCurrentWorkers());

        // simulate load cycles
        for (int cycle = 0; cycle < 5; cycle++) {
            // high load phase
            long oldTime = System.currentTimeMillis() - 100;
            for (int i = 0; i < 75; i++) {
                queue.enqueue(new Message("m" + i, "p", oldTime, 1));
            }
            queue.checkAndScale();
            workerHistory.add(queue.getCurrentWorkers());

            // clear and low load phase
            queue.clear();
            long now = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                queue.enqueue(new Message("m" + i, "p", now, 1));
            }
            queue.checkAndScale();
            workerHistory.add(queue.getCurrentWorkers());
            queue.clear();
        }

        // print worker history
        System.out.print("Worker history: ");
        for (int i = 0; i < workerHistory.size(); i++) {
            System.out.print(workerHistory.get(i) + " ");
        }
        System.out.println();

        // verify: workers changed during cycles
        boolean hasChanges = false;
        for (int i = 1; i < workerHistory.size(); i++) {
            if (!workerHistory.get(i).equals(workerHistory.get(i-1))) {
                hasChanges = true;
                break;
            }
        }

        boolean pass = hasChanges;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // Test 10: concurrent-like access simulation
    static boolean testConcurrentAccess() {
        System.out.println("\n--- Test 10: Concurrent Access Simulation ---");

        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(1000, config);

        // simulate producer
        long now = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            queue.enqueue(new Message("m" + i, "payload", now, i % 3));
        }

        // simulate consumer
        int consumed = 0;
        for (int i = 0; i < 200; i++) {
            if (queue.dequeue() != null) consumed++;
        }

        // check scaling
        var decision = queue.checkAndScale();

        System.out.println("Produced: 500, Consumed: " + consumed);
        System.out.println("Remaining: " + queue.getQueueSize());
        System.out.println("Decision: " + decision);

        // verify: correct counts
        boolean pass = consumed == 200 && queue.getQueueSize() == 300;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
    
    // ==================== MAIN METHOD ====================
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("AUTO-SCALING MESSAGE QUEUE - CUSTOM IMPLEMENTATION");
        System.out.println("================================================");

        // run all tests and collect results
        boolean[] results = {
            testLatencyTriggeredScaling(),
            testQueueFillTriggeredScaling(),
            testNoScalingWhenHealthy(),
            testScaleDown(),
            testLargeDataHandling(),
            testEmptyQueue(),
            testMaxWorkerLimit(),
            testCustomQueueOperations(),
            testMultipleScaleCycles(),
            testConcurrentAccess()
        };

        // count passed tests
        int passed = 0;
        for (boolean result : results) {
            if (result) passed++;
        }

        // print summary
        System.out.println("\n================================================");
        System.out.println("SUMMARY: " + passed + "/" + results.length + " tests passed");
        System.out.println("================================================");

        // final result
        if (passed == results.length) {
            System.out.println("ALL TESTS PASSED!");
        } else {
            System.out.println("SOME TESTS FAILED!");
        }
    }
    
    // ==================== CUSTOM NODE CLASS ====================
    // Node represents single element in our linked list queue
    // Each node holds data and pointer to next node
    static class Node<T> {
        T data;           // actual data stored in this node
        Node<T> next;     // reference to next node in chain

        // constructor to create node with data
        Node(T data) {
            this.data = data;   // store the data
            this.next = null;   // initially no next node
        }
    }
    
    // ==================== CUSTOM THREAD-SAFE QUEUE ====================
    // Custom queue implementation using linked list
    // Thread-safe using synchronized keyword
    static class CustomQueue<T> {

        private final int maxCapacity;  // maximum allowed elements
        private Node<T> head;     // front of queue (dequeue from here)
        private Node<T> tail;     // back of queue (enqueue here)
        private int size;         // current number of elements

        // constructor sets up empty queue with max capacity
        CustomQueue(int maxCapacity) {
            this.head = null;           // empty queue has no head
            this.tail = null;           // empty queue has no tail
            this.size = 0;              // start with zero elements
            this.maxCapacity = maxCapacity;  // set capacity limit
        }

        // add element to back of queue (thread-safe)
        // returns true if successful, false if queue full
        synchronized boolean enqueue(T data) {
            // check if queue is full before adding
            if (size >= maxCapacity) {
                return false;  // reject - queue at capacity
            }

            // create new node to hold the data
            Node<T> newNode = new Node<>(data);

            // handle empty queue case
            if (tail == null) {
                head = newNode;  // first element becomes head
                tail = newNode;  // and also tail
            } else {
                tail.next = newNode;  // link current tail to new node
                tail = newNode;       // new node becomes tail
            }

            size++;       // increment count
            return true;  // success
        }

        // remove and return element from front (thread-safe)
        // returns null if queue empty
        synchronized T dequeue() {
            // check if queue is empty
            if (head == null) {
                return null;  // nothing to return
            }

            T data = head.data;    // grab data from front node
            head = head.next;      // move head to next node

            // if queue becomes empty, reset tail too
            if (head == null) {
                tail = null;
            }

            size--;       // decrement count
            return data;  // return the data
        }

        // peek at front element without removing (thread-safe)
        synchronized T peek() {
            return (head != null) ? head.data : null;
        }

        // get current size (thread-safe)
        synchronized int size() {
            return size;
        }

        // check if empty (thread-safe)
        synchronized boolean isEmpty() {
            return size == 0;
        }

        // get max capacity
        int getMaxCapacity() {
            return maxCapacity;
        }

        // convert queue to array for iteration (thread-safe)
        // needed for stream operations without modifying queue
        @SuppressWarnings("unchecked")
        synchronized Object[] toArray() {
            Object[] arr = new Object[size];  // create array of current size
            Node<T> current = head;           // start from head
            int index = 0;

            // traverse entire queue
            while (current != null) {
                arr[index++] = current.data;  // copy data to array
                current = current.next;       // move to next node
            }

            return arr;  // return array copy
        }

        // clear all elements (thread-safe)
        synchronized void clear() {
            head = null;  // remove head reference
            tail = null;  // remove tail reference
            size = 0;     // reset count
            // garbage collector will clean up orphaned nodes
        }
    }
    
    // ==================== MESSAGE RECORD ====================
    // Immutable message data holder
    // Using record for concise syntax (Java 21)
    record Message(
        String id,        // unique message identifier
        String payload,   // message content/body
        long timestamp,   // when message was created (epoch ms)
        int priority      // message priority (higher = more urgent)
    ) {}
    
    // ==================== SCALING CONFIG RECORD ====================
    // Holds all auto-scaling configuration parameters
    record ScalingConfig(
        long maxLatencyMs,     // max allowed wait time before scaling
        double fillThreshold,  // queue fill % that triggers scaling (0.0-1.0)
        int minWorkers,        // minimum workers to maintain
        int maxWorkers         // maximum workers allowed
    ) {}
    
    // ==================== SCALE DECISION RECORD ====================
    // Result of scaling evaluation
    record ScaleDecision(
        boolean shouldScale,   // true if scaling action needed
        int targetWorkers,     // desired worker count after scaling
        String reason          // explanation for the decision
    ) {}
    
    // ==================== CUSTOM ARRAY LIST ====================
    // Simple dynamic array for storing messages temporarily
    static class CustomArrayList<T> {

        private Object[] elements;  // internal array storage
        private int size;           // current element count

        // constructor with initial capacity
        CustomArrayList(int initialCapacity) {
            this.elements = new Object[initialCapacity];  // allocate array
            this.size = 0;  // start empty
        }

        // default constructor
        CustomArrayList() {
            this(10);  // default capacity of 10
        }

        // add element to list
        void add(T element) {
            // check if array is full
            if (size >= elements.length) {
                grow();  // expand array
            }
            elements[size++] = element;  // add and increment
        }

        // get element at index
        @SuppressWarnings("unchecked")
        T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
            return (T) elements[index];
        }

        // get current size
        int size() {
            return size;
        }

        // expand array when full (double capacity)
        private void grow() {
            int newCapacity = elements.length * 2;  // double size
            Object[] newArray = new Object[newCapacity];

            // copy existing elements
            if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);

            elements = newArray;  // replace old array
        }
    }
    
    // ==================== SCALABLE MESSAGE QUEUE ====================
    // Main class combining queue with auto-scaling logic
    static class ScalableMessageQueue {

        // custom queue to hold messages
        private final CustomQueue<Message> queue;

        // scaling configuration
        private final ScalingConfig config;

        // current active worker count (volatile for thread visibility)
        private volatile int currentWorkers;

        // constructor initializes queue with config
        ScalableMessageQueue(int maxCapacity, ScalingConfig config) {
            this.queue = new CustomQueue<>(maxCapacity);  // create custom queue
            this.config = config;                          // store config
            this.currentWorkers = config.minWorkers();     // start with min workers
        }

        // add message to queue
        boolean enqueue(Message msg) {
            return queue.enqueue(msg);  // delegate to custom queue
        }

        // remove message from queue
        Message dequeue() {
            return queue.dequeue();  // delegate to custom queue
        }

        // calculate queue fill percentage (0.0 to 1.0)
        double getQueueFillPercentage() {
            // current size divided by max capacity
            return (double) queue.size() / queue.getMaxCapacity();
        }

        // find maximum latency among all queued messages
        long getMaxLatency() {
            long now = System.currentTimeMillis();  // current time

            // get array snapshot of queue
            Object[] messages = queue.toArray();

            // if empty, no latency
            if (messages.length == 0) {
                return 0;
            }

            // find message with highest wait time
            long maxLatency = 0;
            for (Object obj : messages) {
                Message msg = (Message) obj;              // cast to Message
                long latency = now - msg.timestamp();     // calculate wait time
                if (latency > maxLatency) {
                    maxLatency = latency;                 // update max
                }
            }

            return maxLatency;
        }

        // alternative: get max latency using streams (Java 8+)
        long getMaxLatencyStream() {
            long now = System.currentTimeMillis();
            Object[] messages = queue.toArray();

            // stream approach for cleaner code
            return java.util.Arrays.stream(messages)
                .map(obj -> (Message) obj)                    // cast each
                .mapToLong(m -> now - m.timestamp())          // calc latency
                .max()                                         // find max
                .orElse(0);                                    // default if empty
        }

        // core auto-scaling decision logic
        ScaleDecision checkAndScale() {
            // gather current metrics
            long maxLatency = getMaxLatency();              // current max wait
            double fillPct = getQueueFillPercentage();      // current fill %

            // CONDITION 1: latency exceeds threshold
            // messages waiting too long = need more processing power
            boolean latencyBreached = maxLatency > config.maxLatencyMs();

            // CONDITION 2: queue filling up
            // incoming rate > processing rate = need more workers
            boolean queueFilling = fillPct > config.fillThreshold();

            // SCALE UP: either condition triggers
            if (latencyBreached || queueFilling) {
                // calculate new worker count
                // increase by 50%, minimum increase of 1
                int increase = Math.max(1, currentWorkers / 2);
                int newWorkers = Math.min(
                    config.maxWorkers(),           // don't exceed max
                    currentWorkers + increase      // add workers
                );

                // build reason string
                String reason;
                if (latencyBreached) {
                    reason = "Latency=" + maxLatency + "ms > " + config.maxLatencyMs() + "ms";
                } else {
                    reason = "QueueFill=" + formatPercent(fillPct);
                }

                // only scale if actually changing
                if (newWorkers > currentWorkers) {
                    currentWorkers = newWorkers;  // apply change
                    return new ScaleDecision(true, newWorkers, "SCALE UP: " + reason);
                }
            }

            // SCALE DOWN: check if load is low enough
            // both conditions must be met for safety
            boolean lowFill = fillPct < 0.2;                              // under 20% full
            boolean lowLatency = maxLatency < config.maxLatencyMs() / 2;  // under 50% of max
            boolean canScaleDown = lowFill && lowLatency;

            if (canScaleDown && currentWorkers > config.minWorkers()) {
                // reduce by 25% (gradual reduction)
                int decrease = Math.max(1, currentWorkers / 4);
                int newWorkers = Math.max(
                    config.minWorkers(),           // don't go below min
                    currentWorkers - decrease      // reduce workers
                );

                if (newWorkers < currentWorkers) {
                    currentWorkers = newWorkers;  // apply change
                    return new ScaleDecision(true, newWorkers, "SCALE DOWN: Low load");
                }
            }

            // NO SCALING: conditions are normal
            return new ScaleDecision(false, currentWorkers, "No scaling needed");
        }

        // format percentage nicely
        private String formatPercent(double value) {
            return String.format("%.1f%%", value * 100);
        }

        // getters for testing
        int getCurrentWorkers() { return currentWorkers; }
        int getQueueSize() { return queue.size(); }
        int getMaxCapacity() { return queue.getMaxCapacity(); }
        ScalingConfig getConfig() { return config; }

        // clear queue (for testing)
        void clear() { queue.clear(); }
    }
}