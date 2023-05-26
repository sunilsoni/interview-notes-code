package com.interview.notes.code.java8;

import java.util.Scanner;

/**
 * Predict the Number using java
 * <p>
 * Programming challenge description:
 * <p>
 * The example sequence 011212201220200112 ... is constructed as follows: 1. The first element in the sequence is 0. 2. For each iteration, repeat the following action: take a copy of the entire current sequence, replace 0 with 1, 1 with 2, and 2 with 0, and place it at the end of the current sequence. E.g. 0 9 01 9 0112 9 01121220... Create an algorithm which determines what number is at the Nth position in the sequence (using 0-based indexing).
 * <p>
 * Input: Your program should read lines from standard input. Each line contains an integer N such that 0 <= N <= 3000000000.
 * <p>
 * <p>
 * Output: Print out the number which is at the Nth position in the sequence.
 * <p>
 * Test Input 5
 * <p>
 * Expected Output 2
 */
public class Sequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(getNumberAtPosition(N));
    }

    public static int getNumberAtPosition(int N) {
        int[] sequence = {0};
        while (N >= sequence.length) {
            int[] newSequence = new int[sequence.length * 2];
            for (int i = 0; i < sequence.length; i++) {
                newSequence[i] = sequence[i];
                newSequence[i + sequence.length] = (sequence[i] + 1) % 3;
            }
            sequence = newSequence;
        }
        return sequence[N];
    }
}
