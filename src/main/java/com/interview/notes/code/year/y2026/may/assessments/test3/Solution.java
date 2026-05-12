package com.interview.notes.code.year.y2026.may.assessments.test3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static List<Integer> getUnfulfilledCustomers(List<List<Long>> requests, long totalInventory) {
        var reqList = requests.stream()
                .map(r -> new Request(r.get(0), r.get(1), r.get(2), r.get(3)))
                .toList();

        var groupedByBid = reqList.stream()
                .collect(Collectors.groupingBy(
                        r -> r.bid,
                        () -> new TreeMap<Long, List<Request>>(Collections.reverseOrder()),
                        Collectors.toList()
                ));

        long inventory = totalInventory;

        for (var entry : groupedByBid.entrySet()) {
            if (inventory <= 0) break;

            var activeReq = new ArrayList<>(entry.getValue());
            activeReq.sort(Comparator.comparingLong(r -> r.ts));

            while (inventory > 0 && !activeReq.isEmpty()) {
                long minQty = activeReq.stream().mapToLong(r -> r.qty).min().orElse(0);
                long n = activeReq.size();

                if (inventory >= n * minQty) {
                    inventory -= n * minQty;
                    for (var r : activeReq) {
                        r.qty -= minQty;
                        r.allocated += minQty;
                    }
                    activeReq.removeIf(r -> r.qty == 0);
                } else {
                    long round = inventory / n;
                    long remainder = inventory % n;
                    for (int i = 0; i < n; i++) {
                        var r = activeReq.get(i);
                        r.qty -= round;
                        r.allocated += round;
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
                .map(r -> (int) r.id)
                .sorted()
                .toList();
    }

    private static class Request {
        long id, qty, bid, ts, allocated;
        Request(long id, long qty, long bid, long ts) {
            this.id = id;
            this.qty = qty;
            this.bid = bid;
            this.ts = ts;
            this.allocated = 0;
        }
    }
}