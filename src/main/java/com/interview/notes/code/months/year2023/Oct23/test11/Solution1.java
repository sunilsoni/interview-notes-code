package com.interview.notes.code.months.year2023.Oct23.test11;

class Solution1 {

    public static void main(String[] args) {
        Solution1 solver = new Solution1();
        System.out.println(solver.solution(5, 8));  // 2
        System.out.println(solver.solution(4, 10));  // 4
        System.out.println(solver.solution(1, 2));  // -1
        System.out.println(solver.solution(10, 5));  // 1
    }

    public int solution(int totalGlasses, int desiredVolume) {
        // If no water is needed, we don't need any glasses.
        if (desiredVolume == 0) return 0;

        // If there are no glasses, it's impossible.
        if (totalGlasses == 0) return -1;

        // canFillVolume[i] will be true if i liters of water can be poured using the glasses.
        boolean[] canFillVolume = new boolean[desiredVolume + 1];
        canFillVolume[0] = true;

        // Update the canFillVolume array using each glass's capacity.
        for (int glassCapacity = 1; glassCapacity <= totalGlasses; glassCapacity++) {
            for (int volume = desiredVolume; volume >= glassCapacity; volume--) {
                canFillVolume[volume] = canFillVolume[volume] || canFillVolume[volume - glassCapacity];
            }
        }

        // If we can't fill the desired volume, return -1.
        if (!canFillVolume[desiredVolume]) return -1;

        // Determine the minimum number of glasses required.
        int remainingVolume = desiredVolume;
        int numberOfGlassesUsed = 0;

        for (int glassCapacity = totalGlasses; glassCapacity >= 1 && remainingVolume > 0; glassCapacity--) {
            if (remainingVolume >= glassCapacity && canFillVolume[remainingVolume - glassCapacity]) {
                remainingVolume -= glassCapacity;
                numberOfGlassesUsed++;
            }
        }

        return numberOfGlassesUsed;
    }
}
