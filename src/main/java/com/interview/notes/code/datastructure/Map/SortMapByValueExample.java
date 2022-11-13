package com.interview.notes.code.datastructure.Map;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class SortMapByValueExample {

    public static void main(String[] args) {
        final Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("USA", 100);
        wordCounts.put("jobs", 200);
        wordCounts.put("software", 50);
        wordCounts.put("technology", 70);
        wordCounts.put("opportunity", 200);

        final Map<String, Integer> sortedByCount = sortByValue(wordCounts);
        System.out.println(sortedByCount);//{jobs=200, opportunity=200, USA=100, technology=70, software=50}

        final Map<String, Integer> sortByValue2 = sortByValue2(wordCounts);
        System.out.println(sortByValue2);//{software=50, technology=70, USA=100, jobs=200, opportunity=200}

        final Map<String, Integer> sortByValueInReverseOrder = sortByValueInReverseOrder(wordCounts);
        System.out.println(sortByValueInReverseOrder);//{jobs=200, opportunity=200, USA=100, technology=70, software=50}


        System.out.println(sortByValue3());//{miscellneous=90, transportation=100, clothes=120, utility=130, grocery=150, rent=1150}

        System.out.println(sortByValue4());//{rent=1150, grocery=150, utility=130, clothes=120, transportation=100, miscellneous=90}
    }

    /**
     * You can see that the  sorted() method takes Comparator as an argument, making it possible to sort a map with any kind of value. For example,  sort can be written with the Comparator as:
     *
     * @param wordCounts
     * @return
     */
    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {

        return wordCounts.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Here, I am usingLinkedHashMap to store the sorted result to preserve the order of the elements in the resulting map.
     * <p>
     * The advantages of this approach are:
     * <p>
     * It doesn't modify the original data wordCounts, making it more thread safe.
     * It is more readable.
     *
     * @param wordCounts
     * @return
     */
    public static Map<String, Integer> sortByValue2(final Map<String, Integer> wordCounts) {

        final Map<String, Integer> sortedByCount = wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedByCount;
    }

    public static Map<String, Integer> sortByValueInReverseOrder(final Map<String, Integer> wordCounts) {

        final Map<String, Integer> sortedByCount = wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedByCount;
    }

    public static Map<String, Integer> sortByValue3() {
        Map<String, Integer> budget = new HashMap<>();
        budget.put("clothes", 120);
        budget.put("grocery", 150);
        budget.put("transportation", 100);
        budget.put("utility", 130);
        budget.put("rent", 1150);
        budget.put("miscellneous", 90);
        System.out.println("map before sorting: " + budget);

        Map<String, Integer> sorted = budget
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("map after sorting by values: " + sorted);
        return sorted;
    }


    public static Map<String, Integer> sortByValue4() {
        Map<String, Integer> budget = new HashMap<>();
        budget.put("clothes", 120);
        budget.put("grocery", 150);
        budget.put("transportation", 100);
        budget.put("utility", 130);
        budget.put("rent", 1150);
        budget.put("miscellneous", 90);
        System.out.println("map before sorting: " + budget);

        Map<String, Integer> sorted = budget
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("map after sorting by values in descending order: "
                + sorted);
        return sorted;
    }
}
