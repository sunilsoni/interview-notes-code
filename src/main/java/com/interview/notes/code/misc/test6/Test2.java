package com.interview.notes.code.misc.test6;

import java.util.ArrayList;
import java.util.ListIterator;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();//Creating arraylist
        list.add("Mango");//Adding object in arraylist
        list.add("Apple");
        list.add("Mango");
        list.add("Banana");

        ListIterator<String> listl = list.listIterator(list.size());

        while (listl.hasPrevious()) {
            String str = listl.previous();
            System.out.println(str);
        }

    }
}
