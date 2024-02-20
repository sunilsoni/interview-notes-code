package com.interview.notes.code.months.jan24.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {


    //WORKING: Passing all tests
    public static int getMinLength(List<Integer> a, int k) {
        Stack<Integer> stack = new Stack<>();

        for (int num : a) {
            // Push the current number onto the stack
            stack.push(num);
            // While the stack has more than one element and the top two elements can be merged...
            while (stack.size() > 1 && ((long) stack.peek() * stack.get(stack.size() - 2) <= k)) {
                // Pop the top element, merge it with the new top, and push the result back
                int merged = stack.pop() * stack.pop();
                stack.push(merged);
            }
        }
        // The size of the stack is the minimum length of the array after merging
        return stack.size();
    }

    public static int getMinLength1(List<Integer> a, int k) {
        // We will keep merging elements until no more merges are possible
        boolean canMerge = true;
        while (canMerge) {
            canMerge = false;
            for (int i = 0; i < a.size() - 1; i++) {
                long product = (long) a.get(i) * a.get(i + 1);
                if (product <= k) {
                    a.set(i, (int) product); // Merge the elements
                    a.remove(i + 1);         // Remove the merged element
                    canMerge = true;         // We were able to perform a merge
                    break;                   // Start from the beginning after every merge
                }
            }
        }
        return a.size(); // Return the reduced size of the array
    }

    public static void main(String[] args) {
        // Example 1:
        System.out.println(getMinLength(new ArrayList<>(Arrays.asList(2, 3, 3, 7, 5)), 20)); // Expected output: 3

        // Example 2:
        System.out.println(getMinLength(new ArrayList<>(Arrays.asList(1, 3, 2, 5, 4)), 6)); // Expected output: 3

        // Example 3:
        System.out.println(getMinLength(new ArrayList<>(Arrays.asList(1, 2, 1, 3, 6, 1)), 6)); // Expected output: 2
    }
}
