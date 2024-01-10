package com.interview.notes.code.months.year2023.nov23.test2;

import java.util.ArrayList;
import java.util.List;

public class ABCCombinations1 {

    public static void main(String[] args) {
        // Assuming your list looks like this
        List<String> items = List.of("A1", "B1", "C1", "A2", "B2", "C2");

        // Separate the items into three different lists
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();
        List<String> listC = new ArrayList<>();

        for (String item : items) {
            if (item.startsWith("A")) {
                listA.add(item);
            } else if (item.startsWith("B")) {
                listB.add(item);
            } else if (item.startsWith("C")) {
                listC.add(item);
            }
        }

        // Combine the items
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                for (int k = 0; k < listC.size(); k++) {
                    // Ensure that we're combining elements with the same number
                    if (listA.get(i).substring(1).equals(listB.get(j).substring(1))
                            && listA.get(i).substring(1).equals(listC.get(k).substring(1))) {
                        String combination = listA.get(i) + listB.get(j) + listC.get(k);
                        System.out.println(combination);
                    }
                }
            }
        }
    }
}
