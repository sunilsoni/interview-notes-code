package com.interview.notes.code.year.y2024.nov24.amazon.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /**
     * Main method to test the findSimilarities function.
     * Reads input from STDIN, processes each test case, and prints the outputs.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of newPasswords list
        int n = Integer.parseInt(scanner.nextLine().trim());
        List<String> newPasswords = new ArrayList<>();

        // Read newPasswords
        for (int i = 0; i < n; i++) {
            newPasswords.add(scanner.nextLine().trim());
        }

        // Read the size of oldPasswords list
        int m = Integer.parseInt(scanner.nextLine().trim());
        List<String> oldPasswords = new ArrayList<>();

        // Read oldPasswords
        for (int i = 0; i < m; i++) {
            oldPasswords.add(scanner.nextLine().trim());
        }

        // Ensure both lists have the same size
        if (n != m) {
            System.out.println("The number of new and old passwords must be the same.");
            return;
        }

        // Call the findSimilarities function
        List<String> results = findSimilarities(newPasswords, oldPasswords);

        // Print the outputs
        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }

    /**
     * Determines if each old password can be obtained as a subsequence of the new password
     * after changing some characters in the new password to their next cyclic character.
     *
     * @param newPasswords List of new passwords.
     * @param oldPasswords List of old passwords.
     * @return List of "YES" or "NO" for each pair of passwords.
     */
    public static List<String> findSimilarities(List<String> newPasswords, List<String> oldPasswords) {
        List<String> results = new ArrayList<>();

        // Process each pair of passwords
        for (int idx = 0; idx < newPasswords.size(); idx++) {
            String newPassword = newPasswords.get(idx);
            String oldPassword = oldPasswords.get(idx);

            if (isSimilar(newPassword, oldPassword)) {
                results.add("YES");
            } else {
                results.add("NO");
            }
        }

        return results;
    }

    /**
     * Checks if the old password can be obtained as a subsequence of the new password
     * after changing some characters to their next cyclic character.
     *
     * @param newPassword The new password string.
     * @param oldPassword The old password string.
     * @return True if similar, False otherwise.
     */
    private static boolean isSimilar(String newPassword, String oldPassword) {
        int n = newPassword.length();
        int m = oldPassword.length();

        int i = 0; // Pointer for newPassword
        int j = 0; // Pointer for oldPassword

        while (i < n && j < m) {
            char newChar = newPassword.charAt(i);
            char nextCyclicChar = nextCyclicChar(newChar);
            char oldChar = oldPassword.charAt(j);

            // Check if oldChar matches newChar or its next cyclic character
            if (oldChar == newChar || oldChar == nextCyclicChar) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        // If all characters in oldPassword are matched
        return j == m;
    }

    /**
     * Computes the next cyclic character for a given character.
     * 'a' -> 'b', 'b' -> 'c', ..., 'z' -> 'a'.
     *
     * @param c The current character.
     * @return The next cyclic character.
     */
    private static char nextCyclicChar(char c) {
        if (c == 'z') {
            return 'a';
        } else {
            return (char) (c + 1);
        }
    }
}
