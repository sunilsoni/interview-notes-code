package com.interview.notes.code.year.y2026.march.visa.test1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValidWordCounterApp {

    public static int countValidWords(String s) {
        return (int) List.of(s.trim().split("\\s+"))
                .stream()
                .filter(w -> !w.isEmpty())
                .filter(ValidWordCounterApp::isValidWord)
                .count();
    }

    static boolean isValidWord(String w) {
        return w.length() >= 3
                && w.chars().allMatch(Character::isLetterOrDigit)
                && w.chars().map(Character::toLowerCase).anyMatch(c -> "aeiou".indexOf(c) >= 0)
                && w.chars().map(Character::toLowerCase).anyMatch(c -> c >= 'a' && c <= 'z' && "aeiou".indexOf(c) < 0);
    }

    static void test(String name, String input, int expected) {
        int actual = countValidWords(input);
        System.out.println(name + " : " + (actual == expected ? "PASS" : "FAIL expected=" + expected + " actual=" + actual));
    }

    public static void main(String[] args) {
        test("Example1", "This is an example string 234", 3);
        test("Example2", "This is Form16 submis$ion date", 3);
        test("Empty", "", 0);
        test("SpacesOnly", "     ", 0);
        test("OnlyDigits", "123 4567 89", 0);
        test("Mixed", "a1b abc aei x1z A2e", 4);
        test("InvalidChars", "abc! ab_cd ab-cd good1", 1);
        test("CaseCheck", "Sunil JAVA code", 3);

        String large = IntStream.range(0, 20000)
                .mapToObj(i -> i % 4 == 0 ? "abc" : i % 4 == 1 ? "a1b" : i % 4 == 2 ? "123" : "ab$")
                .collect(Collectors.joining(" "));
        test("LargeInput", large, 10000);
    }
}