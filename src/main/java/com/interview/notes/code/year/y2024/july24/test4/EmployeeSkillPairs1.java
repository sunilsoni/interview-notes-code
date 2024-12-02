package com.interview.notes.code.year.y2024.july24.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeSkillPairs1 {

    public static void main(String[] args) {
        // Example 1:
        List<Integer> skills1 = Arrays.asList(6, 2, 3);
        int minSkill1 = 7;
        int maxSkill1 = 10;
        System.out.println(getNumTeams(skills1, minSkill1, maxSkill1)); // Output: 3

        // Example 2:
        List<Integer> skills2 = Arrays.asList(100, 100);
        int minSkill2 = 200;
        int maxSkill2 = 200;
        System.out.println(getNumTeams(skills2, minSkill2, maxSkill2)); // Output: 1
    }

    public static long getNumTeams(List<Integer> skill, int minSkill, int maxSkill) {
        Collections.sort(skill); // Sort the skill list
        long count = 0;
        int n = skill.size();

        for (int i = 0; i < n - 1; i++) {
            int left = i + 1, right = n - 1;
            while (left <= right) {
                int sum = skill.get(i) + skill.get(left);
                if (sum >= minSkill && sum <= maxSkill) {
                    count += (right - left + 1);
                    left++;
                } else if (sum < minSkill) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }
}