package com.interview.notes.code.year.y2025.feb.Amazon.test2;

import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * maximizeGroups: Returns the maximum number of batches (groups)
     * that can be formed from the given list of product counts.
     * Each batch i requires i distinct products and each product type
     * can contribute at most one item per batch.
     * <p>
     * The key observation is that for any candidate m (number of batches)
     * we must have:
     * <p>
     * sum_{i=1}^{n} min(products[i], m) >= m*(m+1)/2.
     * <p>
     * Since even if every product is huge the maximum contribution
     * is m per product, the total is at most n*m. Thus we must have
     * n*m >= m*(m+1)/2 which implies m <= 2*n - 1.
     * <p>
     * We use binary search over m in the interval [0, 2*n-1].
     */
    public static int maximizeGroups(List<Integer> products) {
        int n = products.size();
        int left = 0;
        int right = 2 * n - 1;  // maximum possible m
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            // Sum the contribution of each product for candidate mid
            // (each product contributes min(p, mid) items).
            for (int p : products) {
                // Using long for safety; note that if p >= mid, contribution is mid.
                sum += (p < mid ? p : mid);
            }

            long required = ((long) mid * (mid + 1)) / 2;
            if (sum >= required) {
                answer = mid;
                left = mid + 1; // try for a larger m
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /**
     * A simple helper method that runs a test case.
     * It prints PASS/FAIL along with expected and actual values.
     */
    public static void runTest(int testId, List<Integer> products, int expected) {
        int result = maximizeGroups(products);
        if (result == expected) {
            System.out.println("Test " + testId + " PASSED. Expected and Got: " + result);
        } else {
            System.out.println("Test " + testId + " FAILED. Expected: " + expected + ", Got: " + result);
        }
    }

    /**
     * main: runs several test cases including the sample ones and
     * the three longer test cases that were reported as problematic.
     * <p>
     * For very long input arrays we only show a sample prefix of the list.
     */
    public static void main(String[] args) {
        // Sample Case 0
        List<Integer> test0 = Arrays.asList(1, 2, 7);
        // Expected: 3 batches (as explained in the problem statement)
        runTest(0, test0, 3);

        // Sample Case 1
        List<Integer> test1 = Arrays.asList(1, 2, 8, 9);
        // Expected: 4 batches
        runTest(1, test1, 4);

        // Provided Test Case A:
        // products: [1, 1, 8, 11, 11, 12, 12]
        // (You can verify by hand that the answer should be 9.)
        List<Integer> testA = Arrays.asList(1, 1, 8, 11, 11, 12, 12);
        runTest(2, testA, 9);

        // Provided Test Case B:
        // (This is a long list – here we show only a prefix.
        //  In a real test the list would be read fully.)
        List<Integer> testB = Arrays.asList(
                46, 250, 77, 488, 212, 28, 379, 50, 332, 526308251, 51, 187563989, 32, 10, 217,
                445, 13, 69, 24, 647819135, 110, 32, 247259207, 34, 83, 137, 16, 476, 893922480,
                129933738, 184, 12, 230, 39, 43, 343, 91, 446, 62, 41, 418, 395741531, 157, 336,
                649211546, 38, 57, 80, 15703084, 221663445, 196105046, 789300684, 426686168, 39,
                63, 27, 63, 744605921, 66, 209, 83, 246, 50, 151949631, 398, 465, 365, 86, 483,
                216, 899536810, 33, 202, 46, 63, 336, 255, 338, 213, 597758591, 45, 7285260, 9,
                716279149, 27, 444, 13, 416, 246314883, 88, 525321870, 144, 893362512, 83, 303, 41,
                35, 15, 51, 394, 34, 284525620, 467, 115, 51, 448, 33, 51, 278, 374, 14, 485, 68,
                50, 17, 2, 82, 51, 865703263, 964243551, 226, 39, 409957795, 8, 130, 58, 98, 97,
                832320497, 90, 44, 18, 741945971, 80, 39, 0, 335, 77, 34, 408, 21, 12, 15, 90,
                62, 78, 443578029, 437, 69, 86, 112907002, 366144927, 99455182, 320, 494, 29474033,
                348, 39, 7, 501297827, 523167414, 480, 354, 148, 87, 284, 25, 31, 120, 76, 84,
                995127082, 6, 99, 102, 135, 436428232, 165, 461938885, 61, 52, 780405518, 396300761,
                37, 4, 348, 26, 25, 3, 99, 351, 226, 54, 73, 57, 315, 994740633, 39, 386, 563212128,
                94, 614385584, 58, 264058306, 86, 370, 541820843, 373, 488, 964424188, 386, 207,
                463842041, 222741617, 483967527, 223, 73, 395, 68, 96, 458, 193, 16, 349665051, 17,
                2, 99, 74, 59, 21, 640823989, 13, 3, 50, 13, 579666926, 18, 10349676, 90, 438,
                379136671, 15, 5, 381599663, 33, 56, 480, 42, 133, 7, 443, 69, 5, 295, 125, 406,
                130, 66, 63, 53, 84313566, 75, 88500734, 234, 262, 13, 27, 67, 79, 31, 300, 252,
                11, 71, 53, 474, 37, 22, 67, 604581538, 973453502, 35, 37, 65, 851640568, 727333825,
                86, 835507004, 136840300, 423, 303, 377, 99, 63, 460, 48, 56, 96, 237, 13, 960243310,
                79, 38531472, 197, 62, 30, 222, 697201257, 10, 40, 73, 948408986, 24, 26, 96, 72,
                474135982, 59, 38, 540555578, 33, 17, 19, 81, 46, 5, 62, 52, 477, 1, 121, 85, 76,
                409, 57, 66, 0, 98, 9, 799159654, 53, 761950078, 202, 2, 60, 57, 76, 89, 91, 943110618,
                116339638, 491, 71, 63, 21, 982392687, 28, 90, 99, 13, 48, 45, 265, 81, 299129384, 9,
                88, 66, 42, 92, 50, 38, 102234501, 12, 25, 12, 48, 249, 443, 26, 134, 202, 88, 85,
                1, 656699946, 13, 81, 17, 14, 23, 167, 35, 291078189, 51, 358, 3, 11, 33, 941435674,
                78, 38, 9, 5, 905703217, 73, 50, 63, 210, 68, 21, 351, 239, 20, 63, 458, 70, 430302485,
                20, 59, 617705415, 29, 125, 3, 49, 53, 50, 99, 18, 65, 69, 328, 920818624, 471, 108,
                37, 67, 493, 63, 118971232, 20, 35, 41, 364, 9, 45, 58, 21, 91, 31, 892531727, 27,
                39, 91, 68, 114, 12, 64, 87, 16, 654053062, 78, 39, 55, 97, 110786782, 58, 71, 181,
                99, 223243189, 242, 67, 206, 439, 7, 88, 284968074, 90, 536746462, 219, 263, 767559257,
                27, 621343048, 457344040, 296, 90, 441, 275, 71, 33, 406328720, 39, 88, 49, 896190950,
                89, 424712199, 281423498, 351114098, 37, 25, 63, 176, 63937198, 537954520, 59, 43, 51,
                56, 43, 166, 67, 400, 465, 160999808, 245, 35, 271629151, 46, 63, 484, 60, 618685824,
                11, 16, 40, 38, 248, 419522224, 388, 20, 65, 294, 219, 17, 60, 56, 33, 83, 265, 2,
                434, 12, 30, 229684600, 852946921, 5, 480, 55, 10926544, 433, 79, 76, 25, 61, 64, 48,
                91316504, 33, 202, 57, 57, 62, 266, 144685088, 95, 15, 66, 38, 55, 903723683, 630783215,
                18, 96, 57, 132070540, 322, 229, 487, 206, 306, 1, 27, 216, 59, 17, 43, 78, 55, 68,
                73, 770522980, 6, 83, 57, 20, 343, 40, 89, 322595589, 32, 395, 391, 41, 299, 11, 18,
                71, 453, 221, 49, 29, 67, 49376863, 419051429, 9, 11, 90, 29, 0, 27913493, 53, 55,
                92, 68928931, 22, 60, 25, 472, 36, 45, 16, 114586501, 462, 89, 10, 378, 399, 92, 94,
                157, 8, 20, 97, 25, 47, 30, 30, 21, 65, 59, 497, 243, 471, 240, 62, 428140635, 67,
                255, 0, 1, 606624255, 14, 166, 330, 50, 173952058, 27, 96, 40, 912835968, 37, 325,
                463, 72, 66, 15, 56, 806925759, 17, 5, 955708431, 66, 69, 13, 2, 65, 37, 48, 75,
                15, 98, 67, 464, 27838429, 72, 352, 403, 66, 85, 517028082, 14094713, 974396131, 42,
                2, 205, 456186863, 52, 10, 412, 49, 279, 41, 23, 614431106, 91, 637970000, 40, 6,
                67, 52, 81, 28, 49, 53, 82, 98, 7, 47, 51, 391, 47, 98, 227, 170, 16, 19, 530071389,
                35, 266727389, 302113251, 43, 37, 93, 433, 43, 338, 68, 377, 92, 94, 21, 10, 12, 0,
                37, 25, 54, 283, 599511052, 144, 393, 37, 71, 109, 66, 76, 168, 50, 35, 34, 54, 53,
                26, 503401760, 56, 77, 38, 212, 94213685, 25, 29, 38, 875445087, 12591782, 91, 80129894,
                231, 604067806, 39, 140, 322, 46, 88, 22, 35, 124, 348, 66, 55, 396160161, 268, 144,
                921309159, 9, 355281220, 46, 24, 6, 54, 51772786, 27, 58, 429, 66, 324, 183, 87, 560756592,
                481, 96, 55, 95, 222889187, 92, 82, 36, 44, 89, 19, 211, 35, 36, 26, 20, 713262333,
                656921461, 12, 297004120, 10, 45, 346, 7, 76, 17, 304, 90, 78, 44509762, 58, 216, 4,
                85, 412012937, 419, 74, 145, 98, 63, 0, 21, 12, 45, 42, 256327147, 94, 49, 92, 418,
                43, 30, 55808355, 90, 179, 32, 27323086, 36, 452601469, 41, 41, 17, 4, 338, 143067804,
                981649931, 896960798, 69, 140, 503920411, 12, 2, 48, 772936701, 20, 82, 35, 53, 17,
                41, 51, 72, 37, 7, 909219700, 364202100, 213, 15, 93, 493869484, 363, 502135279, 780411769,
                90, 514934875, 11, 230648907, 29, 25, 273, 107, 42, 80, 86, 78, 39, 92, 40, 55, 51, 14,
                62, 50, 18, 279, 4, 80301269, 527101911, 9, 498248226, 550279024, 762909281, 289, 30, 355,
                79, 48, 95, 84, 277, 408, 54, 34536984, 182, 19, 235255285, 27, 359, 49844291, 16, 449017475,
                20, 489, 477, 332, 42, 39, 289897474, 66, 69, 417, 80, 272, 64, 33, 14, 29, 35, 1, 96,
                18, 899058529, 865514487, 285, 12, 42, 9, 78, 65, 49, 389483627, 58, 140404597, 379, 9, 287,
                228799366, 120177789, 24, 110, 40429781, 70, 78, 77, 94, 665706642, 42, 82, 50, 94, 414911489,
                91, 402, 60, 358, 15, 26, 335, 37, 47, 42, 58, 46, 28, 306, 7, 7, 870499502, 82, 89, 36,
                33, 42, 372527470, 84, 415, 211, 63, 118, 5, 65, 71, 63, 134, 191, 38, 135626680, 93, 91,
                5, 32, 54, 446, 146200052, 12, 732066488, 650361822, 53, 336, 458704782, 63, 6, 144, 69,
                383355266, 315, 556107748, 317405746, 438, 11, 60, 79, 63, 171, 25, 31, 712959527, 482369136,
                41, 411, 284, 413232858, 48, 79, 46760756, 41, 878274993, 3, 58, 60, 80, 23, 678975980, 765929983,
                17, 333, 25, 86, 148, 117, 459, 860493025, 31, 61, 26849754, 24, 464, 935848146, 7, 70, 80,
                78, 446, 83, 181, 3, 11, 92, 60, 284, 26, 829411206, 8, 39, 38, 92, 325473816, 75, 86,
                29735709, 11, 107, 97, 17, 57, 29, 313, 378, 39, 932522238, 430, 47, 28779505, 61, 34, 59,
                39, 136157320, 270, 126121199, 42, 713435816, 163, 377, 16, 484, 771159802, 97, 306, 50,
                84, 38, 80, 13, 51, 82, 440, 958885200, 397, 9, 49, 363, 77, 10, 22, 76, 74, 131, 228,
                461, 463327508, 24, 94, 255, 58, 56, 62, 182385363, 32, 74, 39, 13, 68, 72, 492, 27, 28,
                52, 32, 265, 76, 2, 269044945, 29, 40, 183, 10, 344, 644977893, 87298227, 17044801, 90,
                10, 56, 2, 84, 839883347, 68, 53, 477064521, 57, 76, 354, 45, 917807041, 37, 31, 454908728,
                23, 235, 311, 56, 10, 55, 26, 60, 33, 787787650, 815559261, 391, 72, 0, 60, 651852696, 66,
                61, 154, 387, 193, 80, 263917571, 2, 481, 48, 52, 327708600, 71, 79, 82, 15, 10, 668644377,
                145, 233, 11, 10, 59, 90, 69, 724748087, 29, 297, 40, 485, 3, 74, 481, 34, 249432620,
                615372169, 85, 17, 397, 89, 398, 52031240, 188761567, 332, 14, 34, 708677170, 86637087, 12,
                81, 40, 67, 55676683, 74, 92, 198, 95, 592901421, 76, 375, 23, 147067663, 70, 48, 23,
                19, 433, 346, 281643971, 48, 388, 457, 185, 50, 37, 455, 35, 514618558, 221, 946666140, 40,
                12, 99, 538069541, 31, 38, 40, 406, 178, 90, 382, 480, 21508015, 27, 771997748, 365685, 31,
                314, 56, 274455253, 149, 58, 90, 118, 73, 388089588, 66595957, 77, 62, 413, 76, 833058652, 96,
                354049190, 78, 491, 409782538, 69, 859794317, 49, 48, 51, 310, 31, 37, 236, 95, 72, 58, 95,
                109, 834599317, 491, 581220228, 78, 403, 56949067, 437, 87, 55, 16, 89, 83, 6, 28, 30, 983963091,
                588930389, 90, 243, 46, 714473851, 305, 96, 12, 68, 94, 144, 122, 70, 4, 92, 4, 30, 50, 467,
                124, 451124362, 967379959, 73, 13, 52, 161864350, 743357288, 0, 75, 64, 204, 0, 267, 12, 46,
                404, 17, 371, 39, 335, 96, 292, 10, 48, 301, 4, 284, 92, 54, 137, 93, 13, 57, 105, 474, 40,
                78, 11, 63122029, 692162725, 23, 922148687, 33, 11, 1573789, 535581492, 91, 97, 94, 97, 489384496,
                335961343, 21, 15, 66, 32, 611710022, 49, 52, 99, 14, 75, 0, 57, 128, 15, 143, 137332802, 65,
                991150919, 62, 72, 424, 95, 65447739, 21, 37, 513601310, 510429273, 233, 74, 29, 462361211, 86,
                353, 200318459, 262, 3, 457, 208051866, 193, 782500549, 87, 21, 79, 114, 6, 22, 80, 45, 19,
                49, 40, 19, 451, 46, 376, 80, 159, 76, 819658907, 43, 91, 483, 78, 628061704, 364931985, 230,
                10, 74, 38, 10, 68, 30, 91, 156755617, 53, 64, 29, 508256315, 966711927, 9, 77, 41, 293, 401,
                88, 98, 454, 35, 339, 48, 16, 41, 463, 106, 28, 359, 552693107, 45, 68, 262183037, 462, 165,
                316, 65, 57, 3, 29, 20, 53, 27598187, 93, 669406491, 98, 406, 35, 276, 130, 65, 749583494, 57,
                96, 78, 55200757, 22, 28, 96, 59, 426, 76, 29, 79, 213, 46, 338300112, 62, 45, 83, 88, 73,
                3, 72, 26, 38, 494, 141, 79, 28, 35, 223, 119, 169, 48, 39, 497, 47, 17, 3, 56, 1, 8, 25,
                247117415, 84, 79, 123173102, 887536897, 15, 476, 33, 19, 77, 802384505, 33, 73, 315, 28, 73,
                93, 924044598, 69, 75, 89116824, 63, 98, 262700625, 731706281, 58, 284954534, 51, 429, 141, 58,
                306, 76, 328, 457, 15, 46, 33, 79, 878886566, 12, 199, 20, 5, 21, 63, 827785466, 83, 78,
                92, 66, 580331568, 90, 9, 283474763, 281, 204, 73, 435, 67678377, 377, 87, 14, 90, 33, 31,
                93, 254492465, 52, 4, 402, 142, 318, 182, 98, 61, 204, 366744823, 71, 415, 99, 57, 98, 76,
                48, 70, 18, 356, 232, 42, 2, 110, 159738157, 281, 886490450, 485, 937456596, 41, 16, 576543049,
                402, 15, 37, 57, 569490966, 4, 8, 79, 51, 45, 9, 2, 25, 304, 65, 59, 345398925, 339, 2,
                464747984, 400679853, 452700589, 54, 78, 33, 58, 54, 52, 55, 226, 61, 443, 63, 49, 84, 87,
                718042173, 350, 249, 943594684, 51, 28, 761561354, 90, 88, 34, 53, 129599589, 113, 570934734,
                135787024, 38, 450, 77, 87175020, 81, 56, 551826881, 42, 24, 39, 32, 41, 13, 12, 255, 41332221,
                185, 85, 70, 128, 28, 364, 98, 433, 341, 386, 21, 347, 333, 34, 103295799, 385, 226, 3,
                369014579, 51, 419, 169, 566443674, 344, 332, 40, 357, 492, 50, 441794130, 795341233, 238, 89,
                59, 619658224, 28, 77, 488816512, 4, 466, 271008439, 163823946, 146, 101907339, 91191116, 50,
                89, 94513012, 19180629, 47, 385, 348, 77, 66, 22, 74, 43, 10, 58, 91, 172, 27, 82, 92, 66,
                69, 19, 347891065, 834047082, 82, 168, 218, 76, 667486705, 430, 212, 90, 68, 82, 87, 496234304,
                151, 226, 155242624, 52, 1
                // ... (the actual test list is even longer)
        );
        // For this test case, the expected answer would have been computed from the full list.
        // For demonstration, suppose the expected answer is X (replace X with the correct number).
        // For now, we will print the result.
        System.out.println("Test 3 (long list) result: " + maximizeGroups(testB));

        // Provided Test Case C:
        // Another long list; here we show only a prefix.
        List<Integer> testC = Arrays.asList(
                825034897, 4678, 66007761, 2752, 20958099, 235, 232, 1871, 2505, 405, 677813320, 1336,
                528136025, 193, 1908, 282878987, 661734976, 1057, 571537554, 38, 4586, 4667, 61, 248, 144386994,
                350, 352, 614045834, 502797634, 6, 228, 2700, 4863, 463658498, 256121622, 2127, 507725013, 232,
                1036, 345, 486, 4697, 499605437, 69, 115, 3966, 272, 2752, 3128, 257, 138, 311, 179, 431, 876,
                992686950, 176, 143308701, 382, 37, 49, 46, 21, 773725809, 746442901, 2752, 204590451, 543, 311,
                49, 4103, 401, 350, 596, 1077, 435, 134412636, 301, 131, 159, 29, 276, 179, 140250814, 146, 1529,
                67, 84, 117738737, 206, 4666, 294, 326, 1164, 409, 4194, 765589939, 270, 142, 1812, 152, 93, 476,
                2192, 209, 827391528, 3892, 382, 121, 1381, 222, 2173, 398, 484, 232, 4032, 3490, 2807, 48709154,
                32, 251, 91, 4587, 4566, 223, 1943, 64122875, 4312, 413, 665, 1445, 134769882, 863764174, 106,
                393, 724157395, 1051, 773564492, 163, 50, 2710, 1685, 2315, 4210, 405103645, 1725, 276, 812702731,
                353, 2482, 800171828, 595, 216, 335431221, 201, 118, 321, 1496, 4504, 2216, 433, 445, 253, 3363,
                331146865, 3057, 75, 424, 295, 2374, 1621, 1908, 0, 431, 463, 359, 594051441, 310, 4980, 5,
                3519, 391, 3418, 3589, 1665, 62, 163965160, 432, 226, 18, 363, 365665790, 4033, 203, 125, 210,
                975, 442, 269, 311, 347, 72, 3695, 3520, 220, 463, 3619, 1591, 57, 3017, 1356, 383, 181, 4429,
                61, 88, 201700966, 268, 369, 84, 404960883, 69969524, 357, 3507, 114, 4949, 456, 6, 11, 3792,
                28, 333, 2893, 157, 93, 203, 437275344, 163660183, 47, 116, 418, 2984, 3602, 217, 1777, 390,
                645564742, 4153, 642819028, 102, 255, 737126802, 237, 4153, 2027, 150, 3038, 24, 328, 310, 1955,
                115, 127, 37, 3282, 481, 522133298, 4841, 4472, 376, 467, 4148, 1512, 3850, 118, 1
                // ... (and so on)
        );
        // Again, for demonstration we simply print the computed result.
        System.out.println("Test 4 (long list) result: " + maximizeGroups(testC));
    }
}