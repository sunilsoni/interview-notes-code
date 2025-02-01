package com.interview.notes.code.year.y2025.jan25.test7;

import java.util.*;

public class IntuitPartyDJ {

    private IntuitMusicBackend intuitMusic;
    private Set<Person> currentParty;
    private Map<String, Integer> songCounts;
    private Set<String> topSongs;
    private int maxCount;

    /**
     * Constructor for the IntuitPartyDJ.
     *
     * @param intuitMusic the backend service to fetch users' favorite songs
     */
    public IntuitPartyDJ(IntuitMusicBackend intuitMusic) {
        this.intuitMusic = intuitMusic;
        this.currentParty = new HashSet<>();
        this.songCounts = new HashMap<>();
        this.topSongs = new HashSet<>();
        this.maxCount = 0;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Setup mock IntuitMusicBackend
        IntuitMusicBackend intuitMusic = new IntuitMusicBackend();

        // Initialize IntuitPartyDJ
        IntuitPartyDJ dj = new IntuitPartyDJ(intuitMusic);

        // Define test persons
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person carol = new Person("Carol");
        Person dave = new Person("Dave");

        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: No one at the party
        testCases.add(new TestCase("No one at the party", dj, "No songs to play"));

        // Test Case 2: Alice joins
        dj.joinParty(alice);
        testCases.add(new TestCase("Alice joins", dj, "Song A"));

        // Test Case 3: Bob joins
        dj.joinParty(bob);
        testCases.add(new TestCase("Bob joins", dj, "Song A"));

        // Test Case 4: Carol joins
        dj.joinParty(carol);
        testCases.add(new TestCase("Carol joins", dj, "Song B"));

        // Test Case 5: Dave joins
        dj.joinParty(dave);
        testCases.add(new TestCase("Dave joins", dj, "Song B"));

        // Test Case 6: Alice leaves
        dj.leaveParty(alice);
        testCases.add(new TestCase("Alice leaves", dj, "Song B"));

        // Test Case 7: Bob leaves
        dj.leaveParty(bob);
        testCases.add(new TestCase("Bob leaves", dj, "Song B"));

        // Test Case 8: Carol leaves
        dj.leaveParty(carol);
        testCases.add(new TestCase("Carol leaves", dj, "Song C"));

        // Test Case 9: Dave leaves
        dj.leaveParty(dave);
        testCases.add(new TestCase("Dave leaves", dj, "No songs to play"));

        // Run tests
        int passedTests = 0;
        for (TestCase testCase : testCases) {
            String nextSong = testCase.dj.nextSong();
            if (nextSong.equals(testCase.expectedSong)) {
                System.out.println("Test \"" + testCase.description + "\": PASS");
                passedTests++;
            } else {
                System.out.println("Test \"" + testCase.description + "\": FAIL");
                System.out.println("Expected Song: " + testCase.expectedSong);
                System.out.println("Actual Song:   " + nextSong);
            }
        }
        System.out.println(passedTests + " out of " + testCases.size() + " tests passed.");
    }

    /**
     * Adds a person to the party and updates song preferences.
     *
     * @param person the person joining the party
     */
    public void joinParty(Person person) {
        if (currentParty.add(person)) {
            List<String> favoriteSongs = intuitMusic.getTopSongs(person);
            for (String song : favoriteSongs) {
                int count = songCounts.getOrDefault(song, 0) + 1;
                songCounts.put(song, count);

                if (count > maxCount) {
                    maxCount = count;
                    topSongs.clear();
                    topSongs.add(song);
                } else if (count == maxCount) {
                    topSongs.add(song);
                }
            }
        }
    }

    /**
     * Removes a person from the party and updates song preferences.
     *
     * @param person the person leaving the party
     */
    public void leaveParty(Person person) {
        if (currentParty.remove(person)) {
            List<String> favoriteSongs = intuitMusic.getTopSongs(person);
            boolean needRecomputeMax = false;

            for (String song : favoriteSongs) {
                int count = songCounts.getOrDefault(song, 0) - 1;
                if (count <= 0) {
                    songCounts.remove(song);
                    topSongs.remove(song);
                } else {
                    songCounts.put(song, count);
                    if (count < maxCount && topSongs.contains(song)) {
                        topSongs.remove(song);
                        needRecomputeMax = true;
                    }
                }
            }

            if (needRecomputeMax || topSongs.isEmpty()) {
                recomputeTopSongs();
            }
        }
    }

    /**
     * Recomputes the top songs based on current song counts.
     */
    private void recomputeTopSongs() {
        maxCount = 0;
        topSongs.clear();
        for (Map.Entry<String, Integer> entry : songCounts.entrySet()) {
            int count = entry.getValue();
            String song = entry.getKey();
            if (count > maxCount) {
                maxCount = count;
                topSongs.clear();
                topSongs.add(song);
            } else if (count == maxCount) {
                topSongs.add(song);
            }
        }
    }

    /**
     * Returns the next song to play, which is the favorite of the most people currently in the party.
     *
     * @return the next song to play
     */
    public String nextSong() {
        if (topSongs.isEmpty()) {
            return "No songs to play"; // Or a default song
        }
        // Return any song from topSongs
        return topSongs.iterator().next();
    }

    /**
     * Helper class for test cases.
     */
    static class TestCase {
        String description;
        IntuitPartyDJ dj;
        String expectedSong;

        TestCase(String description, IntuitPartyDJ dj, String expectedSong) {
            this.description = description;
            this.dj = dj;
            this.expectedSong = expectedSong;
        }
    }
}

/**
 * Mock class representing a person.
 */
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    // Override equals and hashCode for proper functioning in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

/**
 * Mock class representing the IntuitMusicBackend.
 */
class IntuitMusicBackend {

    private Map<Person, List<String>> userTopSongs;

    public IntuitMusicBackend() {
        userTopSongs = new HashMap<>();
        // Initialize mock data
        // Assume each person has a list of their top favorite songs
        // For simplicity, song names are "Song A", "Song B", "Song C", etc.

        // Alice's top songs
        userTopSongs.put(new Person("Alice"), Arrays.asList("Song A", "Song B", "Song C"));

        // Bob's top songs
        userTopSongs.put(new Person("Bob"), Arrays.asList("Song A", "Song D", "Song E"));

        // Carol's top songs
        userTopSongs.put(new Person("Carol"), Arrays.asList("Song B", "Song F", "Song G"));

        // Dave's top songs
        userTopSongs.put(new Person("Dave"), Arrays.asList("Song B", "Song H", "Song I"));
    }

    /**
     * Fetches the top favorite songs of a person.
     *
     * @param person the person whose songs are to be fetched
     * @return list of favorite songs
     */
    public List<String> getTopSongs(Person person) {
        return userTopSongs.getOrDefault(person, Collections.emptyList());
    }
}