package com.interview.notes.code.year.y2026.feb.common.test1;// TestRunner class for local verification of the specific failing case reported

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TestRunner {
    public static void main(String[] args) {
        // Reproducing the specific "not working" input case
        List<String> input = Arrays.asList(
            "Jasmine", "Rose", "Tulip", "Carnation", "Orchid", "Irises", 
            "Sunflower", "Gardenias", "Daisies", "Lillies", "Lotus", 
            "Daffodils", "Peonies"
        );
        
        System.out.println("Input Count: " + input.size()); // Should be 13
        
        // Logic check
        List<String> filtered = input.stream()
                .filter(s -> s.length() % 3 == 0 || s.length() % 4 == 0)
                .collect(Collectors.toList());
                
        StringJoiner sj = new StringJoiner("-");
        filtered.forEach(sj::add);
        
        String expected = "Rose-Carnation-Orchid-Irises-Sunflower-Gardenias-Daffodils";
        String actual = sj.toString();
        
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println("Status:   " + (expected.equals(actual) ? "PASS" : "FAIL"));
    }
}