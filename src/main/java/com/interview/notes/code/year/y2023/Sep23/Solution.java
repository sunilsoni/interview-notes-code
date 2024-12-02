package com.interview.notes.code.year.y2023.Sep23;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static int playlist(int[] songs) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        int pairs = 0;

        for (int song : songs) {
            int remainder = song % 60;
            int complement = (60 - remainder) % 60; // to handle case when remainder is 0
            pairs += remainderCount.getOrDefault(complement, 0); // find pairs for this song
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1); // update count of this remainder
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] songs = {10, 50, 90, 30};
        System.out.println(playlist(songs));  // Outputs: 2
    }
}
