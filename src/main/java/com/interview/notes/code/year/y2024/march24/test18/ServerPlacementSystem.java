package com.interview.notes.code.year.y2024.march24.test18;

import java.util.ArrayList;
import java.util.List;

// Server class with size and power attributes
class Server {
    int sizeU;
    double powerKVA;

    Server(int sizeU, double powerKVA) {
        this.sizeU = sizeU;
        this.powerKVA = powerKVA;
    }
}


// DataCenter class that holds a collection of racks
class DataCenter {
    List<Rack> racks = new ArrayList<>();

    // Method to find the first rack that can fit the server
    Rack findAvailableRack(Server server) {
        for (Rack rack : racks) {
            if (rack.addServer(server)) {
                return rack;
            }
        }
        return null; // no available rack found
    }

    // Initializes data center with a specified number of racks
    void initializeRacks(int numberOfRacks) {
        for (int i = 0; i < numberOfRacks; i++) {
            racks.add(new Rack());
        }
    }
}

public class ServerPlacementSystem {
    public static void main(String[] args) {
        // Initialize data center with racks
        DataCenter awsDataCenter = new DataCenter();
        awsDataCenter.initializeRacks(10); // for example, initialize 10 racks

        // Create a new server
        Server newServer = new Server(4, 3.0); // a 4U server consuming 3kVA

        // Find the first available rack and add the server to it
        Rack availableRack = awsDataCenter.findAvailableRack(newServer);
        if (availableRack != null) {
            System.out.println("Server added to a rack.");
        } else {
            System.out.println("No available rack could be found for the server.");
        }
    }
}
