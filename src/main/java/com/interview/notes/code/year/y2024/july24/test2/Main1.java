package com.interview.notes.code.year.y2024.july24.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {

        Collection<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g"));
        Collection<String> listTwo = new ArrayList<>(Arrays.asList("a", "b", "d", "e", "f", "gg", "h"));

        List<String> sourceList = new ArrayList<>(listOne);
        List<String> destinationList = new ArrayList<>(listTwo);

// Corrected method calls with parentheses
        sourceList.removeAll(listTwo);
        destinationList.removeAll(listOne);

        System.out.println("sourceList: " + sourceList);
        System.out.println("destinationList: " + destinationList);
        List<Integer> evenNumbers = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> oddNumbers = Arrays.asList(1, 3, 5, 7, 9);

        List<List<Integer>> evenOddNumbers = new ArrayList<>();
        evenOddNumbers.add(evenNumbers);
        evenOddNumbers.add(oddNumbers);

        List<Integer> combinedList = evenOddNumbers.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<Integer> sortedList = combinedList.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted Combined List: " + sortedList);
    }
}
