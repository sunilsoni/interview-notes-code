package com.interview.notes.code.year.y2025.June.common.test1;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    // returns all i in [m,n] whose factorial starts with 2,4,6, or 8
    public static List<Integer> solve(int m, int n) {
        List<Integer> ans = new ArrayList<>();
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
            if (i >= m) {
                char first = fact.toString().charAt(0);
                if (first == '2' || first == '4' || first == '6' || first == '8') {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());

        List<Integer> list = solve(m, n);

        // print count, then the values (if any)
        StringBuilder sb = new StringBuilder();
        sb.append(list.size());
        for (int x : list) {
            sb.append(' ').append(x);
        }
        System.out.print(sb);
    }
}