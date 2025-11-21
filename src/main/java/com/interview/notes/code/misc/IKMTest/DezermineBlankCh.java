package com.interview.notes.code.misc.IKMTest;

public class DezermineBlankCh {
    public static void main(String... args) {
        String characters = " a b a ";
        String newCharacters = characters.strip().repeat(4);
        String[] splitCharacters = newCharacters.split("[ab]");
        int blank = 0;
        for (String t : splitCharacters) {
            if (t.isBlank()) {
                blank++;
            }
        }
        System.out.println(blank);


        var numberList = java.util.List.of(8, 1, -3, 1, 2);
        var array = numberList.toArray(new Integer[numberList.size()]);
        if (array instanceof Number[]) {
            System.out.println("is array of numbers");

            System.out.println(java.util.Arrays.toString(array));
        }

    }
}
