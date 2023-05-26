package com.interview.notes.code.tricky;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LetterCandles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of candles
        int m = sc.nextInt(); // max number of candles that can be removed
        String s = sc.next(); // string representing the candles

        // count the frequency of each character
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // calculate the current cost of the box
        int cost = 0;
        for (int f : freq.values()) {
            cost += f * f;
        }

        // try removing each character and calculate the new cost
        for (char c : freq.keySet()) {
            int f = freq.get(c);
            if (f <= m) { // we can remove all occurrences of this character
                int newCost = cost - f * f;
                m -= f; // update the remaining number of candles that can be removed
                if (m == 0) { // we cannot remove any more candles
                    System.out.println(newCost);
                    return;
                }
            } else { // we can only remove some occurrences of this character
                int newCost = cost - m * m - (f - m) * (f - m);
                System.out.println(newCost);
                return;
            }
        }

        // if we reach here, we can remove all candles from the box
        System.out.println(0);
    }
}
