package com.interview.notes.code.months.year2023.june23.test9;

import java.util.*;

/**
 * In Java Provide solution
 * Below are flight details
 * Source destination cost
 * Dfw nyc 100
 * NYC bos 30
 * BoS Dfw 200
 * <p>
 * DFW to BOS there is no direct flight how to make connection between this
 */
class Flight {
    String source;
    String destination;
    int cost;

    public Flight(String source, String destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }
}

public class FlightConnections {
    private static Map<String, List<Flight>> flightMap;

    public static void main(String[] args) {
        // Create the flight details
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("DFW", "NYC", 100));
        flights.add(new Flight("NYC", "BOS", 30));
        flights.add(new Flight("BOS", "DFW", 200));

        // Build the flight map
        buildFlightMap(flights);

        // Find all connections between DFW and BOS
        List<List<Flight>> connections = findAllConnections("DFW", "BOS");

        if (connections.isEmpty()) {
            System.out.println("No connections found.");
        } else {
            System.out.println("Connections found:");
            for (List<Flight> connection : connections) {
                for (Flight flight : connection) {
                    System.out.println(flight.source + " -> " + flight.destination + " (Cost: " + flight.cost + ")");
                }
                System.out.println();
            }
        }
    }

    private static void buildFlightMap(List<Flight> flights) {
        flightMap = new HashMap<>();

        for (Flight flight : flights) {
            if (!flightMap.containsKey(flight.source)) {
                flightMap.put(flight.source, new ArrayList<>());
            }
            flightMap.get(flight.source).add(flight);
        }
    }

    private static List<List<Flight>> findAllConnections(String source, String destination) {
        Set<String> visited = new HashSet<>();
        List<List<Flight>> allConnections = new ArrayList<>();
        findAllConnectionsDFS(source, destination, visited, new ArrayList<>(), allConnections);
        return allConnections;
    }

    private static void findAllConnectionsDFS(String source, String destination, Set<String> visited,
                                              List<Flight> currentConnection, List<List<Flight>> allConnections) {
        visited.add(source);

        if (source.equals(destination)) {
            allConnections.add(new ArrayList<>(currentConnection));
        } else if (flightMap.containsKey(source)) {
            for (Flight flight : flightMap.get(source)) {
                if (!visited.contains(flight.destination)) {
                    currentConnection.add(flight);
                    findAllConnectionsDFS(flight.destination, destination, visited, currentConnection, allConnections);
                    currentConnection.remove(currentConnection.size() - 1);
                }
            }
        }

        visited.remove(source);
    }
}
