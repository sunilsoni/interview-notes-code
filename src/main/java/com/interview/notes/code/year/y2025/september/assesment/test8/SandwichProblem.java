package com.interview.notes.code.year.y2025.september.assesment.test8;

import java.util.Arrays;

/*
# The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1
# respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
#
# The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack.
# At each step:
# - If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and
# leave the queue.
# - Otherwise, they will leave it and go to the queue's end.
#
# This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
#
# You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the
# ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the
# jth student in the initial queue (j = 0 is the front of the queue).
#
# Return the number of students that are unable to eat.

 */
public class SandwichProblem {

    // Method to calculate number of students who cannot eat
    public static int countStudents(int[] students, int[] sandwiches) {
        // Count number of students who prefer circular (0) and square (1)
        int countCircle = 0;
        int countSquare = 0;

        for (int s : students) {
            if (s == 0) countCircle++;
            else countSquare++;
        }

        // Process sandwiches stack from top to bottom
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                // If no one wants circular, stop
                if (countCircle == 0) break;
                countCircle--; // serve one circular sandwich
            } else {
                // If no one wants square, stop
                if (countSquare == 0) break;
                countSquare--; // serve one square sandwich
            }
        }

        // Remaining students = those still unserved
        return countCircle + countSquare;
    }

    // Testing method with PASS/FAIL checks
    public static void main(String[] args) {
        // Test cases
        int[][] studentsCases = {
                {1, 1, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 0},
        };

        int[][] sandwichesCases = {
                {0, 1, 0, 1},
                {0, 1, 0, 1, 0, 1},
                {0, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0},
        };

        int[] expected = {0, 3, 0, 3};  // expected outputs

        // Run and validate
        for (int i = 0; i < studentsCases.length; i++) {
            int result = countStudents(studentsCases[i], sandwichesCases[i]);
            System.out.println("Test " + (i + 1) + ": " +
                    (result == expected[i] ? "PASS" : "FAIL") +
                    " | Expected=" + expected[i] + ", Got=" + result);
        }

        // Large data test
        int n = 1000000;
        int[] largeStudents = new int[n];
        int[] largeSandwiches = new int[n];
        Arrays.fill(largeStudents, 0);
        Arrays.fill(largeSandwiches, 0);
        System.out.println("Large Test: " + (countStudents(largeStudents, largeSandwiches) == 0 ? "PASS" : "FAIL"));
    }
}