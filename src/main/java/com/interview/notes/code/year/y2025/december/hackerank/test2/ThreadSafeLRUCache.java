package com.interview.notes.code.year.y2025.december.hackerank.test2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * Thread-Safe LRU Cache Implementation
 * <p>
 * Design:
 * - ConcurrentHashMap: Thread-safe O(1) key lookup
 * - Doubly Linked List: O(1) insertion/deletion for order tracking
 * - ReentrantReadWriteLock: Allows multiple readers OR single writer
 * <p>
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 */
public class ThreadSafeLRUCache<K, V> {

    // ==================== INNER CLASS: NODE ====================
    // Maximum number of entries cache can hold
    private final int capacity;

    // ==================== INSTANCE VARIABLES ====================
    // Thread-safe map for O(1) key-to-node lookup
    private final ConcurrentHashMap<K, Node<K, V>> cacheMap;
    // Dummy head node - sentinel marking most recently used end
    private final Node<K, V> head;
    // Dummy tail node - sentinel marking least recently used end
    private final Node<K, V> tail;
    // Read-Write lock for thread safety
    // Multiple threads can read simultaneously
    // Only one thread can write at a time
    private final ReentrantReadWriteLock lock;
    // Separate locks for finer control
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    // Counter for cache hits (for statistics)
    private final AtomicInteger hitCount;
    // Counter for cache misses (for statistics)
    private final AtomicInteger missCount;

    /**
     * Initialize thread-safe LRU Cache
     *
     * @param capacity maximum entries (must be positive)
     * @throws IllegalArgumentException if capacity <= 0
     */
    public ThreadSafeLRUCache(int capacity) {

        // Validate capacity - cannot be zero or negative
        if (capacity <= 0) {
            // Throw exception for invalid capacity
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }

        // Store maximum capacity
        this.capacity = capacity;

        // Initialize ConcurrentHashMap with initial capacity
        // Load factor 0.75 and concurrency level for better performance
        this.cacheMap = new ConcurrentHashMap<>(capacity, 0.75f, 16);

        // Create dummy head node (sentinel - no real data)
        // Head.next points to most recently used real node
        this.head = new Node<>(null, null);

        // Create dummy tail node (sentinel - no real data)
        // Tail.prev points to least recently used real node
        this.tail = new Node<>(null, null);

        // Connect head and tail (empty list state)
        // Head -> Tail initially
        this.head.next = this.tail;

        // Tail <- Head initially
        this.tail.prev = this.head;

        // Create fair read-write lock
        // Fair = threads get lock in order they requested
        this.lock = new ReentrantReadWriteLock(true);

        // Get read lock reference for convenience
        this.readLock = lock.readLock();

        // Get write lock reference for convenience
        this.writeLock = lock.writeLock();

        // Initialize hit counter to zero
        this.hitCount = new AtomicInteger(0);

        // Initialize miss counter to zero
        this.missCount = new AtomicInteger(0);
    }

    // ==================== CONSTRUCTOR ====================

    public static void main(String[] args) {

        // Initialize test counters
        int totalTests = 0;
        int passedTests = 0;

        // Print header
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║       THREAD-SAFE LRU CACHE - COMPREHENSIVE TEST SUITE       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // ==================== TEST 1: Basic Put and Get ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 1: Basic Put and Get Operations                        │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with capacity 3
            ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(3);

            // Put three key-value pairs
            cache.put(1, "Apple");
            cache.put(2, "Banana");
            cache.put(3, "Cherry");

            // Verify all values can be retrieved
            boolean val1Correct = "Apple".equals(cache.get(1));
            boolean val2Correct = "Banana".equals(cache.get(2));
            boolean val3Correct = "Cherry".equals(cache.get(3));
            boolean sizeCorrect = cache.size() == 3;

            // Check all conditions
            boolean testPassed = val1Correct && val2Correct && val3Correct && sizeCorrect;

            // Print result
            System.out.println("  Expected: get(1)=Apple, get(2)=Banana, get(3)=Cherry, size=3");
            System.out.println("  Actual:   get(1)=" + cache.get(1) + ", get(2)=" + cache.get(2) +
                    ", get(3)=" + cache.get(3) + ", size=" + cache.size());
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 2: LRU Eviction ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 2: LRU Eviction When Capacity Exceeded                 │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with capacity 2
            ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(2);

            // Put two items - cache is now full
            cache.put(1, "One");   // List order: [1]
            cache.put(2, "Two");   // List order: [2, 1]

            // Access key 1 - moves it to front (most recently used)
            cache.get(1);          // List order: [1, 2]

            // Put new item - should evict key 2 (LRU)
            cache.put(3, "Three"); // List order: [3, 1], key 2 evicted

            // Verify key 2 was evicted
            boolean key2Evicted = cache.get(2) == null;
            boolean key1Present = "One".equals(cache.get(1));
            boolean key3Present = "Three".equals(cache.get(3));
            boolean sizeCorrect = cache.size() == 2;

            boolean testPassed = key2Evicted && key1Present && key3Present && sizeCorrect;

            System.out.println("  Scenario: put(1), put(2), get(1), put(3)");
            System.out.println("  Expected: key 2 evicted (LRU), keys 1 and 3 present");
            System.out.println("  Actual:   get(2)=" + cache.get(2) + " (should be null)");
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 3: Update Existing Key ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 3: Update Existing Key Value                           │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with capacity 2
            ThreadSafeLRUCache<String, Integer> cache = new ThreadSafeLRUCache<>(2);

            // Put initial value
            cache.put("count", 100);

            // Update same key with new value
            cache.put("count", 200);

            // Verify value was updated
            boolean valueUpdated = cache.get("count") == 200;
            boolean sizeStaySame = cache.size() == 1;

            boolean testPassed = valueUpdated && sizeStaySame;

            System.out.println("  Scenario: put('count', 100), then put('count', 200)");
            System.out.println("  Expected: get('count')=200, size=1");
            System.out.println("  Actual:   get('count')=" + cache.get("count") + ", size=" + cache.size());
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 4: Cache Miss ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 4: Cache Miss (Key Not Found)                          │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(5);

            // Put some data
            cache.put("exists", "value");

            // Try to get non-existent key
            String result = cache.get("nonexistent");

            // Verify null returned for missing key
            boolean testPassed = result == null;

            System.out.println("  Scenario: get key that doesn't exist");
            System.out.println("  Expected: null");
            System.out.println("  Actual:   " + result);
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 5: Capacity 1 Edge Case ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 5: Edge Case - Capacity 1                              │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with minimum capacity
            ThreadSafeLRUCache<Integer, Integer> cache = new ThreadSafeLRUCache<>(1);

            // Put first item
            cache.put(1, 100);
            boolean first = cache.get(1) == 100;

            // Put second item - should evict first
            cache.put(2, 200);
            boolean secondPresent = cache.get(2) == 200;
            boolean firstEvicted = cache.get(1) == null;

            boolean testPassed = first && secondPresent && firstEvicted;

            System.out.println("  Scenario: Capacity=1, put(1), then put(2)");
            System.out.println("  Expected: Only key 2 remains");
            System.out.println("  Actual:   get(1)=" + cache.get(1) + ", get(2)=" + cache.get(2));
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 6: Remove Operation ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 6: Remove Operation                                    │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(3);

            // Put items
            cache.put("a", "alpha");
            cache.put("b", "beta");
            cache.put("c", "gamma");

            // Remove middle item
            String removed = cache.remove("b");

            // Verify removal
            boolean removedCorrect = "beta".equals(removed);
            boolean notInCache = cache.get("b") == null;
            boolean sizeCorrect = cache.size() == 2;
            boolean othersPresent = cache.get("a") != null && cache.get("c") != null;

            boolean testPassed = removedCorrect && notInCache && sizeCorrect && othersPresent;

            System.out.println("  Scenario: Put a,b,c then remove b");
            System.out.println("  Expected: remove returns 'beta', size=2, a and c remain");
            System.out.println("  Actual:   removed=" + removed + ", size=" + cache.size());
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 7: Clear Operation ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 7: Clear Operation                                     │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            ThreadSafeLRUCache<Integer, Integer> cache = new ThreadSafeLRUCache<>(5);

            // Add items
            cache.put(1, 10);
            cache.put(2, 20);
            cache.put(3, 30);

            // Clear cache
            cache.clear();

            // Verify empty
            boolean isEmpty = cache.isEmpty();
            boolean sizeZero = cache.size() == 0;
            boolean allNull = cache.get(1) == null && cache.get(2) == null && cache.get(3) == null;

            boolean testPassed = isEmpty && sizeZero && allNull;

            System.out.println("  Scenario: Add 3 items then clear");
            System.out.println("  Expected: isEmpty=true, size=0, all gets return null");
            System.out.println("  Actual:   isEmpty=" + isEmpty + ", size=" + cache.size());
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 8: Invalid Capacity ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 8: Invalid Capacity Exception                          │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            boolean zeroCaught = false;
            boolean negativeCaught = false;

            // Test capacity = 0
            try {
                new ThreadSafeLRUCache<>(0);
            } catch (IllegalArgumentException e) {
                zeroCaught = true;
            }

            // Test negative capacity
            try {
                new ThreadSafeLRUCache<>(-5);
            } catch (IllegalArgumentException e) {
                negativeCaught = true;
            }

            boolean testPassed = zeroCaught && negativeCaught;

            System.out.println("  Scenario: Create cache with capacity 0 and -5");
            System.out.println("  Expected: IllegalArgumentException for both");
            System.out.println("  Actual:   Zero caught=" + zeroCaught + ", Negative caught=" + negativeCaught);
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 9: Large Data Input ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 9: Large Data Input (100,000 entries)                  │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with capacity 10000
            final int cacheCapacity = 10000;
            final int totalEntries = 100000;

            ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(cacheCapacity);

            // Record start time
            long startTime = System.currentTimeMillis();

            // Insert 100000 entries using Stream API
            IntStream.range(0, totalEntries)
                    .forEach(i -> cache.put(i, "Value_" + i));

            // Record end time for puts
            long putTime = System.currentTimeMillis() - startTime;

            // Verify size equals capacity (older entries evicted)
            boolean sizeCorrect = cache.size() == cacheCapacity;

            // Verify old entries evicted (keys 0 to 89999 should be gone)
            boolean oldEvicted = cache.get(0) == null;

            // Verify recent entries present (keys 90000 to 99999 should exist)
            boolean recentPresent = cache.get(totalEntries - 1) != null;

            // Test gets performance
            startTime = System.currentTimeMillis();
            IntStream.range(totalEntries - cacheCapacity, totalEntries)
                    .forEach(i -> cache.get(i));
            long getTime = System.currentTimeMillis() - startTime;

            boolean testPassed = sizeCorrect && oldEvicted && recentPresent;

            System.out.println("  Scenario: Insert 100,000 entries into cache of size 10,000");
            System.out.println("  Expected: size=10000, old keys evicted, recent keys present");
            System.out.println("  Actual:   size=" + cache.size() + ", get(0)=" + cache.get(0) +
                    ", get(99999)=" + cache.get(99999));
            System.out.println("  Performance: PUT time=" + putTime + "ms, GET time=" + getTime + "ms");
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
            e.printStackTrace();
        }
        System.out.println();

        // ==================== TEST 10: Concurrent Access (Thread Safety) ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 10: Concurrent Access (Multi-threaded)                 │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache for concurrent test
            final ThreadSafeLRUCache<Integer, Integer> cache = new ThreadSafeLRUCache<>(1000);

            // Counter for successful operations
            final AtomicInteger successfulOps = new AtomicInteger(0);
            final AtomicInteger errors = new AtomicInteger(0);

            // Number of threads and operations
            final int threadCount = 10;
            final int opsPerThread = 1000;

            // Create thread pool
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);

            // Record start time
            long startTime = System.currentTimeMillis();

            // Submit tasks for concurrent put/get operations
            IntStream.range(0, threadCount).forEach(threadId -> {
                executor.submit(() -> {
                    try {
                        // Each thread does put and get operations
                        for (int i = 0; i < opsPerThread; i++) {
                            // Calculate unique key for this thread
                            int key = threadId * opsPerThread + i;

                            // Put operation
                            cache.put(key, key * 10);

                            // Get operation and verify
                            Integer value = cache.get(key);

                            // Value might be evicted, so just count operation
                            successfulOps.incrementAndGet();
                        }
                    } catch (Exception e) {
                        errors.incrementAndGet();
                    }
                });
            });

            // Shutdown executor and wait for completion
            executor.shutdown();
            boolean completed = executor.awaitTermination(30, TimeUnit.SECONDS);

            // Record end time
            long totalTime = System.currentTimeMillis() - startTime;

            // Calculate expected operations
            int expectedOps = threadCount * opsPerThread;

            // Verify no errors and all operations completed
            boolean noErrors = errors.get() == 0;
            boolean allCompleted = completed;
            boolean opsSuccessful = successfulOps.get() == expectedOps;

            boolean testPassed = noErrors && allCompleted && opsSuccessful;

            System.out.println("  Scenario: " + threadCount + " threads, " + opsPerThread + " ops each");
            System.out.println("  Expected: " + expectedOps + " successful operations, 0 errors");
            System.out.println("  Actual:   " + successfulOps.get() + " operations, " + errors.get() + " errors");
            System.out.println("  Performance: " + totalTime + "ms for " + expectedOps + " operations");
            System.out.println("  Final cache size: " + cache.size());
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
            e.printStackTrace();
        }
        System.out.println();

        // ==================== TEST 11: Concurrent Reads and Writes ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 11: Mixed Concurrent Reads and Writes                  │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            final ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(500);

            // Pre-populate cache
            IntStream.range(0, 500).forEach(i -> cache.put(i, "Initial_" + i));

            // Counters
            final AtomicInteger readOps = new AtomicInteger(0);
            final AtomicInteger writeOps = new AtomicInteger(0);
            final AtomicInteger errors = new AtomicInteger(0);

            // Create executor
            ExecutorService executor = Executors.newFixedThreadPool(20);

            long startTime = System.currentTimeMillis();

            // Submit reader threads (10 threads)
            IntStream.range(0, 10).forEach(t -> {
                executor.submit(() -> {
                    try {
                        for (int i = 0; i < 500; i++) {
                            cache.get(i % 500);
                            readOps.incrementAndGet();
                        }
                    } catch (Exception e) {
                        errors.incrementAndGet();
                    }
                });
            });

            // Submit writer threads (10 threads)
            IntStream.range(0, 10).forEach(t -> {
                executor.submit(() -> {
                    try {
                        for (int i = 0; i < 500; i++) {
                            cache.put(i % 500, "Updated_" + t + "_" + i);
                            writeOps.incrementAndGet();
                        }
                    } catch (Exception e) {
                        errors.incrementAndGet();
                    }
                });
            });

            // Wait for completion
            executor.shutdown();
            boolean completed = executor.awaitTermination(30, TimeUnit.SECONDS);

            long totalTime = System.currentTimeMillis() - startTime;

            // Verify
            boolean noErrors = errors.get() == 0;
            boolean testPassed = noErrors && completed;

            System.out.println("  Scenario: 10 reader threads + 10 writer threads, 500 ops each");
            System.out.println("  Expected: No errors, all operations complete");
            System.out.println("  Actual:   Reads=" + readOps.get() + ", Writes=" + writeOps.get() +
                    ", Errors=" + errors.get());
            System.out.println("  Performance: " + totalTime + "ms total");
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 12: LRU Order Verification ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 12: LRU Order Verification                             │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache with capacity 3
            ThreadSafeLRUCache<String, Integer> cache = new ThreadSafeLRUCache<>(3);

            // Add items: A, B, C (order: C most recent, A least recent)
            cache.put("A", 1);  // [A]
            cache.put("B", 2);  // [B, A]
            cache.put("C", 3);  // [C, B, A]

            // Access A - makes it most recent: [A, C, B]
            cache.get("A");

            // Add D - should evict B (now least recent): [D, A, C]
            cache.put("D", 4);

            // Verify B is evicted, others present
            boolean bEvicted = cache.get("B") == null;
            boolean aPresent = cache.get("A") == 1;
            boolean cPresent = cache.get("C") == 3;
            boolean dPresent = cache.get("D") == 4;

            boolean testPassed = bEvicted && aPresent && cPresent && dPresent;

            System.out.println("  Scenario: put(A), put(B), put(C), get(A), put(D)");
            System.out.println("  Expected: B evicted (LRU after get(A)), A,C,D present");
            System.out.println("  Actual:   B=" + cache.get("B") + ", A=" + aPresent +
                    ", C=" + cPresent + ", D=" + dPresent);
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 13: Hit/Miss Statistics ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 13: Cache Hit/Miss Statistics                          │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            ThreadSafeLRUCache<Integer, Integer> cache = new ThreadSafeLRUCache<>(5);

            // Add 5 items
            for (int i = 1; i <= 5; i++) {
                cache.put(i, i * 10);
            }

            // 3 hits (existing keys)
            cache.get(1);
            cache.get(3);
            cache.get(5);

            // 2 misses (non-existing keys)
            cache.get(10);
            cache.get(20);

            // Verify statistics
            boolean hitCountCorrect = cache.getHitCount() == 3;
            boolean missCountCorrect = cache.getMissCount() == 2;
            boolean ratioCorrect = Math.abs(cache.getHitRatio() - 0.6) < 0.001;

            boolean testPassed = hitCountCorrect && missCountCorrect && ratioCorrect;

            System.out.println("  Scenario: 3 cache hits, 2 cache misses");
            System.out.println("  Expected: hits=3, misses=2, ratio=0.6");
            System.out.println("  Actual:   hits=" + cache.getHitCount() + ", misses=" + cache.getMissCount() +
                    ", ratio=" + String.format("%.2f", cache.getHitRatio()));
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 14: Null Key/Value Handling ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 14: Null Key/Value Handling                            │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(5);

            boolean nullKeyExceptionCaught = false;
            boolean nullValueExceptionCaught = false;
            boolean nullGetReturnsNull = false;

            // Test null key in put
            try {
                cache.put(null, "value");
            } catch (IllegalArgumentException e) {
                nullKeyExceptionCaught = true;
            }

            // Test null value in put
            try {
                cache.put("key", null);
            } catch (IllegalArgumentException e) {
                nullValueExceptionCaught = true;
            }

            // Test null key in get (should return null, not throw)
            nullGetReturnsNull = cache.get(null) == null;

            boolean testPassed = nullKeyExceptionCaught && nullValueExceptionCaught && nullGetReturnsNull;

            System.out.println("  Scenario: put(null, value), put(key, null), get(null)");
            System.out.println("  Expected: Exceptions for null key/value in put, null for get(null)");
            System.out.println("  Actual:   nullKey exception=" + nullKeyExceptionCaught +
                    ", nullValue exception=" + nullValueExceptionCaught +
                    ", get(null)=" + (nullGetReturnsNull ? "null" : "not null"));
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
        }
        System.out.println();

        // ==================== TEST 15: Stress Test with Large Data ====================
        totalTests++;
        System.out.println("┌─────────────────────────────────────────────────────────────┐");
        System.out.println("│ TEST 15: Stress Test (1 Million Operations)                 │");
        System.out.println("└─────────────────────────────────────────────────────────────┘");

        try {
            // Create cache
            final ThreadSafeLRUCache<Long, String> cache = new ThreadSafeLRUCache<>(50000);

            final int totalOperations = 1_000_000;
            final AtomicInteger completedOps = new AtomicInteger(0);

            long startTime = System.currentTimeMillis();

            // Use parallel stream for stress test
            IntStream.range(0, totalOperations)
                    .parallel()
                    .forEach(i -> {
                        // Mix of put and get operations
                        if (i % 3 == 0) {
                            cache.get((long) (i % 50000));
                        } else {
                            cache.put((long) i, "Value_" + i);
                        }
                        completedOps.incrementAndGet();
                    });

            long totalTime = System.currentTimeMillis() - startTime;

            // Verify
            boolean allOpsCompleted = completedOps.get() == totalOperations;
            boolean cacheFunctional = cache.size() <= 50000 && cache.size() > 0;

            boolean testPassed = allOpsCompleted && cacheFunctional;

            System.out.println("  Scenario: 1 million mixed put/get operations");
            System.out.println("  Expected: All operations complete, cache size <= 50000");
            System.out.println("  Actual:   Completed=" + completedOps.get() + ", Cache size=" + cache.size());
            System.out.println("  Performance: " + totalTime + "ms (" +
                    (totalOperations / (totalTime / 1000.0)) + " ops/sec)");
            System.out.println("  Result: " + (testPassed ? "PASS ✓" : "FAIL ✗"));

            if (testPassed) passedTests++;

        } catch (Exception e) {
            System.out.println("  Result: FAIL ✗ (Exception: " + e.getMessage() + ")");
            e.printStackTrace();
        }
        System.out.println();

        // ==================== FINAL SUMMARY ====================
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     TEST SUMMARY                             ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total Tests: %-46d ║%n", totalTests);
        System.out.printf("║  Passed:      %-46d ║%n", passedTests);
        System.out.printf("║  Failed:      %-46d ║%n", (totalTests - passedTests));
        System.out.printf("║  Pass Rate:   %-46.1f%% ║%n", (100.0 * passedTests / totalTests));
        System.out.println("╠══════════════════════════════════════════════════════════════╣");

        if (passedTests == totalTests) {
            System.out.println("║           ★ ALL TESTS PASSED SUCCESSFULLY! ★                ║");
        } else {
            System.out.println("║              ⚠ SOME TESTS FAILED                             ║");
        }

        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    // ==================== CORE OPERATIONS ====================

    /**
     * Get value from cache by key
     * Thread-safe: Uses read lock initially, upgrades to write for reordering
     *
     * @param key the key to lookup
     * @return value if found, null if not present
     */
    public V get(K key) {

        // Null keys not allowed
        if (key == null) {
            // Return null for null key
            return null;
        }

        // Acquire write lock (need to modify list order on access)
        writeLock.lock();

        try {
            // Lookup node in map - O(1) operation
            Node<K, V> node = cacheMap.get(key);

            // Check if key was found
            if (node == null) {
                // Key not in cache - increment miss count
                missCount.incrementAndGet();

                // Return null to indicate cache miss
                return null;
            }

            // Key found - increment hit count
            hitCount.incrementAndGet();

            // Move accessed node to front (most recently used)
            // This maintains LRU order
            moveToFront(node);

            // Return the cached value
            return node.value;

        } finally {
            // ALWAYS release lock in finally block
            // This ensures lock is released even if exception occurs
            writeLock.unlock();
        }
    }

    /**
     * Put key-value pair into cache
     * Thread-safe: Uses write lock for modification
     * <p>
     * If key exists: Update value and move to front
     * If key new and cache full: Evict LRU item first
     *
     * @param key   the key (cannot be null)
     * @param value the value to cache (cannot be null)
     */
    public void put(K key, V value) {

        // Validate key - null not allowed
        if (key == null) {
            // Throw exception for null key
            throw new IllegalArgumentException("Key cannot be null");
        }

        // Validate value - null not allowed
        if (value == null) {
            // Throw exception for null value
            throw new IllegalArgumentException("Value cannot be null");
        }

        // Acquire write lock for thread-safe modification
        writeLock.lock();

        try {
            // Check if key already exists in cache
            Node<K, V> existingNode = cacheMap.get(key);

            if (existingNode != null) {
                // Key exists - update the value
                existingNode.value = value;

                // Move to front (mark as most recently used)
                moveToFront(existingNode);

            } else {
                // Key is new - check if we need to evict
                if (cacheMap.size() >= capacity) {
                    // Cache is full - must evict LRU item
                    evictLeastRecentlyUsed();
                }

                // Create new node with key-value pair
                Node<K, V> newNode = new Node<>(key, value);

                // Add node to front of linked list
                addToFront(newNode);

                // Add to map for O(1) lookup
                cacheMap.put(key, newNode);
            }

        } finally {
            // ALWAYS release write lock in finally
            writeLock.unlock();
        }
    }

    /**
     * Remove key from cache if present
     * Thread-safe operation
     *
     * @param key the key to remove
     * @return removed value, or null if key not found
     */
    public V remove(K key) {

        // Null check for key
        if (key == null) {
            return null;
        }

        // Acquire write lock for modification
        writeLock.lock();

        try {
            // Get node from map
            Node<K, V> node = cacheMap.get(key);

            // Check if key exists
            if (node == null) {
                // Key not found - return null
                return null;
            }

            // Remove from linked list
            removeNode(node);

            // Remove from map
            cacheMap.remove(key);

            // Return the removed value
            return node.value;

        } finally {
            // Release write lock
            writeLock.unlock();
        }
    }

    // ==================== HELPER METHODS (Private) ====================

    /**
     * Move existing node to front of list
     * Called when node is accessed (get) or updated (put)
     *
     * @param node the node to move to front
     */
    private void moveToFront(Node<K, V> node) {

        // First: Remove node from current position
        removeNode(node);

        // Second: Add node to front (after head)
        addToFront(node);
    }

    /**
     * Remove node from its current position in doubly linked list
     * Does NOT remove from map - just unlinks from list
     *
     * @param node the node to unlink
     */
    private void removeNode(Node<K, V> node) {

        // Get reference to node before current
        Node<K, V> prevNode = node.prev;

        // Get reference to node after current
        Node<K, V> nextNode = node.next;

        // Connect previous to next (skip over current node)
        // Before: prev <-> current <-> next
        // After:  prev <-> next
        prevNode.next = nextNode;

        // Connect next back to previous
        nextNode.prev = prevNode;

        // Clear current node's pointers (optional but clean)
        node.prev = null;
        node.next = null;
    }

    /**
     * Add node to front of list (right after head sentinel)
     * Front = most recently used position
     *
     * @param node the node to add
     */
    private void addToFront(Node<K, V> node) {

        // Get current first real node (node after head)
        Node<K, V> firstNode = head.next;

        // Point head to new node
        // head -> newNode
        head.next = node;

        // Point new node back to head
        // head <-> newNode
        node.prev = head;

        // Point new node to old first node
        // head <-> newNode -> firstNode
        node.next = firstNode;

        // Point old first node back to new node
        // head <-> newNode <-> firstNode
        firstNode.prev = node;
    }

    /**
     * Evict the least recently used item from cache
     * LRU item is at tail.prev (just before tail sentinel)
     */
    private void evictLeastRecentlyUsed() {

        // Get LRU node (node just before tail)
        Node<K, V> lruNode = tail.prev;

        // Safety check - don't remove head sentinel
        if (lruNode == head) {
            // Cache is empty - nothing to evict
            return;
        }

        // Remove from linked list
        removeNode(lruNode);

        // Remove from map using stored key
        // This is why we store key in node
        cacheMap.remove(lruNode.key);
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Get current number of entries in cache
     * Thread-safe read operation
     *
     * @return current cache size
     */
    public int size() {

        // Acquire read lock for consistent read
        readLock.lock();

        try {
            // Return map size
            return cacheMap.size();
        } finally {
            // Release read lock
            readLock.unlock();
        }
    }

    /**
     * Check if cache contains given key
     * Thread-safe read operation
     *
     * @param key the key to check
     * @return true if key exists in cache
     */
    public boolean containsKey(K key) {

        // Acquire read lock
        readLock.lock();

        try {
            // Check map for key
            return cacheMap.containsKey(key);
        } finally {
            // Release read lock
            readLock.unlock();
        }
    }

    /**
     * Check if cache is empty
     *
     * @return true if cache has no entries
     */
    public boolean isEmpty() {

        readLock.lock();

        try {
            return cacheMap.isEmpty();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Clear all entries from cache
     * Thread-safe operation
     */
    public void clear() {

        // Acquire write lock for modification
        writeLock.lock();

        try {
            // Clear the map
            cacheMap.clear();

            // Reset linked list to empty state
            head.next = tail;
            tail.prev = head;

            // Reset statistics
            hitCount.set(0);
            missCount.set(0);

        } finally {
            // Release write lock
            writeLock.unlock();
        }
    }

    /**
     * Get cache hit count for statistics
     *
     * @return number of cache hits
     */
    public int getHitCount() {
        return hitCount.get();
    }

    /**
     * Get cache miss count for statistics
     *
     * @return number of cache misses
     */
    public int getMissCount() {
        return missCount.get();
    }

    /**
     * Calculate cache hit ratio
     *
     * @return hit ratio (0.0 to 1.0)
     */
    public double getHitRatio() {

        // Get total accesses
        int hits = hitCount.get();
        int misses = missCount.get();
        int total = hits + misses;

        // Avoid division by zero
        if (total == 0) {
            return 0.0;
        }

        // Calculate and return ratio
        return (double) hits / total;
    }

    /**
     * Get maximum capacity of cache
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    // ==================== MAIN METHOD - TEST SUITE ====================

    /**
     * Node represents single cache entry in doubly linked list
     * Stores key (for map removal), value, and navigation pointers
     */
    private static class Node<K, V> {

        // Key stored in node - needed when evicting to remove from map
        final K key;

        // Actual cached value - volatile for thread visibility
        volatile V value;

        // Pointer to previous node (towards head - more recent)
        volatile Node<K, V> prev;

        // Pointer to next node (towards tail - less recent)
        volatile Node<K, V> next;

        /**
         * Constructor creates node with key-value pair
         *
         * @param key   the cache key
         * @param value the cached value
         */
        Node(K key, V value) {
            // Store key for later map removal during eviction
            this.key = key;

            // Store the actual value
            this.value = value;
        }
    }
}