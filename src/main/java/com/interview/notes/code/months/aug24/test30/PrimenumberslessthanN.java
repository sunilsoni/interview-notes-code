package com.interview.notes.code.months.aug24.test30;

/*


Prime numbers less than N
You are given a non-negative integer N. Your task is to write a program that can print the number of prime numbers less than N.
Input
The input contains an integer N, representing the non-negative integer.
Output
Print the number of prime numbers less than N.
Constraints
0 <= N<= 5 * 106
Example #1
Input
10
Output
4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
Example #2
Input
0
Output
Explanation: There are no prime numbers less than



 */
public class PrimenumberslessthanN {
    public static int solve(int N) {
        if (N <= 2) {
            return 0;
        }

        boolean[] isComposite = new boolean[N];
        int count = 0;

        for (int i = 2; i < N; i++) {
            if (!isComposite[i]) {
                count++;
                for (long j = (long) i * i; j < N; j += i) {
                    isComposite[(int) j] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(solve(10)); // Expected output: 4
        System.out.println(solve(0));  // Expected output: 0
        System.out.println(solve(2));  // Expected output: 0
        System.out.println(solve(20)); // Expected output: 8
        System.out.println(solve(100)); // Expected output: 25
        System.out.println(solve(1000000)); // Expected output: 78498
    }
}
