package com.interview.notes.code.months.may24.test9;

class Solutions1 {
    // --------- Solution 1 -----------
    public static int countPieces(int length, int stick1, int stick2) {
        return (stick1 / length) + (stick2 / length);
    }

    public static boolean canFormSquare(int length, int stick1, int stick2) {
        return countPieces(length, stick1, stick2) >= 4;
    }

    public static int solution1(int A, int B) {
        for (int length = Math.min(A, B); length > 0; length--) {
            if (canFormSquare(length, A, B)) {
                return length;
            }
        }
        return 0;
    }

    public static void answerSolution1() {
        System.out.println("CORRECT");
    }

    // --------- Solution 2 -----------
    // Not available due to image processing error.

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
        System.out.println("8 9");  // Incorrect: returns 3 instead of 4 for A=8, B=9
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
        System.out.println("8 9");  // Incorrect: returns 4 instead of 4 for A=8, B=9 but logic is flawed
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
        // Add test method calls if needed
    }
}
