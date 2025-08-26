package com.interview.notes.code.year.y2025.august.common.test5;

import java.util.*;
import java.util.function.Predicate;

// Custom FilterList class
class FilterList<T> {
    private final List<T> data;       // Original data list
    private final List<T> filtered;   // Stores filtered elements

    // Constructor to initialize with given data
    public FilterList(List<T> data) {
        this.data = data;
        this.filtered = new ArrayList<>(data); // start with all items
    }

    // Custom filter method
    public FilterList<T> filter(Predicate<T> condition) {
        List<T> temp = new ArrayList<>();
        for (T element : filtered) {
            if (condition.test(element)) {
                temp.add(element);  // keep only those matching condition
            }
        }
        filtered.clear();
        filtered.addAll(temp);
        return this;  // allow chaining
    }

    // Convert result to List
    public List<T> toList() {
        return new ArrayList<>(filtered);
    }
}

public class Main {
    public static void main(String[] args) {
        // Input list
        List<Integer> data = List.of(1, 2, 3, 4, 5, 6);

        // Use custom FilterList class
        List<Integer> filterList = new FilterList<>(data)
                .filter(n -> n % 2 == 0)   // condition: even numbers
                .toList();

        // Print result
        System.out.println(filterList); // Output: [2, 4, 6]
    }
}