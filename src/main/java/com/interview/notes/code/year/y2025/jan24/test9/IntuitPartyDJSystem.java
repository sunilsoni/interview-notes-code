package com.interview.notes.code.year.y2025.jan24.test9;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a party attendee with their music preferences
 */
record Person(String id, List<String> favoriteSongs) {
    Person(String id) {
        this(id, new ArrayList<>());
    }
}

/**
 * Simulates Intuit Music backend service
 */
class IntuitMusicBackend {
    public List<String> getTopFavoriteSongs(Person person) {
        return person.favoriteSongs();
    }
}

/**
 * Main DJ class that manages party music based on attendees' preferences
 */
class IntuitPartyDJ {
    private final IntuitMusicBackend intuitMusic;
    private final Map<String, Person> partyGoers;
    private final Map<String, Long> songPopularity;

    public IntuitPartyDJ(IntuitMusicBackend intuitMusic) {
        this.intuitMusic = intuitMusic;
        this.partyGoers = new ConcurrentHashMap<>();
        this.songPopularity = new HashMap<>();
    }

    public synchronized void joinParty(Person person) {
        partyGoers.put(person.id(), person);
        updateSongPopularity();
    }

    public synchronized void leaveParty(Person person) {
        partyGoers.remove(person.id());
        updateSongPopularity();
    }

    private void updateSongPopularity() {
        songPopularity.clear();
        songPopularity.putAll(
                partyGoers.values().stream()
                        .flatMap(person -> intuitMusic.getTopFavoriteSongs(person).stream())
                        .collect(Collectors.groupingBy(
                                song -> song,
                                Collectors.counting()
                        ))
        );
    }

    public String nextSong() {
        return songPopularity.isEmpty() ? "No songs available" :
                songPopularity.entrySet().stream()
                        .collect(Collectors.groupingBy(
                                Map.Entry::getValue,
                                Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                        ))
                        .entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getKey))
                        .map(entry -> {
                            List<String> mostPopular = entry.getValue();
                            return mostPopular.get(new Random().nextInt(mostPopular.size()));
                        })
                        .orElse("No songs available");
    }
}

/**
 * Main class with test cases
 */
public class IntuitPartyDJSystem {
    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        testBasicFunctionality();
        testEmptyParty();
        testLargeParty();
        testDynamicParty();
    }

    private static void testBasicFunctionality() {
        System.out.println("\n=== Basic Functionality Test ===");

        var backend = new IntuitMusicBackend();
        var dj = new IntuitPartyDJ(backend);

        // Create test persons with overlapping favorite songs
        var person1 = new Person("1", List.of("Song1", "Song2", "Song3"));
        var person2 = new Person("2", List.of("Song2", "Song3", "Song4"));

        dj.joinParty(person1);
        dj.joinParty(person2);

        String song = dj.nextSong();
        System.out.println("Selected song: " + song);
        System.out.println("Test passed: " + (song.equals("Song2") || song.equals("Song3")));
    }

    private static void testEmptyParty() {
        System.out.println("\n=== Empty Party Test ===");

        var dj = new IntuitPartyDJ(new IntuitMusicBackend());
        String result = dj.nextSong();

        System.out.println("Result: " + result);
        System.out.println("Test passed: " + result.equals("No songs available"));
    }

    private static void testLargeParty() {
        System.out.println("\n=== Large Party Test ===");

        var dj = new IntuitPartyDJ(new IntuitMusicBackend());
        var random = new Random();

        // Add 1000 people with random song preferences
        IntStream.range(0, 1000)
                .forEach(i -> {
                    List<String> favorites = IntStream.range(0, 5)
                            .mapToObj(j -> "Song" + random.nextInt(20))
                            .collect(Collectors.toList());
                    dj.joinParty(new Person(String.valueOf(i), favorites));
                });

        long startTime = System.currentTimeMillis();
        String song = dj.nextSong();
        long duration = System.currentTimeMillis() - startTime;

        System.out.println("Selected song: " + song);
        System.out.println("Processing time: " + duration + "ms");
        System.out.println("Performance test passed: " + (duration < 1000));
    }

    private static void testDynamicParty() {
        System.out.println("\n=== Dynamic Party Test ===");

        var dj = new IntuitPartyDJ(new IntuitMusicBackend());

        // Test dynamic joining and leaving
        var person1 = new Person("1", List.of("SongA", "SongB"));
        var person2 = new Person("2", List.of("SongB", "SongC"));

        System.out.println("Initial song: " + dj.nextSong());

        dj.joinParty(person1);
        System.out.println("After person1 joins: " + dj.nextSong());

        dj.joinParty(person2);
        System.out.println("After person2 joins: " + dj.nextSong());

        dj.leaveParty(person1);
        System.out.println("After person1 leaves: " + dj.nextSong());
    }
}
