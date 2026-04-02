package com.interview.notes.code.year.y2026.april.assessments.test1;

class Solution
{
    static void test(int id, int actual, int expected)
    {
        System.out.println("Case " + id + ": " + (actual == expected ? "PASS" : "FAIL") + " | got=" + actual + " expected=" + expected);
    }

    public static void main(String[] args)
    {
        Solution s = new Solution();

        test(1, s.solution(new int[]{1, 1, 0, 0, 1}), 4);
        test(2, s.solution(new int[]{0, 1}), 1);
        test(3, s.solution(new int[]{0, 0, 0}), 0);
        test(4, s.solution(new int[]{1}), 0);
        test(5, s.solution(new int[]{1, 0}), 1);
        test(6, s.solution(new int[]{0, 1, 0}), 1);
        test(7, s.solution(new int[]{1, 0, 1}), 1);
        test(8, s.solution(new int[]{1, 0, 0, 0, 1}), 3);
        test(9, s.solution(new int[]{1, 1, 1, 1}), 0);
        test(10, s.solution(new int[]{0, 0, 1, 1, 0, 0}), 4);

        int n = 40000;
        int[] big = new int[n];
        for (int i = 0; i < n; i += 2)
        {
            big[i] = 1;
        }
        int r = s.solution(big);
        System.out.println("Case 11: " + (r >= 0 ? "PASS" : "FAIL") + " | got=" + r);
    }

    public int solution(int[] board)
    {
        int n = board.length, k = 0;
        for (int v : board)
        {
            if (v == 1)
            {
                k++;
            }
        }
        if (k == 0)
        {
            return 0;
        }

        int[] p = new int[k];
        for (int i = 0, j = 0; i < n; i++)
        {
            if (board[i] == 1)
            {
                p[j++] = i;
            }
        }

        int[] left = new int[k + 1];
        for (int i = 0; i < k; i++)
        {
            left[i + 1] = left[i] + p[i] - i;
        }

        int[] right = new int[k + 1];
        for (int i = k - 1; i >= 0; i--)
        {
            right[i] = right[i + 1] + (n - k + i - p[i]);
        }

        int ans = 0;
        for (int a = 0; a <= k; a++)
        {
            ans = Math.max(ans, left[a] + right[a]);
        }
        return ans;
    }
}