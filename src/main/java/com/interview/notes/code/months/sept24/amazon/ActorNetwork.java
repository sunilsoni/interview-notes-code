package com.interview.notes.code.months.sept24.amazon;

import java.util.*;

/*
We need to find the highest-rated actor connected to a given actor through any number of collaborations. Each actor has a rating and a list of other actors they've worked with.

 */
class Actor {
    double rating;
    List<Actor> actedWith;

    Actor(double rating) {
        this.rating = rating;
        this.actedWith = new ArrayList<>();
    }

    void addCoActor(Actor actor) {
        actedWith.add(actor);
    }
}

public class ActorNetwork {

    public static Actor findHighestRatedActor(Actor startActor) {
        if (startActor == null) return null;

        Queue<Actor> queue = new LinkedList<>();
        Set<Actor> visited = new HashSet<>();
        Actor highestRatedActor = startActor;

        queue.add(startActor);
        visited.add(startActor);

        while (!queue.isEmpty()) {
            Actor current = queue.poll();

            // Check if current actor has a higher rating
            if (current.rating > highestRatedActor.rating) {
                highestRatedActor = current;
            }

            // Traverse all co-actors
            for (Actor coActor : current.actedWith) {
                if (!visited.contains(coActor)) {
                    visited.add(coActor);
                    queue.add(coActor);
                }
            }
        }

        return highestRatedActor;
    }

    public static void main(String[] args) {
        // Test Case 1
        Actor actorA = new Actor(4.5);
        Actor actorB = new Actor(5.0);
        Actor actorC = new Actor(4.8);
        actorA.addCoActor(actorB);
        actorB.addCoActor(actorC);

        Actor highestRated = findHighestRatedActor(actorA);
        System.out.println("Highest Rated Actor: " + highestRated.rating); // Expected: 5.0

        // Additional test cases can be added here
    }
}
