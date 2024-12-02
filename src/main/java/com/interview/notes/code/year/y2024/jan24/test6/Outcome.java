package com.interview.notes.code.year.y2024.jan24.test6;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Vehicle Parking Problem
 * Sofia was delayed in reaching the office and wanted to park the car close to the entrance because she needed to punch quickly into a biometric system for attendance.
 * She saw a display board at the parking entrance that gave the occupancy status of the parking lot.
 * Based on the board, she would park the car quickly and punch into the attendance system.
 * The parking slots are as follows:
 * There are four basements -- B1, B2, B3, and B4. The number of cars that can be parked in the basements B1, B2, B3, and B4 are 100, 200,300, and 500. The cars parked in the corresponding basements are N1, N2, N3, and N4, and the distances between the basements and the entrances are D1, D2, D3, and D4. The values of N1, N2, N3, N4, and D1, D2, D3, and D4 are displayed on the giant display board at the mall entrance.
 * [Assume that no other car is in the queue to get parked and no other car leaves at this time].
 * Input
 * In the second line 4 integers corresponding to N1, N2, N3, and N4.
 * In the fourth line 4 integers corresponding to D1, D2, D3, and D4.
 * WHERE N1, N2, N3, and N4 correspond to the cars parked in the basements B1, B2, B3, and B4.
 * WHERE D1, D2, D3, and D4 correspond to the distance between the basements and the entrance.
 * Output
 * The output consists of a string that is either 'B1' or
 * 'B2' or 'B3' or 'B4 or 'EXIT.' EXIT corresponds to external parking on the adjacent ground.
 * <p>
 * <p>
 * Output
 * The output consists of a string that is either 'B1' or
 * 'B2' or 'B3' or 'B4' or 'EXIT.' EXIT corresponds to external parking on the adjacent ground.
 * Example#1
 * Input:
 * 4
 * 4
 * 100 50 40 40
 * 5657
 * Output:
 * B3
 * Explanation:
 * In this example, the first preference is given to B1 and B3 because those slots are closer to the entrance. But B1's basement is fully occupied, so you need to move to the next slot, i.e., B3. In B3, the total number of slots is 300, but currently, only 40 slots are occupied, so B3 is allotted.
 * Example#2
 * Input:
 * 4
 * 100 200 300 500
 * 4
 * 56 8 3
 * Output:
 * EXIT
 * Explanation:
 * In this example, all parking slots are occupied. So, they printed EXIT.
 */
class Outcome {

    //Working
    public static String solution(List<Integer> Narr, List<Integer> Darr) {
        int[] capacities = {100, 200, 300, 500};
        int minDistanceIndex = -1;
        int minDistanceValue = Integer.MAX_VALUE;

        for (int i = 0; i < capacities.length; i++) {
            if (Narr.get(i) < capacities[i]) {
                if (Darr.get(i) < minDistanceValue) {
                    minDistanceValue = Darr.get(i);
                    minDistanceIndex = i;
                }
            }
        }

        if (minDistanceIndex == -1) {
            return "EXIT";
        } else {
            return "B" + (minDistanceIndex + 1);
        }
    }

    public static void main(String[] args) {
        // Test cases from the examples provided
        System.out.println(solution(new ArrayList<>(List.of(100, 50, 40, 40)), new ArrayList<>(List.of(5, 6, 5, 7))));
        System.out.println(solution(new ArrayList<>(List.of(100, 200, 300, 500)), new ArrayList<>(List.of(5, 6, 8, 3))));
    }
}
