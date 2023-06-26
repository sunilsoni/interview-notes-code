package com.interview.notes.code.june23.test9;

import java.util.*;

class Flight1 {
    String source;
    String destination;
    int cost;

    public Flight1(String source, String destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }
}

public class FlightConnection1 {
    private static Map<String, List<Flight1>> flightMap;

    public static void main(String[] args) {
        // Create the flight details
        List<Flight1> flights = new ArrayList<>();
        flights.add(new Flight1("DFW", "NYC", 100));
        flights.add(new Flight1("NYC", "BOS", 30));
        flights.add(new Flight1("BOS", "DFW", 200));

        // Build the flight map
        buildFlightMap(flights);

        // Find the connection between DFW and BOS
        List<Flight1> connection = findConnection("DFW", "BOS");

        if (connection != null) {
            System.out.println("Connection found!");
            for (Flight1 flight : connection) {
                System.out.println(flight.source + " -> " + flight.destination + " (Cost: " + flight.cost + ")");
            }
        } else {
            System.out.println("No connection found.");
        }
    }

    private static void buildFlightMap(List<Flight1> flights) {
        flightMap = new HashMap<>();

        for (Flight1 flight : flights) {
            if (!flightMap.containsKey(flight.source)) {
                flightMap.put(flight.source, new ArrayList<>());
            }
            flightMap.get(flight.source).add(flight);
        }
    }

    private static List<Flight1> findConnection(String source, String destination) {
        Set<String> visited = new HashSet<>();
        return findConnectionDFS(source, destination, visited);
    }

    private static List<Flight1> findConnectionDFS(String source, String destination, Set<String> visited) {
        if (source.equals(destination)) {
            return new ArrayList<>(); // Found the destination
        }

        visited.add(source);

        if (!flightMap.containsKey(source)) {
            return null; // No flights available from the current source
        }

        for (Flight1 flight : flightMap.get(source)) {
            if (!visited.contains(flight.destination)) {
                List<Flight1> connection = findConnectionDFS(flight.destination, destination, visited);
                if (connection != null) {
                    connection.add(0, flight); // Add the current flight to the connection
                    return connection;
                }
            }
        }

        return null; // No connection found
    }
}
