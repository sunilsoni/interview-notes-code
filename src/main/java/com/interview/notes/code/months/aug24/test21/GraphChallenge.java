package com.interview.notes.code.months.aug24.test21;

import java.util.*;

/*
Graph Challenge
Have the function GraphChallenge (strArr) take strArr which will be an array of strings which models a non-looping weighted Graph. The structure of the array will be as follows: The first element in the array will be the number of nodes N (points) in the array as a string. The next N elements will be the nodes which can be anything (A, B, C .. Brick Street, Main Street. etc.). Then after the Nth element, the rest of the elements in the array will be the connections between all of the nodes along with their weights (integers) separated by the pipe symbol (I. They will look like this: (A|B|3, B|C|12 . Brick Street|Main Street|14 . etc.). Although, there may exist no connections at all.
An example of strArr may be: ["4","A","B","C","D","A|B|1","B|DI9" "B|C|3","CID4"]. Your program should return the shortest path when the weights are added up from node to node from the first Node to the last Node in the array separated by dashes. So in the example above the output should be A-B-C-D. Here is another example with strArr being ["7",A","B""C","D","E",F","G" "A|B|1","A|E|9","B|C|2" "CIDI1","D FI2" "E|DI6","FIG|"]. The output for this array should be A-B-C-D-F-G. There will only ever be one shortest path for the array.
If no path between the first and last node exists, return -1. The array will at minimum have two nodes. Also, the connection A-B for example, means that A can get to B and B can get to A. A path may not go through any Node more than once.


Examples
Input: new String[] {"4","A","B","C","D", "AIBl2", "CIBl11", "CIDl3", "BlD12"}
Output: A-B-D

Input: new String[] {"4", "x","y","z", "w", "x|y|2", "y|z|14", "z|y|31"}
Output: -1
 */
public class GraphChallenge {
    public static String GraphChallenge(String[] strArr) {
        // Parse input and create graph
        int n = Integer.parseInt(strArr[0]);
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        // Build graph
        for (int i = 1; i <= n; i++) {
            graph.put(strArr[i], new HashMap<>());
        }
        for (int i = n + 1; i < strArr.length; i++) {
            String[] edge = strArr[i].split("\\|");
            graph.get(edge[0]).put(edge[1], Integer.parseInt(edge[2]));
            graph.get(edge[1]).put(edge[0], Integer.parseInt(edge[2]));
        }

        // Run Dijkstra's algorithm
        String start = strArr[1];
        String end = strArr[n];
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));

        for (String node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distances.put(start, 0);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.name.equals(end)) break;

            for (Map.Entry<String, Integer> neighbor : graph.get(current.name).entrySet()) {
                int newDist = distances.get(current.name) + neighbor.getValue();
                if (newDist < distances.get(neighbor.getKey())) {
                    distances.put(neighbor.getKey(), newDist);
                    previous.put(neighbor.getKey(), current.name);
                    pq.offer(new Node(neighbor.getKey(), newDist));
                }
            }
        }

        // Reconstruct path
        if (distances.get(end) == Integer.MAX_VALUE) return "-1";

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return String.join("-", path);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(GraphChallenge(new String[]{"4", "A", "B", "C", "D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"}));
        System.out.println(GraphChallenge(new String[]{"4", "x", "y", "z", "w", "x|y|2", "y|z|14", "z|y|31"}));
        // Add more test cases here
    }

    private static class Node {
        String name;
        int distance;

        Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }
}
