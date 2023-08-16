package com.interview.notes.code.july23.test12;

import java.util.StringJoiner;

public class StringJoinerExample {
    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]"); // Delimiter, Prefix, Suffix
        
        joiner.add("Apple");
        joiner.add("Banana");
        joiner.add("Orange");
        
        String result = joiner.toString();
        System.out.println(result); // Prints: [Apple, Banana, Orange]
    }
}
