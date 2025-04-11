package com.interview.notes.code.year.y2025.april.visa.test2;

import java.util.Arrays;

public class ConsecutiveColorsSolution {
    
    /**
     * This method applies each query on the array (naively) 
     * and returns the number of consecutive pairs for each query.
     *
     * @param length  The size of the line (0 to length-1)
     * @param queries Each query is [start, end, color]
     * @return An array of size queries.length, where each element 
     *         is the number of consecutive pairs of the same color after that query
     */
    public static int[] solution(int length, int[][] queries) {
        // If length is 0 or queries is null, return empty array
        if (length <= 0 || queries == null) {
            return new int[0];
        }
        
        // Initialize all positions to color 0
        int[] colors = new int[length];
        Arrays.fill(colors, 0);
        
        // Result array for storing consecutive pairs after each query
        int[] result = new int[queries.length];
        
        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end   = queries[i][1];
            int color = queries[i][2];
            
            // Paint the segment [start..end]
            for (int pos = start; pos <= end; pos++) {
                colors[pos] = color;
            }
            
            // Count consecutive pairs
            int consecutivePairs = 0;
            for (int pos = 0; pos < length - 1; pos++) {
                if (colors[pos] == colors[pos + 1]) {
                    consecutivePairs++;
                }
            }
            
            // Store result
            result[i] = consecutivePairs;
        }
        
        return result;
    }
    
    /**
     * Simple main method to test the solution (no JUnit).
     */
    public static void main(String[] args) {
        // We will create multiple test cases here
        // and simply print "PASS" or "FAIL" for each.
        
        // Test case 1 (from the example if available)
        int length1 = 7;
        int[][] queries1 = {
            {1, 2, 1}, // color [1..2] with 1
            {2, 3, 2}, // color [2..3] with 2
            {2, 5, 2}, // color [2..5] with 2
            {0, 6, 3}  // color [0..6] with 3
        };
        // The expected output from the example: [1, 1, 3, 6]
        int[] expected1 = {1, 1, 3, 6};
        
        int[] result1 = solution(length1, queries1);
        System.out.println("Test 1 result    : " + Arrays.toString(result1));
        System.out.println("Test 1 expected  : " + Arrays.toString(expected1));
        System.out.println("Test 1 " + (Arrays.equals(result1, expected1) ? "PASS" : "FAIL"));
        System.out.println();
        
        // Test case 2: Very small array with one query
        int length2 = 3;
        int[][] queries2 = {
            {0, 1, 9} // color [0..1] with color 9
        };
        // After coloring, array = [9, 9, 0]
        // Consecutive pairs = (9->9) = 1
        int[] expected2 = {1};
        
        int[] result2 = solution(length2, queries2);
        System.out.println("Test 2 result    : " + Arrays.toString(result2));
        System.out.println("Test 2 expected  : " + Arrays.toString(expected2));
        System.out.println("Test 2 " + (Arrays.equals(result2, expected2) ? "PASS" : "FAIL"));
        System.out.println();
        
        // Test case 3: Edge scenario (all same color from start, multiple queries)
        int length3 = 5;
        int[][] queries3 = {
            {0, 4, 1}, // color entire array with 1
            {1, 3, 1}, // color [1..3] with 1 again (no real change)
            {2, 2, 2}  // color only index 2 with 2
        };
        // Step by step:
        //  1) [1,1,1,1,1] => consecutive pairs = 4
        //  2) no change => consecutive pairs = 4
        //  3) [1,1,2,1,1] => consecutive pairs = 
        //     (1->1)=1, (1->2)=0, (2->1)=0, (1->1)=1 => total=2
        int[] expected3 = {4, 4, 2};
        
        int[] result3 = solution(length3, queries3);
        System.out.println("Test 3 result    : " + Arrays.toString(result3));
        System.out.println("Test 3 expected  : " + Arrays.toString(expected3));
        System.out.println("Test 3 " + (Arrays.equals(result3, expected3) ? "PASS" : "FAIL"));
        System.out.println();

        // Test case 4: Large data scenario (demo only, not truly large in code)
        // Example: length4 = 10, queries4 with 3 queries
        // Just ensure it runs quickly, not an actual large scale test
        int length4 = 10;
        int[][] queries4 = {
            {0, 9, 7},  // fill entire array with 7 => 9 consecutive pairs
            {3, 5, 7},  // fill [3..5] with 7 => no change
            {2, 8, 1}   // fill [2..8] with 1
        };
        // Step by step:
        //  1) [7,7,7,7,7,7,7,7,7,7], consecutive pairs = 9
        //  2) no change => consecutive pairs = 9
        //  3) [7,7,1,1,1,1,1,1,1,7]
        //     consecutive pairs: 
        //       index 0->1 = same(7->7)=1
        //       index 1->2 = diff(7->1)=0
        //       index 2->3 = same(1->1)=1
        //       index 3->4 = same(1->1)=1
        //       index 4->5 = same(1->1)=1
        //       index 5->6 = same(1->1)=1
        //       index 6->7 = same(1->1)=1
        //       index 7->8 = same(1->1)=1
        //       index 8->9 = diff(1->7)=0
        //     total = 1+1+1+1+1+1+1 = 7
        int[] expected4 = {9, 9, 7};
        
        int[] result4 = solution(length4, queries4);
        System.out.println("Test 4 result    : " + Arrays.toString(result4));
        System.out.println("Test 4 expected  : " + Arrays.toString(expected4));
        System.out.println("Test 4 " + (Arrays.equals(result4, expected4) ? "PASS" : "FAIL"));
        System.out.println();

        // You can add more tests here or scale up length/queries for performance checks.
    }
}