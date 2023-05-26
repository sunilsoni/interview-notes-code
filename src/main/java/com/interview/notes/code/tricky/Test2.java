package com.interview.notes.code.tricky;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(10);
        list.add(15);
        for (Integer ar : list) {
            if (ar == 10) {
                list.remove(1);
            }
        }


        System.out.println(list);


        int a = 2;
        int b = 3;
        int c = 0;


        while (true) {
            c = a + b;
        }
    }
}
