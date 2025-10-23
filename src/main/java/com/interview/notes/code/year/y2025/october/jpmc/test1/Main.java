package com.interview.notes.code.year.y2025.october.jpmc.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            words.add(line);
        }
        sc.close();

        if (words.isEmpty()) return;

        StringBuilder result = new StringBuilder(words.get(0).toLowerCase());
        for (int i = 1; i < words.size(); i++) {
            String w = words.get(i).toLowerCase();
            result.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        }
        System.out.println(result);
    }
}