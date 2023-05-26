package com.interview.notes.code.tricky;

/**
 * // find the longest consecutive char public class Main
 * public static void main(String[] args) {
 * }
 * a
 * String str = new String("wwwwwtttuuuuuu0000ssssssssslllllll");
 * int maxIndex = —1, maxCount = 0; char maxChar = '\n';
 * // to do
 * System.out.println("Max consecutive char is " + maxChar);
 * System.out.printf("It starts at position %s, length is %s", maxIndex, maxCount);
 */
public class Main {
    public static void main(String[] args) {
        String str = "wwwwwtttuuuuuu0000ssssssssslllllll";
        int maxIndex = -1, maxCount = 0;
        char maxChar = '\n';
        char currentChar = '\n';
        int currentCount = 0;
        int currentIndex = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == currentChar) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    maxIndex = currentIndex;
                    maxChar = currentChar;
                }
            } else {
                currentChar = str.charAt(i);
                currentCount = 1;
                currentIndex = i;
            }
        }
        System.out.println("maxIndex " + maxIndex);
        System.out.println("Max consecutive char is " + maxChar);
        System.out.printf("It starts at position %d, length is %d", maxIndex, maxCount);
    }


}

/**
 * The code is a Java program that finds the longest consecutive character in a string. Here's an explanation of the code:
 * <p>
 * A string str is declared and initialized with a value.
 * <p>
 * Two variables maxIndex and maxCount are declared to store the starting index and the length of the longest consecutive character, respectively. maxChar is declared to store the character that occurs the most consecutively.
 * <p>
 * A loop is used to iterate over each character in the string str.
 * <p>
 * Within the loop, a check is made to see if the current character is equal to the previous character. If it is, then the count of consecutive occurrences of the character is incremented.
 * <p>
 * If the current character is different from the previous character, the values of currentChar, currentCount, and currentIndex are updated to store the new character, count, and index.
 * <p>
 * After the loop, the final values of maxChar, maxIndex, and maxCount are printed to the console to show the result of the program.
 * <p>
 * The program uses the charAt() method to access individual characters in the string and the printf() method to format the output.
 */