package com.interview.notes.code.months.year2023.june23.test4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(10));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", n, n);
        return result;
    }

    private static void generate(List<String> result, String combination, int leftRemaining, int rightRemaining) {
        if (leftRemaining == 0 && rightRemaining == 0) {
            result.add(combination);
            return;
        }

        if (leftRemaining > 0) {
            generate(result, combination + "(", leftRemaining - 1, rightRemaining);
        }

        if (rightRemaining > leftRemaining) {
            generate(result, combination + ")", leftRemaining, rightRemaining - 1);
        }
    }
}
