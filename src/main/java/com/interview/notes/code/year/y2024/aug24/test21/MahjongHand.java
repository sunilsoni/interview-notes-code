package com.interview.notes.code.year.y2024.aug24.test21;

public class MahjongHand {
    public static boolean complete(String tiles) {
        if (tiles == null || tiles.isEmpty()) {
            return false;
        }

        int[] counts = new int[10]; // 0-9 tiles
        for (char c : tiles.toCharArray()) {
            counts[c - '0']++;
        }

        int pairs = 0;
        for (int count : counts) {
            if (count % 3 == 1) {
                return false; // Can't form a valid group
            }
            if (count % 3 == 2) {
                pairs++;
            }
        }

        return pairs == 1; // Exactly one pair
    }

    public static void main(String[] args) {
        String[] testCases = {
                "88844", "99", "55555", "22333333",
                "73797439949499477339977777997394947947477993",
                "111333555", "42", "888", "100100000",
                "346664366", "8999998999898", "17610177",
                "600061166", "6996999", "03799449",
                "64444333355556", "7", "776655"
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = complete(testCases[i]);
            System.out.println("complete(tiles_" + (i + 1) + ") => " + result);
        }
    }
}
