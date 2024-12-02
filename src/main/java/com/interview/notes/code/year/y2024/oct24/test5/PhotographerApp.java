package com.interview.notes.code.year.y2024.oct24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*

Design an app using appropriate data structures and algorithms - where users can specify for what type of event and location they need a photographer. You can refer to https://www.thumbtack.com/ as an example.
We want two functionalities -
• You will design a method which saves these photographer profiles in some in memory data store. A profile consists of location, rates and types of events handled by that photographer.
• a search functionality which will return a list of profiles for a user's specific needs using the data store designed above.
Example events: Wedding, Rehearsal dinner, Bridal Shower, Birthday Parties, Corporate Events
Feel free to ask questions and document any assumptions.


Example
Input
eventType: Wedding location San Jose, CA

Output
"Dream Weddings", location='San Jose, CA' rate=$150, eventType: Wedding, Rehearsal Di nner, Bridal Shower
"Memorable Moments", location='San Jose, C A', rate=$180, eventType: Wedding, Receptio
n
"Beautiful weddings", location='San Jose,
CA', rate=$100, eventType: Wedding

 */
public class PhotographerApp {
    // Data structure to store photographer profiles
    private static List<PhotographerProfile> profiles = new ArrayList<>();

    public static void main(String[] args) {
        // Sample data population
        saveProfile("Dream Weddings", "San Jose, CA", 150, Arrays.asList("Wedding", "Rehearsal Dinner", "Bridal Shower"));
        saveProfile("Memorable Moments", "San Jose, CA", 180, Arrays.asList("Wedding", "Reception"));
        saveProfile("Beautiful Weddings", "San Jose, CA", 100, Arrays.asList("Wedding"));

        // Test cases
        testSearch("Wedding", "San Jose, CA");
        testSearch("Birthday Parties", "San Francisco, CA");
    }

    // Method to save photographer profiles
    public static void saveProfile(String name, String location, int rate, List<String> eventTypes) {
        PhotographerProfile profile = new PhotographerProfile(name, location, rate, eventTypes);
        profiles.add(profile);
    }

    // Search functionality
    public static List<PhotographerProfile> searchPhotographers(String eventType, String location) {
        return profiles.stream()
                .filter(p -> p.getLocation().equalsIgnoreCase(location) && p.getEventTypes().contains(eventType))
                .sorted(Comparator.comparingInt(PhotographerProfile::getRate))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    // Test method
    private static void testSearch(String eventType, String location) {
        System.out.println("Searching for: " + eventType + " in " + location);
        List<PhotographerProfile> results = searchPhotographers(eventType, location);
        if (results.isEmpty()) {
            System.out.println("No matching photographers found.");
        } else {
            results.forEach(System.out::println);
        }
        System.out.println();
    }

    // PhotographerProfile class
    static class PhotographerProfile {
        private String name;
        private String location;
        private int rate;
        private List<String> eventTypes;

        public PhotographerProfile(String name, String location, int rate, List<String> eventTypes) {
            this.name = name;
            this.location = location;
            this.rate = rate;
            this.eventTypes = eventTypes;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public int getRate() {
            return rate;
        }

        public List<String> getEventTypes() {
            return eventTypes;
        }

        @Override
        public String toString() {
            return String.format("%s, location='%s', rate=$%d, eventTypes: %s",
                    name, location, rate, String.join(", ", eventTypes));
        }
    }
}
