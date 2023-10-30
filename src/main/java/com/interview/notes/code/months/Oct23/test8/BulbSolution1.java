package com.interview.notes.code.months.Oct23.test8;

public class BulbSolution1 {

    public static void main(String[] args) {
        BulbSolution1 bulbSolution = new BulbSolution1();

        int[] example1 = {2, 1, 3, 5, 4};
        int[] example2 = {2, 3, 4, 1, 5};
        int[] example3 = {1, 3, 4, 2, 5};

        System.out.println("Example 1 result: " + bulbSolution.solution(example1));
        System.out.println("Example 2 result: " + bulbSolution.solution(example2));
        System.out.println("Example 3 result: " + bulbSolution.solution(example3));
    }

    public int solution(int[] A) {
        int moments = 0;
        int maxBulb = 0;
        int turnedOn = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxBulb) {
                maxBulb = A[i];
            }
            turnedOn++;

            if (maxBulb == turnedOn) {
                moments++;
            }
        }

        return moments;
    }
}
