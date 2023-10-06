package com.interview.notes.code.months.Sep23.test2;

import java.util.ArrayList;
import java.util.List;

public class SortString {

    public static void main(String[] args) {
        String s = "Test String!";
        sortAndPrint(s);
    }

    public static void sortAndPrint(String s) {
        List<CharWithIndex> list = new ArrayList<>();

        // Create a list of characters with their respective indices
        for (int i = 0; i < s.length(); i++) {
            list.add(new CharWithIndex(s.charAt(i), i));
        }

        // Sort the list based on the character's ASCII value
        list.sort((o1, o2) -> {
            if (o1.character == o2.character) {
                return Integer.compare(o1.index, o2.index);
            }
            return Character.compare(o1.character, o2.character);
        });

        // Print the sorted characters along with their original indices
        for (CharWithIndex cwi : list) {
            System.out.println("Char>>index>>" + cwi.character + " >> " + cwi.index);
        }
    }

    static class CharWithIndex {
        char character;
        int index;

        CharWithIndex(char character, int index) {
            this.character = character;
            this.index = index;
        }
    }
}
