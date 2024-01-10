package com.interview.notes.code.months.jan24.test1;

import java.util.Arrays;

public class Outcome1 {
    public static int solve1(int N, int M, String S) {
        // Create an array to count the occurrences of each character
        int[] charCount = new int[26]; // Assuming only lowercase English letters

        // Count the occurrences of each character
        for (char c : S.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Create an array to store the frequencies of occurrences
        int[] frequencies = new int[N + 1];

        // Calculate the frequencies of occurrences
        for (int count : charCount) {
            if (count > 0) {
                frequencies[count]++;
            }
        }

        int cost = 0;
        int candlesToRemove = 0;

        // Iterate from the highest frequency towards the lowest
        for (int i = N; i > 0; i--) {
            if (frequencies[i] > 0) {
                int numToRemove = Math.min(M - candlesToRemove, frequencies[i]);
                cost += i * i * numToRemove;
                candlesToRemove += numToRemove;

                if (candlesToRemove >= M) {
                    break;
                }
            }
        }

        return cost;
    }

    public static int solve(int N, int M, String S) {
        int[] freq = new int[26];
        for (int i = 0; i < N; i++) {
            freq[S.charAt(i) - 'a']++;
        }
        Arrays.sort(freq);
        int ans = 0;
        for (int i = 0; i < 26 && M > 0; i++) {
            int take = Math.min(M, freq[i]);
            ans += take * take;
            M -= take;
        }
        return ans;
    }

    public static void main(String[] args) {
        int N1 = 6;
        int M1 = 2;
        String S1 = "bacaco";
        int result1 = solve(N1, M1, S1);
        System.out.println("Example #1: " + result1);

        int N2 = 6;
        int M2 = 2;
        String S2 = "bacacc";
        int result2 = solve(N2, M2, S2);
        System.out.println("Example #2: " + result2);

        int N3 = 15;
        int M3 = 3;
        String S3 = "XXXXXXXXXXXXXXX";
        int result3 = solve(N3, M3, S3);
        System.out.println("Example #3: " + result3);
    }
}
