package com.interview.notes.code.year.y2024.july24.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeSkillPairs5 {

    public static void main(String[] args) {
        // Example 1:
        List<Integer> skills1 = Arrays.asList(6, 2, 3);
        int minSkill1 = 7;
        int maxSkill1 = 10;
        System.out.println(getNumTeams(skills1, minSkill1, maxSkill1)); // Output: 2

        // Example 2:
        List<Integer> skills2 = Arrays.asList(100, 100);
        int minSkill2 = 200;
        int maxSkill2 = 200;
        System.out.println(getNumTeams(skills2, minSkill2, maxSkill2)); // Output: 1
    }

    public static long getNumTeams(List<Integer> skill, int minSkill, int maxSkill) {
        Collections.sort(skill); // Sort the skill list.
        long count = 0;
        int left = 0;
        int right = skill.size() - 1;

        while (left < right) {
            int sum = skill.get(left) + skill.get(right);

            if (sum < minSkill) {
                left++;
            } else if (sum > maxSkill) {
                right--;
            } else {
                // Counting valid pairs
                int leftTemp = left;
                int rightTemp = right;

                // Counting how many times skill[left] can form a valid pair
                while (leftTemp < right && skill.get(leftTemp) + skill.get(right) >= minSkill && skill.get(leftTemp) + skill.get(right) <= maxSkill) {
                    leftTemp++;
                }
                // Counting how many times skill[right] can form a valid pair
                while (left < rightTemp && skill.get(left) + skill.get(rightTemp) >= minSkill && skill.get(left) + skill.get(rightTemp) <= maxSkill) {
                    rightTemp--;
                }

                count += (long) (rightTemp - left + 1) * (right - leftTemp + 1);

                left++;
            }
        }

        return count;
    }
}