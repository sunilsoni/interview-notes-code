package com.interview.notes.code.year.y2024.aug24.test7;

import java.util.LinkedList;

class Graph {
    private final int V; // Number of vertices
    private final LinkedList<Integer>[] adj; // Adjacency list

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Depth First Traversal (starting from vertex 2)");
        g.DFS(2);
    }

    // Function to add an edge to graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // DFS traversal from a given source s
    void DFS(int s) {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(s, visited);
    }

    // A recursive function used by DFS
    void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
}
