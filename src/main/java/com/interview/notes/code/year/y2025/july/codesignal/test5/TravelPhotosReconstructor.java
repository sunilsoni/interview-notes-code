package com.interview.notes.code.year.y2025.july.codesignal.test5;

import java.util.*;

public class TravelPhotosReconstructor {

    public static int[] solution(int[][] travelPhotos) {
        int m = travelPhotos.length;
        if (m == 1) {
            return new int[]{ travelPhotos[0][0], travelPhotos[0][1] };
        }

        // 1) Build adjacency list + degree count
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer,Integer> degree = new HashMap<>();
        for (int[] p : travelPhotos) {
            adj.computeIfAbsent(p[0], k->new ArrayList<>()).add(p[1]);
            adj.computeIfAbsent(p[1], k->new ArrayList<>()).add(p[0]);
            degree.put(p[0], degree.getOrDefault(p[0],0)+1);
            degree.put(p[1], degree.getOrDefault(p[1],0)+1);
        }

        // 2) Collect the two endpoints (degree == 1)
        Set<Integer> endpoints = new HashSet<>();
        for (Map.Entry<Integer,Integer> e : degree.entrySet()) {
            if (e.getValue() == 1) {
                endpoints.add(e.getKey());
            }
        }

        // 3) Pick start as the first endpoint that appears in travelPhotos
        int start = 0;
        outer:
        for (int[] p : travelPhotos) {
            if (endpoints.contains(p[0])) {
                start = p[0];
                break outer;
            }
            if (endpoints.contains(p[1])) {
                start = p[1];
                break outer;
            }
        }

        // 4) Walk the path
        int n = m + 1;
        int[] path = new int[n];
        path[0] = start;
        // second node is the only neighbor of the start
        path[1] = adj.get(start).get(0);

        for (int i = 2; i < n; i++) {
            List<Integer> neigh = adj.get(path[i-1]);
            int prev = path[i-2];
            path[i] = (neigh.get(0)==prev ? neigh.get(1) : neigh.get(0));
        }

        return path;
    }

    // quick test
    public static void main(String[] args) {
        int[][] photos = {{3,5},{1,4},{2,4},{1,5}};
        int[] want    = {3,5,1,4,2};
        int[] got     = solution(photos);
        System.out.println(Arrays.equals(got, want)
            ? "PASS: " + Arrays.toString(got)
            : "FAIL: got " + Arrays.toString(got)
                   + " expected " + Arrays.toString(want));
    }
}