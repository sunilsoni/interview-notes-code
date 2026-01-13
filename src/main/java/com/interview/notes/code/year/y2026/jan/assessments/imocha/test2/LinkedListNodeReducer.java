package com.interview.notes.code.year.y2026.jan.assessments.imocha.test2;

import java.util.Arrays;

public class LinkedListNodeReducer {

    public static int removalOfNodes(int N, int[] Node) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ) {
            int j = i;
            while (j < N && Node[j] == Node[i]) j++;
            if ((j - i) % 2 == 0) {
                for (int k = i; k < j; k++) sb.append(Node[k]).append(" ");
            }
            i = j;
        }
        String out = sb.toString().trim();
        if (!out.isEmpty()) System.out.print(out);
        return 0;
    }

    static boolean test(int[] in, String exp) {
        java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
        java.io.PrintStream p = System.out;
        System.setOut(new java.io.PrintStream(b));
        removalOfNodes(in.length, in);
        System.setOut(p);
        return b.toString().trim().equals(exp);
    }

    public static void main(String[] args) {
        boolean ok = true;

        ok &= test(new int[]{1,1,2,2,2,3,4,4,5}, "1 1 4 4");
        ok &= test(new int[]{1}, "");
        ok &= test(new int[]{2,2}, "2 2");
        ok &= test(new int[]{3,3,3}, "");
        ok &= test(new int[]{5,5,5,5}, "5 5 5 5");

        int[] large = new int[100000];
        Arrays.fill(large, 7);
        ok &= test(large, new String(new char[100000]).replace("\0", "7 ").trim());

        System.out.println(ok ? "PASS" : "FAIL");
    }
}
