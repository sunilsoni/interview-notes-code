package com.interview.notes.code.months.aug24.test25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();

        // Test case 1
        String text1 = "Hello, world!";
        int limit1 = 10;
        String[] result1 = solution.solution(text1, limit1);
        System.out.println("Test case 1:");
        System.out.println("Expected: [\"Hello<1/3>\", \", wor<2/3>\", \"ld!<3/3>\"]");
        System.out.println("Actual: " + Arrays.toString(result1));

        // Test case 2
        String text2 = "Very long message! More than 10 messages needed.";
        int limit2 = 10;
        String[] result2 = solution.solution(text2, limit2);
        System.out.println("\nTest case 2:");
        System.out.println("Expected: [\"Very<1/13>\", \" lon<2/13>\", \"g me<3/13>\", \"ssag<4/13>\", \"e! M<5/13>\", \"ore <6/13>\", \"than<7/13>\", \" 10 <8/13>\", \"mess<9/13>\", \"age<10/13>\", \"s n<11/13>\", \"eed<12/13>\", \"ed.<13/13>\"]");
        System.out.println("Actual: " + Arrays.toString(result2));

        // Test case 3
        String text3 = "CodeSignal";
        int limit3 = 6;
        String[] result3 = solution.solution(text3, limit3);
        System.out.println("\nTest case 3:");
        System.out.println("Expected: []");
        System.out.println("Actual: " + Arrays.toString(result3));

        // Test case 4: Single character
        String text4 = "A";
        int limit4 = 5;
        String[] result4 = solution.solution(text4, limit4);
        System.out.println("\nTest case 4:");
        System.out.println("Expected: [\"A<1/1>\"]");
        System.out.println("Actual: " + Arrays.toString(result4));

        // Test case 5: Empty string
        String text5 = "";
        int limit5 = 10;
        String[] result5 = solution.solution(text5, limit5);
        System.out.println("\nTest case 5:");
        System.out.println("Expected: []");
        System.out.println("Actual: " + Arrays.toString(result5));

        // Test case 6: Limit exactly fits one character and suffix
        String text6 = "Hello";
        int limit6 = 5;
        String[] result6 = solution.solution(text6, limit6);
        System.out.println("\nTest case 6:");
        System.out.println("Expected: [\"H<1/5>\", \"e<2/5>\", \"l<3/5>\", \"l<4/5>\", \"o<5/5>\"]");
        System.out.println("Actual: " + Arrays.toString(result6));
    }

    public String[] solution(String text, int limit) {
        if (limit < 5) return new String[0]; // Minimum limit to fit at least one character and suffix

        int maxMessages = (text.length() + limit - 5) / (limit - 4); // Maximum possible number of messages
        int left = 1;
        int right = maxMessages;

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
            if (availableSpace <= 0) return false;
            if (i == numMessages) {
                return remainingText <= availableSpace;
            }
            remainingText -= availableSpace;
            if (remainingText <= 0) return true;
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
            int endIndex = Math.min(startIndex + availableSpace, text.length());

            String message = text.substring(startIndex, endIndex) + "<" + i + "/" + numMessages + ">";
            messages.add(message);

            startIndex = endIndex;
            remainingText -= (endIndex - startIndex);

            if (remainingText == 0) break;
        }

        return messages.toArray(new String[0]);
    }
}
