package com.interview.notes.code.year.y2025.september.assesment.test7;

import java.util.LinkedList;
import java.util.Queue;

public class SandwichProblemFixed {

    public static int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        for (int s : students) queue.offer(s);

        int index = 0; // sandwich pointer
        int rotations = 0; // count how many times we rotated without success

        while (!queue.isEmpty() && rotations < queue.size()) {
            if (queue.peek() == sandwiches[index]) {
                // student eats sandwich
                queue.poll();
                index++;
                rotations = 0; // reset rotations since someone ate
            } else {
                // move student to end
                queue.offer(queue.poll());
                rotations++;
            }
        }

        return queue.size(); // remaining students can't eat
    }

    public static void main(String[] args) {
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

        for (int i = 0; i < studentsCases.length; i++) {
            int result = countStudents(studentsCases[i], sandwichesCases[i]);
            System.out.println("Test " + (i + 1) + ": " +
                    (result == expected[i] ? "PASS" : "FAIL") +
                    " | Expected=" + expected[i] + ", Got=" + result);
        }
    }
}