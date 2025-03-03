package com.interview.notes.code.year.y2025.feb.common.test5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhoneNumberGenerator {

    /**
     * Generates all valid phone numbers of given length, excluding up to 3 disallowed digits,
     * ensuring no digit repeats consecutively, and if '4' is present, the phone number starts with '4'.
     *
     * @param length     The length of the phone number
     * @param disallowed A set of digits (as chars) that are disallowed
     * @return List of valid phone numbers as Strings
     */
    public static List<String> generatePhoneNumbers(int length, Set<Character> disallowed) {
        List<String> result = new ArrayList<>();

        // Quick edge case: If length <= 0, return empty result
        if (length <= 0) {
            return result;
        }

        // If '4' is disallowed, then we simply generate phone numbers
        // using all allowed digits (without '4') and no consecutive repeats.
        // Otherwise, we have two "branches":
        //   1) phone numbers that do not use '4' at all
        //   2) phone numbers that start with '4' (and may use 4 again, if not consecutive)

        // 1) phone numbers that do NOT use '4'
        if (!disallowed.contains('4')) {
            // We'll treat it as if '4' is effectively disallowed in this branch
            Set<Character> noFourSet = new HashSet<>(disallowed);
            noFourSet.add('4');  // block '4' so we never pick it
            backtrack("", length, noFourSet, result);
        }

        // 2) phone numbers that START with '4' (only if 4 is not disallowed)
        if (!disallowed.contains('4')) {
            // Start with '4'
            backtrack("4", length, disallowed, result);
        }

        // If '4' is disallowed, we only did the "noFourSet" branch
        if (disallowed.contains('4')) {
            backtrack("", length, disallowed, result);
        }

        return result;
    }

    /**
     * Helper method to build phone numbers via DFS/backtracking.
     *
     * @param current    The current partial phone number
     * @param length     Desired total length
     * @param disallowed Digits that cannot be used
     * @param result     A list collecting all valid phone numbers
     */
    private static void backtrack(String current, int length,
                                  Set<Character> disallowed,
                                  List<String> result) {

        // If we've reached the desired length, add to result
        if (current.length() == length) {
            result.add(current);
            return;
        }

        // Possible digits: 0..9 (as chars), skipping disallowed
        for (char d = '0'; d <= '9'; d++) {
            // skip disallowed
            if (disallowed.contains(d)) {
                continue;
            }
            // skip if same as last digit
            if (!current.isEmpty() && current.charAt(current.length() - 1) == d) {
                continue;
            }
            // build next
            backtrack(current + d, length, disallowed, result);
        }
    }

    /**
     * Simple main method that demonstrates testing without JUnit.
     */
    public static void main(String[] args) {
        // We will define a few tests and manually verify PASS/FAIL.
        // For convenience, we print out the results in full or count them.

        boolean allPassed = true;

        // Test 1: length = 1, disallowed = {}
        // Possible digits are 0..9. But if '4' is used, it must be at the start -> length=1 is always "start"
        // So the valid set is all digits 0..9. That is 10 phone numbers.
        Set<Character> test1Disallowed = new HashSet<>();
        List<String> test1Result = generatePhoneNumbers(1, test1Disallowed);
        if (test1Result.size() == 10) {
            System.out.println("Test 1 PASS. Found " + test1Result.size() + " results: " + test1Result);
        } else {
            allPassed = false;
            System.out.println("Test 1 FAIL. Found " + test1Result.size());
        }

        // Test 2: length = 2, disallowed = { '5' }
        // We skip '5'. If '4' appears, it must be the first digit. Let's see if that logic holds.
        Set<Character> test2Disallowed = new HashSet<>();
        test2Disallowed.add('5');
        List<String> test2Result = generatePhoneNumbers(2, test2Disallowed);
        // We won't do an exact count in code here, but let's do a quick check:
        //   - We disallow '5', so digits = {0,1,2,3,4,6,7,8,9} are allowed.
        //   - No consecutive same digits.
        //   - If '4' is present, it must be in the first position.
        // We'll just print how many we got and do a quick eyeball check.
        System.out.println("Test 2 result count: " + test2Result.size() + " -> " + test2Result);
        // Manually verifying correctness would be next step
        // For automation, we might do a sanity check:
        if (test2Result.contains("54") || test2Result.contains("55")) {
            allPassed = false;
            System.out.println("Test 2 FAIL. Found disallowed usage of '5'.");
        }

        // Test 3: length = 3, disallowed = { '4' }
        // '4' is disallowed entirely. So we can only use {0,1,2,3,5,6,7,8,9}, no consecutive repeats.
        Set<Character> test3Disallowed = new HashSet<>();
        test3Disallowed.add('4');
        List<String> test3Result = generatePhoneNumbers(3, test3Disallowed);
        // Just print summary
        System.out.println("Test 3 result count: " + test3Result.size());
        // Quick check that none contain '4'
        for (String s : test3Result) {
            if (s.contains("4")) {
                allPassed = false;
                System.out.println("Test 3 FAIL. Found a number with '4': " + s);
                break;
            }
        }

        // Large data test (basic example)
        // For example, length=5, no disallowed digits (except none).
        // We won't list them all, just measure time or count. Should still be feasible for a 5-digit phone number.
        long start = System.currentTimeMillis();
        List<String> largeResult = generatePhoneNumbers(5, new HashSet<>());
        long end = System.currentTimeMillis();
        System.out.println("Large test (# of results=" + largeResult.size() + ") took " + (end - start) + "ms");

        // Summarize pass/fail:
        if (allPassed) {
            System.out.println("Overall: ALL TESTS PASSED");
        } else {
            System.out.println("Overall: SOME TEST(S) FAILED");
        }
    }
}
