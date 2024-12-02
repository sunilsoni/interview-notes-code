package com.interview.notes.code.year.y2024.july24.test3;

/*

Square Numbers
You are given two numbers, M and N such that M<=
N. Your task is to find the count of every integer Kin the interval of numbers between M and N (inclusive) that satisfies the following conditions:
• Kis the square of an integer.
• In the number K, every digit holds the following property: d1 < d2 > d3 < d4 and so on, where di represents a digit of K.
Input
The first line of input contains an integer M.
The second line of input contains an integer N.
Output
Print the count of numbers in the given interval that satisfies the given requirements.
Constraints
1 <= M<= N<= 1011
Example #1
Input
121
121
Output
1
Explanation: There is one number in the given interval -> 121:
• 112 = 121
• 1 < 2>1

Example #1
Input
121
121
Output
1
Explanation: There is one number in the given interval -> 121:
• 112 = 121
• 1 < 2 > 1
Example #2
Input
40
70
Output
1
Explanation: There are two perfect squares in the given interval, 49 and 64. 49 satisfies the given requirements as 4 < 9, while 64 does not. The first digit of 64 is not less than the second digit.

 */
public class SquareNumbers {
    public static int solve(long M, long N) {
        // Calculate the starting and ending square roots
        long start = (long) Math.ceil(Math.sqrt(M));
        long end = (long) Math.floor(Math.sqrt(N));

        int count = 0;
        for (long i = start; i <= end; i++) {
            long square = i * i;
            if (square >= M && square <= N && hasZigzagPattern(square)) {
                count++;
            }
        }
        return count;
    }

    // Helper method to check if a number has the zigzag digit pattern
    private static boolean hasZigzagPattern(long num) {
        String s = Long.toString(num);
        for (int i = 1; i < s.length(); i++) {
            int prev = s.charAt(i - 1) - '0';
            int curr = s.charAt(i) - '0';
            // Check the pattern based on the index (even index: <, odd index: >)
            if ((i % 2 == 1 && prev >= curr) || (i % 2 == 0 && prev <= curr)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(solve(121, 121));
        System.out.println(solve(40, 70));
    }
}
