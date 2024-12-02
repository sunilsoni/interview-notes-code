package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonFlightDataFinder {

    public static Set<String> findCommonElements(List<String> origins, List<String> stopovers, List<String> destinations) {
        // Convert the first list to a set
        Set<String> commonElements = new HashSet<>(origins);

        // Retain only elements that are also in stopovers and destinations
        commonElements.retainAll(new HashSet<>(stopovers));
        commonElements.retainAll(new HashSet<>(destinations));

        return commonElements;
    }

    public static void main(String[] args) {
        // Sample lists
        List<String> origins = Arrays.asList("123ORDDFW", "456SFOLAX", "456SFOLAX");
        List<String> stopovers = Arrays.asList("456SFOLAX", "123ORDDFW");
        List<String> destinations = Arrays.asList("123DFWORD", "345NYCLON", "123ORDDFW");

        // Find common elements
        Set<String> commonElements = findCommonElements(origins, stopovers, destinations);
        System.out.println(commonElements);
    }
}
