package com.interview.notes.code.year.y2025.october.common.test14;

public class Solution {
    private static int ref(int i, int j) {
        long a = i, b = j;
        int steps = 0;
        int da = 63 - Long.numberOfLeadingZeros(a);
        int db = 63 - Long.numberOfLeadingZeros(b);
        while (da > db) {
            a >>= 1;
            da--;
            steps++;
        }
        while (db > da) {
            b >>= 1;
            db--;
            steps++;
        }
        while (a != b) {
            a >>= 1;
            b >>= 1;
            steps += 2;
        }
        return steps;
    }

    public int find_shortest_path(int i, int j) {
        long a = i, b = j;
        int steps = 0;
        while (a != b) {
            if (a > b) a >>= 1;
            else b >>= 1;
            steps++;
        }
        return steps;
    }

}