package com.interview.notes.code.year.y2024.nov24.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DataStoreTest {
    public static void main(String[] args) {
        runTestCases();
        runLargeDataTest();
    }

    private static void runTestCases() {
        System.out.println("Running test cases...");

        // Test Case 1
        boolean passed = testCase1();
        System.out.println("Test Case 1: " + (passed ? "PASSED" : "FAILED"));

        // Test Case 2
        passed = testCase2();
        System.out.println("Test Case 2: " + (passed ? "PASSED" : "FAILED"));
    }

    private static boolean testCase1() {
        DataStore ds = new DataStore();
        ds.set("user1", "shirt");
        ds.set("user2", "hat");

        if (!ds.get("user1").equals("shirt")) return false;

        ds.begin();
        if (!ds.get("user1").equals("shirt")) return false;

        ds.set("user1", "pants");
        if (!ds.get("user1").equals("pants")) return false;

        ds.delete("user2");
        if (ds.get("user2") != null) return false;

        ds.rollback();
        return ds.get("user1").equals("shirt") && ds.get("user2").equals("hat");
    }

    private static boolean testCase2() {
        DataStore ds = new DataStore();
        ds.set("user1", "shirt");
        ds.set("user2", "hat");

        ds.begin();
        ds.set("user1", "pants");
        ds.delete("user2");
        ds.commit();

        return ds.get("user1").equals("pants") && ds.get("user2") == null;
    }

    private static void runLargeDataTest() {
        System.out.println("\nRunning large data test...");
        DataStore ds = new DataStore();

        // Insert 100,000 items
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            ds.set("key" + i, "value" + i);
        }

        ds.begin();
        // Modify 50,000 items in transaction
        for (int i = 0; i < 50000; i++) {
            ds.set("key" + i, "newvalue" + i);
        }
        ds.commit();

        long endTime = System.currentTimeMillis();
        System.out.println("Large data test completed in " + (endTime - startTime) + "ms");

        // Verify some random values
        boolean largeDataTestPassed =
                ds.get("key0").equals("newvalue0") &&
                        ds.get("key49999").equals("newvalue49999") &&
                        ds.get("key50000").equals("value50000");

        System.out.println("Large Data Test: " +
                (largeDataTestPassed ? "PASSED" : "FAILED"));
    }

    static class DataStore {
        private Map<String, String> store;
        private Stack<Map<String, String>> transactions;

        public DataStore() {
            store = new HashMap<>();
            transactions = new Stack<>();
        }

        public void set(String key, String value) {
            if (!transactions.isEmpty()) {
                transactions.peek().put(key, value);
            } else {
                store.put(key, value);
            }
        }

        public String get(String key) {
            if (!transactions.isEmpty()) {
                String value = transactions.peek().get(key);
                return value != null ? value : store.get(key);
            }
            return store.get(key);
        }

        public void delete(String key) {
            if (!transactions.isEmpty()) {
                transactions.peek().put(key, null);
            } else {
                store.remove(key);
            }
        }

        public void begin() {
            transactions.push(new HashMap<>());
        }

        public void commit() {
            if (transactions.isEmpty()) {
                throw new IllegalStateException("No active transaction");
            }
            Map<String, String> currentTransaction = transactions.pop();
            if (transactions.isEmpty()) {
                for (Map.Entry<String, String> entry : currentTransaction.entrySet()) {
                    if (entry.getValue() == null) {
                        store.remove(entry.getKey());
                    } else {
                        store.put(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                transactions.peek().putAll(currentTransaction);
            }
        }

        public void rollback() {
            if (transactions.isEmpty()) {
                throw new IllegalStateException("No active transaction");
            }
            transactions.pop();
        }
    }
}