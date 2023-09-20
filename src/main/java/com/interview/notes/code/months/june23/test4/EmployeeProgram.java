package com.interview.notes.code.months.june23.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeProgram {
    private boolean isAscendingOrder = true;

    public void handleButtonClick(int groupId) {
        //fetchDetailsByGroupId(groupId)
        //      .thenAccept(this::updateUI);
    }

    private void fetchDetailsByGroupId(int groupId) {
        // Implement the logic to fetch details from the remote API
        // and return a Promise or a CompletableFuture with the data
        // as a space delimited string of integers.
    }

    private void updateUI(String data) {
        List<Integer> items = parseItems(data);
        if (!isAscendingOrder) {
            Collections.reverse(items);
        }
        // Implement the logic to update the UI with the items in ascending
        // or descending order based on the current order setting.
    }

    private List<Integer> parseItems(String data) {
        String[] itemsArray = data.split(" ");
        Integer[] items = new Integer[itemsArray.length];
        for (int i = 0; i < itemsArray.length; i++) {
            items[i] = Integer.parseInt(itemsArray[i]);
        }
        return Arrays.asList(items);
    }

    public void toggleOrder() {
        isAscendingOrder = !isAscendingOrder;
    }
}
