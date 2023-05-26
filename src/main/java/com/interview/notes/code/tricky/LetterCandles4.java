package com.interview.notes.code.tricky;

import java.util.*;

public class LetterCandles4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // number of letter candles
        int m = scanner.nextInt(); // number of candles Alice can remove
        String s = scanner.next(); // string representing the letter candles

        // create a frequency map of letters in the string
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // create a list of frequencies and sort it in descending order
        List<Integer> freqList = new ArrayList<>(freqMap.values());
        Collections.sort(freqList, Collections.reverseOrder());

        // remove at most m frequencies from the list and calculate the minimum cost
        int cost = 0;
        for (int i = 0; i < freqList.size(); i++) {
            if (m > 0) {
                m--;
            } else {
                cost += freqList.get(i) * freqList.get(i);
            }
        }

        System.out.println(cost);
    }
}
