package com.interview.notes.code.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FindSmallestMissingPositiveNumber {
    // Function to find the smallest missing positive number from an unsorted array
    public static int findSmallestMissing(int[] nums) {
        // use a range constructor to initialize the set from array elements
        Set<Integer> distinct = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        // return first smallest missing positive number from the set
        int index = 1;
        while (true) {
            if (!distinct.contains(index)) {
                return index;
            }
            index++;
        }
    }

    public static void main(String[] args) {

        HashSet<String> myset = new HashSet<>();
        myset.add("Hello");
        myset.add("Hello");
        System.out.println(myset);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(null, "SQL");
        map.put(1, "spring");
        map.put(null, "MS");
        System.out.println(map);//{null=MS, 1=spring}


        int[] nums = {1, 4, 2, -1, 6, 5};

        System.out.println("The smallest missing positive number from the array is "
                + findSmallestMissing(nums));
    }
}
