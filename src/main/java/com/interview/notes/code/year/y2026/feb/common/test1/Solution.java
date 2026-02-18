package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Ensure we read N even if hasNextInt check behaves oddly on some platforms
        int n = scan.nextInt(); 
        
        // Subtask 1: Create a list of string type
        List<String> nameslist = new ArrayList<>();

        String temp;
        for (int i = 0; i < n; i++) {
            // Use next() to read token by token, skipping whitespace/newlines
            temp = scan.next();
            // Subtask 2: Add elements to the created list
            nameslist.add(temp);
        }

        // Subtask 3: Create a StringJoiner using hyphen as delimiter
        StringJoiner sj = new StringJoiner("-");

        // Subtask 4: Filter strings (length divisible by 3 or 4) using stream
        // Reassign the filtered list back to nameslist so the next loop works correctly
        nameslist = nameslist.stream()
                .filter(s -> s.length() % 3 == 0 || s.length() % 4 == 0)
                .collect(Collectors.toList());

        for (int i = 0; i < nameslist.size(); i++) {
            // Subtask 5: Add the content of the string list created above in StringJoiner
            sj.add(nameslist.get(i));
        }

        // Subtask 6: Display the final StringJoiner
        System.out.println(sj);
    }
}