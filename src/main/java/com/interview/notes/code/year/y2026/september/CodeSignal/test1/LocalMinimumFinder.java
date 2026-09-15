package com.interview.notes.code.year.y2026.september.CodeSignal.test1;

import java.util.stream.IntStream;

public class LocalMinimumFinder {

    static int solution(int[] numbers) {
        return IntStream.range(1, numbers.length - 1)
                .filter(i -> numbers[i] < numbers[i - 1] && numbers[i] < numbers[i + 1])
                .findFirst()
                .orElse(-1);
    }

    static void test(int[] numbers, int expected) {
        System.out.println(solution(numbers) == expected ? "PASS" : "FAIL");
    }

    static void main(String[] args) {
        test(new int[]{1, 2, 3, 4}, -1);
        test(new int[]{3, 2, 1, 3, 2, 3}, 2);
        test(new int[]{1}, -1);
        test(new int[]{3, 1, 3}, 1);
        test(new int[]{1, 1, 1}, -1);
        test(new int[]{2, 1}, -1);

        int[] large = new int[1_000_000];
        large[500_000] = -1;
        test(large, 500_000);
    }
}