package com.interview.notes.code.months.Oct23.test8;

public class BulbMoments {

    public static void main(String[] args) {
        BulbMoments solution = new BulbMoments();

        int[] testArray1 = {2, 1, 3, 5, 4};
        int[] testArray2 = {2, 3, 4, 1, 5};
        int[] testArray3 = {1, 3, 4, 2, 5};

        System.out.println("Test Array 1 result: " + solution.countShiningMoments(testArray1));
        System.out.println("Test Array 2 result: " + solution.countShiningMoments(testArray2));
        System.out.println("Test Array 3 result: " + solution.countShiningMoments(testArray3));
    }

    public int countShiningMoments(int[] bulbs) {
        int shiningMoments = 0;       // To count the moments every turned on bulb shines
        int highestTurnedOnBulb = 0; // To track the highest bulb number that was turned on so far
        int bulbsTurnedOn = 0;       // To count the number of bulbs that have been turned on so far

        for (int i = 0; i < bulbs.length; i++) {
            if (bulbs[i] > highestTurnedOnBulb) {
                highestTurnedOnBulb = bulbs[i];
            }
            bulbsTurnedOn++;

            // If the number of turned on bulbs matches the highest bulb number that was turned on,
            // it means all the bulbs till the highest number are turned on and shining.
            if (highestTurnedOnBulb == bulbsTurnedOn) {
                shiningMoments++;
            }
        }

        return shiningMoments;
    }
}
