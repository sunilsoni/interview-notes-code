package com.interview.notes.code.year.y2026.feb.common.test6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder inputData = new StringBuilder();
        String thisLine = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((thisLine = br.readLine()) != null) {
            inputData.append(thisLine).append("\n");
        }
        System.out.print(codeHere(inputData));
    }

    public static String codeHere(StringBuilder inputData) {
        String[] lines = inputData.toString().trim().split("\\s+");
        int t = Integer.parseInt(lines[0]);
        return IntStream.range(0, t)
                .mapToObj(i -> nextPalindrome(Long.parseLong(lines[i + 1])))
                .collect(Collectors.joining("\n"));
    }

    static String nextPalindrome(long k) {
        long n = k + 1;
        if (n < 0) return "0";
        while (!isPalindrome(n)) {
            n++;
        }
        return String.valueOf(n);
    }

    static boolean isPalindrome(long n) {
        String s = String.valueOf(n);
        return s.contentEquals(new StringBuilder(s).reverse());
    }
}
