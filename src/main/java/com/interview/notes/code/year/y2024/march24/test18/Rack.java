package com.interview.notes.code.year.y2024.march24.test18;

import java.util.ArrayList;
import java.util.List;

class Rack {
    // Existing attributes...
    int capacityU = 8; // total capacity in U
    double capacityKVA = 5.0; // total power capacity in kVA
    int usedSpaceU = 0; // currently used space in U
    double usedPowerKVA = 0.0; // currently used power in kVA
    // A list to keep track of the servers in this rack
    List<Server> serversInRack = new ArrayList<>();

    // Existing methods...

    // Modified addServer method to track the servers
    boolean addServer(Server server) {
        if (canAddServer(server)) {
            usedSpaceU += server.sizeU;
            usedPowerKVA += server.powerKVA;
            serversInRack.add(server); // Keep track of the server
            return true;
        }
        return false;
    }

    // Method to check if a server can be added without actually adding it
    boolean canAddServer(Server server) {
        return server.sizeU + usedSpaceU <= capacityU && server.powerKVA + usedPowerKVA <= capacityKVA;
    }

    // Method to get a list of servers in this rack (if needed)
    List<Server> getServersInRack() {
        return new ArrayList<>(serversInRack); // Return a copy to avoid external modification
    }
}
