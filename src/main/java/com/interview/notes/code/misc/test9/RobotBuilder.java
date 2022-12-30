package com.interview.notes.code.misc.test9;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//All robots are made of the same types of parts, and we have a string of all of the parts required to form a complete robot.
// Given a list of available parts, return the collection of robot names for which we can build at least one complete robot.

/**
 * To solve this problem, you could use a greedy approach where you iterate through the list of available parts and try to form as many
 * robots as possible using the available parts. You can do this by keeping track of a count for each type of part and
 * decrementing the count every time you use a part to form a robot.
 */

/**
 * This code first creates a map to store the count of each type of part in the partsString.
 * It then iterates through the list of robot types and checks if we have enough parts to build each robot.
 * If we do, it adds the robot to the set of built robots and decrements the counts of the parts that were used.
 *
 */
public class RobotBuilder {

    public static Set<String> buildRobots(String partsString, Set<String> robotTypes) {
        // Create a map to store the count of each type of part
        Map<Character, Integer> parts = new HashMap<>();
        for (char c : partsString.toCharArray()) {
            parts.put(c, parts.getOrDefault(c, 0) + 1);
        }

        // Create a set to store the names of the robots that can be built
        Set<String> builtRobots = new HashSet<>();

        // Iterate through the list of robot types
        for (String robotType : robotTypes) {
            // Check if we have enough parts to build this robot
            boolean canBuild = true;
            for (char c : robotType.toCharArray()) {
                if (parts.getOrDefault(c, 0) == 0) {
                    canBuild = false;
                    break;
                }
            }

            // If we have enough parts, add the robot to the set of built robots and decrement the counts of the parts
            if (canBuild) {
                builtRobots.add(robotType);
                for (char c : robotType.toCharArray()) {
                    parts.put(c, parts.get(c) - 1);
                }
            }
        }

        return builtRobots;
    }

}
