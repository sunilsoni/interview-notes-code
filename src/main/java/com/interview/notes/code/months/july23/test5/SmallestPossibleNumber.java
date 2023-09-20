package com.interview.notes.code.months.july23.test5;

import java.util.Stack;

public class SmallestPossibleNumber {
    public static void main(String[] args) {
        SmallestPossibleNumber s = new SmallestPossibleNumber();
        System.out.println(s.smallestPossibleNumber("IIIDIDDD"));  // Output: "123549876"
        System.out.println(s.smallestPossibleNumber("DDD"));  // Output: "4321"
        System.out.println(s.smallestPossibleNumber("IIIDIDDD"));  // Output: "986723451"

    }

    public String smallestPossibleNumber(String pattern) {
        // Create two stacks
        Stack<Integer> increasingStack = new Stack<>();
        Stack<Integer> decreasingStack = new Stack<>();

        // Push numbers 1 to 9 to the increasing stack
        for (int i = 1; i <= 9; i++) {
            increasingStack.push(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            // If the current character is 'I', pop from the increasing stack and push to the decreasing stack
            if (pattern.charAt(i) == 'I') {
                while (!decreasingStack.empty()) {
                    sb.append(decreasingStack.pop());
                }
                decreasingStack.push(increasingStack.pop());
            }
            // If the current character is 'D', pop from the increasing stack and push to the decreasing stack
            else if (pattern.charAt(i) == 'D') {
                decreasingStack.push(increasingStack.pop());
            }
        }

        // Append the remaining numbers from the decreasing stack to the result string
        while (!decreasingStack.empty()) {
            sb.append(decreasingStack.pop());
        }

        // Append the remaining numbers from the increasing stack to the result string
        while (!increasingStack.empty()) {
            sb.append(increasingStack.pop());
        }

        return sb.toString();
    }
}
