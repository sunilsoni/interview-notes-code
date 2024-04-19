package com.interview.notes.code.months.april24.test9;

import java.util.*;

/**
 * This is just a simple shared plaintext pad, with no execution capabilities.
 * When you know what language you'd like to use for your interview, simply choose it from the dots menu on the tab, or add a new language tab using the Languages button on the left.
 * You can also change the default language your pads are created with in your account settings: https://app.coderpad.io/settings
 * Enjoy your interview!
 * ーニーーー
 * songlist = [a,b,c,d,e, f, g,h] 
 */
public class UniqueShuffleSongs {
    private static List<String> history = new ArrayList<>();

    public static void uniqueShuffle(String[] songList) {
        Random random = new Random();
        String representation;
        do {
            for (int i = songList.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                String temp = songList[i];
                songList[i] = songList[j];
                songList[j] = temp;
            }
            representation = Arrays.toString(songList);
        } while (history.contains(representation));
        
        history.add(representation); // Add the new unique shuffle to the history
        System.out.println("Shuffled list: " + representation);
    }

    public static void main(String[] args) {
        String[] songList = {"a", "b", "c", "d", "e", "f", "g", "h"};
        uniqueShuffle(songList); // Perform a unique shuffle
        uniqueShuffle(songList); // Try another shuffle
    }
}
