package com.interview.notes.code.year.y2026.feb.common.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int n = scan.nextInt();
            
            // Subtask 1: Create a list of string type
            List<String> nameslist = new ArrayList<>();

            String temp;
            for (int i = 0; i < n; i++) {
                if (scan.hasNext()) {
                    temp = scan.next();
                    // Subtask 2: Add elements to the created list
                    nameslist.add(temp);
                }
            }

            // Subtask 3: Create a StringJoiner using hyphen as delimiter
            StringJoiner sj = new StringJoiner("-");

            // Subtask 4: Filter strings (length divisible by 3 or 4) using stream
            nameslist = nameslist.stream()
                    .filter(s -> s.length() % 3 == 0 || s.length() % 4 == 0)
                    .collect(Collectors.toList());

            // Subtask 5: Add content of filtered list to StringJoiner
            // Note: The loop below is part of the provided skeleton in the image
            for (int i = 0; i < nameslist.size(); i++) {
                sj.add(nameslist.get(i));
            }

            // Subtask 6: Display the final StringJoiner
            System.out.println(sj);
        }
    }
}