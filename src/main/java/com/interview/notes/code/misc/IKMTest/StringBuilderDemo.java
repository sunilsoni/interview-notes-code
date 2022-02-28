package com.interview.notes.code.misc.IKMTest;

import java.util.Scanner;

public class StringBuilderDemo {
    public static void main(String[] argList) {
        StringBuilder str = new StringBuilder();
        for (String arg : argList) {
            if (str.indexOf(arg) < 1) {
                str.append(arg + " ");
            }
        }
        System.out.println(str.toString());
        Scanner sc = new Scanner(str.toString());

        while (sc.hasNext()) {
            if (sc.hasNextInt()) System.out.print(sc.nextInt() + " ");
            else
                sc.next();
        }
    }
}
