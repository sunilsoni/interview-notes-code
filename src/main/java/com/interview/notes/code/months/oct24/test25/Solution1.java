package com.interview.notes.code.months.oct24.test25;

import java.util.*;

public class Solution1 {
    /*
     * Complete the 'getMinimumOperations' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY items as parameter.
     */
    public static int getMinimumOperations(List<Integer> items) {
        int n = items.size();
        if(n == 0){
            return 0;
        }
        
        // Initialize previous even and odd operation counts
        long prevEven = getOpsToEven(items.get(0));
        long prevOdd = getOpsToOdd(items.get(0));
        
        for(int i =1; i<n; i++) {
            int x = items.get(i);
            long currEven = Long.MAX_VALUE;
            long currOdd = Long.MAX_VALUE;
            
            // If previous was even, current must be odd
            if(prevEven != Long.MAX_VALUE){
                long opsToOdd = getOpsToOdd(x);
                if(opsToOdd != Long.MAX_VALUE){
                    currOdd = prevEven + opsToOdd;
                }
            }
            
            // If previous was odd, current must be even
            if(prevOdd != Long.MAX_VALUE){
                long opsToEven = getOpsToEven(x);
                if(opsToEven != Long.MAX_VALUE){
                    currEven = prevOdd + opsToEven;
                }
            }
            
            // Update previous for next iteration
            prevEven = currEven;
            prevOdd = currOdd;
        }
        
        // Return the minimum of the two possibilities for the last element
        long result = Math.min(prevEven, prevOdd);
        return result == Long.MAX_VALUE ? -1 : (int)result;
    }
    
    // Function to get operations to make x even
    private static int getOpsToEven(int x){
        if(x %2 ==0){
            return 0;
        }
        int ops =0;
        while(x >0 && x %2 !=0){
            x = x /2;
            ops++;
        }
        if(x %2 ==0){
            return ops;
        }
        return Integer.MAX_VALUE;
    }
    
    // Function to get operations to make x odd
    private static int getOpsToOdd(int x){
        if(x %2 ==1){
            return 0;
        }
        if(x ==0){
            return Integer.MAX_VALUE;
        }
        int ops=0;
        while(x >0 && x %2 ==0){
            x =x /2;
            ops++;
        }
        if(x >0 && x%2 ==1){
            return ops;
        }
        return Integer.MAX_VALUE;
    }
    
    // Test method
    public static void main(String[] args){
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();
        
        // Example1
        testCases.add(new TestCase(Arrays.asList(6,5,4,7,3), 3));
        
        // Example2
        testCases.add(new TestCase(Arrays.asList(2,1,4,7,2), 0));
        
        // Example3
        testCases.add(new TestCase(Arrays.asList(4,10,10,6,2), 2));
        
        // Additional Test Cases
        
        // TestCase4: Single element, already organized
        testCases.add(new TestCase(Arrays.asList(1), 0));
        
        // TestCase5: Single element, needs to be even
        testCases.add(new TestCase(Arrays.asList(3), 0)); // Correct expectation: 0
        
        // TestCase6: All even, need to adjust every second element
        testCases.add(new TestCase(Arrays.asList(2,4,6,8,10), 3)); // Correct expectation:3
        
        // TestCase7: All odd, need to adjust every second element
        testCases.add(new TestCase(Arrays.asList(1,3,5,7,9), 4)); // Correct expectation:4
        
        // TestCase8: Mixed, already organized
        testCases.add(new TestCase(Arrays.asList(2,3,4,5,6),0));
        
        // TestCase9: Large Input
        List<Integer> largeInput = new ArrayList<>();
        for(int i=1;i<=100000;i++){
            largeInput.add(i); //1 to100000
        }
        // The expected value for largeInput is complex to calculate, set to 0 for placeholder
        testCases.add(new TestCase(largeInput, 0));
        
        // Execute test cases
        int passed =0;
        for(int i=0; i<testCases.size(); i++){
            TestCase tc = testCases.get(i);
            int result = getMinimumOperations(tc.items);
            if(tc.expected ==0 && (i==4 || i==8)) { // For TestCase5 and TestCase9
                // For TestCase5 (single element, expected=0) and TestCase9 (large input, expected=0 as placeholder)
                // Adjust based on actual requirements
            }
            if(result == tc.expected){
                System.out.println("TestCase"+(i+1)+": PASS");
                passed++;
            }
            else{
                System.out.println("TestCase"+(i+1)+": FAIL. Expected "+tc.expected+" but got "+result);
            }
        }
        System.out.println(passed + "/" + testCases.size() + " Test Cases Passed.");
    }
    
    // TestCase class
    static class TestCase{
        List<Integer> items;
        int expected;
        TestCase(List<Integer> items, int expected){
            this.items = items;
            this.expected = expected;
        }
    }
}
