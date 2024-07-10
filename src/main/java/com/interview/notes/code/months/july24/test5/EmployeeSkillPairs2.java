package com.interview.notes.code.months.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeSkillPairs2 {

    public static void main(String[] args) {
        // Example 1:
        List<Integer> skills1 = Arrays.asList(6, 2, 3);
        int minSkill1 = 7;
        int maxSkill1 = 10;
        System.out.println(getNumTeams(skills1, minSkill1, maxSkill1)); // Expected output: 2

        // Example 2:
        List<Integer> skills2 = Arrays.asList(100, 100);
        int minSkill2 = 200;
        int maxSkill2 = 200;
        System.out.println(getNumTeams(skills2, minSkill2, maxSkill2)); // Expected output: 1
    }

    public static long getNumTeams(List<Integer> skills, int minSkill, int maxSkill) {
        Collections.sort(skills);
        int n = skills.size();
        long count = 0;
        int left = 0;
        int right = 1;

        while (right < n) {
            if (skills.get(left) + skills.get(right) < minSkill) {
                right++;  // Increase the right pointer to get a larger sum
            } else if (skills.get(left) + skills.get(right) > maxSkill) {
                left++;  // Increase the left pointer to get a smaller sum
                if (left == right) {
                    right++;  // Ensure right is always ahead of left
                }
            } else {
                // When the sum is within the valid range
                // Find the range of valid right indices with the same sum
                int validRight = right;
                while (validRight < n && skills.get(left) + skills.get(validRight) <= maxSkill) {
                    validRight++;
                }
                count += validRight - right;  // Add all these valid pairs
                left++;  // Move left to consider the next new pair
                if (left == right) {
                    right++;  // Ensure right is always ahead of left
                }
            }
        }

        return count;
    }
}
