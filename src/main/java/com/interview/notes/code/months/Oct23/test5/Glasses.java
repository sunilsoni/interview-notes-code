package com.interview.notes.code.months.Oct23.test5;

public class Glasses {
    public static void main(String[] args) {
        Glasses solution = new Glasses();

        int result1 = solution.solution(5, 8);
        System.out.println(result1); // 2

        int result2 = solution.solution(4, 10);
        System.out.println(result2); // 4

        int result3 = solution.solution(1, 2);
        System.out.println(result3); // -1

        int result4 = solution.solution(10, 5);
        System.out.println(result4); // 1
    }

    public int solution(int N, int K) {
        int[] glasses = new int[N];
        for (int i = 0; i < N; i++) {
            glasses[i] = i + 1;
        }

        //Arrays.sort(glasses, Collections.reverseOrder());

        int glassesUsed = 0;
        while (K > 0) {
            int largestGlass = glasses[0];
            if (largestGlass > K) {
                return -1;
            }

            K -= largestGlass;
            glassesUsed++;
        }

        return glassesUsed;
    }
}
