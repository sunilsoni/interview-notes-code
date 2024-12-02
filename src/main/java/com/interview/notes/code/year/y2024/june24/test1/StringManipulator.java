package com.interview.notes.code.year.y2024.june24.test1;

import java.util.ArrayList;
import java.util.Iterator;

public class StringManipulator {

    public static void main(String[] args) {
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("apple");
        stringList.add("banana");
        stringList.add("apple");
        stringList.add("orange");

        StringManipulator stringManipulator = new StringManipulator();
        int removedCount = stringManipulator.removeStringOccurrences(stringList, "apple");
        System.out.println("Removed " + removedCount + " occurrences of 'apple' from the list.");

        System.out.println("List after removal: " + stringList);
    }

    public int removeStringOccurrences(ArrayList<String> stringList, String targetString) {
        int numRemoved = 0;
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals(targetString)) {
                iterator.remove();
                numRemoved++;
            }
        }
        return numRemoved;
    }
}
