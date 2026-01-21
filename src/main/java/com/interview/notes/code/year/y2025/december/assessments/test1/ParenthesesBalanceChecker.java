package com.interview.notes.code.year.y2025.december.assessments.test1;

public class ParenthesesBalanceChecker {

    public static String isBalanced(String S) {
        if (S == null || S.isEmpty()) return "balanced";
        int count = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return "not balanced";
        }
        return count == 0 ? "balanced" : "not balanced";
    }

    public static void main(String[] args) {
        test("", "balanced");
        test("()", "balanced");
        test("(( ))", "balanced");
        test("() () ()", "balanced");
        test("(( () () ))", "balanced");
        test("(", "not balanced");
        test("))", "not balanced");
        test("( )) (( )", "not balanced");
        test("(( ) ))", "not balanced");
        test("() (( )( )) (", "not balanced");
        test("() (( )( )) )", "not balanced");
        test(")) ((", "not balanced");
        test(")() () )(", "not balanced");

        var largeBalanced = "(".repeat(50000) + ")".repeat(50000);
        test(largeBalanced, "balanced");

        var largeUnbalanced = "(".repeat(50000) + ")".repeat(50001);
        test(largeUnbalanced, "not balanced");

        var alternating = "() ".repeat(33333);
        test(alternating, "balanced");
    }

    static void test(String input, String expected) {
        var result = isBalanced(input);
        var status = result.equals(expected) ? "PASS" : "FAIL";
        var display = input.length() > 25 ? input.substring(0, 25) + "..." : input;
        System.out.println(status + " | Input: \"" + display + "\" | Expected: " + expected + " | Got: " + result);
    }
}