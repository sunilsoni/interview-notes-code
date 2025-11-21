package com.interview.notes.code.year.y2024.oct24.test12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomDataStructure class implements a data structure that supports
 * insertion, deletion, and search of strings in O(1) time.
 */
public class CustomDataStructure {

    // Map to store element and its index in the list
    private final Map<String, Integer> elementIndexMap;
    // List to store elements
    private final List<String> elements;

    /**
     * Constructor initializes the data structures.
     */
    public CustomDataStructure() {
        elementIndexMap = new HashMap<>();
        elements = new ArrayList<>();
    }

    /**
     * Main method for testing the CustomDataStructure.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        CustomDataStructure ds = new CustomDataStructure();

        // Basic Test Cases
        System.out.println("Starting basic test cases...");

        // Test insertion of elements
        ds.insert("a");
        ds.insert("abc");
        ds.insert("b");
        ds.insert("a"); // Duplicate insertion should be ignored

        // Expected elements: ["a", "abc", "b"]
        System.out.println("Elements after insertion: " + ds.getElements());

        // Test search operation
        assert ds.search("a") : "Test search 'a' failed";
        assert ds.search("abc") : "Test search 'abc' failed";
        assert !ds.search("c") : "Test search 'c' failed";

        // Test delete operation
        ds.delete("a");
        assert !ds.search("a") : "Test delete 'a' failed";
        ds.delete("d"); // Deleting non-existent element should do nothing

        // Expected elements after deletion: ["b", "abc"]
        System.out.println("Elements after deletion: " + ds.getElements());

        System.out.println("All basic test cases passed!\n");

        // Large Data Test Cases
        System.out.println("Starting large data test cases...");
        int largeNumber = 1000000;

        // Insert a large number of elements
        for (int i = 0; i < largeNumber; i++) {
            ds.insert("element" + i);
        }

        // Verify the size after insertion
        assert ds.elements.size() == largeNumber + 2 : "Large data insertion test failed";

        // Delete all large data elements
        for (int i = 0; i < largeNumber; i++) {
            ds.delete("element" + i);
        }

        // Verify the size after deletion
        assert ds.elements.size() == 2 : "Large data deletion test failed";

        System.out.println("Large data test cases passed!");
    }

    /**
     * Inserts an element into the data structure if not already present.
     *
     * @param element The string element to insert.
     */
    public void insert(String element) {
        if (!elementIndexMap.containsKey(element)) {
            elements.add(element);
            elementIndexMap.put(element, elements.size() - 1);
        }
    }

    /**
     * Deletes an element from the data structure if present.
     *
     * @param element The string element to delete.
     */
    public void delete(String element) {
        Integer index = elementIndexMap.get(element);
        if (index != null) {
            String lastElement = elements.get(elements.size() - 1);
            // Move the last element to the place of the element to delete
            elements.set(index, lastElement);
            elementIndexMap.put(lastElement, index);
            // Remove the last element
            elements.remove(elements.size() - 1);
            elementIndexMap.remove(element);
        }
    }

    /**
     * Searches for an element in the data structure.
     *
     * @param element The string element to search for.
     * @return True if the element is present, false otherwise.
     */
    public boolean search(String element) {
        return elementIndexMap.containsKey(element);
    }

    /**
     * Returns a list of all elements in the data structure.
     *
     * @return A list of strings.
     */
    public List<String> getElements() {
        return new ArrayList<>(elements);
    }
}
