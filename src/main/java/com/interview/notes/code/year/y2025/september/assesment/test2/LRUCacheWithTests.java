package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.*; // import collections framework classes used below
import java.util.stream.*; // import streams for test data generation and processing

public class LRUCacheWithTests { // main class containing LRU cache implementation and tests

    // LRUCache implementation using LinkedHashMap with accessOrder = true
    public static class LRUCache { // public static inner class for the cache
        private final int capacity; // store capacity limit of the cache
        private final LinkedHashMap<Integer, Integer> map; // LinkedHashMap to maintain order and store key->value

        public LRUCache(int capacity) { // constructor to initialize cache with given capacity
            this.capacity = capacity; // assign provided capacity to instance field
            // create a LinkedHashMap with access-order enabled to track recent usage
            this.map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) { // anonymous subclass to override removal behavior
                private static final long serialVersionUID = 1L; // serial version id for anonymous class
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) { // called after each put
                    return size() > LRUCache.this.capacity; // remove eldest when size exceeds capacity
                }
            }; // end of LinkedHashMap initialization
        } // end constructor

        public int get(int key) { // get the value associated with key, or -1 if absent
            Integer val = map.get(key); // LinkedHashMap.get updates access order when accessOrder=true
            return val == null ? -1 : val; // return -1 if key missing, else return actual value
        } // end get

        public void put(int key, int value) { // put key,value into cache, updating if exists
            map.put(key, value); // LinkedHashMap.put will update value and access order; removal handled by removeEldestEntry
        } // end put

        // helper to expose current keys order from most-recent to least-recent for testing/inspection
        public List<Integer> keysMostRecentFirst() { // returns keys in MRU->LRU order
            List<Integer> keys = new ArrayList<>(map.keySet()); // LinkedHashMap.keySet is in LRU->MRU order when accessOrder=true? Actually iteration returns from least-recently-accessed to most-recently-accessed.
            Collections.reverse(keys); // reverse to MRU->LRU for intuitive tests
            return keys; // return reversed list
        } // end keysMostRecentFirst

        public int currentSize() { // return current number of entries
            return map.size(); // size of the underlying map
        } // end currentSize
    } // end LRUCache class

    // simple test case container
    static class TestCase { // holds test metadata and expected results
        final String name; // name of test case
        final Runnable action; // action that performs the test and asserts behavior
        final boolean expectedPass; // expected pass/fail outcome (true => expected to pass)

        TestCase(String name, Runnable action, boolean expectedPass) { // constructor
            this.name = name; // assign name
            this.action = action; // assign action
            this.expectedPass = expectedPass; // assign expected pass flag
        }
    }

    // main method runs all tests and prints PASS/FAIL results
    public static void main(String[] args) { // main entry point, no JUnit, just simple runner
        List<TestCase> tests = new ArrayList<>(); // collect all tests into a list

        // Test 1: Basic put/get and eviction behavior (LeetCode style)
        tests.add(new TestCase("basic-1", () -> {
            LRUCache cache = new LRUCache(2); // capacity 2
            cache.put(1, 1); // put key1=1
            cache.put(2, 2); // put key2=2
            assertEqual(cache.get(1), 1); // get(1) should return 1 and make key=1 most recent
            cache.put(3, 3); // this should evict key=2 (least recently used)
            assertEqual(cache.get(2), -1); // key=2 was evicted
            cache.put(4, 4); // evict key=1 now that 1 was recently used and 3 is older
            assertEqual(cache.get(1), -1); // key=1 evicted
            assertEqual(cache.get(3), 3); // key=3 present
            assertEqual(cache.get(4), 4); // key=4 present
        }, true));

        // Test 2: Update existing key and access order changes
        tests.add(new TestCase("update-and-access-order", () -> {
            LRUCache cache = new LRUCache(2); // capacity 2
            cache.put(1, 1); // insert 1
            cache.put(2, 2); // insert 2
            cache.put(1, 10); // update 1's value and make it most recent
            assertEqual(cache.get(1), 10); // updated value should be returned
            cache.put(3, 3); // this should evict key=2 as it is least recently used now
            assertEqual(cache.get(2), -1); // 2 evicted
            assertEqual(cache.get(3), 3); // 3 present
            assertEqual(cache.get(1), 10); // 1 present with updated value
        }, true));

        // Test 3: Capacity one behaves correctly
        tests.add(new TestCase("capacity-one", () -> {
            LRUCache cache = new LRUCache(1); // capacity 1
            cache.put(1, 1); // insert
            cache.put(2, 2); // should evict 1
            assertEqual(cache.get(1), -1); // 1 evicted
            assertEqual(cache.get(2), 2); // 2 present
        }, true));

        // Test 4: Empty cache behavior (capacity 0 edge)
        tests.add(new TestCase("capacity-zero", () -> {
            LRUCache cache = new LRUCache(0); // capacity 0 (allowed but holds nothing)
            cache.put(1, 1); // attempt to put; should be immediately evicted
            assertEqual(cache.get(1), -1); // nothing should be stored
        }, true));

        // Test 5: Order of keys after accesses
        tests.add(new TestCase("keys-order-check", () -> {
            LRUCache cache = new LRUCache(3); // capacity 3
            cache.put(1, 1); // insert 1
            cache.put(2, 2); // insert 2
            cache.put(3, 3); // insert 3
            cache.get(2); // access 2 making it most recent
            List<Integer> keysMRU = cache.keysMostRecentFirst(); // get MRU->LRU order
            // expected MRU->LRU = [2,3,1]
            assertEqual(keysMRU, Arrays.asList(2, 3, 1));
        }, true));

        // Test 6: Large stress test - many puts with modest capacity to verify eviction and performance
        tests.add(new TestCase("large-put-eviction", () -> {
            final int capacity = 10_000; // capacity for this large test
            final int totalPuts = 100_000; // total number of put operations
            LRUCache cache = new LRUCache(capacity); // create cache
            // perform put operations for keys 0..totalPuts-1
            IntStream.range(0, totalPuts).forEach(i -> cache.put(i, i * 2)); // store value = key*2
            // after all puts, only last 'capacity' keys should remain: keys totalPuts-capacity .. totalPuts-1
            int cutoff = totalPuts - capacity; // keys less than cutoff must be evicted
            // verify eviction and presence using streams
            boolean allGood = IntStream.range(0, totalPuts).allMatch(k -> {
                int val = cache.get(k); // get for key k
                if (k < cutoff) { // expected evicted
                    return val == -1; // must be -1
                } else { // expected present
                    return val == k * 2; // must match stored value
                }
            });
            if (!allGood) throw new AssertionError("Large put/eviction test failed"); // throw to mark test failure
        }, true));

        // Test 7: Large mixed put/get operations to simulate realistic usage
        tests.add(new TestCase("large-mixed-ops", () -> {
            final int capacity = 5_000; // smaller capacity for mixed ops
            final int totalOps = 60_000; // total operations
            LRUCache cache = new LRUCache(capacity); // instantiate
            Random rnd = new Random(12345); // deterministic random for repeatability
            // perform operations: put or get randomly
            IntStream.range(0, totalOps).forEach(i -> {
                int k = rnd.nextInt(10_000); // random key in 0..9999
                if ((i & 1) == 0) { // even step -> put
                    cache.put(k, k + i); // store varying value
                } else { // odd step -> get
                    cache.get(k); // just access to influence order
                }
            });
            // Basic sanity: ensure cache size does not exceed capacity
            if (cache.currentSize() > capacity) throw new AssertionError("Cache exceeded capacity after mixed ops");
        }, true));

        // run all tests and collect results
        List<String> results = tests.stream() // stream test cases
                .map(tc -> { // map each test case to a string result
                    boolean passed = true; // assume pass
                    String errorMsg = null; // store any error message
                    long start = System.currentTimeMillis(); // start time for test
                    try {
                        tc.action.run(); // execute test action
                    } catch (Throwable t) { // catch any assertion or runtime error
                        passed = false; // mark failed
                        errorMsg = t.getClass().getSimpleName() + ":" + (t.getMessage() == null ? t.toString() : t.getMessage()); // capture message
                    }
                    long duration = System.currentTimeMillis() - start; // compute duration
                    String status = (passed == tc.expectedPass) ? "PASS" : "FAIL"; // determine status relative to expectation
                    // prepare readable summary line for this test
                    return String.format("%s: %s (expected=%s, actual=%s, timeMs=%d%s)",
                            tc.name,
                            status,
                            tc.expectedPass,
                            passed,
                            duration,
                            (errorMsg == null ? "" : ", err=" + errorMsg));
                })
                .collect(Collectors.toList()); // collect results

        // print each test result line by line
        results.forEach(System.out::println); // output results

        // print summary counts
        long passCount = results.stream().filter(line -> line.contains("PASS")).count(); // count passed tests
        System.out.println(String.format("Summary: Passed %d out of %d tests", passCount, tests.size())); // print summary
    } // end main

    // helper assertion utilities used in tests

    private static void assertEqual(int actual, int expected) { // compares ints and throws AssertionError on mismatch
        if (actual != expected) { // check inequality
            throw new AssertionError("Expected int " + expected + " but got " + actual); // throw with message
        }
    } // end assertEqual for int

    private static <T> void assertEqual(T actual, T expected) { // generic equality assertion for objects
        if (!Objects.equals(actual, expected)) { // use Objects.equals to handle null-safe comparison
            throw new AssertionError("Expected " + expected + " but got " + actual); // throw with message
        }
    } // end assertEqual generic
} // end class LRUCacheWithTests