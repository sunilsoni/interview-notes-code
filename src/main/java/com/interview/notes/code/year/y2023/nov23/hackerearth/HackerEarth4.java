package com.interview.notes.code.year.y2023.nov23.hackerearth;

import java.util.ArrayList;
import java.util.List;

class HackerEarth4 {
    public static void main(String[] args) {
        int[] array = {6, 9, 8};
        List<Integer> al = new ArrayList<>();
        al.add(array[0]);
        al.add(array[2]);
        al.set(1, array[1]);
        al.remove(0);
        System.out.println(al);
    }
}