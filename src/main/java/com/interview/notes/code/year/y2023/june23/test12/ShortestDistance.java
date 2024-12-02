package com.interview.notes.code.year.y2023.june23.test12;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Java Problem
 * You have a large text file containing words. Given any two words, find the shortest distance (in
 * terms of number of words) between them in the file.
 */
public class ShortestDistance {
    public static void main(String[] args) throws FileNotFoundException {
        //    Scanner in = new Scanner(new File("large_text_file.txt"));
        HashMap<String, List<Integer>> map = new HashMap<>();

        String str = "The quick brown fox jumps over the lazy dog" +
                "The dog was not amused." +
                "The fox, feeling guilty, went to the dog to apologize.";
        int index = 0;
        for (String word : str.split(" ")) {
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<Integer>());
            }
            map.get(word).add(index++);
        }


        String word1 = "fox"; // replace with actual word
        String word2 = "dog"; // replace with actual word

        if (!map.containsKey(word1) || !map.containsKey(word2)) {
            System.out.println("One or both words not found in the file.");
            return;
        }

        List<Integer> indexes1 = map.get(word1);
        List<Integer> indexes2 = map.get(word2);
        int i = 0, j = 0, diff = Integer.MAX_VALUE;

        while (i < indexes1.size() && j < indexes2.size()) {
            int index1 = indexes1.get(i), index2 = indexes2.get(j);
            if (index1 < index2) {
                diff = Math.min(diff, index2 - index1);
                i++;
            } else {
                diff = Math.min(diff, index1 - index2);
                j++;
            }
        }

        System.out.println("The shortest distance is " + diff);
    }
}
