package com.interview.notes.code.months.aug24.test6;

class Solutions1 {
    // Solution 0
    public int solution0(int A, int B) {
        return A / 4;
    }

    public static void answerSolution0() {
        System.out.println("1 3");
    }

    // Solution 1
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

    // Solution 2
    public static boolean canBuildSquare(int sideLength, int A, int B) {
        int numA = A / sideLength;
        int numB = B / sideLength;
        return numA + numB >= 4;
    }

    public static int solution2(int A, int B) {
        int lo = 0;
        int hi = Math.min(A, B);
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canBuildSquare(mid, A, B)) {
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

    // Solution 3
    public static int solution3(int A, int B) {
        if (B > A) {
            int temp = A;
            A = B;
            B = temp;
        }
        int longestSide = Math.max(A / 4, Math.max(Math.min(A / 3, B), B / 2));
        return longestSide;
    }

    public static void answerSolution3() {
        System.out.println("8 9");
    }

    // Solution 4
    public static int solution4(int A, int B) {
        int result = 1;
        for (int length = 1; length < A + B; length++) {
            int piecesA = A / length;
            int piecesB = B / length;
            if (piecesA + piecesB >= 4) {
                result = Math.max(result, length);
            }
        }
        return result;
    }

    public static void answerSolution4() {
        System.out.println("1 2");
    }

    // Solution 5
    public static int solution5(int A, int B) {
        int case1 = A / 4;
        int case2 = Math.min(A / 3, B);
        int case3 = B / 2;
        return Math.max(case1, Math.max(case2, case3));
    }

    public static void answerSolution5() {
        System.out.println("5 5");
    }

    // Solution 6
    public static int solution6(int A, int B) {
        int maxSize = 0;
        maxSize = Math.max(maxSize, B / 4);
        if (A >= B / 3) {
            maxSize = Math.max(maxSize, B / 3);
        }
        if (A >= 2 * (B / 2)) {
            maxSize = Math.max(maxSize, B / 2);
        }
        if (B >= A / 3) {
            maxSize = Math.max(maxSize, A / 3);
        }
        maxSize = Math.max(maxSize, A / 4);
        return maxSize;
    }

    public static void answerSolution6() {
        System.out.println("2 3");
    }

    public static void main(String[] args) {
        // Testing Solution 0
        System.out.println("Solution 0: " + new Solutions1().solution0(1, 3)); // Expected: 0
        answerSolution0();

        // Testing Solution 1
        System.out.println("Solution 1: " + solution1(8, 9)); // Expected: 4
        answerSolution1();

        // Testing Solution 2
        System.out.println("Solution 2: " + solution2(8, 9)); // Expected: 4
        answerSolution2();

        // Testing Solution 3
        System.out.println("Solution 3: " + solution3(8, 9)); // Expected: 4
        answerSolution3();

        // Testing Solution 4
        System.out.println("Solution 4: " + solution4(1, 2)); // Expected: 0
        answerSolution4();

        // Testing Solution 5
        System.out.println("Solution 5: " + solution5(5, 5)); // Expected: 1
        answerSolution5();

        // Testing Solution 6
        System.out.println("Solution 6: " + solution6(2, 3)); // Expected: 0
        answerSolution6();
    }
}