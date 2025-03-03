package com.interview.notes.code.year.y2025.feb.common.test2;

import java.util.ArrayList;
import java.util.List;

public class _C {
    private static int $; // Static variable $ is declared but not initialized


    public static void main(String[] args) {
        String a_b = null;//"Hello"; // Local variable a_b must be initialized
        // System.out.print($); // Output: 0 (default value of uninitialized static int)
        // System.out.print(a_b); // Output: Hello


        int[] array = {6, 9, 8};
        List<Integer> list = new ArrayList<>();
        list.add(array[0]);
        list.add(array[2]);
        list.set(1, array[1]);
        list.remove(0);
        System.out.println(list);//output: 9
    }
}