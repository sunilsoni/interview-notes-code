package com.interview.notes.code.months.year2023.july23.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 5. Website Pagination Java
 * There will be a list of items in the form of a 2-dimensional string array where each element contains [name,
 * relevance, price]. Given the sort column, the sort order (0: ascending, 1: descending), the number of items to be
 * displayed on each page (except for the last page which may have fewer), and a page number, determine the list of
 * item names in the specified page while respecting the item's order. Page numbering starts at 0.
 * Example
 * items = [['item 1', '10', '15], ['item2', '3', '4'], ['item3', '17', '8']]
 * sortParameter = 1
 * sortOrder = 0
 * itemsPerPage = 2
 * pageNumber = 1
 * •   n = 3items
 * •  Sort them by (relevance: 1) in ascending order (items =[['item2', '3', '4'], ['iteml', '10', '15] ['item3', '17', 8]])
 * •   Display up to 2 items in each page
 * •  The page 0contains 2item names ['item2', 'iteml']and page 1 contains only 1 item name, so result = 'item3'
 * Function Description
 * Complete the function fetchltemsToDisplayin the editor below.
 * fetchltemsToDisplayhas the following parameter(s):
 * string items[n][3]. a 2D array of arrays of strings in the form [name, relevance, price]
 * int sortParameter: the column of the items to sort on
 * int sortOrder:0 = ascending and 1 = descending
 * int itemsPerPage: the number of items per page
 * intpageNumber: the page number to display item names
 * Returns:
 * stringpageltems[m]: array of item names on the requested page in the order they are displayed
 * <p>
 * <p>
 * Constraints
 * •   1<n<1(£
 * •   1 <m <n
 * •   0 < relevance, price < 108
 * •   relevance and price are both integers
 * •   7 < itemsPerPage < 20
 * •   0< page Number < 10
 * Sample Input
 * STDIN              Function
 * 2                       items[2][] size n = 2
 * 3                       items[2][3] size of each item is always 3 [name, relevance, price]
 * pl 1 2        ->     items[2][3] = [[’pl*, ’I’, ’2’], [’p2•, ’2', ’1']]
 * p2 2 1
 * 0              -»     sortParameter = 0 (name)
 * 0              ■»    sortOrder = 0 (ascending)
 * 1                       itemsPerPage = 1
 * 0
 * Sample Output
 * Pl
 * Explanation
 * pageNumber = 0
 * The items are sorted by name, items column 0, in ascending order —»['pl', 'p2'J. Each page contains 7 item, so
 * page 0 contains only the first item in the sorted list, p 7.
 * •  There are n = 2items (items = [['pl', '7' '2], ['p2‘, '2', '!']]).
 * •   Sort them by (name: 0) in ascending order (items=[['p 1', T, 2], ['p2', '2', '7']]).
 * •   Up to 7 item can be displayed per page.
 * •  The page 0contains 1 item name pl in the sorted list [p1,p2]
 */
public class WebsitePagination2 {
    public static void main(String[] args) {
        List<List<String>> items = Arrays.asList(
                Arrays.asList("item1", "10", "15"),
                Arrays.asList("item2", "3", "4"),
                Arrays.asList("item3", "17", "8")
        );
        int sortParameter = 1;
        int sortOrder = 0;
        int itemsPerPage = 2;
        int pageNumber = 1;

        List<String> pageItems = fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        pageItems.forEach(System.out::println);
    }

    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder,
                                                   int itemsPerPage, int pageNumber) {
        // Sort items and collect into a new list
        List<List<String>> sortedItems = items.stream()
                .sorted((a, b) -> {
                    if (sortParameter == 0)
                        return a.get(sortParameter).compareTo(b.get(sortParameter)) *
                                (sortOrder == 0 ? 1 : -1);
                    else
                        return Integer.compare(Integer.parseInt(a.get(sortParameter)),
                                Integer.parseInt(b.get(sortParameter))) *
                                (sortOrder == 0 ? 1 : -1);
                })
                .collect(Collectors.toList());

        // Calculate start and end index of items on the given page
        int startIndex = pageNumber * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, sortedItems.size());

        // Return the item names on the given page as a new list
        return sortedItems.subList(startIndex, endIndex).stream()
                .map(item -> item.get(0))
                .collect(Collectors.toList());
    }
}
