package com.interview.notes.code.year.y2023.july23.test12;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonZipCodes {
    public static void main(String[] args) {
        // Creating 4 map objects
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        Map<String, Integer> m3 = new HashMap<>();
        Map<String, Integer> m4 = new HashMap<>();

        // Populating the maps with some example zip codes and associated populations
        m1.put("12345", 10000);
        m2.put("12345", 10000);
        m3.put("67890", 20000);
        m4.put("12345", 10000);
        m4.put("67890", 20000);

        // Find common keys (zip codes) by combining the key sets and retaining only the duplicates
        Set<String> commonZipCodes = m1.keySet();
        commonZipCodes.retainAll(m2.keySet());
        commonZipCodes.retainAll(m3.keySet());
        commonZipCodes.retainAll(m4.keySet());

        // Alternatively, using Java 8 Streams
        commonZipCodes = m1.keySet().stream()
                .filter(m2.keySet()::contains)
                .filter(m3.keySet()::contains)
                .filter(m4.keySet()::contains)
                .collect(Collectors.toSet());

        // Printing the common zip codes
        System.out.println("Common Zip Codes: " + commonZipCodes);
    }
}
