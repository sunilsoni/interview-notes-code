package com.interview.notes.code.year.y2024.june24.test10;

import java.util.stream.IntStream;

/*
1)	Your task is to output all numbers that have a certain number of digits where the following constraints apply.
The digits in the number must be in ascending order. A digit must be of the same or greater value than the preceding digit.
So for a five digit number 12345; 11222 might be valid solutions but 12354; 11131 will never be valid.
Leading zeroes are not allowed i.e. 00123; 01234 are not valid solutions. Your output must be in ascending order.
The maximum execution time for this challenge is 60 seconds. INPUT int: an integer.
The required number of digits for the output values.
The input value will be between 1 and 9.
OUTPUT string: a string containing all the solutions in ascending order, separated by comma.
Example INPUT 2
OUTPUT 11,12,13,14,15,16,17,18,19,22,23,24,25,26,27,28,29,33,34,35,36,37,38,39,44,45,46,47,48,49,55,56,57,58,59,66,67,68,69,77,78,79,88,89,99
 */
public class IsAscending {

    public static void main(String[] args) {
        IsAscending s = new IsAscending();

        // Test case 1: Input 0
        String output1 = s.run(0);
        System.out.println("Test case 1: " + (output1.equals("") ? "Passed" : "Failed"));

        // Test case 2: Input 1
        String output2 = s.run(1);
        System.out.println("Test case 2: " + (output2.equals("1,2,3,4,5,6,7,8,9") ? "Passed" : "Failed"));

        // Test case 3: Input 2
        String output3 = s.run(2);
        System.out.println("Test case 3: " + (output3.equals("12,13,14,15,16,17,18,19,23,24,25,26,27,28,29,34,35,36,37,38,39,45,46,47,48,49,56,57,58,59,67,68,69,78,79,89") ? "Passed" : "Failed"));

        // Test case 4: Input 3
        String output4 = s.run(3);
        System.out.println("Test case 4: " + (output4.equals("123,124,125,126,127,128,129,134,135,136,137,138,139,145,146,147,148,149,156,157,158,159,167,168,169,178,179,189,234,235,236,237,238,239,245,246,247,248,249,256,257,258,259,267,268,269,278,279,289,345,346,347,348,349,356,357,358,359,367,368,369,378,379,389,456,457,458,459,467,468,469,478,479,489,567,568,569,578,579,589,678,679,689,789") ? "Passed" : "Failed"));

        // Test case 5: Input 4
        String output5 = s.run(4);
        System.out.println("Test case 5: " + (output5.equals("1234,1235,1236,1237,1238,1239,1245,1246,1247,1248,1249,1256,1257,1258,1259,1267,1268,1269,1278,1279,1289,1345,1346,1347,1348,1349,1356,1357,1358,1359,1367,1368,1369,1378,1379,1389,1456,1457,1458,1459,1467,1468,1469,1478,1479,1489,1567,1568,1569,1578,1579,1589,1678,1679,1689,1789,2345,2346,2347,2348,2349,2356,2357,2358,2359,2367,2368,2369,2378,2379,2389,2456,2457,2458,2459,2467,2468,2469,2478,2479,2489,2567,2568,2569,2578,2579,2589,2678,2679,2689,2789,3456,3457,3458,3459,3467,3468,3469,3478,3479,3489,3567,3568,3569,3578,3579,3589,3678,3679,3689,3789,4567,4568,4569,4578,4579,4589,4678,4679,4689,4789,5678,5679,5689,5789,6789") ? "Passed" : "Failed"));
    }

    public String run(int input) {
        if (input == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int start = (int) Math.pow(10, input - 1);
        int end = (int) Math.pow(10, input) - 1;

        IntStream.rangeClosed(start, end)
                .mapToObj(Integer::toString)
                .filter(this::isAscending)
                .forEach(num -> result.append(result.length() == 0 ? "" : ",").append(num));

        return result.toString();
    }

    private boolean isAscending(String number) {
        for (int i = 0; i < number.length() - 1; i++) {
            if (number.charAt(i) >= number.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
