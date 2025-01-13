package com.interview.notes.code.year.y2025.jan24.test6;

import java.util.*;

interface IntuitMusicBackend {
    /**
     * Returns top 10 favorite songs for the given person.
     * For simulation purposes, we will use a simple map.
     */
    List<String> getTopFavorites(Person person);
}

/**
 * Simulated classes for the problem
 */
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}

/**
 * A simple implementation of IntuitMusicBackend for testing.
 */
class SimpleIntuitMusicBackend implements IntuitMusicBackend {
    private Map<String, List<String>> userFavorites;

    public SimpleIntuitMusicBackend() {
        userFavorites = new HashMap<>();
    }

    public void setFavorites(Person person, List<String> favorites) {
        userFavorites.put(person.name, favorites);
    }

    @Override
    public List<String> getTopFavorites(Person person) {
        return userFavorites.getOrDefault(person.name, new ArrayList<>());
    }
}

class IntuitPartyDJ {
    private IntuitMusicBackend intuitMusic;
    private Set<Person> currentParty;

    public IntuitPartyDJ(IntuitMusicBackend backend) {
        this.intuitMusic = backend;
        this.currentParty = new HashSet<>();
    }

    public void joinParty(Person person) {
        currentParty.add(person);
    }

    public void leaveParty(Person person) {
        currentParty.remove(person);
    }

    /**
     * Determine the next song to play based on the favorite songs of those currently at the party.
     */
    public String nextSong() {
        if (currentParty.isEmpty()) {
            return null;
        }

        Map<String, Integer> songFrequency = new HashMap<>();
        for (Person person : currentParty) {
            List<String> favorites = intuitMusic.getTopFavorites(person);
            for (String song : favorites) {
                songFrequency.put(song, songFrequency.getOrDefault(song, 0) + 1);
            }
        }

        String topSong = null;
        int maxCount = 0;
        // Choose song with highest count. If tie, pick any.
        for (Map.Entry<String, Integer> entry : songFrequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                topSong = entry.getKey();
            }
        }
        return topSong;
    }
}

public class IntuitPartyDJTest {
    public static void main(String[] args) {
        // Initialize backend and DJ
        SimpleIntuitMusicBackend backend = new SimpleIntuitMusicBackend();
        IntuitPartyDJ partyDJ = new IntuitPartyDJ(backend);

        // Create test persons
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person carol = new Person("Carol");

        // Setup favorites for each person
        backend.setFavorites(alice, Arrays.asList("SongA", "SongB", "SongC"));
        backend.setFavorites(bob, Arrays.asList("SongB", "SongC", "SongD"));
        backend.setFavorites(carol, Arrays.asList("SongC", "SongD", "SongE"));

        // List of tests to run
        List<Boolean> tests = new ArrayList<>();

        // Test 1: No one in party -> nextSong() returns null
        tests.add(partyDJ.nextSong() == null);

        // Test 2: Alice joins, nextSong should be one of her favorites since she's alone.
        partyDJ.joinParty(alice);
        String next = partyDJ.nextSong();
        tests.add(next != null && Arrays.asList("SongA", "SongB", "SongC").contains(next));

        // Test 3: Bob joins, SongB or SongC should now be most common.
        partyDJ.joinParty(bob);
        next = partyDJ.nextSong();
        tests.add(next != null && (next.equals("SongB") || next.equals("SongC")));

        // Test 4: Carol joins, SongC becomes most common.
        partyDJ.joinParty(carol);
        next = partyDJ.nextSong();
        tests.add("SongC".equals(next));

        // Test 5: Remove Carol, most common becomes SongB or SongC.
        partyDJ.leaveParty(carol);
        next = partyDJ.nextSong();
        tests.add(next != null && (next.equals("SongB") || next.equals("SongC")));

        // Test 6: Remove all, returns null.
        partyDJ.leaveParty(alice);
        partyDJ.leaveParty(bob);
        tests.add(partyDJ.nextSong() == null);

        // Print test results
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("Test " + (i + 1) + ": " + (tests.get(i) ? "PASS" : "FAIL"));
        }

        // Additional tests for large data input
        largeDataTest();
    }

    /**
     * Testing with a large number of persons to check performance.
     */
    public static void largeDataTest() {
        SimpleIntuitMusicBackend backend = new SimpleIntuitMusicBackend();
        IntuitPartyDJ partyDJ = new IntuitPartyDJ(backend);

        // Generate a large number of persons and assign random favorites
        int numPersons = 10000;
        Random rand = new Random();
        String[] songs = {"SongA", "SongB", "SongC", "SongD", "SongE", "SongF", "SongG", "SongH", "SongI", "SongJ"};
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < numPersons; i++) {
            Person p = new Person("Person" + i);
            persons.add(p);
            List<String> favorites = new ArrayList<>();
            // Each person gets 10 random favorite songs.
            for (int j = 0; j < 10; j++) {
                favorites.add(songs[rand.nextInt(songs.length)]);
            }
            backend.setFavorites(p, favorites);
            partyDJ.joinParty(p);
        }

        // Run nextSong to ensure it works with large data
        String largeDataNextSong = partyDJ.nextSong();
        System.out.println("Large Data Test nextSong result: " + largeDataNextSong);
        System.out.println("Large Data Test: PASS if no errors encountered.");
    }
}
