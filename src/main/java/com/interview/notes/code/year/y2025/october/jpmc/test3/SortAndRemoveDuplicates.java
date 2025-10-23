package com.interview.notes.code.year.y2025.october.jpmc.test3;

import java.util.*;

public class SortAndRemoveDuplicates {
    public static Collection<String> sortAndRemoveDuplicates(Collection<String> names) {
        Set<String> sortedSet = new TreeSet<>(Collections.reverseOrder());
        for (String name : names) {
            if (name != null) sortedSet.add(name);
        }
        return new ArrayList<>(sortedSet);
    }

    public static void main(String[] args) {
        List<String> input1 = Arrays.asList("John", "Alice", "Bob", "Alice", "John");
        List<String> input2 = Arrays.asList("Zara", "Mike", "Bob", "Alice", "Zara");
        List<String> input3 = Arrays.asList("apple", "Apple", "banana", "Banana", "apple");
        List<String> input4 = Collections.emptyList();
        List<String> input5 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) input5.add("Item" + (i % 1000));

        test(input1, Arrays.asList("John", "Bob", "Alice"));
        test(input2, Arrays.asList("Zara", "Mike", "Bob", "Alice"));
        test(input3, Arrays.asList("banana", "apple", "Banana", "Apple"));
        test(input4, Collections.emptyList());
        test(input5, new ArrayList<>(sortAndRemoveDuplicates(input5)));
    }

    private static void test(Collection<String> input, Collection<String> expected) {
        Collection<String> output = sortAndRemoveDuplicates(input);
        boolean pass = new ArrayList<>(output).equals(new ArrayList<>(expected));
        System.out.println("Input=" + input.size() + " | Output=" + output.size() + " | Result=" + (pass ? "PASS" : "FAIL"));
    }
}