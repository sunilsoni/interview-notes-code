package com.interview.notes.code.months.aug24.test6;

public class Solutions {

    public static void answerSolution0() {
        System.out.println("1 3");
    }

    public static String solution1(int A, int B) {
        if (A >= 1 && A <= 9 && B >= 1 && B <= 9) {
            return "CORRECT";
        } else {
            return "INCORRECT";
        }
    }

    public static void answerSolution1() {
        System.out.println("CORRECT");
    }

    public static String solution2(int A, int B) {
        if (A >= 1 && A <= 9 && B >= 1 && B <= 9) {
            return "CORRECT";
        } else {
            return "INCORRECT";
        }
    }

    public static void answerSolution2() {
        System.out.println("CORRECT");
    }

    public static void answerSolution3() {
        System.out.println("5 5");
    }

    public static String solution4(int A, int B) {
        if (A >= 1 && A <= 9 && B >= 1 && B <= 9) {
            return "CORRECT";
        } else {
            return "INCORRECT";
        }
    }

    public static void answerSolution4() {
        System.out.println("CORRECT");
    }

    public static void answerSolution5() {
        System.out.println("5 5");
    }

    public static void answerSolution6() {
        System.out.println("5 5");
    }

    public static void main(String[] args) {
        Solutions solutions = new Solutions();

        // Test Solution 0
        System.out.println("Testing Solution 0:");
        System.out.print("Expected output: 1 3, Actual output: ");
        answerSolution0();
        System.out.println("Actual result for 1 3: " + solutions.solution0(1, 3));
        System.out.println();

        // Test Solution 1
        System.out.println("Testing Solution 1:");
        System.out.print("Expected output: CORRECT, Actual output: ");
        answerSolution1();
        System.out.println("Test case 8 9: " + solution1(8, 9));
        System.out.println("Test case 1 8: " + solution1(1, 8));
        System.out.println();

        // Test Solution 2
        System.out.println("Testing Solution 2:");
        System.out.print("Expected output: CORRECT, Actual output: ");
        answerSolution2();
        System.out.println("Test case 8 9: " + solution2(8, 9));
        System.out.println("Test case 1 8: " + solution2(1, 8));
        System.out.println();

        // Test Solution 3
        System.out.println("Testing Solution 3:");
        System.out.print("Expected output: 5 5, Actual output: ");
        answerSolution3();
        System.out.println("Test case 5 5: " + solutions.solution3(5, 5));
        System.out.println("Test case 1 2: " + solutions.solution3(1, 2));
        System.out.println();

        // Test Solution 4
        System.out.println("Testing Solution 4:");
        System.out.print("Expected output: CORRECT, Actual output: ");
        answerSolution4();
        System.out.println("Test case 8 9: " + solution4(8, 9));
        System.out.println("Test case 1 8: " + solution4(1, 8));
        System.out.println();

        // Test Solution 5
        System.out.println("Testing Solution 5:");
        System.out.print("Expected output: 5 5, Actual output: ");
        answerSolution5();
        System.out.println("Test case 5 5: " + solutions.solution5(5, 5));
        System.out.println("Test case 1 10: " + solutions.solution5(1, 10));
        System.out.println();

        // Test Solution 6
        System.out.println("Testing Solution 6:");
        System.out.print("Expected output: 5 5, Actual output: ");
        answerSolution6();
        System.out.println("Test case 5 5: " + solutions.solution6(5, 5));
        System.out.println("Test case 1 10: " + solutions.solution6(1, 10));
    }

    public String solution0(int A, int B) {
        return A + " " + B;
    }

    public String solution3(int A, int B) {
        if (A == B) {
            return "CORRECT";
        } else {
            return "INCORRECT";
        }
    }

    public String solution5(int A, int B) {
        if (A < 1 || A > 9 || B < 1 || B > 9) {
            return "INCORRECT";
        }
        return "CORRECT";
    }

    public String solution6(int A, int B) {
        if (A < 1 || A > 9 || B < 1 || B > 9) {
            return "INCORRECT";
        }
        return A + " " + B;
    }
}