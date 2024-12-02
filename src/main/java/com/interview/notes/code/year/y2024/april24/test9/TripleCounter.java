package com.interview.notes.code.year.y2024.april24.test9;

public class TripleCounter {
    public static int countTriplets(String s, int tripletLength) {
        int count = 0;

        // Iterate through the string characters up to the position where triplets can start
        for (int i = 0; i < s.length() - (tripletLength - 1); i++) {
            boolean isTriplet = true;
            // Check if the current character and the next (tripletLength - 1) characters are the same
            for (int j = 1; j < tripletLength; j++) {
                if (s.charAt(i) != s.charAt(i + j)) {
                    isTriplet = false;
                    break;
                }
            }
            if (isTriplet) {
                count++; // Increment the count if a triplet is found
                i += (tripletLength - 1); // Skip ahead by tripletLength - 1 characters
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example usage
        String s1 = "aaa";
        String s2 = "aaab";
        String s3 = "aaaaaa";

        int tripletLength = 3; // Length of the triplets

        System.out.println("Triplets of length " + tripletLength + " in \"" + s1 + "\": " + countTriplets(s1, tripletLength)); // Output: 1
        System.out.println("Triplets of length " + tripletLength + " in \"" + s2 + "\": " + countTriplets(s2, tripletLength)); // Output: 1
        System.out.println("Triplets of length " + tripletLength + " in \"" + s3 + "\": " + countTriplets(s3, tripletLength)); // Output: 2
    }
}
