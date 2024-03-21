package com.interview.notes.code.months.march24.test15;

import java.util.ArrayList;
import java.util.List;

/**
 * X
 * 5
 * X
 * 2
 * X
 * 3
 * 0 (Space station)
 * ♥ Asteroids
 * Mass
 * Direction {5,2, 7,33
 * 7
 * <
 * Question: Can you find out if there will an asteroid hitting the space station.
 * Condition:
 * When 2 asteroids collide, the one with smaller mass gets destroyed. And the one with larger mass, losses his mass equal to the smaller asteroid's mass.
 */

public class AsteroidCollision {
    public static void main(String[] args) {
        int[] asteroidMasses = new int[]{5, 2, 7, 3};
        char[] directions = new char[]{'>', '<', '>', '<'};

        List<Integer> finalAsteroids = simulateAsteroids(asteroidMasses, directions);
        System.out.println("Final state of asteroids: " + finalAsteroids);

        boolean willHitSpaceStation = false;
        for (int mass : finalAsteroids) {
            if (mass > 0) {  // Positive mass indicates movement towards the space station
                willHitSpaceStation = true;
                break;
            }
        }

        if (willHitSpaceStation) {
            System.out.println("An asteroid will hit the space station.");
        } else {
            System.out.println("No asteroid will hit the space station.");
        }
    }

    public static List<Integer> simulateAsteroids(int[] masses, char[] directions) {
        List<Integer> asteroids = new ArrayList<>();
        for (int i = 0; i < masses.length; i++) {
            int mass = directions[i] == '>' ? masses[i] : -masses[i];  // Use negative for left-moving asteroids
            asteroids.add(mass);
        }

        for (int i = 1; i < asteroids.size(); i++) {
            if (asteroids.get(i) > 0 && asteroids.get(i - 1) < 0) {
                // Collision detected
                int massLeft = -asteroids.get(i - 1);
                int massRight = asteroids.get(i);

                if (massLeft == massRight) {
                    // Both asteroids are destroyed
                    asteroids.remove(i - 1);
                    asteroids.remove(i - 1);
                    i -= 2;  // Adjust index after removal
                } else if (massLeft > massRight) {
                    // Update the mass of the left-moving asteroid and remove the right-moving one
                    asteroids.set(i - 1, -(massLeft - massRight));
                    asteroids.remove(i);
                    i--;  // Adjust index after removal
                } else {
                    // Update the mass of the right-moving asteroid and remove the left-moving one
                    asteroids.set(i, massRight - massLeft);
                    asteroids.remove(i - 1);
                    i--;  // Adjust index after removal
                }
            }
        }

        return asteroids;
    }
}
