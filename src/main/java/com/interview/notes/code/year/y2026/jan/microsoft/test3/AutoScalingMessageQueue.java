package com.interview.notes.code.year.y2026.jan.microsoft.test3;

public class AutoScalingMessageQueue {

    // Test 1: latency triggers scaling (using enqueueTime)
    static boolean testLatencyTriggeredScaling() {
        System.out.println("\n--- Test 1: Latency Triggered Scaling (EnqueueTime) ---");

        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add message and wait for latency to build
        queue.enqueue(new Message("m1", "test-payload", System.currentTimeMillis(), 1));

        // simulate time passing (sleep)
        try {
            Thread.sleep(150);                         // wait 150ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // now check - latency should be ~150ms > 100ms threshold
        var decision = queue.checkAndScale();
        long latency = queue.getMaxLatency();

        System.out.println("Actual Queue Latency: " + latency + "ms");
        System.out.println("Threshold: " + config.maxLatencyMs() + "ms");
        System.out.println("Decision: " + decision);

        // verify: should scale up due to queue wait time > 100ms
        boolean pass = decision.shouldScale() &&
                latency > 100 &&
                decision.reason().contains("QueueLatency");

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 2: queue fill triggers scaling
    static boolean testQueueFillTriggeredScaling() {
        System.out.println("\n--- Test 2: Queue Fill Triggered Scaling ---");

        var config = new ScalingConfig(100000, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // fill queue to 85%
        for (int i = 0; i < 85; i++) {
            queue.enqueue(new Message("m" + i, "payload", System.currentTimeMillis(), 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

        boolean pass = decision.shouldScale() && decision.reason().contains("QueueFill");

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 3: no scaling when healthy
    static boolean testNoScalingWhenHealthy() {
        System.out.println("\n--- Test 3: No Scaling When Healthy ---");

        var config = new ScalingConfig(1000, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add moderate messages (50% fill, fresh)
        for (int i = 0; i < 50; i++) {
            queue.enqueue(new Message("m" + i, "payload", System.currentTimeMillis(), 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Queue Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        boolean pass = !decision.shouldScale();

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 4: scale down when load reduces
    static boolean testScaleDown() {
        System.out.println("\n--- Test 4: Scale Down When Load Reduces ---");

        var config = new ScalingConfig(50, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // fill and trigger scale up
        for (int i = 0; i < 85; i++) {
            queue.enqueue(new Message("m" + i, "payload", System.currentTimeMillis(), 1));
        }

        // wait for latency to build
        try { Thread.sleep(100); } catch (InterruptedException e) {}

        queue.checkAndScale();
        int workersAfterUp = queue.getCurrentWorkers();
        System.out.println("Workers after scale up: " + workersAfterUp);

        // clear and add few fresh messages
        queue.clear();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(new Message("m" + i, "payload", System.currentTimeMillis(), 1));
        }

        var decision = queue.checkAndScale();

        System.out.println("Queue Fill: " + String.format("%.1f%%", queue.getQueueFillPercentage() * 100));
        System.out.println("Decision: " + decision);

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
        int enqueued = 0;
        for (int i = 0; i < 100000; i++) {
            if (queue.enqueue(new Message("m" + i, "payload-" + i, System.currentTimeMillis(), i % 5))) {
                enqueued++;
            }
        }

        long enqueueTime = System.currentTimeMillis() - start;
        System.out.println("Enqueued " + enqueued + " messages in " + enqueueTime + "ms");

        // check scaling
        start = System.currentTimeMillis();
        var decision = queue.checkAndScale();
        long scaleTime = System.currentTimeMillis() - start;

        System.out.println("Scale check took: " + scaleTime + "ms");
        System.out.println("Max Queue Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        boolean pass = enqueued == 100000 && scaleTime < 2000;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 6: empty queue handling
    static boolean testEmptyQueue() {
        System.out.println("\n--- Test 6: Empty Queue ---");

        var config = new ScalingConfig(100, 0.8, 2, 10);
        var queue = new ScalableMessageQueue(100, config);

        var decision = queue.checkAndScale();

        System.out.println("Queue Size: " + queue.getQueueSize());
        System.out.println("Queue Latency: " + queue.getMaxLatency() + "ms");
        System.out.println("Decision: " + decision);

        boolean pass = !decision.shouldScale() && queue.getCurrentWorkers() == 2;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 7: max worker limit
    static boolean testMaxWorkerLimit() {
        System.out.println("\n--- Test 7: Max Worker Limit Respected ---");

        var config = new ScalingConfig(10, 0.5, 1, 5);
        var queue = new ScalableMessageQueue(100, config);

        for (int round = 0; round < 10; round++) {
            for (int i = 0; i < 80; i++) {
                queue.enqueue(new Message("m" + i, "p", System.currentTimeMillis(), 1));
            }
            try { Thread.sleep(20); } catch (InterruptedException e) {}
            queue.checkAndScale();
            queue.clear();
        }

        System.out.println("Final workers: " + queue.getCurrentWorkers());

        boolean pass = queue.getCurrentWorkers() <= 5;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 8: verify enqueueTime vs createdTime difference
    static boolean testEnqueueTimeVsCreatedTime() {
        System.out.println("\n--- Test 8: EnqueueTime vs CreatedTime ---");

        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // create message with OLD timestamp (simulating message created long ago)
        long oldCreatedTime = System.currentTimeMillis() - 5000;  // 5 seconds ago
        Message oldMsg = new Message("m1", "payload", oldCreatedTime, 1);

        // enqueue NOW - enqueueTime will be current time
        queue.enqueue(oldMsg);

        // get latency - should be very small (just enqueued)
        // NOT 5000ms (which would be wrong if using createdTime)
        long queueLatency = queue.getMaxLatency();

        System.out.println("Message Created: 5000ms ago");
        System.out.println("Actual Queue Latency: " + queueLatency + "ms");
        System.out.println("Using enqueueTime (correct): latency should be ~0ms");

        // verify: latency should be small (< 100ms)
        // if it were using createdTime, it would be ~5000ms
        boolean pass = queueLatency < 100;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // ==================== TEST METHODS ====================

    // Test 9: latency stats
    static boolean testLatencyStats() {
        System.out.println("\n--- Test 9: Latency Statistics ---");

        var config = new ScalingConfig(1000, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(100, config);

        // add messages with delays between them
        queue.enqueue(new Message("m1", "p", System.currentTimeMillis(), 1));
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        queue.enqueue(new Message("m2", "p", System.currentTimeMillis(), 1));
        try { Thread.sleep(50); } catch (InterruptedException e) {}

        queue.enqueue(new Message("m3", "p", System.currentTimeMillis(), 1));

        var stats = queue.getLatencyStats();

        System.out.println("Min Latency: " + stats.minLatencyMs() + "ms");
        System.out.println("Max Latency: " + stats.maxLatencyMs() + "ms");
        System.out.println("Avg Latency: " + stats.avgLatencyMs() + "ms");

        // verify: max > min (first message waited longer)
        boolean pass = stats.maxLatencyMs() > stats.minLatencyMs();

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 10: deque operations (addFirst, removeLast)
    static boolean testDequeOperations() {
        System.out.println("\n--- Test 10: Deque Operations ---");

        CustomDeque<Integer> deque = new CustomDeque<>(10);

        // test addLast
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // test addFirst
        deque.addFirst(0);

        // should be: 0, 1, 2, 3
        Integer first = deque.removeFirst();   // should be 0
        Integer last = deque.removeLast();     // should be 3

        System.out.println("First removed: " + first + " (expected 0)");
        System.out.println("Last removed: " + last + " (expected 3)");
        System.out.println("Remaining size: " + deque.size() + " (expected 2)");

        boolean pass = first == 0 && last == 3 && deque.size() == 2;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 11: multiple scale cycles
    static boolean testMultipleScaleCycles() {
        System.out.println("\n--- Test 11: Multiple Scale Cycles ---");

        var config = new ScalingConfig(30, 0.7, 1, 20);
        var queue = new ScalableMessageQueue(100, config);

        CustomArrayList<Integer> history = new CustomArrayList<>();
        history.add(queue.getCurrentWorkers());

        for (int cycle = 0; cycle < 5; cycle++) {
            // high load
            for (int i = 0; i < 75; i++) {
                queue.enqueue(new Message("m" + i, "p", System.currentTimeMillis(), 1));
            }
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            queue.checkAndScale();
            history.add(queue.getCurrentWorkers());

            // low load
            queue.clear();
            for (int i = 0; i < 10; i++) {
                queue.enqueue(new Message("m" + i, "p", System.currentTimeMillis(), 1));
            }
            queue.checkAndScale();
            history.add(queue.getCurrentWorkers());
            queue.clear();
        }

        System.out.print("Worker history: ");
        for (int i = 0; i < history.size(); i++) {
            System.out.print(history.get(i) + " ");
        }
        System.out.println();

        // verify workers changed
        boolean hasChanges = false;
        for (int i = 1; i < history.size(); i++) {
            if (!history.get(i).equals(history.get(i-1))) {
                hasChanges = true;
                break;
            }
        }

        boolean pass = hasChanges;
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // Test 12: capacity limit
    static boolean testCapacityLimit() {
        System.out.println("\n--- Test 12: Capacity Limit ---");

        var config = new ScalingConfig(100, 0.8, 1, 10);
        var queue = new ScalableMessageQueue(5, config);  // only 5 capacity

        int successCount = 0;
        for (int i = 0; i < 10; i++) {
            if (queue.enqueue(new Message("m" + i, "p", System.currentTimeMillis(), 1))) {
                successCount++;
            }
        }

        System.out.println("Attempted: 10, Succeeded: " + successCount);
        System.out.println("Queue size: " + queue.getQueueSize());

        boolean pass = successCount == 5 && queue.getQueueSize() == 5;

        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        return pass;
    }

    // ==================== MAIN METHOD ====================
    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("AUTO-SCALING MESSAGE QUEUE - WITH ENQUEUE TIME TRACKING");
        System.out.println("================================================================");
        System.out.println("Key Feature: Node stores enqueueTime for accurate latency calc");
        System.out.println("Latency = CurrentTime - EnqueueTime (NOT message createdTime)");
        System.out.println("================================================================");

        // run all tests
        boolean[] results = {
                testLatencyTriggeredScaling(),
                testQueueFillTriggeredScaling(),
                testNoScalingWhenHealthy(),
                testScaleDown(),
                testLargeDataHandling(),
                testEmptyQueue(),
                testMaxWorkerLimit(),
                testEnqueueTimeVsCreatedTime(),
                testLatencyStats(),
                testDequeOperations(),
                testMultipleScaleCycles(),
                testCapacityLimit()
        };

        // count passed
        int passed = 0;
        for (boolean result : results) {
            if (result) passed++;
        }

        // summary
        System.out.println("\n================================================================");
        System.out.println("SUMMARY: " + passed + "/" + results.length + " tests passed");
        System.out.println("================================================================");

        if (passed == results.length) {
            System.out.println("ALL TESTS PASSED!");
        } else {
            System.out.println("SOME TESTS FAILED!");
        }
    }

    // ==================== DOUBLY-LINKED NODE CLASS ====================
    // Node represents single element in doubly-linked list
    // Has next, prev pointers AND enqueueTime for latency calculation
    static class Node<T> {

        // actual data stored in this node
        T data;

        // reference to next node (towards tail)
        // null if this is last node
        Node<T> next;

        // reference to previous node (towards head)
        // null if this is first node
        Node<T> prev;

        // CRITICAL: timestamp when this node entered queue
        // used for accurate latency calculation
        // latency = currentTime - enqueueTime
        long enqueueTime;

        // constructor with data only
        // enqueueTime set when actually added to queue
        Node(T data) {
            this.data = data;                          // store payload
            this.next = null;                          // no next initially
            this.prev = null;                          // no prev initially
            this.enqueueTime = 0;                      // set during enqueue
        }

        // constructor with data and enqueue time
        // used when adding to queue
        Node(T data, long enqueueTime) {
            this.data = data;                          // store payload
            this.next = null;                          // no next initially
            this.prev = null;                          // no prev initially
            this.enqueueTime = enqueueTime;            // when entered queue
        }

        // full constructor with all fields
        // useful for complex insertions
        Node(T data, Node<T> prev, Node<T> next, long enqueueTime) {
            this.data = data;                          // store payload
            this.prev = prev;                          // link backward
            this.next = next;                          // link forward
            this.enqueueTime = enqueueTime;            // queue entry time
        }

        // calculate how long this node has been in queue
        // returns latency in milliseconds
        long getLatency() {
            return System.currentTimeMillis() - enqueueTime;
        }
    }

    // ==================== CUSTOM THREAD-SAFE DEQUE ====================
    // Doubly-linked list based deque implementation
    // Thread-safe using synchronized keyword
    // Tracks enqueue time for each node
    static class CustomDeque<T> {

        // maximum allowed elements
        private final int maxCapacity;
        // front of deque (dequeue from here for FIFO)
        private Node<T> head;
        // back of deque (enqueue here for FIFO)
        private Node<T> tail;
        // current number of elements
        private int size;

        // constructor sets up empty deque
        CustomDeque(int maxCapacity) {
            this.head = null;                          // empty has no head
            this.tail = null;                          // empty has no tail
            this.size = 0;                             // start empty
            this.maxCapacity = maxCapacity;            // set limit
        }

        // add element to front (head) - O(1)
        // sets enqueueTime to current time
        synchronized boolean addFirst(T data) {
            // check capacity before adding
            if (size >= maxCapacity) {
                return false;                          // reject if full
            }

            // create node with current time as enqueue time
            long now = System.currentTimeMillis();
            Node<T> newNode = new Node<>(data, now);

            // handle empty deque case
            if (head == null) {
                head = newNode;                        // becomes head
                tail = newNode;                        // and tail
            } else {
                // link to existing head
                newNode.next = head;                   // point to old head
                head.prev = newNode;                   // old head points back
                head = newNode;                        // new node is head
            }

            size++;                                    // increment count
            return true;                               // success
        }

        // add element to back (tail) - O(1)
        // standard enqueue operation
        // sets enqueueTime to current time
        synchronized boolean addLast(T data) {
            // check capacity before adding
            if (size >= maxCapacity) {
                return false;                          // reject if full
            }

            // create node with current time as enqueue time
            long now = System.currentTimeMillis();
            Node<T> newNode = new Node<>(data, now);

            // handle empty deque case
            if (tail == null) {
                head = newNode;                        // becomes head
                tail = newNode;                        // and tail
            } else {
                // link to existing tail
                newNode.prev = tail;                   // point to old tail
                tail.next = newNode;                   // old tail points forward
                tail = newNode;                        // new node is tail
            }

            size++;                                    // increment count
            return true;                               // success
        }

        // remove and return from front (head) - O(1)
        // standard dequeue operation
        synchronized T removeFirst() {
            // check if empty
            if (head == null) {
                return null;                           // nothing to return
            }

            // grab data from head
            T data = head.data;

            // move head forward
            head = head.next;

            // update links
            if (head == null) {
                tail = null;                           // deque now empty
            } else {
                head.prev = null;                      // clear back pointer
            }

            size--;                                    // decrement count
            return data;                               // return data
        }

        // remove and return from back (tail) - O(1)
        // possible because we have prev pointer
        synchronized T removeLast() {
            // check if empty
            if (tail == null) {
                return null;                           // nothing to return
            }

            // grab data from tail
            T data = tail.data;

            // move tail backward
            tail = tail.prev;

            // update links
            if (tail == null) {
                head = null;                           // deque now empty
            } else {
                tail.next = null;                      // clear forward pointer
            }

            size--;                                    // decrement count
            return data;                               // return data
        }

        // peek front without removing - O(1)
        synchronized T peekFirst() {
            return (head != null) ? head.data : null;
        }

        // peek back without removing - O(1)
        synchronized T peekLast() {
            return (tail != null) ? tail.data : null;
        }

        // get current size - O(1)
        synchronized int size() {
            return size;
        }

        // check if empty - O(1)
        synchronized boolean isEmpty() {
            return size == 0;
        }

        // get max capacity
        int getMaxCapacity() {
            return maxCapacity;
        }

        // CRITICAL: get maximum latency in queue
        // iterates all nodes and finds longest wait time
        // uses enqueueTime from each node
        synchronized long getMaxLatency() {
            // empty queue has no latency
            if (head == null) {
                return 0;
            }

            long now = System.currentTimeMillis();     // current time
            long maxLatency = 0;                       // track maximum
            Node<T> current = head;                    // start from head

            // traverse entire queue
            while (current != null) {
                // calculate this node's wait time
                long latency = now - current.enqueueTime;

                // update max if this is longer
                if (latency > maxLatency) {
                    maxLatency = latency;
                }

                current = current.next;                // move forward
            }

            return maxLatency;                         // return longest wait
        }

        // get oldest node (head has been waiting longest)
        // useful for priority processing
        synchronized Node<T> getOldestNode() {
            return head;                               // FIFO: head is oldest
        }

        // get newest node (tail just arrived)
        synchronized Node<T> getNewestNode() {
            return tail;                               // tail is newest
        }

        // remove specific node from middle - O(1)
        synchronized boolean removeNode(Node<T> node) {
            // null check
            if (node == null) {
                return false;
            }

            // fix previous node's next pointer
            if (node.prev != null) {
                node.prev.next = node.next;            // skip this node
            } else {
                head = node.next;                      // was head
            }

            // fix next node's prev pointer
            if (node.next != null) {
                node.next.prev = node.prev;            // skip this node
            } else {
                tail = node.prev;                      // was tail
            }

            // clear node links for GC
            node.prev = null;
            node.next = null;

            size--;                                    // decrement count
            return true;
        }

        // convert to array - O(n)
        synchronized Object[] toArray() {
            Object[] arr = new Object[size];
            Node<T> current = head;
            int index = 0;

            while (current != null) {
                arr[index++] = current.data;
                current = current.next;
            }

            return arr;
        }

        // get all nodes as array (includes enqueueTime) - O(n)
        @SuppressWarnings("unchecked")
        synchronized Node<T>[] toNodeArray() {
            Node<T>[] arr = new Node[size];
            Node<T> current = head;
            int index = 0;

            while (current != null) {
                arr[index++] = current;
                current = current.next;
            }

            return arr;
        }

        // clear all elements - O(1)
        synchronized void clear() {
            head = null;                               // remove head ref
            tail = null;                               // remove tail ref
            size = 0;                                  // reset count
        }

        // get latency statistics
        synchronized LatencyStats getLatencyStats() {
            if (head == null) {
                return new LatencyStats(0, 0, 0);      // empty queue
            }

            long now = System.currentTimeMillis();
            long min = Long.MAX_VALUE;
            long max = 0;
            long sum = 0;
            int count = 0;

            Node<T> current = head;
            while (current != null) {
                long latency = now - current.enqueueTime;

                if (latency < min) min = latency;
                if (latency > max) max = latency;
                sum += latency;
                count++;

                current = current.next;
            }

            long avg = (count > 0) ? sum / count : 0;
            return new LatencyStats(min, max, avg);
        }
    }

    // ==================== LATENCY STATS RECORD ====================
    // Holds latency statistics for monitoring
    record LatencyStats(
            long minLatencyMs,     // shortest wait time
            long maxLatencyMs,     // longest wait time
            long avgLatencyMs      // average wait time
    ) {}

    // ==================== MESSAGE RECORD ====================
    // Immutable message data holder
    record Message(
            String id,             // unique message identifier
            String payload,        // message content
            long createdTime,      // when message was CREATED (not queued)
            int priority           // message priority level
    ) {}

    // ==================== SCALING CONFIG RECORD ====================
    // Auto-scaling configuration parameters
    record ScalingConfig(
            long maxLatencyMs,     // max allowed queue wait time
            double fillThreshold,  // queue fill % trigger (0.0-1.0)
            int minWorkers,        // minimum workers to maintain
            int maxWorkers         // maximum workers allowed
    ) {}

    // ==================== SCALE DECISION RECORD ====================
    // Result of scaling evaluation
    record ScaleDecision(
            boolean shouldScale,   // true if scaling needed
            int targetWorkers,     // desired worker count
            String reason          // explanation for decision
    ) {}

    // ==================== CUSTOM ARRAY LIST ====================
    // Simple dynamic array for storing results
    static class CustomArrayList<T> {

        private Object[] elements;                     // internal storage
        private int size;                              // current count

        // constructor with initial capacity
        CustomArrayList(int initialCapacity) {
            this.elements = new Object[initialCapacity];
            this.size = 0;
        }

        // default constructor
        CustomArrayList() {
            this(10);                                  // default size 10
        }

        // add element
        void add(T element) {
            if (size >= elements.length) {
                grow();                                // expand if full
            }
            elements[size++] = element;
        }

        // get element at index
        @SuppressWarnings("unchecked")
        T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index);
            }
            return (T) elements[index];
        }

        // get size
        int size() {
            return size;
        }

        // expand array
        private void grow() {
            int newCapacity = elements.length * 2;
            Object[] newArray = new Object[newCapacity];
            if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    // ==================== SCALABLE MESSAGE QUEUE ====================
    // Main queue with auto-scaling logic
    static class ScalableMessageQueue {

        // custom deque to hold messages
        private final CustomDeque<Message> queue;

        // scaling configuration
        private final ScalingConfig config;

        // current active worker count
        private volatile int currentWorkers;

        // constructor
        ScalableMessageQueue(int maxCapacity, ScalingConfig config) {
            this.queue = new CustomDeque<>(maxCapacity);
            this.config = config;
            this.currentWorkers = config.minWorkers();
        }

        // add message to queue
        // enqueueTime set automatically by CustomDeque
        boolean enqueue(Message msg) {
            return queue.addLast(msg);                 // FIFO: add to tail
        }

        // remove message from queue
        Message dequeue() {
            return queue.removeFirst();                // FIFO: remove from head
        }

        // calculate queue fill percentage (0.0 to 1.0)
        double getQueueFillPercentage() {
            return (double) queue.size() / queue.getMaxCapacity();
        }

        // CRITICAL: get max latency using NODE's enqueueTime
        // NOT message's createdTime
        // This is the actual queue wait time
        long getMaxLatency() {
            return queue.getMaxLatency();              // delegate to deque
        }

        // get full latency statistics
        LatencyStats getLatencyStats() {
            return queue.getLatencyStats();
        }

        // CORE: auto-scaling decision logic
        ScaleDecision checkAndScale() {
            // gather metrics
            long maxLatency = getMaxLatency();         // queue wait time
            double fillPct = getQueueFillPercentage(); // fill percentage

            // CONDITION 1: latency exceeds threshold
            // messages waiting too long in queue
            boolean latencyBreached = maxLatency > config.maxLatencyMs();

            // CONDITION 2: queue filling up
            // incoming rate > processing rate
            boolean queueFilling = fillPct > config.fillThreshold();

            // SCALE UP: either condition triggers
            if (latencyBreached || queueFilling) {
                // calculate new worker count (+50%, min +1)
                int increase = Math.max(1, currentWorkers / 2);
                int newWorkers = Math.min(
                        config.maxWorkers(),               // cap at max
                        currentWorkers + increase          // add workers
                );

                // build reason
                String reason;
                if (latencyBreached) {
                    reason = "QueueLatency=" + maxLatency + "ms > " + config.maxLatencyMs() + "ms";
                } else {
                    reason = "QueueFill=" + formatPercent(fillPct);
                }

                // apply if changed
                if (newWorkers > currentWorkers) {
                    currentWorkers = newWorkers;
                    return new ScaleDecision(true, newWorkers, "SCALE UP: " + reason);
                }
            }

            // SCALE DOWN: check if load is low
            boolean lowFill = fillPct < 0.2;
            boolean lowLatency = maxLatency < config.maxLatencyMs() / 2;
            boolean canScaleDown = lowFill && lowLatency;

            if (canScaleDown && currentWorkers > config.minWorkers()) {
                // reduce by 25%
                int decrease = Math.max(1, currentWorkers / 4);
                int newWorkers = Math.max(
                        config.minWorkers(),               // floor at min
                        currentWorkers - decrease          // reduce
                );

                if (newWorkers < currentWorkers) {
                    currentWorkers = newWorkers;
                    return new ScaleDecision(true, newWorkers, "SCALE DOWN: Low load");
                }
            }

            // NO SCALING: conditions normal
            return new ScaleDecision(false, currentWorkers, "No scaling needed");
        }

        // format percentage
        private String formatPercent(double value) {
            return String.format("%.1f%%", value * 100);
        }

        // getters
        int getCurrentWorkers() { return currentWorkers; }
        int getQueueSize() { return queue.size(); }
        int getMaxCapacity() { return queue.getMaxCapacity(); }
        ScalingConfig getConfig() { return config; }

        // clear queue
        void clear() { queue.clear(); }
    }
}