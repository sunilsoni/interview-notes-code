package com.interview.notes.code.months.nov24.test9;

public class ArrayRearrangement {
    
    // Main method to test the solution
    public static void main(String[] args) {
        // Test cases
        runTests();
    }
    
    // Solution method to rearrange array
    public static int[] rearrangeArray(int[] arr) {
        if (arr == null || arr.length <= 2) return arr;
        
        int n = arr.length;
        int[] result = new int[n];
        
        // Count frequency of each number
        java.util.Map<Integer, Integer> freqMap = new java.util.HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Create a max heap based on frequency
        java.util.PriorityQueue<java.util.Map.Entry<Integer, Integer>> pq = 
            new java.util.PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(freqMap.entrySet());
        
        int index = 0;
        
        // Process elements with highest frequency first
        while (!pq.isEmpty()) {
            java.util.Map.Entry<Integer, Integer> current = pq.poll();
            int num = current.getKey();
            int freq = current.getValue();
            
            // Place numbers maintaining the constraint
            while (freq > 0 && index < n) {
                result[index] = num;
                index++;
                if (index >= 2 && result[index-1] == result[index-2] && result[index-1] == num) {
                    // If constraint violated, try next number
                    index--;
                    break;
                }
                freq--;
            }
            
            if (freq > 0) {
                current.setValue(freq);
                pq.offer(current);
            }
        }
        
        return result;
    }
    
    // Test method
    private static void runTests() {
        // Test case 1: Basic test
        testCase(
            new int[]{1,1,2,2,2,3,4,4,4,4,5},
            new int[]{1,1,2,2,3,4,4,5,2,4,4},
            "Basic test case"
        );
        
        // Test case 2: Empty array
        testCase(
            new int[]{},
            new int[]{},
            "Empty array"
        );
        
        // Test case 3: Single element
        testCase(
            new int[]{1},
            new int[]{1},
            "Single element"
        );
        
        // Test case 4: All same elements
        testCase(
            new int[]{1,1,1,1},
            new int[]{1,1,1,1},
            "All same elements"
        );
        
        // Test case 5: Large input
        int[] largeInput = new int[10000];
        java.util.Arrays.fill(largeInput, 1);
        testCase(
            largeInput,
            largeInput,
            "Large input with same elements"
        );
    }
    
    // Helper method to run individual test cases
    private static void testCase(int[] input, int[] expected, String testName) {
        int[] result = rearrangeArray(input.clone());
        boolean pass = validateResult(result);
        
        System.out.println("Test: " + testName);
        System.out.println("Input: " + java.util.Arrays.toString(input));
        System.out.println("Result: " + java.util.Arrays.toString(result));
        System.out.println("Status: " + (pass ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    // Validate if result meets the requirement
    private static boolean validateResult(int[] arr) {
        if (arr == null || arr.length <= 2) return true;
        
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == arr[i-1] && arr[i] == arr[i-2]) {
                return false;
            }
        }
        return true;
    }
}