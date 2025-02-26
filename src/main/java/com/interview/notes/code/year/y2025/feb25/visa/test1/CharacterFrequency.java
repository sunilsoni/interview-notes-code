package com.interview.notes.code.year.y2025.feb25.visa.test1;

import java.util.stream.Collectors;
import java.util.function.Function;

public class CharacterFrequency {
    public static void main(String[] args) {
        String s = "Java Concept Of The Day";
        
        System.out.println("Original String: " + s);
        
        System.out.println("\nMethod 1: Basic character frequency (case-sensitive):");
        s.chars()
         .mapToObj(ch -> String.valueOf((char)ch))
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
         .forEach((character, frequency) -> 
             System.out.println(character + " : " + frequency));

        System.out.println("\nMethod 2: Alternative approach (case-sensitive):");
        s.chars()
         .mapToObj(ch -> (char)ch)
         .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
         .forEach((character, frequency) -> 
             System.out.println(character + " : " + frequency));

        System.out.println("\nMethod 3: Case-insensitive counting:");
        s.toLowerCase()
         .chars()
         .mapToObj(ch -> (char)ch)
         .collect(Collectors.groupingBy(
             Function.identity(),
             Collectors.counting()))
         .forEach((character, frequency) -> 
             System.out.println(character + " : " + frequency));

        System.out.println("\nMethod 4: Case-insensitive counting (excluding spaces):");
        s.replaceAll("\\s", "")  // Remove spaces
         .toLowerCase()           // Convert to lowercase
         .chars()
         .mapToObj(ch -> (char)ch)
         .collect(Collectors.groupingBy(
             Function.identity(),
             Collectors.counting()))
         .forEach((character, frequency) -> 
             System.out.println(character + " : " + frequency));
        

    }
}
