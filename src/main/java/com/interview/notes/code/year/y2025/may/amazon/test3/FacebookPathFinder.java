package com.interview.notes.code.year.y2025.may.amazon.test3;

import java.util.*;
import java.util.stream.Collectors;

class Profile {
    String name;
    List<Profile> friends;

    Profile(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
    }

    void addFriend(Profile friend) {
        friends.add(friend);
    }
}

public class FacebookPathFinder {

    // Finds the shortest path from start profile to end profile using BFS
    public static List<String> findConnectionPath(Profile start, Profile end) {
        Queue<List<Profile>> queue = new LinkedList<>();
        Set<Profile> visited = new HashSet<>();

        // Start the queue with the initial profile
        queue.add(Collections.singletonList(start));

        while (!queue.isEmpty()) {
            List<Profile> path = queue.poll(); // Get the next path to evaluate
            Profile current = path.get(path.size() - 1); // Get last profile in the path

            if (current.equals(end)) {
                // Found the destination profile
                return path.stream().map(p -> p.name).collect(Collectors.toList());
            }

            if (!visited.contains(current)) {
                visited.add(current);
                // Add all friends of current profile to the queue
                for (Profile friend : current.friends) {
                    List<Profile> newPath = new ArrayList<>(path);
                    newPath.add(friend);
                    queue.add(newPath);
                }
            }
        }
        // Return empty if no path found
        return Collections.emptyList();
    }

    // Simple main method for testing
    public static void main(String[] args) {
        // Create sample profiles
        Profile gordon = new Profile("Gordon");
        Profile shantel = new Profile("Shantel");
        Profile maria = new Profile("Maria");
        Profile david = new Profile("David");
        Profile stephen = new Profile("Stephen");

        // Connect profiles
        gordon.addFriend(shantel);
        shantel.addFriend(maria);
        maria.addFriend(david);
        david.addFriend(stephen);

        // Run test
        List<String> path = findConnectionPath(gordon, stephen);

        // Check if the test passes
        if (path.equals(Arrays.asList("Gordon", "Shantel", "Maria", "David", "Stephen"))) {
            System.out.println("Test PASS: " + path);
        } else {
            System.out.println("Test FAIL: " + path);
        }

        // Additional edge-case test: direct friends
        gordon.addFriend(stephen);
        path = findConnectionPath(gordon, stephen);

        if (path.equals(Arrays.asList("Gordon", "Stephen"))) {
            System.out.println("Edge Test PASS: " + path);
        } else {
            System.out.println("Edge Test FAIL: " + path);
        }

        // Additional test: No connection
        Profile alice = new Profile("Alice");
        path = findConnectionPath(gordon, alice);

        if (path.isEmpty()) {
            System.out.println("No Connection Test PASS");
        } else {
            System.out.println("No Connection Test FAIL: " + path);
        }
    }
}
