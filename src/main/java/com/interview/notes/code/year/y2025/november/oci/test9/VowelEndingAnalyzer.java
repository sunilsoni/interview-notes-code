package com.interview.notes.code.year.y2025.november.oci.test9;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class VowelEndingAnalyzer {
    
    public static int vowelEnd(int N, List<String> W) {
        var vowels = Set.of('A','E','I','O','U','a','e','i','o','u');
        return (int)W.stream()
            .filter(s -> !s.isEmpty() && vowels.contains(s.charAt(s.length()-1)))
            .count();
    }
    
    public static void main(String[] args) {
        var testCases = List.of(
            new TestData(3, List.of("Flow","Echo","Cats"), 1),
            new TestData(1, List.of("Flow"), 0),
            new TestData(4, List.of("Area","IDEA","Fly","floor"), 2),
            new TestData(0, List.of(), 0),
            new TestData(5, List.of("a","E","xyz","","aeiou"), 3),
            new TestData(1000, Stream.generate(() -> "testA").limit(1000).toList(), 1000),
            new TestData(1000, Stream.generate(() -> "testB").limit(1000).toList(), 0),
            new TestData(3, List.of("","",""), 0),
            new TestData(2, List.of("I","O"), 2)
        );
        
        int passed = 0;
        for(int i = 0; i < testCases.size(); i++) {
            var test = testCases.get(i);
            int result = vowelEnd(test.n, test.words);
            boolean pass = result == test.expected;
            System.out.println("Test " + (i+1) + ": " + (pass ? "PASS" : "FAIL") + 
                              " (Expected: " + test.expected + ", Got: " + result + ")");
            if(pass) passed++;
        }
        System.out.println("\nTotal: " + passed + "/" + testCases.size() + " passed");
    }
    
    record TestData(int n, List<String> words, int expected) {}
}