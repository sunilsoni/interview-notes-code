package com.interview.notes.code.year.y2024.nov24.test4;

public class AverageStringLength {
    public static void main(String[] args) {
        String[] strings = {"apple", "banana", "cherry", "date", "elderberry"};

        double averageLength = calculateAverageLength(strings);

        System.out.println("Average length of strings: " + averageLength);
    }

    public static double calculateAverageLength(String[] strings) {
        int totalLength = 0;
        int count = 0;

        // Iterate through the array
        for (int i = 0; ; i++) {
            try {
                String str = strings[i];
                if (str == null) {
                    break;
                }

                int length = 0;

                // Calculate the length of each string
                try {
                    while (true) {
                        str.charAt(length);
                        length++;
                    }
                } catch (IndexOutOfBoundsException e) {
                    // End of string reached
                }

                totalLength += length;
                count++;
            } catch (ArrayIndexOutOfBoundsException e) {
                // End of array reached
                break;
            }
        }

        // Calculate the average length
        if (count == 0) {
            return 0;
        }

        return (double) totalLength / count;
    }
}
