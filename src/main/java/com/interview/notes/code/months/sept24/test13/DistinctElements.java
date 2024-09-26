package com.interview.notes.code.months.sept24.test13;

import java.util.*;

public class DistinctElements {

    public static int getMaximumDistinctCount(List<Integer> a, List<Integer> b, int k) {
        Set<Integer> distinctElements = new HashSet<>(a);
        Set<Integer> elementsInA = new HashSet<>(a);

        // If a already has distinctly elements equal to length of a, return that length
        if (distinctElements.size() == a.size()) {
            return distinctElements.size();
        }

        // Frequency map for elements in b
        Map<Integer, Integer> freqMapB = new HashMap<>();
        for (int num : b) {
            freqMapB.put(num, freqMapB.getOrDefault(num, 0) + 1);
        }

        // Get the unique elements in b that are not in a
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> freqMapB.get(y) - freqMapB.get(x));
        for (int num : b) {
            if (!elementsInA.contains(num)) {
                pq.offer(num);
            }
        }

        int swapsDone = 0;
        while (!pq.isEmpty() && swapsDone < k) {
            int newDistinctElement = pq.poll();
            distinctElements.add(newDistinctElement);
            swapsDone++;
        }

        return distinctElements.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            a.add(scanner.nextInt());
        }

        int n2 = scanner.nextInt();
        List<Integer> b = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            b.add(scanner.nextInt());
        }

        int k = scanner.nextInt();

        int result = getMaximumDistinctCount(a, b, k);
        System.out.println(result);

        scanner.close();
    }
}