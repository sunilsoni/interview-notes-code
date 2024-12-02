package com.interview.notes.code.year.y2024.oct24.test7;

import java.util.ArrayList;
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

    // In-memory data store
    private static List<PhotographerProfile> profiles = new ArrayList<>();

    // Save photographer profile
    public static void saveProfile(PhotographerProfile profile) {
        profiles.add(profile);
    }

    // Search photographer profiles based on location and event type
    public static List<PhotographerProfile> searchProfiles(String location, String eventType) {
        List<PhotographerProfile> result = new ArrayList<>();
        for (PhotographerProfile profile : profiles) {
            if (profile.location.equals(location) && profile.eventTypes.contains(eventType)) {
                result.add(profile);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Sample input profiles
        List<String> events1 = new ArrayList<>();
        events1.add("Wedding");
        events1.add("Rehearsal Dinner");
        events1.add("Bridal Shower");

        List<String> events2 = new ArrayList<>();
        events2.add("Wedding");
        events2.add("Reception");

        List<String> events3 = new ArrayList<>();
        events3.add("Wedding");

        saveProfile(new PhotographerProfile("Dream Weddings", "San Jose, CA", 150, events1));
        saveProfile(new PhotographerProfile("Memorable Moments", "San Jose, CA", 180, events2));
        saveProfile(new PhotographerProfile("Beautiful Weddings", "San Jose, CA", 100, events3));

        // Search example
        List<PhotographerProfile> searchResults = searchProfiles("San Jose, CA", "Wedding");
        for (PhotographerProfile profile : searchResults) {
            System.out.println(profile);
        }
    }

    // Photographer Profile class
    static class PhotographerProfile {
        String name;
        String location;
        List<String> eventTypes;
        int rate;

        public PhotographerProfile(String name, String location, int rate, List<String> eventTypes) {
            this.name = name;
            this.location = location;
            this.rate = rate;
            this.eventTypes = eventTypes;
        }

        @Override
        public String toString() {
            return "\"" + name + "\", location='" + location + "', rate=$" + rate + ", eventType=" + eventTypes;
        }
    }
}
