package com.interview.notes.code.misc.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MayTest {
    public static void main(String[] args) {
      //  Arrays.stream(new int[]{1,2,3}).map(i->System.out.println(" "+i));return i*2;}).sum();
        List<Integer> list = Arrays.asList(10, 23, -4, 0, 18);
        List<Integer> sortedList = list.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        System.out.println(sortedList);
    }
}
