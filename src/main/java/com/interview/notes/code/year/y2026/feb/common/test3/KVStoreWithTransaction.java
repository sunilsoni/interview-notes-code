package com.interview.notes.code.year.y2026.feb.common.test3;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class KVStoreWithTransaction {

    // Permanent storage
    private final Map<String, String> mainStore = new ConcurrentHashMap<>();

    // Transactional storage (Staging area)
    // In a real server, these would be ThreadLocal to handle multiple users. 
    // For this simple interview problem, we assume one active transaction sequence.
    private Map<String, String> tempStore = null;
    private Set<String> deletedKeys = null;
    private boolean isTransactionActive = false;

    // --- BASIC METHODS ---

    public static void main(String[] args) {
        var db = new KVStoreWithTransaction();
        System.out.println("--- Starting Transaction Tests ---");

        // 1. Setup initial state
        db.set("key1", "value1");
        check("Initial Setup", "value1".equals(db.get("key1")));

        // --- Test 1: Begin -> Set -> Commit ---
        db.begin();
        db.set("key1", "value2"); // Update existing
        db.set("key2", "value3"); // Add new
        check("Read within Tx (Updated)", "value2".equals(db.get("key1")));
        check("Read within Tx (New)", "value3".equals(db.get("key2")));

        db.commit(); // Persist changes

        check("Read after Commit (Updated)", "value2".equals(db.get("key1")));
        check("Read after Commit (New)", "value3".equals(db.get("key2")));

        // --- Test 2: Begin -> Delete -> Rollback ---
        db.begin();
        db.delete("key1"); // Delete inside Tx
        check("Read deleted within Tx", db.get("key1") == null); // Should be null

        db.rollback(); // Undo delete

        check("Read after Rollback", "value2".equals(db.get("key1"))); // Should be back

        // --- Test 3: Begin -> Set -> Rollback ---
        db.begin();
        db.set("key3", "temp");
        db.rollback();
        check("Rollback new key", db.get("key3") == null);

        System.out.println("--- All Tests Completed ---");
    }

    private static void check(String name, boolean pass) {
        System.out.println((pass ? "[PASS] " : "[FAIL] ") + name);
    }

    public void set(String key, String value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        if (isTransactionActive) {
            // If in transaction, write to temp store and clear any pending delete marker
            tempStore.put(key, value);
            deletedKeys.remove(key);
        } else {
            // Direct write if no transaction
            mainStore.put(key, value);
        }
    }

    // --- TRANSACTION METHODS ---

    public String get(String key) {
        if (isTransactionActive) {
            // 1. Check if it was deleted in this transaction
            if (deletedKeys.contains(key)) {
                return null;
            }
            // 2. Check if it was updated/added in this transaction
            if (tempStore.containsKey(key)) {
                return tempStore.get(key);
            }
            // 3. Fallback to main store
            return mainStore.get(key);
        }
        // No transaction: read directly from main
        return mainStore.get(key);
    }

    public void delete(String key) {
        if (isTransactionActive) {
            // In transaction: Mark as deleted and remove from temp store if present
            tempStore.remove(key);
            deletedKeys.add(key);
        } else {
            // Direct delete
            mainStore.remove(key);
        }
    }

    public void begin() {
        if (isTransactionActive) {
            throw new IllegalStateException("Transaction already active");
        }
        // Initialize staging areas
        tempStore = new ConcurrentHashMap<>();
        deletedKeys = new HashSet<>();
        isTransactionActive = true;
    }

    // --- TEST RUNNER ---

    public void commit() {
        if (!isTransactionActive) {
            throw new IllegalStateException("No transaction to commit");
        }

        // 1. Apply deletes to main store
        for (String key : deletedKeys) {
            mainStore.remove(key);
        }

        // 2. Apply updates/adds to main store
        mainStore.putAll(tempStore);

        // 3. Cleanup
        tempStore = null;
        deletedKeys = null;
        isTransactionActive = false;
    }

    public void rollback() {
        if (!isTransactionActive) {
            throw new IllegalStateException("No transaction to rollback");
        }
        // Simply discard the staging areas
        tempStore = null;
        deletedKeys = null;
        isTransactionActive = false;
    }
}