package com.interview.notes.code.months.year2023.Oct23.test13;

import java.util.ArrayList;

public class ImmutableTest {

    private final ArrayList<Integer> lst;

    @SuppressWarnings("unchecked")

    public ImmutableTest(ArrayList<Integer> list) {

        this.lst = list;

    }

    public static void main(String[] args) {

        ArrayList<Integer> newList = new ArrayList<Integer>();

        newList.add(2);

        newList.add(4);

        ImmutableTest myImmutableRef = new ImmutableTest(newList);

        System.out.println("Before constructing " + myImmutableRef);

        newList.add(1, 5); // change (i.e. mutate) the element

        System.out.println("After constructing " + myImmutableRef);

    }

    public String toString() {

        StringBuffer sb = new StringBuffer("Numbers are: ");

        for (int i = 0; i < lst.size(); i++) {

            sb.append(lst.get(i) + " ");

        }

        return sb.toString();

    }

}


