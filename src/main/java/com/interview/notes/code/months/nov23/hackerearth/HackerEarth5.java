package com.interview.notes.code.months.nov23.hackerearth;

import java.util.ArrayList;
import java.util.List;

class HackerEarth5 {
    public static void main(String[] args) {
        int[] array = {3, 6, 9, 5};
        List<Integer> al = new ArrayList<>();

        al.add(array[0]);
        al.add(array[2]);
        al.set(1, array[0]);
        al.set(1, array[1]);
        al.remove(0);
        al.add(array[0]);
        System.out.println(al);
    }

}
