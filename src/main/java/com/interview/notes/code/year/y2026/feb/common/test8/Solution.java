package com.interview.notes.code.year.y2026.feb.common.test8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        List<String> list = new ArrayList<>();

        String temp;
        for (int i = 0; i < n; i++) {
            temp = scan.next();
            list.add(temp);
        }

        StringJoiner sj = new StringJoiner("-");

        List<String> nameslist = list.stream()
                .filter(s -> s.length() % 3 == 0 || s.length() % 4 == 0)
                .collect(Collectors.toList());

        for (int i = 0; i < nameslist.size(); i++) {
            sj.add(nameslist.get(i));
        }

        System.out.print(sj);
    }
}
