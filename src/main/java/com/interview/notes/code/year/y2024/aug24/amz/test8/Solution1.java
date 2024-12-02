package com.interview.notes.code.year.y2024.aug24.amz.test8;

import java.util.*;

public class Solution1 {
    public static List<Integer> getServerIds(int num_servers, List<Integer> requests) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Server> serverQueue = new PriorityQueue<>(Comparator.comparingInt(server -> server.load));

        // Initialize the priority queue with all servers
        for (int i = 0; i < num_servers; i++) {
            serverQueue.add(new Server(i, 0));
        }

        for (int request : requests) {
            // Create a temporary list to store the removed servers
            List<Server> tempList = new ArrayList<>();
            Server selectedServer = null;

            // Extract the server with the minimum load eligible to handle the current request
            while (!serverQueue.isEmpty()) {
                Server server = serverQueue.poll();
                if (server.id <= request) {
                    selectedServer = server;
                    break;
                }
                tempList.add(server);
            }

            // Re-add all the removed servers back to the priority queue
            serverQueue.addAll(tempList);

            // Update the load of the selected server
            selectedServer.load++;
            result.add(selectedServer.id);

            // Add the selected server back to the priority queue with the updated load
            serverQueue.add(selectedServer);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int num_servers1 = 5;
        List<Integer> requests1 = Arrays.asList(0, 1, 2, 3);
        System.out.println("Test case 1 output: " + getServerIds(num_servers1, requests1));

        int num_servers2 = 5;
        List<Integer> requests2 = Arrays.asList(4, 0, 2, 2);
        System.out.println("Test case 2 output: " + getServerIds(num_servers2, requests2));

        int num_servers3 = 5;
        List<Integer> requests3 = Arrays.asList(3, 2, 3, 2, 4);
        System.out.println("Test case 3 output: " + getServerIds(num_servers3, requests3));
    }

    static class Server {
        int id;
        int load;

        Server(int id, int load) {
            this.id = id;
            this.load = load;
        }
    }
}