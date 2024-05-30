package com.interview.notes.code.months.may24.test11;

import java.util.Optional;

public class Main1 {
    public static void main1(String[] args) {
        String S = null;
        System.out.println(S);  // This will print "null"
        // The next line will throw a NullPointerException
        System.out.println(S.length());
    }


    public static void main(String[] args) {
        String S = null;

        // Using Optional to avoid NullPointerException
        Optional<String> optionalS = Optional.ofNullable(S);

        // Checking if the value is present before accessing it
        if (optionalS.isPresent()) {
            System.out.println(optionalS.get().length());
        } else {
            System.out.println("String S is null");
        }

        Optional.ofNullable(S).ifPresentOrElse(str -> System.out.println(str.length()), () -> System.out.println("String S is null"));

    }
}
