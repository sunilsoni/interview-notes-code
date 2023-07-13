package com.interview.notes.code.july23.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WebsitePagination {

    public static void main(String[] args) {
        String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
        int sortParameter = 1;
        int sortOrder = 0;
        int itemsPerPage = 2;
        int pageNumber = 1;

        List<String> pageItems = fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        for (String itemName : pageItems) {
            System.out.println(itemName);
        }
    }

    public static List<String> fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder,
                                                   int itemsPerPage, int pageNumber) {
        // Sort items array
        Arrays.sort(items, new Comparator<String[]>() {
            public int compare(String[] a, String[] b) {
                if (sortParameter == 0)
                    return a[sortParameter].compareTo(b[sortParameter]) * (sortOrder == 0 ? 1 : -1);
                else
                    return Integer.compare(Integer.parseInt(a[sortParameter]), Integer.parseInt(b[sortParameter])) *
                            (sortOrder == 0 ? 1 : -1);
            }
        });

        // Calculate start and end index of items on the given page
        int startIndex = pageNumber * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, items.length);

        // Collect and return the item names on the given page
        List<String> pageItems = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            pageItems.add(items[i][0]);
        }

        return pageItems;
    }


}
