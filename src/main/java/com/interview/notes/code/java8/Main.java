package com.interview.notes.code.java8;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // Find the length of the current sequence
        int len = 1;
        while (len <= n) {
            n -= len;
            len *= 2;
        }

        // Find the number at the Nth position in the sequence
        int num = 0;
        while (len > 1) {
            len /= 2;
            if (n < len) {
                num = (num + 1) % 3;
            } else {
                n -= len;
                num = (num + 2) % 3;
            }
        }
        System.out.println(num);
    }
}