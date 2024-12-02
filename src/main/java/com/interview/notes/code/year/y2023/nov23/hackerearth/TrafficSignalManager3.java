package com.interview.notes.code.year.y2023.nov23.hackerearth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrafficSignalManager3 {
    public static List<Road> solve(int N, int[][] A) {
        List<Road> roads = new ArrayList<>();
        for (int[] roadInfo : A) {
            roads.add(new Road(roadInfo[0], roadInfo[1], roadInfo[2], roadInfo[3]));
        }

        // Sort roads based on the time to clear and then by road number
        roads.sort(Comparator.comparingInt(Road::timeToClear).thenComparingInt(r -> r.number));

        int currentTime = 0;
        for (Road road : roads) {
            road.waitingTime = currentTime;
            currentTime += road.timeToClear();
        }

        Collections.sort(roads); // Sort by road number for final output
        return roads;
    }

    public static void main(String[] args) {

        int N = 3;
        int[][] A = {
                {1, 0, 1, 1},
                {2, 1, 0, 0},
                {3, 0, 0, 5}
        };

        List<List<Integer>> result = Crossroad1.solve(N, A);
        //int[][] result = Crossroad.solve(N, A);

        for (List<Integer> item : result) {
            System.out.println(item.get(0) + " " + item.get(1));
        }
    }

    static class Road implements Comparable<Road> {
        int number;
        int vehicles;
        int waitingTime;

        Road(int number, int twoWheelers, int threeWheelers, int fourWheelers) {
            this.number = number;
            this.vehicles = twoWheelers + threeWheelers + fourWheelers;
            this.waitingTime = 0; // Initialized to 0, will be calculated later
        }

        // Method to calculate the time required to clear this road
        int timeToClear() {
            return (vehicles + 2) / 3; // Every 3 vehicles take 1 second to clear
        }

        @Override
        public int compareTo(Road other) {
            return this.number - other.number;
        }
    }
}
