package com.interview.notes.code.year.y2023.nov23.test7;

public class BullsAndCows {
    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int[] secretFreq = new int[10];
        int[] guessFreq = new int[10];

        // Count bulls and update frequency arrays
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                secretFreq[secret.charAt(i) - '0']++;
                guessFreq[guess.charAt(i) - '0']++;
            }
        }

        // Calculate cows
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretFreq[i], guessFreq[i]);
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint("1807", "7810")); // 1A3B
        System.out.println(getHint("1123", "0111")); // 1A1B
    }
}
