package com.interview.notes.code.year.y2025.august.common.test4;

import java.util.ArrayList;
import java.util.List;

// Custom FilterList class (no Stream API)
class FilterList {
    private final List<Integer> data;   // store original numbers

    // Constructor
    public FilterList(List<Integer> data) {
        this.data = data;
    }

    // Custom filter method
    public List<Integer> filterEvenNumbers() {
        List<Integer> result = new ArrayList<>();
        for (Integer n : data) {
            if (n % 2 == 0) {  // check even
                result.add(n);
            }
        }
        return result;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Input list
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);

        // Use our custom FilterList class
        FilterList fl = new FilterList(data);
        List<Integer> filterList = fl.filterEvenNumbers();

        // Print result
        System.out.println(filterList);  // Output: [2, 4, 6]
    }
}