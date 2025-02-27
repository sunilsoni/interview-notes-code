package com.interview.notes.code.year.y2023.nov23.test3;

public class MemoryManagement {

    public static void main(String[] args) {
        int[] memory = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] queries = {
                {0, 5},  // Try to allocate a block of 5
                {0, 8},  // Try to allocate a block of 8
                {1, 2},  // Try to erase at position 2
                {0, 3}   // Try to allocate a block of 3
        };

        int[] result = solution(memory, queries);
        for (int r : result) {
            System.out.println(r); // Output the result of each query
        }
    }

    static int[] solution(int[] memory, int[][] queries) {
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int queryType = queries[i][0];
            int size = queries[i][1];

            if (queryType == 0) { // alloc
                result[i] = alloc(memory, size);
            } else if (queryType == 1) { // erase
                result[i] = erase(memory, size);
            }
        }

        return result;
    }

    static int alloc(int[] memory, int size) {
        for (int i = 0; i <= memory.length - size; i++) {
            boolean canAllocate = true;
            for (int j = 0; j < size; j++) {
                if (memory[i + j] == 1) {
                    canAllocate = false;
                    break;
                }
            }
            if (canAllocate) {
                for (int j = 0; j < size; j++) {
                    memory[i + j] = 1;
                }
                return i;
            }
        }
        return -1;
    }

    static int erase(int[] memory, int position) {
        if (position >= memory.length || memory[position] == 0) { // Invalid or not allocated
            return -1;
        }

        // Find the start of the allocated block
        int start = position;
        while (start > 0 && memory[start - 1] == 1) {
            start--;
        }

        // Erase the block
        while (start < memory.length && memory[start] == 1) {
            memory[start] = 0;
            start++;
        }

        return position;
    }
}
