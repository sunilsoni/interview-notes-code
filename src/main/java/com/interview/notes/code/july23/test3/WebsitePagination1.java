import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WebsitePagination1 {
    public static void main(String[] args) {
        String[][] items = {{"item1", "10", "15"}, {"item2", "3", "4"}, {"item3", "17", "8"}};
        int sortParameter = 1;
        int sortOrder = 0;
        int itemsPerPage = 2;
        int pageNumber = 1;

        List<String> pageItems = fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        pageItems.forEach(System.out::println);
    }

    public static List<String> fetchItemsToDisplay(String[][] items, int sortParameter, int sortOrder,
                                                   int itemsPerPage, int pageNumber) {
        // Sort items and collect into a new list
        List<String[]> sortedItems = Arrays.stream(items)
                .sorted((a, b) -> {
                    if (sortParameter == 0)
                        return a[sortParameter].compareTo(b[sortParameter]) * (sortOrder == 0 ? 1 : -1);
                    else
                        return Integer.compare(Integer.parseInt(a[sortParameter]),
                                Integer.parseInt(b[sortParameter])) *
                                (sortOrder == 0 ? 1 : -1);
                })
                .collect(Collectors.toList());

        // Calculate start and end index of items on the given page
        int startIndex = pageNumber * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, sortedItems.size());

        // Return the item names on the given page as a new list
        return sortedItems.subList(startIndex, endIndex).stream()
                .map(item -> item[0])
                .collect(Collectors.toList());
    }
}
