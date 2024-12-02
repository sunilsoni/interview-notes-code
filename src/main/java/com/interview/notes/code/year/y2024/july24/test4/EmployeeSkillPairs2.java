package com.interview.notes.code.year.y2024.july24.test4;

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

    public static long getNumTeams(List<Integer> skill, int minSkill, int maxSkill) {
        Collections.sort(skill);
        long count = 0;
        int left = 0;
        int right = skill.size() - 1;

        while (left < right) {
            int sum = skill.get(left) + skill.get(right);
            if (sum < minSkill) {
                left++; // Increase the sum by moving the left pointer to the right
            } else if (sum > maxSkill) {
                right--; // Decrease the sum by moving the right pointer to the left
            } else {
                // When sum is within the range, count all valid pairs with current left and right
                int countLeft = 1, countRight = 1;

                // Count duplicates at the left
                while (left + 1 < right && skill.get(left).equals(skill.get(left + 1))) {
                    countLeft++;
                    left++;
                }

                // Count duplicates at the right
                while (right - 1 > left && skill.get(right).equals(skill.get(right - 1))) {
                    countRight++;
                    right--;
                }

                // If left and right meet and are the same, adjust the count
                if (left == right) {
                    count += (countLeft * (countLeft - 1)) / 2;
                } else {
                    count += countLeft * countRight;
                }

                // Move pointers past the current numbers
                left++;
                right--;
            }
        }

        return count;
    }
}
