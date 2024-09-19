package com.interview.notes.code.months.sept24.test5;

class CanPrepareRecipe {
    public static void main(String[] args) {
        CanPrepareRecipe solution = new CanPrepareRecipe();

        // Test case 1
        String[] A1 = {"toast", "bread", "breada", "cheese"};
        String S1 = "abcdeeehrs";
        int result1 = solution.solution(A1, S1);
        System.out.println("Test case 1: " + (result1 == 2 ? "PASS" : "FAIL"));

        // Test case 2
        String[] A2 = {"az", "azz", "zza", "zzz", "zzzz"};
        String S2 = "azzz";
        int result2 = solution.solution(A2, S2);
        System.out.println("Test case 2: " + (result2 == 4 ? "PASS" : "FAIL"));

        // Additional test case: empty ingredients
        String[] A3 = {"a", "b", "c"};
        String S3 = "";
        int result3 = solution.solution(A3, S3);
        System.out.println("Test case 3 (empty ingredients): " + (result3 == 0 ? "PASS" : "FAIL"));

        // Additional test case: no matching recipes
        String[] A4 = {"xyz", "abc"};
        String S4 = "def";
        int result4 = solution.solution(A4, S4);
        System.out.println("Test case 4 (no matching recipes): " + (result4 == 0 ? "PASS" : "FAIL"));
    }

    public int solution(String[] A, String S) {
        int recipeCount = 0;
        int[] availableIngredients = getFrequencyMap(S);

        for (String recipe : A) {
            int[] recipeIngredients = getFrequencyMap(recipe);
            if (canPrepareRecipe(recipeIngredients, availableIngredients)) {
                recipeCount++;
            }
        }

        return recipeCount;
    }

    private int[] getFrequencyMap(String s) {
        int[] frequencyMap = new int[26];
        for (char c : s.toCharArray()) {
            frequencyMap[c - 'a']++;
        }
        return frequencyMap;
    }

    private boolean canPrepareRecipe(int[] recipe, int[] available) {
        for (int i = 0; i < 26; i++) {
            if (recipe[i] > available[i]) {
                return false;
            }
        }
        return true;
    }
}
