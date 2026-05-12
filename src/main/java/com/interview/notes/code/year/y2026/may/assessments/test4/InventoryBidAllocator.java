package com.interview.notes.code.year.y2026.may.assessments.test4;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InventoryBidAllocator {

    public static List<Integer> getUnfulfilledCustomers(List<List<Integer>> requests, int totalInventory) {
        var byBid = requests.stream()
                .map(Request::new)
                .collect(Collectors.groupingBy(Request::bid, TreeMap::new, Collectors.toList()))
                .descendingMap();

        var got = new HashSet<Integer>();
        long stock = totalInventory;

        for (var group : byBid.values()) {
            group.sort(Comparator.comparingInt(Request::time));
            long need = group.stream().mapToLong(Request::qty).sum();

            if (stock <= 0) {
                break;
            }

            long firstRound = Math.min(stock, group.size());

            IntStream.range(0, (int) firstRound)
                    .mapToObj(group::get)
                    .map(Request::id)
                    .forEach(got::add);

            stock -= Math.min(stock, need);
        }

        return requests.stream()
                .map(r -> r.get(0))
                .filter(id -> !got.contains(id))
                .sorted()
                .toList();
    }

    static void test(String name, List<List<Integer>> req, int stock, List<Integer> exp) {
        var act = getUnfulfilledCustomers(req, stock);
        System.out.println(name + " : " + (act.equals(exp) ? "PASS" : "FAIL " + act));
    }

    public static void main(String[] args) {
        test("Sample 0", List.of(
                List.of(1, 2, 5, 0),
                List.of(2, 1, 4, 2),
                List.of(3, 5, 4, 6)
        ), 3, List.of(3));

        test("Sample 1", List.of(
                List.of(101, 3, 10, 15),
                List.of(102, 2, 8, 20),
                List.of(103, 5, 8, 25),
                List.of(104, 4, 5, 30)
        ), 8, List.of(104));

        test("Main Example", List.of(
                List.of(1, 5, 5, 0),
                List.of(2, 7, 8, 1),
                List.of(3, 7, 5, 1),
                List.of(4, 10, 3, 1)
        ), 18, List.of(4));

        test("Zero Stock", List.of(
                List.of(3, 1, 5, 1),
                List.of(1, 1, 5, 2),
                List.of(2, 1, 5, 3)
        ), 0, List.of(1, 2, 3));

        test("Tie Round Robin", List.of(
                List.of(10, 5, 7, 2),
                List.of(5, 5, 7, 1),
                List.of(7, 5, 7, 3)
        ), 2, List.of(7));

        var large = IntStream.rangeClosed(1, 10_000)
                .mapToObj(i -> List.of(i, 1, 1, i))
                .toList();

        var largeExpected = IntStream.rangeClosed(5_001, 10_000)
                .boxed()
                .toList();

        test("Large Data", large, 5_000, largeExpected);
    }

    record Request(int id, int qty, int bid, int time) {
        Request(List<Integer> r) {
            this(r.get(0), r.get(1), r.get(2), r.get(3));
        }
    }
}