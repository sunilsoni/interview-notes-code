package com.interview.notes.code.months.sept24.test9;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
    public static void main(String[] args) {
        String[] testCases = {"RD", "RDD", "RRDDD", "RRRR", "DDDD"};
        for (String senate : testCases) {
            System.out.println(predictPartyVictory(senate));
        }
    }

    public static String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }

        int n = senate.length();
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();

            if (rIndex < dIndex) {
                radiant.add(rIndex + n);
            } else {
                dire.add(dIndex + n);
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
