package com.interview.notes.code.year.y2025.october.CodeSignal.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    static String[] solution(String[][] paragraphs, int width) {
        List<String> lines = new ArrayList<>();
        for (String[] para : paragraphs) {
            List<String> parts = new ArrayList<>();
            int len = 0;
            for (String w : para) {
                int add = parts.isEmpty() ? w.length() : 1 + w.length();
                if (len + add > width) {
                    lines.add(center(join(parts), width));
                    parts.clear();
                    len = 0;
                }
                if (parts.isEmpty()) {
                    parts.add(w);
                    len = w.length();
                } else {
                    parts.add(w);
                    len += 1 + w.length();
                }
            }
            if (!parts.isEmpty()) lines.add(center(join(parts), width));
        }
        String border = repeat('*', width + 2);
        List<String> out = new ArrayList<>();
        out.add(border);
        for (String s : lines) out.add("*" + s + "*");
        out.add(border);
        return out.toArray(new String[0]);
    }

    static String join(List<String> parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(parts.get(i));
        }
        return sb.toString();
    }

    static String center(String s, int width) {
        int gap = width - s.length();
        int left = gap / 2;
        int right = gap - left;
        return repeat(' ', left) + s + repeat(' ', right);
    }

    static String repeat(char c, int n) {
        char[] a = new char[n];
        Arrays.fill(a, c);
        return new String(a);
    }

    static void runExact(String name, String[][] paragraphs, int width, String[] expected) {
        String[] got = solution(paragraphs, width);
        System.out.println(name + ": " + (Arrays.equals(got, expected) ? "PASS" : "FAIL") + " -> " + Arrays.toString(got));
    }

    static void runInvariant(String name, String[][] paragraphs, int width) {
        String[] got = solution(paragraphs, width);
        boolean ok = got.length >= 2 && got[0].equals(repeat('*', width + 2)) && got[got.length - 1].equals(repeat('*', width + 2));
        for (int i = 1; i < got.length - 1 && ok; i++) {
            String line = got[i];
            ok &= line.length() == width + 2 && line.charAt(0) == '*' && line.charAt(line.length() - 1) == '*';
            String mid = line.substring(1, line.length() - 1);
            int lead = 0; while (lead < mid.length() && mid.charAt(lead) == ' ') lead++;
            int trail = 0; while (trail < mid.length() && mid.charAt(mid.length() - 1 - trail) == ' ') trail++;
            int gap = width - (mid.length() - lead - trail);
            ok &= lead + trail == gap && (trail == lead || trail == lead + 1);
        }
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " -> lines=" + got.length);
    }

    public static void main(String[] args) {
        String[][] p1 = {{"hello","world"},{"How","areYou","doing"},{"Please look","and align","to center"}};
        int w1 = 16;
        String[] e1 = {
            "******************",
            "*  hello world   *",
            "*How areYou doing*",
            "*  Please look   *",
            "*   and align    *",
            "*   to center    *",
            "******************"
        };
        runExact("Example", p1, w1, e1);

        String[][] p2 = {{"ab","cd"},{"xyz"}};
        int w2 = 5;
        String[] e2 = {
            "*******",
            "*ab cd*",
            "* xyz *",
            "*******"
        };
        runExact("ExactFitAndCenter", p2, w2, e2);

        String[][] p3 = {{"a"},{"A"},{"aaaaa"},{"b"},{"aaaa","a"}};
        runInvariant("EdgeSmallWords", p3, 7);

        Random r = new Random(9);
        int paras = 20, chunks = 10, width = 50;
        String[][] big = new String[paras][];
        for (int i = 0; i < paras; i++) {
            int m = 1 + r.nextInt(chunks);
            big[i] = new String[m];
            for (int j = 0; j < m; j++) {
                int len = 1 + r.nextInt(10);
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < len; k++) sb.append((char)('a' + r.nextInt(26)));
                big[i][j] = sb.toString();
            }
        }
        runInvariant("LargeRandom", big, width);
    }
}
