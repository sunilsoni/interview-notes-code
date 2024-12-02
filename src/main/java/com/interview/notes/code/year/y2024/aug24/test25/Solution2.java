package com.interview.notes.code.year.y2024.aug24.test25;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Test case 1
        String text1 = "Hello, world!";
        int limit1 = 10;
        String[] result1 = solution.solution(text1, limit1);
        System.out.println("Test case 1:");
        for (String msg : result1) {
            System.out.println(msg);
        }

        // Test case 2
        String text2 = "Very long message! More than 10 messages needed.";
        int limit2 = 10;
        String[] result2 = solution.solution(text2, limit2);
        System.out.println("\nTest case 2:");
        for (String msg : result2) {
            System.out.println(msg);
        }
    }

    public String[] solution(String text, int limit) {
        int left = 1;
        int right = text.length();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSplit(text, limit, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (!canSplit(text, limit, left)) {
            return new String[0];
        }

        return splitIntoMessages(text, limit, left);
    }

    private boolean canSplit(String text, int limit, int numMessages) {
        int remainingText = text.length();
        for (int i = 1; i <= numMessages; i++) {
            int suffixLength = 3 + String.valueOf(numMessages).length();
            int availableSpace = limit - suffixLength;
            if (i == numMessages) {
                return remainingText <= limit;
            }
            if (remainingText <= availableSpace) {
                return true;
            }
            remainingText -= availableSpace;
        }
        return false;
    }

    private String[] splitIntoMessages(String text, int limit, int numMessages) {
        List<String> messages = new ArrayList<>();
        int remainingText = text.length();
        int startIndex = 0;

        for (int i = 1; i <= numMessages; i++) {
            int suffixLength = 3 + String.valueOf(numMessages).length();
            int availableSpace = limit - suffixLength;
            int endIndex;

            if (i == numMessages) {
                endIndex = text.length();
            } else {
                endIndex = Math.min(startIndex + availableSpace, text.length());
            }

            String message = text.substring(startIndex, endIndex) + "<" + i + "/" + numMessages + ">";
            messages.add(message);

            startIndex = endIndex;
            if (startIndex >= text.length()) break;
        }

        return messages.toArray(new String[0]);
    }
}
