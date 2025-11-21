package com.interview.notes.code.java.lambda;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ForEachExample {

    static List<String> names = new ArrayList<>();

    public static void main(String[] args) {
        setInitialValues();

        scanUsingIterator(names);

        scanUsingAdvanceForLoop(names);

        scanUsingAnonymousInnerClass(names);

        scanUsingLambda(names);

        scanUsingReusableLamdba(names);

    }

    private static void setInitialValues() {
        names.add("Vihaan");
        names.add("Aarav");
    }

    private static void scanUsingReusableLamdba(List<String> names) {
        //Using lambda and re usable code
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String t) {
                System.out.println(" anonymous class value " + t);

            }

        };

        names.forEach(consumer);
    }

    private static void scanUsingLambda(List<String> names) {
        //Using lambda expression
        names.forEach(name -> System.out.println(" lambda value " + name));
    }

    private static void scanUsingAnonymousInnerClass(List<String> names) {
        //Using anonymous inner class Consumer.
        names.forEach(new Consumer<String>() {

            @Override
            public void accept(String t) {
                System.out.println(" anonymous class value " + t);

            }

        });
    }

    private static void scanUsingAdvanceForLoop(List<String> names) {
        // using advance for loop.
        for (String string : names) {
            System.out.println(" forLoopvalue " + string);
        }
    }

    private static void scanUsingIterator(List<String> names) {
        //using iterator approach
        for (Iterator<String> iterator = names.iterator(); iterator.hasNext(); ) {
            String string = iterator.next();
            System.out.println(" Iterator value " + string);
        }
    }
}