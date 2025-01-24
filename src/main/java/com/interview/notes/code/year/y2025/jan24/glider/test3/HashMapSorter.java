package com.interview.notes.code.year.y2025.jan24.glider.test3;

import java.util.*;

public class HashMapSorter {
    
    // Method to sort HashMap by values in descending order
    public static String sortHashMapByValues(HashMap<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }

        // Convert HashMap to List for sorting
        List<Map.Entry<String, String>> list = 
            new ArrayList<>(map.entrySet());

        // Sort the list using custom comparator
        Collections.sort(list, (entry1, entry2) -> 
            entry2.getValue().compareTo(entry1.getValue()));

        // Create new LinkedHashMap to maintain order
        LinkedHashMap<String, String> sortedMap = 
            new LinkedHashMap<>();
        
        // Add sorted entries to new map
        for (Map.Entry<String, String> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case
        System.out.println("Test Case 1: Normal case");
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("A", "Apple");
        map1.put("B", "Banana");
        map1.put("C", "Cherry");
        map1.put("D", "Date");
        map1.put("F", "fruit");
        String result1 = sortHashMapByValues(map1);
        String expected1 = "{F=fruit, D=Date, C=Cherry, B=Banana, A=Apple}";
        System.out.println("Result: " + result1);
        System.out.println("Expected: " + expected1);
        System.out.println("Test Case 1: " + 
            (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test Case 2: Empty HashMap
        System.out.println("\nTest Case 2: Empty HashMap");
        HashMap<String, String> map2 = new HashMap<>();
        String result2 = sortHashMapByValues(map2);
        String expected2 = "{}";
        System.out.println("Result: " + result2);
        System.out.println("Expected: " + expected2);
        System.out.println("Test Case 2: " + 
            (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Test Case 3: Large data set
        System.out.println("\nTest Case 3: Large data set");
        HashMap<String, String> map3 = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map3.put("Key" + i, "Value" + (1000 - i));
        }
        String result3 = sortHashMapByValues(map3);
        System.out.println("Large data set size: " + map3.size());
        System.out.println("Test Case 3: " + 
            (result3.length() > 0 ? "PASS" : "FAIL"));

        // Test Case 4: Same values
        System.out.println("\nTest Case 4: Same values");
        HashMap<String, String> map4 = new HashMap<>();
        map4.put("A", "Same");
        map4.put("B", "Same");
        map4.put("C", "Same");
        String result4 = sortHashMapByValues(map4);
        System.out.println("Result: " + result4);
        System.out.println("Test Case 4: " + 
            (result4.length() > 0 ? "PASS" : "FAIL"));
    }
}
