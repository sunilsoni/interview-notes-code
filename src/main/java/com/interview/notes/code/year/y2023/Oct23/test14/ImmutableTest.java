package com.interview.notes.code.year.y2023.Oct23.test14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {

    private final List<Integer> lst;

    public ImmutableTest(List<Integer> list) {
        // Create a defensive copy of the list and make it unmodifiable
        this.lst = Collections.unmodifiableList(new ArrayList<>(list));
    }

    public static void main(String[] args) {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(2);
        newList.add(4);

        ImmutableTest myImmutableRef = new ImmutableTest(newList);

        System.out.println("Before modifying the original list: " + myImmutableRef);

        newList.add(1, 5); // This change will not affect myImmutableRef

        System.out.println("After modifying the original list: " + myImmutableRef);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Numbers are: ");
        for (int num : lst) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }
}
