package com.interview.notes.code.july23.test2;

import java.util.HashSet;
import java.util.Set;

public class HaveSameCharacters {
    public static boolean haveSameCharacters(String a, String b) {
        Set<Character> setA = new HashSet<>();
        Set<Character> setB = new HashSet<>();

        a = a.toLowerCase();
        b = b.toLowerCase();

        for (char c : a.toCharArray()) {
            if (Character.isLetter(c)) {
                setA.add(c);
            }
        }

        for (char c : b.toCharArray()) {
            if (Character.isLetter(c)) {
                setB.add(c);
            }
        }

        return setA.equals(setB);
    }

    public static void main(String[] args) {
        System.out.println(haveSameCharacters("Cat!", "tac."));  // true
        System.out.println(haveSameCharacters("CCaAtT,", "tac"));  // true
        System.out.println(haveSameCharacters("Dog", "Do!"));   // false
    }
}
