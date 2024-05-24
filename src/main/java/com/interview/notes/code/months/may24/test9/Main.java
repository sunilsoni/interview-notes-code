package com.interview.notes.code.months.may24.test9;

public class Main {
    public static void main(String[] args) {
        System.out.println(solve("asdfghjk1")); // .s.d.f.g.h.j.k.1
        System.out.println(solve("ConSonanT")); // .c.n.s.n.n.t
    }

    public static String solve(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!isVowel(c)) {
                result.append('.').append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
