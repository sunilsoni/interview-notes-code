package com.interview.notes.code.misc;

import java.util.LinkedList;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * <p>
 * n → n/2      (n is even)
 * n → 3n + 1 (n is odd)
 * <p>
 * Using the rule above and starting with 13, we generate the following sequence:
 * <p>
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet, consider that all starting numbers finish at 1.
 * <p>
 * Which starting number, under 10000, produces the longest chain?
 */
public class LongestCollatzSequence {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        LinkedList<Long> list = new LinkedList<Long>();

        long length = 0;
        int res = 0;

        for (int j = 10; j < 1000000; j++) {
            long i = j;

            while (i != 1) {
                if (i % 2 == 0) {
                    i /= 2;
                    list.add(i);
                } else {
                    i = (3 * i) + 1;
                    list.add(i);
                }
            }

            if (list.size() > length) {
                length = list.size();
                res = j;
            }
            list.clear();
        }
        long end = System.currentTimeMillis();

        System.out.println(res + " with chain size: " + length);
        System.out.println(end - begin + "ms");
    }
}
