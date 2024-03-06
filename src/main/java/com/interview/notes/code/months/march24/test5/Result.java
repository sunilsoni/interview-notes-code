package com.interview.notes.code.months.march24.test5;

class Result {

    /*
     *
     * /
     * Complete the 'getLargestNumber' function below.
     * The function is expected to return a STRING.
     * The function accepts STRING num as parameter.
     */
    public static String getLargestNumber(String num) {
        StringBuilder largest = new StringBuilder();
        int[] digits = new int[num.length()];
        boolean[] visited = new boolean[num.length()];

        // Convert string to integer array
        for (int i = 0; i < num.length(); i++) {
            digits[i] = num.charAt(i) - '0';
        }

        // Iterate over digits, prioritizing even digits first
        for (int i = 0; i < num.length(); i++) {
            if (!visited[i]) {
                int maxDigitIndex = findMaxDigitIndex(digits, visited, i % 2 == 0);
                largest.append(digits[maxDigitIndex]);
                visited[maxDigitIndex] = true;
            }
        }

        return largest.toString();
    }

    // Helper function to find the index of the largest unvisited digit with the specified parity preference
    private static int findMaxDigitIndex(int[] digits, boolean[] visited, boolean evenFirst) {
        int maxIndex = -1;
        int maxValue = -1;

        for (int i = 0; i < digits.length; i++) {
            if (!visited[i] && ((digits[i] % 2 == 0 && evenFirst) || (digits[i] % 2 == 1 && !evenFirst))) {
                if (digits[i] > maxValue) {
                    maxValue = digits[i];
                    maxIndex = i;
                }
            }
        }

        return maxIndex;
    }


    public static void main(String[] args) {
        String num = "0082663";
        System.out.println(getLargestNumber(num)); // Output should be "9758601"

        num = "1806579";
        System.out.println(getLargestNumber(num)); // Output should be "1860975"





    }
}
