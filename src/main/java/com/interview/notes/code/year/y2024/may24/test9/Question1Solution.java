package com.interview.notes.code.year.y2024.may24.test9;

public class Question1Solution {

    // --------- Solution 0 -----------
    public static int solution0(int A, int B) {
        return A / 4;
    }

    public static void answerSolution0() {
        System.out.println("1 3");
    }

    // --------- Solution 1 -----------
    public static int solution1(int A, int B) {
        int maxLength = 0;
        for (int length = 1; length <= Math.min(A, B); length++) {
            if (((A / length) + (B / length)) >= 4) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    public static void answerSolution1() {
        System.out.println("CORRECT");
    }

    // --------- Solution 2 -----------
    public static int solution2(int A, int B) {
        int lo = 0;
        int hi = Math.min(A, B);
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (((A / mid) + (B / mid)) >= 4) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return hi;
    }

    public static void answerSolution2() {
        System.out.println("CORRECT");
    }

    // --------- Solution 3 -----------
    public static int solution3(int A, int B) {
        if (B > A) {
            int temp = A;
            A = B;
            B = temp;
        }
        return Math.max(A / 4, Math.max(Math.min(A / 3, B), B / 2));
    }

    public static void answerSolution3() {
        System.out.println("8 9");
    }

    // --------- Solution 4 -----------
    public static int solution4(int A, int B) {
        int result = 0;
        for (int length = Math.min(A, B); length > 0; length--) {
            int piecesA = A / length;
            int piecesB = B / length;
            if (piecesA + piecesB >= 4) {
                result = Math.max(result, length);
            }
        }
        return result;
    }

    public static void answerSolution4() {
        System.out.println("CORRECT");
    }

    // --------- Solution 5 -----------
    public static int solution5(int A, int B) {
        int case1 = A / 4;
        int case2 = Math.min(A / 3, B);
        int case3 = B / 2;
        return Math.max(case1, Math.max(case2, case3));
    }

    public static void answerSolution5() {
        System.out.println("8 9");
    }

    // --------- Solution 6 -----------
    public static int solution6(int A, int B) {
        int maxSize = 0;
        for (int length = 1; length <= Math.min(A, B); length++) {
            if ((A / length) + (B / length) >= 4) {
                maxSize = Math.max(maxSize, length);
            }
        }
        return maxSize;
    }

    public static void answerSolution6() {
        System.out.println("CORRECT");
    }

    public static void main(String[] args) {
        // Testing each solution
        System.out.println("Solution 0 output: " + solution0(4, 16));  // Should output 1
        System.out.println("Solution 1 output: " + solution1(8, 9));   // Should output 4
        System.out.println("Solution 2 output: " + solution2(8, 9));   // Should output 4
        System.out.println("Solution 3 output: " + solution3(8, 9));   // Should output 3
        System.out.println("Solution 4 output: " + solution4(8, 9));   // Should output 4
        System.out.println("Solution 5 output: " + solution5(8, 9));   // Should output 4
        System.out.println("Solution 6 output: " + solution6(8, 9));   // Should output 4
    }
}

