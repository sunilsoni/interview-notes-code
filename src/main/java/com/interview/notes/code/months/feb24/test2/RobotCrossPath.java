package com.interview.notes.code.months.feb24.test2;

import java.util.HashSet;
import java.util.Set;

public class RobotCrossPath {
    // Method to determine if the robot crosses its path
    public static boolean doesRobotCrossPath(String moves) {
        // Initialize starting position and a set to track visited points
        int x = 0, y = 0;
        Set<String> visited = new HashSet<>();
        visited.add(x + "," + y); // Add starting point
        
        for(char move : moves.toCharArray()) {
            switch(move) {
                case 'N': y++; break;
                case 'E': x++; break;
                case 'S': y--; break;
                case 'W': x--; break;
            }
            // Convert current position to a string key
            String posKey = x + "," + y;
            // Check if the position has been visited
            if(visited.contains(posKey)) {
                return true; // The robot crosses its path
            }
            visited.add(posKey); // Mark this position as visited
        }
        
        // No crossing detected
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(doesRobotCrossPath("NES")); // Should return false
        System.out.println(doesRobotCrossPath("NESW")); // Should return true
        System.out.println(doesRobotCrossPath("NNEESSWW")); // Should return true, crosses path at the start
    }
}
