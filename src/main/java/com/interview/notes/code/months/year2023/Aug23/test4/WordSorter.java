package com.interview.notes.code.months.year2023.Aug23.test4;

import java.util.ArrayList;
import java.util.List;

public class WordSorter {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("orange");
        words.add("banana");
        words.add("grape");
        words.add("pineapple");

        bubbleSort(words);

        for (String word : words) {
            System.out.println(word);
        }
    }

    public static void bubbleSort(List<String> list) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    // swap list.get(j) and list.get(j+1)
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            // if no two elements were swapped by inner loop, then the list is sorted
            if (!swapped) {
                break;
            }
        }
    }
}
