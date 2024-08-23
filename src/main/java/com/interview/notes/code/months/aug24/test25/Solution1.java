package com.interview.notes.code.months.aug24.test25;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

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
        int messageLength = limit - String.valueOf(numMessages).length() - 3; // -3 for "</>"
        int totalLength = 0;
        for (int i = 1; i <= numMessages; i++) {
            int currentLimit = (i == numMessages) ? limit : messageLength;
            totalLength += currentLimit;
            if (totalLength >= text.length()) {
                return true;
            }
        }
        return false;
    }

    private String[] splitIntoMessages(String text, int limit, int numMessages) {
        List<String> messages = new ArrayList<>();
        int messageLength = limit - String.valueOf(numMessages).length() - 3; // -3 for "</>"
        int start = 0;

        for (int i = 1; i <= numMessages; i++) {
            int currentLimit = (i == numMessages) ? limit : messageLength;
            int end = Math.min(start + currentLimit, text.length());
            String message = text.substring(start, end) + "<" + i + "/" + numMessages + ">";
            messages.add(message);
            start = end;
            if (start >= text.length()) break;
        }

        return messages.toArray(new String[0]);
    }
}
