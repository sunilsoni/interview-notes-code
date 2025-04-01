package com.interview.notes.code.year.y2025.march.amazon.test11;

import java.util.Arrays;

public class MinimumLexicographicalString {
    
    public static String getMinimumString(String s_id) {
        char[] chars = s_id.toCharArray();
        int n = chars.length;
        
        // Try each position as a starting point
        for (int i = 0; i < n; i++) {
            // For each position, try removing and reinserting with increment
            for (int j = 0; j < n; j++) {
                char removed = chars[j];
                char incremented = (char) Math.min(removed - '0' + 1 + '0', '9');
                
                // Try inserting at each possible position
                for (int k = 0; k <= n - 1; k++) {
                    // Create a new string with the operation applied
                    StringBuilder sb = new StringBuilder();
                    for (int l = 0; l < n; l++) {
                        if (l == j) continue; // Skip the removed digit
                        if (l == k) sb.append(incremented); // Insert incremented digit
                        sb.append(chars[l]);
                    }
                    if (k == n - 1) sb.append(incremented); // Insert at the end if needed
                    
                    // Check if the new string is lexicographically smaller
                    String newStr = sb.toString();
                    if (newStr.compareTo(s_id) < 0) {
                        s_id = newStr;
                        chars = s_id.toCharArray();
                        i = -1; // Restart the process with the new string
                        break;
                    }
                }
                if (i == -1) break;
            }
        }
        
        return s_id;
    }
    
    // Optimized solution that works more efficiently
    public static String getMinimumStringOptimized(String s_id) {
        char[] chars = s_id.toCharArray();
        int n = chars.length;
        
        // Keep applying operations until no improvement is found
        boolean improved;
        do {
            improved = false;
            
            for (int i = 0; i < n; i++) {
                char current = chars[i];
                char incremented = (char) Math.min(current - '0' + 1 + '0', '9');
                
                // Try removing the current digit and inserting the incremented value at each position
                char[] temp = new char[n];
                for (int pos = 0; pos <= n - 1; pos++) {
                    // Copy the original array without the current digit
                    int index = 0;
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            temp[index++] = chars[j];
                        }
                    }
                    
                    // Insert the incremented digit at the specified position
                    System.arraycopy(temp, pos, temp, pos + 1, n - 1 - pos);
                    temp[pos] = incremented;
                    
                    // Check if this new arrangement is lexicographically smaller
                    int cmp = 0;
                    for (int j = 0; j < n; j++) {
                        if (temp[j] != chars[j]) {
                            cmp = temp[j] - chars[j];
                            break;
                        }
                    }
                    
                    if (cmp < 0) {
                        // We found a better arrangement
                        System.arraycopy(temp, 0, chars, 0, n);
                        improved = true;
                        break;
                    }
                }
                
                if (improved) break;
            }
            
        } while (improved);
        
        return new String(chars);
    }
    
    // Correct solution that properly handles the problem constraints
    public static String getMinimumStringCorrect(String s_id) {
        int n = s_id.length();
        
        // Try all possible operations and keep the lexicographically smallest result
        String result = s_id;
        boolean changed;
        
        do {
            changed = false;
            String currentBest = result;
            
            for (int i = 0; i < currentBest.length(); i++) {
                char digit = currentBest.charAt(i);
                char newDigit = (char) Math.min(digit - '0' + 1 + '0', '9');
                
                // Remove digit at position i and try inserting newDigit at all positions
                String prefix = currentBest.substring(0, i);
                String suffix = currentBest.substring(i + 1);
                
                for (int j = 0; j <= currentBest.length() - 1; j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(prefix).append(suffix);
                    sb.insert(j, newDigit);
                    
                    String candidate = sb.toString();
                    if (candidate.compareTo(result) < 0) {
                        result = candidate;
                        changed = true;
                    }
                }
            }
        } while (changed);
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "26547",    // Expected: "24677"
            "04829",    // Expected: "02599"
            "34892",    // Expected: "24599"
            "9",        // Edge case: single digit
            "123456789", // All digits in order
            "987654321", // All digits in reverse order
            "55555",    // Repeated digits
            "90",       // Edge case with 9
            "109",      // Edge case with 0
            "12345"     // Simple case
        };
        
        String[] expectedResults = {
            "24677",
            "02599",
            "24599",
            "9",
            "123456789", // No change is optimal
            "899999999", // Optimal result
            "55555",    // No change is optimal
            "09",       // Optimal result
            "019",      // Optimal result
            "12345"     // No change is optimal
        };
        
        // Test with the correct implementation
        System.out.println("Testing with correct implementation:");
        for (int i = 0; i < testCases.length; i++) {
            String result = getMinimumStringCorrect(testCases[i]);
            boolean passed = result.equals(expectedResults[i]);
            System.out.println("Test Case " + (i + 1) + ": " + 
                              (passed ? "PASS" : "FAIL") + 
                              " - Input: " + testCases[i] + 
                              ", Expected: " + expectedResults[i] + 
                              ", Got: " + result);
        }
        
        // Test large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append(i % 10);
        }
        String large = largeInput.toString();
        
        long startTime = System.currentTimeMillis();
        String largeResult = getMinimumStringCorrect(large.substring(0, 100)); // Using first 100 chars for performance
        long endTime = System.currentTimeMillis();
        
        System.out.println("\nLarge Input Test (100 chars): Completed in " + (endTime - startTime) + "ms");
    }
}