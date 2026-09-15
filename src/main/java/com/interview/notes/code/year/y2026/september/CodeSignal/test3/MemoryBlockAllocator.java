package com.interview.notes.code.year.y2026.september.CodeSignal.test3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MemoryBlockAllocator {

    static int[] solution(int[] memory, int[][] queries) {
        int[] blocks = IntStream.range(0, memory.length)
                .map(i -> memory[i] == 1 ? -1 : 0)
                .toArray();

        int[] result = new int[queries.length];
        int id = 0;

        for (int q = 0; q < queries.length; q++) {
            int type = queries[q][0];
            int x = queries[q][1];

            if (type == 0) {
                int start = IntStream.rangeClosed(0, blocks.length - x)
                        .filter(i -> IntStream.range(i, i + x).allMatch(j -> blocks[j] == 0))
                        .findFirst()
                        .orElse(-1);

                result[q] = start;

                if (start >= 0) {
                    Arrays.fill(blocks, start, start + x, ++id);
                }
            } else {
                int count = 0;

                for (int i = 0; i < blocks.length; i++) {
                    if (blocks[i] == x) {
                        blocks[i] = 0;
                        count++;
                    }
                }

                result[q] = count == 0 ? -1 : count;
            }
        }

        return result;
    }

    static void test(int[] memory, int[][] queries, int[] expected) {
        System.out.println(
                Arrays.equals(solution(memory, queries), expected) ? "PASS" : "FAIL"
        );
    }

    static void main(String[] args) {
        test(
                new int[]{0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                new int[][]{{0, 2}, {0, 1}, {0, 1}, {1, 2}, {1, 4}, {0, 4}},
                new int[]{2, 0, 4, 1, -1, -1}
        );

        test(
                new int[]{0},
                new int[][]{{0, 1}, {1, 1}},
                new int[]{0, 1}
        );

        test(
                new int[]{1},
                new int[][]{{0, 1}, {1, 1}},
                new int[]{-1, -1}
        );

        test(
                new int[]{0, 0, 0},
                new int[][]{{0, 2}, {0, 2}, {1, 1}, {0, 3}},
                new int[]{0, -1, 2, 0}
        );

        test(
                new int[]{0, 0, 1, 0, 0},
                new int[][]{{0, 2}, {0, 2}, {1, 1}, {0, 3}},
                new int[]{0, 3, 2, -1}
        );

        test(
                new int[]{0, 0},
                new int[][]{{0, 2}, {0, 1}, {1, 1}, {0, 1}, {1, 2}},
                new int[]{0, -1, 2, 0, 1}
        );

        test(
                new int[300],
                new int[][]{{0, 300}, {1, 1}, {0, 299}},
                new int[]{0, 300, 0}
        );
    }
}