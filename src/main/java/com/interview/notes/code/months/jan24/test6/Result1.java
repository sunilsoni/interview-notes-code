package com.interview.notes.code.months.jan24.test6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2. Code Question 2
 * Amazon allows customers to add reviews for the products they bought from their store. The review must follow Amazon's community guidelines in order to be published.
 * Suppose that Amazon has marked n strings that are prohibited in reviews. They assign a score to each review that denotes how well it follows the guidelines. The score of a review is defined as the longest contiguous substring of the review which does not contain any string among the list of n prohibited strings. A string contains a prohibited word if it has a contiguous substring that matches a word from the prohibited list, ignoring the case.
 * Given a review and a list of prohibited strings, calculate the review score.
 * Example
 * review = "GoodProductButScrapAfterWash"
 * prohibitedWords = ['"crap", "odpro"]
 * Some of the substrings that do not contain a prohibited word are:
 * • ProductBut
 * • rapAfterWash
 * • dProductButScu
 * • Wash
 * The longest substring is "dProductBut Scra", return its length, 15.
 * Function Description
 * Complete the function findReviewScore in the editor below.
 * findReviewScore has the following parameters:
 * review: a string
 * string prohibitedWords[n]: the prohibited words
 */
class Result1 {
    public static void main(String[] args) {


        //  Example 1
        String review1 = "FastDeliveryOkayProduct";
        List<String> prohibitedWords2 = Arrays.asList("eryoka", "yo", "eli");//11
        System.out.println(Result1.findReviewScore1(review1, prohibitedWords2));

        // Example 2
        String review2 = "ExtremeValueForMoney";
        List<String> prohibitedWords3 = Arrays.asList("tuper", "douche");//20
        System.out.println(Result1.findReviewScore1(review2, prohibitedWords3));
    }

    public static int findReviewScore12(String review, List<String> prohibitedWords) {
        review = review.toLowerCase();
        Set<String> prohibitedSet = new HashSet<>(prohibitedWords);
        int maxLen = 0;

        for (int i = 0; i < review.length(); i++) {
            for (int j = i; j <= review.length(); j++) {
                String sub = review.substring(i, j);
                boolean isValid = true;
                for (String word : prohibitedSet) {
                    if (sub.contains(word)) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid && sub.length() > maxLen) {
                    maxLen = sub.length();
                }
            }
        }

        return maxLen;
    }

    public static int findReviewScore1(String review, List<String> prohibitedWords) {
        review = review.toLowerCase();
        Set<String> prohibitedSet = new HashSet<>(prohibitedWords);
        int maxLen = 0;

        for (int start = 0; start < review.length(); start++) {
            if (maxLen >= review.length() - start) break;

            for (int end = review.length(); end > start; end--) {
                if (end - start <= maxLen) break;
                String sub = review.substring(start, end);

                if (prohibitedSet.stream().noneMatch(sub::contains)) {
                    maxLen = sub.length();
                    break;
                }
            }
        }

        return maxLen;
    }


    public static int findReviewScore13(String review, List<String> prohibitedWords) {
        review = review.toLowerCase();
        Set<String> prohibitedSet = new HashSet<>(prohibitedWords);
        int maxLen = 0;

        for (int i = 0; i < review.length(); i++) {
            if (maxLen >= review.length() - i) break;
            for (int j = review.length(); j > i; j--) {
                if (j - i <= maxLen) break;
                String sub = review.substring(i, j);
                boolean isValid = true;
                for (String word : prohibitedSet) {
                    if (sub.contains(word)) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    maxLen = sub.length();
                    break;
                }
            }
        }

        return maxLen;
    }

    public static int findReviewScore2(String review, List<String> prohibitedWords) {
        int maxLen = 0;
        int n = review.length();

        // Check each substring starting from every index i
        outerloop:
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = review.substring(i, j);
                // Check if the substring contains any prohibited word
                for (String word : prohibitedWords) {
                    if (sub.contains(word)) {
                        continue outerloop;
                    }
                }
                // Update the maximum length found
                maxLen = Math.max(maxLen, j - i);
            }
        }

        return maxLen;
    }


    //error
    public static int findReviewScore3(String review, List<String> prohibitedWords) {
        // Initialize variables to keep track of the longest contiguous substring
        int maxLength = 0;
        int currentLength = 0;
        int start = 0;

        // Convert the review and prohibitedWords to lowercase for case-insensitive comparison
        String lowercaseReview = review.toLowerCase();
        List<String> lowercaseProhibitedWords = prohibitedWords.stream().map(String::toLowerCase).toList();

        // Iterate through the characters of the review
        for (int i = 0; i < review.length(); i++) {
            char currentChar = review.charAt(i);

            // Check if the current substring (from start to i) contains any prohibited word
            boolean containsProhibitedWord = false;
            for (String word : lowercaseProhibitedWords) {
                if (lowercaseReview.substring(start, i + 1).contains(word)) {
                    containsProhibitedWord = true;
                    break;
                }
            }

            // If the current substring contains a prohibited word, update the start index
            // to the position after the first occurrence of the prohibited word
            if (containsProhibitedWord) {
                int prohibitedWordIndex = lowercaseReview.indexOf(lowercaseProhibitedWords.get(0), start);
                start = prohibitedWordIndex + 1;
                currentLength = i - start + 1;
            } else {
                // Update the current length of the contiguous substring without prohibited words
                currentLength = i - start + 1;
            }

            // Update the maximum length if the current length is greater
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static int findReviewScore4(String review, List<String> prohibitedWords) {
        Set<String> prohibitedSet = new HashSet<>(prohibitedWords); // Efficient lookup for prohibited words
        int maxLength = 0;
        int start = 0;
        int end = 0;

        while (end < review.length()) {
            String currentSubstring = review.substring(start, end + 1).toLowerCase(); // Case-insensitive comparison

            if (prohibitedSet.contains(currentSubstring)) {
                start = end + 1; // Move start to the character after the prohibited word
            } else {
                maxLength = Math.max(maxLength, end - start + 1); // Update maxLength if necessary
            }

            end++; // Expand the substring
        }

        return maxLength;
    }

    // A utility method to convert an array of strings to a hash set
    public static HashSet<String> toHashSet(String[] arr) {
        HashSet<String> set = new HashSet<>();
        for (String s : arr) {
            set.add(s.toLowerCase());
        }
        return set;
    }

    // The main method to find the review score
    public static int findReviewScore(String review, String[] prohibitedWords) {
        // Convert the review and the prohibited words to lowercase
        review = review.toLowerCase();
        HashSet<String> set = toHashSet(prohibitedWords);

        // Initialize the start and end pointers, and the max length
        int start = 0;
        int end = 0;
        int max = 0;

        // Loop until the end pointer reaches the end of the string
        while (end < review.length()) {
            // Get the current substring
            String curr = review.substring(start, end + 1);

            // Check if the current substring contains any prohibited word
            boolean contains = false;
            for (String word : set) {
                int index = curr.indexOf(word);
                if (index != -1) {
                    // If it does, move the start pointer to the right of the first occurrence
                    start = start + index + word.length();
                    // Update the current substring
                    curr = review.substring(start, end + 1);
                    // Set the flag to true
                    contains = true;
                    // Break the loop
                    break;
                }
            }

            // If the current substring does not contain any prohibited word
            if (!contains) {
                // Update the max length if the current substring is longer
                max = Math.max(max, curr.length());
                // Increment the end pointer
                end++;
            }
        }

        // Return the max length
        return max;
    }
}
