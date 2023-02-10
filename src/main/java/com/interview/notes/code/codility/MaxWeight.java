package com.interview.notes.code.codility;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 * Max Weight   COmplete in Java
 *
 * Adam was given N bags of Nuts. Each bag has a sticker on it. The contents of the sticker are two integers X and Y, denoting the type of nut and total weight of nuts (in kilograms) present in that bag respectively. Adam being a shopkeeper wanted to keep the nuts to sell, but as there were many types of nuts and each customer takes a sample of nuts to taste before buying, Adam feared that the customers will finish all the nuts by taking just the sample. So he decided to keep only 2 types of nuts in the shop, and the rest all the nutbags will go to the storehouse.
 *
 * Also, the nutbags should be arranged alternatively. This means if he decided to keep the nutbag on type 2 & 3, then he will keep them in the shop in order 232323...... (keeping the bags alternatively). Find out the maximum sum of weights of bags he will keep in the store.
 * Function Description  In the provided code snippet, implement the provided maxWeight ( .. . ) method using the variables to print the maximum sum of weights of bags he will keep in the store. You can write your code in the space below the phrase "WRITE YOUR LOGIC HERE".
 *
 *
 * There will be multiple test cases running so the Input and Output should match exactly as provided. The base Output variable result is set to a default value of -404 which can be modified. Additionally, you can add or remove these output variables.
 * Input Format  The first line contains N the number of bags.
 * Next, N lines contain two integers X and Y type of nut & the weight of the ith bag res
 *
 * Sample Input
 *
 * 5 --- denotes N 15 14 22 35 26
 *
 * Constraints  2 <= N <= 105 1<= /X/ <= 105. 1<= /y/ <= 109.
 *
 * each denoting the pectively.
 */
public class MaxWeight {
    public static int maxWeight1(int N,int[] X,int[] Y) {
        // this is the default output. You can change it.
        int result = -404;
        // Create a map to store the nut type and its weight
        Map<Integer, Integer> nutMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int type = X[i];
            int weight = Y[i];
            if (nutMap.containsKey(type)) {
                nutMap.put(type, nutMap.get(type) + weight);
            } else {
                nutMap.put(type, weight);
            }
        }
        // Sort the nutMap by value
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(nutMap.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        // Keep track of the two largest nuts
        int firstLargest = 0, secondLargest = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            if (count == 0) {
                firstLargest = entry.getValue();
            } else if (count == 1) {
                secondLargest = entry.getValue();
                break;
            }
            count++;
        }
        result = firstLargest + secondLargest;
        return result;
    }
    public static int maxWeight2(int N, int[] X, int[] Y) {
        int result = -404;

        ArrayList<Bag> bags = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            bags.add(new Bag(X[i], Y[i]));
        }

        Collections.sort(bags, (a, b) -> Integer.compare(b.weight, a.weight));

        int type1 = -1, type2 = -1;
        int weight1 = 0, weight2 = 0;
        for (int i = 0; i < N; i++) {
            Bag bag = bags.get(i);
            if (bag.type == type1 || bag.type == type2) {
                continue;
            }
            if (type1 == -1) {
                type1 = bag.type;
                weight1 = bag.weight;
            } else {
                type2 = bag.type;
                weight2 = bag.weight;
                break;
            }
        }

        int i = 0, j = 0;
        int sum = 0;
        while (i < N && j < N) {
            if (bags.get(i).type == type1) {
                sum += bags.get(i).weight;
                j += 2;
            } else {
                sum += bags.get(j).weight;
                i += 2;
            }
        }

        result = sum;
        return result;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);
        // INPUT [uncomment & modify if required]
        int N = sc.nextInt();
        int X[] = new int[N];
        int Y[] = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }
        sc.close();
        // OUTPUT [uncomment & modify if required]
        System.out.println(maxWeight(N, X, Y));
    }

    private static class Bag {
        int type;
        int weight;

        public Bag(int type, int weight) {
            this.type = type;
            this.weight = weight;
        }
    }

    public static int maxWeight(int N, int[] X, int[] Y) {
        int result = -404;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(X[i])) {
                map.put(X[i], Y[i]);
            } else {
                map.put(X[i], map.get(X[i]) + Y[i]);
            }
        }
        int max1 = 0;
        int max2 = 0;
        int type1 = 0;
        int type2 = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max1) {
                max2 = max1;
                max1 = entry.getValue();
                type2 = type1;
                type1 = entry.getKey();
            } else if (entry.getValue() > max2) {
                max2 = entry.getValue();
                type2 = entry.getKey();
            }
        }
        result = max1 + max2;
        return result;
    }
}
