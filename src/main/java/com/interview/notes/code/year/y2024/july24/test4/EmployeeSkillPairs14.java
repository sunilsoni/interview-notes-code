package com.interview.notes.code.year.y2024.july24.test4;

import java.util.Arrays;
import java.util.List;

public class EmployeeSkillPairs14 {

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
        int n = skill.size();
        int[] skillCount = new int[maxSkill - minSkill + 1];

        // Pre-compute the frequency of each skill level (within the range)
        for (int skillLevel : skill) {
            int index = skillLevel - minSkill;
            if (index >= 0 && index < skillCount.length) {
                skillCount[index]++;
            }
        }

        long count = 0;
        // Iterate through skillCount array (represents skill levels)
        for (int i = 0; i < skillCount.length; i++) {
            // Partner each skill level with its complement to reach the target range
            int complement = maxSkill - i;
            if (complement >= minSkill) {
                // Consider only valid complements within the range
                count += (long) skillCount[i] * skillCount[complement];
            }
        }

        return count;
    }
}