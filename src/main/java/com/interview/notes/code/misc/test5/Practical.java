package com.interview.notes.code.misc.test5;

import java.util.*;

public class Practical {
    public static void main(String[] args) {

        String a = "abc";
        String b = "abc";
        System.out.println(a.equals(b));
        System.out.println(a == b);


        Collection<String> names = new ArrayList<>();
        names.add("John");
        names.add("Ana");
        names.add("Mary");
        names.add("Anthony");
        names.add("Mark");

        // String[] namesArray = new String[]{"John", "Ana", "Mary", "Anthony", "Mark", "David"};


        // Print results after each step:

        // Please remove items starting with M using iterator.

        Iterator<String> iter = names.iterator();
        while (iter.hasNext()) {
            if (iter.next().startsWith("M")) {
                iter.remove();
            }
        }
        names.forEach(System.out::print);
        System.out.println();
        // Please remove items starting with A using Collection interface with Predicate.
        names.stream().filter(s -> s.startsWith("A")).forEach(System.out::print);
        System.out.println();
        // Please remove items starting with J using Stream filtering.
        names.stream().filter(s -> s.startsWith("J")).forEach(System.out::print);
        System.out.println();

        //        Clear list.
        names.clear();
        System.out.println(names);

        String[] namesArray = new String[]{"John", "Ana", "Mary", "Anthony", "Mark", "David"};
        System.out.println();
        //Print results after each step:

        //Convert array to list.
        List<String> list = new ArrayList<>();
        for (String s : namesArray) {
            list.add(s);
        }
        list.forEach(System.out::print);
        System.out.println();

        //Please remove items starting with M using iterator.
        List<String> nameList = Arrays.asList(namesArray);
        Iterator<String> iterator = nameList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().startsWith("M")) {
                iterator.remove();
            }
        }
        System.out.println();
        //  nameList.forEach(System.out::print);
        // Add list to list of names using Collections
        List<String> newNames = Arrays.asList("Mary", "Anthony", "Mark");

        nameList.addAll(newNames);
        nameList.forEach(System.out::print);
    }
}
