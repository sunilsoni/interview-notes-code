package com.interview.notes.code.june23.test4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CopartEmployee {

    private List<Integer> items;
    private Function<String, List<Integer>> itemParser = null;// s -> Arrays.stream(s.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

    public CopartEmployee() {
        this.items = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Create a CopartEmployee object
        CopartEmployee employee = new CopartEmployee();

        // Fetch the items in the first group
        employee.fetchDetailsByGroupId(1);

        // Sort the items in decreasing order
        employee.sortItemsInDecreasingOrder();
    }

    public void fetchDetailsByGroupId(int groupId) {
        // Make an API call to fetch the items in the group
        //    String itemsString = fetchDetailsByGroupId(groupId);

        // Parse the items string into a list of integers
        // List<Integer> items = itemParser.apply(itemsString);

        // Update the UI with the items
        updateUI(items);
    }

    public void updateUI(List<Integer> items) {
        // Sort the items in ascending order
        Collections.sort(items);

        // Display the items in the UI
        System.out.println(items);
    }

    public void sortItemsInDecreasingOrder() {
        // Sort the items in decreasing order
        Collections.sort(items, Collections.reverseOrder());

        // Display the items in the UI
        System.out.println(items);
    }
}
