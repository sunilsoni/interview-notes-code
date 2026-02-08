package com.interview.notes.code.year.y2026.feb.microsoft.test5;

import java.util.List;

public class AllCombos {  
    // method to generate permutations
    static List<String> perms(String s) {  
        if (s.isEmpty()) return List.of(""); // base case: empty string → one combo ""  
        // take each char, permute rest, add char in front
        return s.chars().mapToObj(c -> (char)c)  
                .flatMap(ch -> perms(s.replaceFirst(ch+"",""))  
                .stream().map(rest -> ch+rest))  
                .toList();  
    }  

    public static void main(String[] args) {  
        int a = 1234;  
        String s = String.valueOf(a); // convert number to string "1234"  
        List<String> result = perms(s); // get all permutations  
        System.out.println("Total: " + result.size()); // should be 24 (4!)  
        System.out.println(result); // print all combos  
    }  
}
