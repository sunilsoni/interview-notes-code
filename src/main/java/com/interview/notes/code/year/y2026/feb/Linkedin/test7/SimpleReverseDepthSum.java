package com.interview.notes.code.year.y2026.feb.Linkedin.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleReverseDepthSum {

    public static int reverseDepthSum(List<Object> input) {
        int unweightedSum = 0; // Sum of all plain numbers we've seen so far
        int weightedSum = 0;   // The final accumulated answer we will return
        
        // Loop level by level until there is no more data left to process
        while (!input.isEmpty()) {
            
            // Create a temporary bucket to hold the items for the NEXT level down
            List<Object> nextLevel = new ArrayList<>();
            
            // Look at every single item in our current level
            for (Object item : input) {
                
                if (item instanceof Integer) {
                    // If it's a plain number, add it to our running total
                    unweightedSum += (Integer) item; 
                } 
                else if (item instanceof List) {
                    // If it's a nested list, dump its contents into our nextLevel bucket
                    // (We will process these in the next cycle of the while loop)
                    nextLevel.addAll((List<?>) item); 
                }
            }
            
            // Add the running total to our final answer. 
            // The Magic: Because we do this step every time we go a level deeper, 
            // upper-level numbers get added multiple times. 
            // This perfectly mimics the "reverse depth" multiplier without doing any complex math!
            weightedSum += unweightedSum;
            
            // Replace our current input with the next level down, and repeat the loop
            input = nextLevel; 
        }
        
        return weightedSum;
    }

    // ==========================================
    // SIMPLE TESTING METHOD
    // ==========================================
    public static void main(String[] args) {
        
        // Test Case 2: {1, {4, {6, 2}}} 
        // Notice how much easier it is to build the test data without the interface
        List<Object> test2 = Arrays.asList(
            1, 
            Arrays.asList(
                4, 
                Arrays.asList(6, 2)
            )
        );

        int result = reverseDepthSum(test2);
        
        System.out.println("Test Case 2 -> Expected: 19 | Got: " + result);
        System.out.println(result == 19 ? "PASS" : "FAIL");
    }
}