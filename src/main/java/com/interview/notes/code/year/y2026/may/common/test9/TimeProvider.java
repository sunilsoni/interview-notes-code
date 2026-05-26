package com.interview.notes.code.year.y2026.may.common.test9;

import java.util.Comparator; // Required for comparing objects in streams
import java.util.HashMap; // Required for our core hash map data structure
import java.util.Map; // Required for the Map interface

interface TimeProvider { // Interface provided to mock system time for predictable testing
    long getMillis(); // Returns the current system time in milliseconds
} // End of TimeProvider interface

class TtlCache { // Main cache class implementing the TTL logic
    private final int maxSize; // Defines the absolute maximum number of elements the cache can hold
    private final TimeProvider time; // Stores our dependency-injected time provider
    private final Map<String, Item> cache = new HashMap<>(); // Standard Map to hold our cache keys and wrapped items

    TtlCache(TimeProvider time, int maxSize) { // Constructor to initialize cache constraints
        this.time = time; // Assign the injected time provider to our instance variable
        this.maxSize = maxSize; // Assign the requested max capacity
    } // End of constructor

    private void clean() { // Helper method to purge all expired elements from the map
        long now = time.getMillis(); // Fetch the current time exactly once per cleanup for consistency
        cache.entrySet().removeIf(e -> e.getValue().exp() <= now); // Safely remove all map entries where expiration time has passed
    } // End of clean method

    public void put(String k, String v, long ttl) { // Inserts or updates an element with a specified lifetime
        clean(); // First, clear out expired items to ensure we have an accurate map size
        long exp = time.getMillis() + ttl; // Calculate the absolute future timestamp when this specific item will die
        if (cache.containsKey(k) || cache.size() < maxSize) { // Check if we are updating an existing key OR if we have free slots
            cache.put(k, new Item(v, exp)); // Either insert fresh or overwrite the existing value and TTL
            return; // Terminate early since the insertion is fully complete
        } // End of basic insertion logic block

        var min = cache.entrySet().stream().min(Comparator.comparingLong(e -> e.getValue().exp())).get(); // If full, use Streams to locate the single existing entry that will expire soonest
        if (exp > min.getValue().exp()) { // Check if our NEW item will live longer than the soonest-expiring EXISTING item
            cache.remove(min.getKey()); // Evict the soonest-expiring item to free up exactly one slot
            cache.put(k, new Item(v, exp)); // Safely insert our newly provided item into the newly available space
        } // End of conditional eviction logic (if exp is lower, the item is simply ignored as per requirements)
    } // End of put method

    public String get(String k) { // Retrieves an element's value if it exists and is alive
        clean(); // Clear expired items first to guarantee we never accidentally return a "dead" value
        var item = cache.get(k); // Attempt to fetch the wrapped Item object from the HashMap
        return item == null ? null : item.val(); // Return null if missing, otherwise unwrap and return the actual string value
    } // End of get method

    public int size() { // Returns the current number of valid, unexpired elements
        clean(); // Clear expired items first so they aren't incorrectly counted in the total
        return cache.size(); // Return the exact current size of the underlying HashMap
    } // End of size method

    private record Item(String val, long exp) {} // Java 21 Record: securely and concisely holds a value and its exact expiration timestamp
} // End of TtlCache class