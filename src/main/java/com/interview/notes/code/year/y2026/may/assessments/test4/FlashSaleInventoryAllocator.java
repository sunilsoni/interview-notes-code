package com.interview.notes.code.year.y2026.may.assessments.test4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlashSaleInventoryAllocator {

    public static List<Integer> getUnfulfilledCustomers(List<List<Integer>> requests, int totalInventory) {
        var reqList = requests.stream()
                .map(r -> new Request(r.get(0), r.get(1), r.get(2), r.get(3)))
                .toList();

        var groupedByBid = reqList.stream()
                .collect(Collectors.groupingBy(
                        r -> r.bid,
                        () -> new TreeMap<Integer, List<Request>>(Collections.reverseOrder()),
                        Collectors.toList()
                ));

        int inventory = totalInventory;

        for (var entry : groupedByBid.entrySet()) {
            if (inventory <= 0) break;

            var currentLevelRequests = entry.getValue();
            currentLevelRequests.sort(Comparator.comparingInt(r -> r.ts));

            var activeRequests = new ArrayList<>(currentLevelRequests);

            while (inventory > 0 && !activeRequests.isEmpty()) {
                int minQty = activeRequests.stream().mapToInt(r -> r.qty).min().orElse(0);
                int n = activeRequests.size();

                if (inventory >= n * minQty) {
                    inventory -= n * minQty;
                    for (var r : activeRequests) {
                        r.qty -= minQty;
                        r.allocated += minQty;
                    }
                    activeRequests.removeIf(r -> r.qty == 0);
                } else {
                    int rounds = inventory / n;
                    int remainder = inventory % n;
                    for (int i = 0; i < n; i++) {
                        var r = activeRequests.get(i);
                        r.qty -= rounds;
                        r.allocated += rounds;
                        if (i < remainder) {
                            r.qty--;
                            r.allocated++;
                        }
                    }
                    inventory = 0;
                }
            }
        }

        return reqList.stream()
                .filter(r -> r.allocated == 0)
                .map(r -> r.id)
                .sorted()
                .toList();
    }

    public static void main(String[] args) {
        test(1, List.of(
                List.of(1, 2, 5, 0),
                List.of(2, 1, 4, 2),
                List.of(3, 5, 4, 6)
        ), 3, List.of(3));

        test(2, List.of(
                List.of(101, 3, 10, 15),
                List.of(102, 2, 8, 20),
                List.of(103, 5, 8, 25),
                List.of(104, 4, 5, 30)
        ), 8, List.of(104));

        test(3, List.of(
                List.of(1, 5, 5, 0),
                List.of(2, 7, 8, 1),
                List.of(3, 7, 5, 1),
                List.of(4, 10, 3, 3)
        ), 18, List.of(4));

        var largeRequests = new ArrayList<List<Integer>>();
        for (int i = 1; i <= 10000; i++) {
            largeRequests.add(List.of(i, 100, 10, i));
        }
        test(4, largeRequests, 50000, List.of());

        var expectedLargeFail = IntStream.rangeClosed(5001, 10000).boxed().toList();
        test(5, largeRequests, 5000, expectedLargeFail);
    }

    private static void test(int testId, List<List<Integer>> requests, int inventory, List<Integer> expected) {
        var result = getUnfulfilledCustomers(requests, inventory);
        if (result.equals(expected)) {
            System.out.println("Test Case " + testId + ": PASS");
        } else {
            System.out.println("Test Case " + testId + ": FAIL. Expected " + expected + ", Got " + result);
        }
    }

    private static class Request {
        int id;
        int qty;
        int bid;
        int ts;
        int allocated;

        Request(int id, int qty, int bid, int ts) {
            this.id = id;
            this.qty = qty;
            this.bid = bid;
            this.ts = ts;
            this.allocated = 0;
        }
    }
}