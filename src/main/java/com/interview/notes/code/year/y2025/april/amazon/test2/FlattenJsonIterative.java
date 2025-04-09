package com.interview.notes.code.year.y2025.april.amazon.test2;

import java.util.*;

public class FlattenJsonIterative {

    /**
     * Flattens a nested Map (representing a JSON) using dot notation for nested keys,
     * using an iterative (non-recursive) approach with a stack.
     *
     * @param input The input map to flatten.
     * @return A flattened map with dot notation keys.
     */
    public static Map<String, String> flattenMapIterative(Map<String, Object> input) {
        Map<String, String> result = new HashMap<>();
        Deque<Map.Entry<String, Object>> stack = new ArrayDeque<>();

        // Initialize the stack with the top-level entries
        input.forEach((key, value) -> stack.push(new AbstractMap.SimpleEntry<>(key, value)));

        // Process entries until the stack is empty
        while (!stack.isEmpty()) {
            Map.Entry<String, Object> current = stack.pop();
            String currentKey = current.getKey();
            Object currentValue = current.getValue();

            // If the value is a map, push its entries onto the stack with updated key prefixes
            if (currentValue instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) currentValue;
                nestedMap.forEach((key, value) -> {
                    String newKey = currentKey + "." + key;
                    stack.push(new AbstractMap.SimpleEntry<>(newKey, value));
                });
            } else {
                // Otherwise, add the current key-value pair to the result map
                result.put(currentKey, currentValue == null ? "null" : currentValue.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test case: Example provided
        Map<String, Object> input = new HashMap<>();
        input.put("firstName", "Bobby");
        input.put("lastName", "Shaftoe");
        Map<String, Object> address = new HashMap<>();
        address.put("street", "123 manila road");
        address.put("suburb", "intramuros");
        input.put("address", address);

        Map<String, String> flattened = flattenMapIterative(input);
        System.out.println(flattened);
        // Expected Output:
        // {firstName=Bobby, lastName=Shaftoe, address.street=123 manila road, address.suburb=intramuros}
    }
}
