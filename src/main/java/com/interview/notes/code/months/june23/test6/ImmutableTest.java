package com.interview.notes.code.months.june23.test6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {
    private final List<Integer> lst;

    public ImmutableTest(List<Integer> list) {
        this.lst = Collections.unmodifiableList(new ArrayList<>(list));
    }

    public static void main(String[] args) {
        List<Integer> newList = new ArrayList<>();
        newList.add(2);
        newList.add(4);
        ImmutableTest myImmutableRef = new ImmutableTest(newList);
        System.out.println("Before constructing " + myImmutableRef);
        newList.add(1, 5); // Attempt to change the element
        System.out.println("After constructing " + myImmutableRef);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Numbers are: ");
        for (int i = 0; i < lst.size(); i++) {
            sb.append(lst.get(i)).append(" ");
        }
        return sb.toString();
    }
}
