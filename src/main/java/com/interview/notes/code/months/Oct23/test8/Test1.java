package com.interview.notes.code.months.Oct23.test8;

public class Test1 {
    public static void main(String[] args) {

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
