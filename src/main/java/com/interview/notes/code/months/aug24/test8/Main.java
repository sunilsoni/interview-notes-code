package com.interview.notes.code.months.aug24.test8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
       /* Stream<String> stream = Arrays.stream(new String[] { "a", "b", "c" }); //1
        String output = stream.filter(s -> { //2
                    if (s.compareTo("abc") > 0)
                        return true;
                })
                .peek(System.out::print) //3
                .collect(Collectors.joining()); //4
        System.out.print(output);*/
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        list.parallelStream()
                .peek(System.out::print)
                .forEachOrdered(System.out::print);


        String contactinfo = "Bob, India";
        if (contactinfo.endsWith("India")) {
            if (contactinfo.contains("Joe")) {
                System.out.println("Your name is Joe");
            } else if (contactinfo.contains("Mina")) {
                System.out.println("Your name is Mina");
            } else {
                System.out.println(contactinfo.split(",")[0]);
            }
        } else {
            System.out.println("You do not have a name");
        }
    }
}
