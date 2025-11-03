package com.interview.notes.code.year.y2025.october.CodeSignal.test7;

import java.util.Arrays;
import java.util.Random;

public class Main {
    static String[] solution(String[] text) {
        return Arrays.stream(text).map(Main::transform).toArray(String[]::new);
    }

    static boolean isVowel(char c) {
        char x = Character.toLowerCase(c);
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
    }

    static String transform(String s) {
        int n = s.length();
        if (n < 2) return s;
        if (!isVowel(s.charAt(0)) || !isVowel(s.charAt(n - 1))) return s;
        if (n == 2) return s;
        String mid = s.substring(1, n - 1);
        String rev = new StringBuilder(mid).reverse().toString();
        return s.charAt(0) + rev + s.charAt(n - 1);
    }

    static String[] baseline(String[] text) {
        String[] res = new String[text.length];
        for (int k = 0; k < text.length; k++) {
            String s = text[k];
            int n = s.length();
            if (n < 2 || !isVowel(s.charAt(0)) || !isVowel(s.charAt(n - 1)) || n == 2) {
                res[k] = s;
            } else {
                char[] mid = s.substring(1, n - 1).toCharArray();
                for (int i = 0, j = mid.length - 1; i < j; i++, j--) {
                    char t = mid[i]; mid[i] = mid[j]; mid[j] = t;
                }
                res[k] = s.charAt(0) + new String(mid) + s.charAt(n - 1);
            }
        }
        return res;
    }

    static void run(String name, String[] input, String[] expected) {
        String[] out = solution(input);
        boolean ok = Arrays.equals(out, expected);
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " -> " + Arrays.toString(out));
    }

    static String[] arr(String... a) { return a; }

    static String randWord(Random r) {
        int len = 1 + r.nextInt(50);
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int v = r.nextInt(52);
            char ch = v < 26 ? (char) ('a' + v) : (char) ('A' + (v - 26));
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        run("Ex1", arr("apple", "banana", "OranGe"), arr("alppe", "banana", "OGnare"));
        run("Ex2", arr("AE", "CodeSignal"), arr("AE", "CodeSignal"));
        run("Empty", new String[0], new String[0]);
        run("Singles", arr("a", "b", "U"), arr("a", "b", "U"));
        run("TwoChars", arr("ab", "ae", "IO"), arr("ab", "ae", "IO"));
        run("Mixed", arr("Idea", "test", "area", "sky"), arr("Ieda", "test", "aera", "sky"));

        Random rng = new Random(7);
        int n = 50000;
        String[] big = new String[n];
        for (int i = 0; i < n; i++) big[i] = randWord(rng);
        String[] expect = baseline(big);
        String[] got = solution(big);
        System.out.println("Large: " + (Arrays.equals(expect, got) ? "PASS" : "FAIL") + " -> " + n + " items");
    }
}
