package com.interview.notes.code.year.y2025.september.assesment.test8;

import java.util.*;

/*
Feature: Degrees of separation between businesses + shortest relationship path.

What this file contains:
- A simple graph model of companies (nodes) and their purchase relationships (undirected edges).
- A BFS-based algorithm to compute the shortest path between two companies.
- A definition for "degree of separation" used in this context: the number of intermediary businesses
  (nodes strictly between the start and the target on the shortest path). Therefore:
  - If A is directly connected to B, degree(A, B) = 0 (no intermediaries).
  - If A is connected to C via B (A-B-C), degree(A, C) = 1 (B is the only intermediary).
- Console examples demonstrating both the degree and the actual shortest path.

Why BFS?
- Breadth-First Search on an unweighted graph finds shortest paths in O(V + E) time.
- It explores the graph level by level, ensuring the first time we reach the target is via the
  minimum number of edges.

Returned values and edge cases:
- If either company is missing, or no path exists between them, degreeOfSeparation returns -1 and shortestPath returns an empty list.
- If start equals target, shortestPath returns a singleton list [start], and degreeOfSeparation returns 0 (no intermediaries between a company and itself).

Example:
- Company A purchases dairy from Company B.
- Company B purchases pet feed from Company C.
- Relationship chain: A <--> B <--> C
- Degree of separation between A and C = 1 (B is the intermediary). The shortest path is [A, B, C].
*/
// Company class to store company details
class Company {
    private final int id;        // unique identifier
    private final String name;   // company name
    private final String address;
    private final String type;

    public Company(int id, String name, String address, String type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; // For easy display
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// BusinessNetwork class manages companies and their relationships
class BusinessNetwork {
    private final Map<Integer, Company> companies = new HashMap<>();
    private final Map<Company, List<Company>> relations = new HashMap<>();

    // Add a company to the network
    public void addCompany(Company company) {
        companies.put(company.getId(), company);
        relations.putIfAbsent(company, new ArrayList<>());
    }

    // Add a relationship (bidirectional: A <-> B)
    public void addRelation(int companyId1, int companyId2) {
        Company c1 = companies.get(companyId1);
        Company c2 = companies.get(companyId2);
        if (c1 == null || c2 == null) return;

        relations.get(c1).add(c2);
        relations.get(c2).add(c1);
    }

    /**
     * Returns the shortest path (as a list of companies) between two company IDs using BFS.
     * If no path exists, returns an empty list.
     */
    public List<Company> shortestPath(int id1, int id2) {
        Company start = companies.get(id1);
        Company target = companies.get(id2);
        if (start == null || target == null) return Collections.emptyList();
        if (start.equals(target)) return List.of(start);

        Queue<Company> queue = new LinkedList<>();
        Map<Company, Company> parent = new HashMap<>(); // to reconstruct path
        Set<Company> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Company current = queue.poll();
            if (current.equals(target)) break;
            for (Company neighbor : relations.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct path if target was reached
        if (!parent.containsKey(target) && !Objects.equals(start, target)) {
            return Collections.emptyList();
        }
        List<Company> path = new ArrayList<>();
        Company cur = target;
        path.add(cur);
        while (!cur.equals(start)) {
            cur = parent.get(cur);
            if (cur == null) {
                // target not reached
                return Collections.emptyList();
            }
            path.add(cur);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Degree of separation definition used here:
     * - We count the number of intermediary businesses between start and target.
     * - For a direct relation A <-> B, degree = 0 (no intermediary).
     * - For A <-> B <-> C, degree between A and C = 1 (B is the single intermediary).
     * If no path exists or either company is unknown, returns -1.
     */
    public int degreeOfSeparation(int id1, int id2) {
        List<Company> path = shortestPath(id1, id2);
        if (path.isEmpty()) return -1;
        // intermediaries = nodes on path minus the two endpoints
        int intermediaries = Math.max(0, path.size() - 2);
        return intermediaries;
    }
}

// -------------------- Testing --------------------
public class Main {
    public static void main(String[] args) {
        BusinessNetwork network = new BusinessNetwork();

        // Create companies
        Company A = new Company(1, "Grocery Store", "123 Main St", "Retail");
        Company B = new Company(2, "Dairy Farm", "456 Farm Rd", "Supplier");
        Company C = new Company(3, "Pet Supplier", "789 Pet Ln", "Wholesale");

        // Add companies to network
        network.addCompany(A);
        network.addCompany(B);
        network.addCompany(C);

        // Define relationships
        network.addRelation(1, 2); // A <-> B
        network.addRelation(2, 3); // B <-> C

        // Run test cases
        runTest(network, 1, 2, 0); // A-B degree = 0
        runTest(network, 1, 3, 1); // A-C degree = 1
        runTest(network, 2, 3, 0); // B-C degree = 0
        runTest(network, 1, 4, -1); // Nonexistent company

        // Large network test (10000 companies in chain)
        BusinessNetwork bigNetwork = new BusinessNetwork();
        for (int i = 0; i <= 10000; i++) {
            bigNetwork.addCompany(new Company(i, "Company" + i, "Addr" + i, "Type"));
        }
        for (int i = 0; i < 10000; i++) {
            bigNetwork.addRelation(i, i + 1);
        }
        runTest(bigNetwork, 0, 10000, 9999); // Expected degree = 9999
    }

    // Test helper function
    private static void runTest(BusinessNetwork network, int id1, int id2, int expected) {
        int actual = network.degreeOfSeparation(id1, id2);
        List<Company> path = network.shortestPath(id1, id2);
        String pathStr = path.isEmpty() ? "<no path>" : String.join(" -> ", path.stream().map(Company::getName).toList());
        System.out.println("Input: " + id1 + " -> " + id2 +
                " | Expected Degree: " + expected +
                " | Actual Degree: " + actual +
                " | Path: " + pathStr +
                " | Note: Degree counts intermediaries (nodes between endpoints)." +
                " | " + (expected == actual ? "PASS" : "FAIL"));
    }
}