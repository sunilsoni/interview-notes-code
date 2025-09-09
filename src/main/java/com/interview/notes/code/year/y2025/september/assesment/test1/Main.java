package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;

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
        return name; // For easy display in outputs
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

    // -------------------- MAIN LOGIC --------------------
    // Find degree of separation using BFS
    public int degreeOfSeparation(int id1, int id2) {
        Company start = companies.get(id1);   // starting company
        Company target = companies.get(id2);  // target company

        // If either company is missing, no path can exist
        if (start == null || target == null) return -1;

        // Queue for BFS traversal
        Queue<Company> queue = new LinkedList<>();

        // Map to keep track of distance from start company
        // (distance means "number of hops taken so far")
        Map<Company, Integer> distance = new HashMap<>();

        // Start BFS with the starting company
        queue.add(start);
        distance.put(start, 0); // start point has distance 0

        // BFS loop
        while (!queue.isEmpty()) {
            Company current = queue.poll(); // take next company in queue
            int currentDist = distance.get(current); // distance from start

            // If we reached the target company → stop and return degree
            if (current.equals(target)) {
                // Degree = pathLength - 1
                // Example: A -> B -> C → path length = 2, degree = 1
                return currentDist - 1;
            }

            // Explore all connected neighbors (suppliers/customers)
            for (Company neighbor : relations.getOrDefault(current, Collections.emptyList())) {
                // Only visit unvisited neighbors
                if (!distance.containsKey(neighbor)) {
                    queue.add(neighbor); // push neighbor into BFS queue
                    distance.put(neighbor, currentDist + 1); // record distance
                }
            }
        }

        // If BFS finished without finding target → no path exists
        return -1;
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
        System.out.println("Input: " + id1 + " -> " + id2 +
                " | Expected: " + expected +
                " | Got: " + actual +
                " | " + (expected == actual ? "PASS" : "FAIL"));
    }
}