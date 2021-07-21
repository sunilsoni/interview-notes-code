package com.interview.notes.code.anagram;

import java.util.*;

public class PrintAnagramTogether {

    public void print(String[] string) {
        Map<String, List<Integer>> invertedIndex = new HashMap<String, List<Integer>>();
        int index = 0;
        for (String str : string) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String newString = new String(charArray);
            if (invertedIndex.containsKey(newString)) {
                List<Integer> pos = invertedIndex.get(newString);
                pos.add(index);
            } else {
                List<Integer> pos = new ArrayList<Integer>();
                pos.add(index);
                invertedIndex.put(newString, pos);
            }
            index++;
        }
        for (List<Integer> result : invertedIndex.values()) {
            for (Integer i : result) {
                System.out.println(string[i]);
            }
        }
    }
}