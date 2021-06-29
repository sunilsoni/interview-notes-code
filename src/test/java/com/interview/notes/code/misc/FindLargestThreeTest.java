package com.interview.notes.code.misc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FindLargestThreeTest {
    static FindLargestThree findLargestThree;

    @BeforeAll
    public static void setUp() {
        findLargestThree = new FindLargestThree();
    }

    @Test
    void findLargestThree() {
        int arr[] = {19, 5, 78, 1, 33, 11, 20};
        findLargestThree.findLargestThree(arr);
    }
}