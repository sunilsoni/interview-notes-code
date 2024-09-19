package com.interview.notes.code.months.sept24.test9;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate1 {
    public static String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        // Initialize queues
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        // Simulate the banning process
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r_index = radiant.poll();
            int d_index = dire.poll();

            if (r_index < d_index) {
                radiant.offer(r_index + n);
            } else {
                dire.offer(d_index + n);
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"RD", "RDD", "DDRRR", "DRRD"};
        String[] expectedOutputs = {"Radiant", "Dire", "Radiant", "Dire"};

        for (int i = 0; i < testCases.length; i++) {
            String result = predictPartyVictory(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " + 
                               (result.equals(expectedOutputs[i]) ? "PASS" : "FAIL") +
                               " (Expected: " + expectedOutputs[i] + ", Got: " + result + ")");
        }
    }
}