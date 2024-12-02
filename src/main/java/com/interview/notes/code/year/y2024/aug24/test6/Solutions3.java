package com.interview.notes.code.year.y2024.aug24.test6;

class Solutions3 {
    public static void answerSolution0() {
        System.out.println("1 3");
    }

    // Solution 1
    public static int countPieces(int length, int stick1, int stick2) {
        // Returns the total number of pieces of length "length" that can be cut
        // from both sticks, taking into account any leftover pieces
        return (stick1 / length) + (stick2 / length);
    }

    public static boolean canFormSquare(int length, int stick1, int stick2) {
        // Returns true if it's possible to form a square with side length "length"
        // by cutting both sticks into pieces of length "length"
        return countPieces(length, stick1, stick2) >= 4;
    }

    public static int solution1(int A, int B) {
        // Try all possible lengths of the square, starting from the longest
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
        // Calculate the number of sticks of length sideLength we can cut from the two sticks
        int numA = A / sideLength;
        int numB = B / sideLength;
        return numA + numB >= 4;
    }

    public static int solution2(int A, int B) {
        // Binary search for the largest possible square
        int lo = 0;
        int hi = B;
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
        // Swap A and B if B is greater than A, so that A is always the larger stick
        if (B > A) {
            int temp = A;
            A = B;
            B = temp;
        }
        // Calculate the longest possible square side that can be constructed from A and B
        int longestSide = Math.max(A / 4, Math.max(Math.min(A / 3, B), B / 2));
        // Return the longest side of the square
        return longestSide;
    }

    public static void answerSolution3() {
        System.out.println("1 5");
    }

    // Solution 4
    public static int solution4(int A, int B) {
        int result = 1;
        // Try all possible stick lengths, starting from the maximum
        for (int length = 1; length < A + B; length++) {
            // Calculate the number of pieces we can get from each stick
            int piecesA = A / length;
            int piecesB = B / length;
            // If we can get at least four pieces from each stick, return the length
            if (piecesA + piecesB >= 4) {
                result = Math.max(result, length);
            }
        }
        // Return the calculated result
        return result;
    }

    public static void answerSolution4() {
        System.out.println("CORRECT");
    }

    // Solution 5
    public static int solution5(int A, int B) {
        // Calculate the maximum number of sticks of length (A/4) that can be obtained from A
        int case1 = A / 4;
        // Calculate the maximum number of sticks of length min(A/3, B) that can be obtained from A and B
        int case2 = Math.min(A / 3, B);
        // Calculate the maximum number of sticks of length (B/2) that can be obtained from B
        int case3 = B / 2;
        // Return the largest number of sticks that can be obtained from the three cases
        return Math.max(case1, Math.max(case2, case3));
    }

    public static void answerSolution5() {
        System.out.println("1 5");
    }

    // Solution 6
    public static int solution6(int A, int B) {
        // Initialize variable to keep track of maximum square size
        int maxSize = 0;
        // Check if it's possible to create 4 sticks of equal length with A and B
        // and update maxSize accordingly
        maxSize = Math.max(maxSize, B / 4);
        // Check if it's possible to create 4 sticks of equal length with B divided
        // into 3 parts and update maxSize accordingly if A is long enough
        if (A >= B / 3) {
            maxSize = Math.max(maxSize, B / 3);
        }
        // Check if it's possible to create 4 sticks of equal length with B divided
        // into 2 parts and update maxSize accordingly if A is long enough
        if (A >= 2 * (B / 2)) {
            maxSize = Math.max(maxSize, B / 2);
        }
        // Check if it's possible to create 4 sticks of equal length with A divided
        // into 3 parts and update maxSize accordingly if B is long enough
        if (B >= A / 3) {
            maxSize = Math.max(maxSize, A / 3);
        }
        // Check if it's possible to create 4 sticks of equal length with A
        // and update maxSize accordingly
        maxSize = Math.max(maxSize, A / 4);
        return maxSize;
    }

    public static void answerSolution6() {
        System.out.println("1 5");
    }

    public static void main(String[] args) {
        Solutions3 sol = new Solutions3();

        System.out.println("Solution 0 Test:");
        answerSolution0();
        System.out.println(sol.solution0(1, 3));

        System.out.println("\nSolution 1 Test:");
        answerSolution1();
        System.out.println(sol.solution1(8, 9));

        System.out.println("\nSolution 2 Test:");
        answerSolution2();
        System.out.println(sol.solution2(8, 9));

        System.out.println("\nSolution 3 Test:");
        answerSolution3();
        System.out.println(sol.solution3(1, 5));

        System.out.println("\nSolution 4 Test:");
        answerSolution4();
        System.out.println(sol.solution4(8, 9));

        System.out.println("\nSolution 5 Test:");
        answerSolution5();
        System.out.println(sol.solution5(1, 5));

        System.out.println("\nSolution 6 Test:");
        answerSolution6();
        System.out.println(sol.solution6(1, 5));
    }

    // Solution 0
    public int solution0(int A, int B) {
        return A / 4;
    }
}