package com.interview.notes.code.year.y2025.july.common.test1;

import java.util.Map;
import java.util.stream.Collectors;

public class PermutationChecker {
    
    public static boolean isPermutationPossible(String source, String target) {
        if (source == null || target == null) {
            return false;
        }
        
        // Convert strings to character frequency maps
        Map<Character, Integer> sourceMap = source.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
            
        // Check if target characters exist in source with correct frequencies
        for (char c : target.toCharArray()) {
            int count = sourceMap.getOrDefault(c, 0);
            if (count == 0) {
                return false;
            }
            sourceMap.put(c, count - 1);
        }
        
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        testCase("entertainment", "mat", true);
        testCase("entertainment", "maat", false);
        testCase("entertainment", "pat", false);
        testCase("entertainment", "tent", true);
        testCase("entertainment", "", true);
        testCase("", "mat", false);
        testCase(null, "mat", false);
        testCase("entertainment", null, false);
        
        // Large data test
        StringBuilder largeSource = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeSource.append("entertainment");
        }
        testCase(largeSource.toString(), "entertainment", true);
    }
    
    private static void testCase(String source, String target, boolean expected) {
        boolean result = isPermutationPossible(source, target);
        System.out.printf("Source: %s, Target: %s, Expected: %b, Result: %b, %s%n",
            source != null ? source.substring(0, Math.min(20, source.length())) + "..." : "null",
            target,
            expected,
            result,
            expected == result ? "PASS" : "FAIL"
        );
    }
}
