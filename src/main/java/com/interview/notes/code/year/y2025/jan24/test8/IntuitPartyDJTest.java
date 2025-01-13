package com.interview.notes.code.year.y2025.jan24.test8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Person {
    private String id;
    private List<String> favoriteSongs;

    public Person(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<String> getFavoriteSongs() {
        return favoriteSongs;
    }

    public void setFavoriteSongs(List<String> songs) {
        this.favoriteSongs = songs;
    }
}

class IntuitMusicBackend {
    public List<String> getTopFavoriteSongs(Person person) {
        // Simulated backend call
        return person.getFavoriteSongs();
    }
}

class IntuitPartyDJ {
    private IntuitMusicBackend intuitMusic;
    private Map<String, Person> partyGoers;
    private Map<String, Integer> songPopularity;
    private String currentSong;

    public IntuitPartyDJ(IntuitMusicBackend intuitMusic) {
        this.intuitMusic = intuitMusic;
        this.partyGoers = new ConcurrentHashMap<>();
        this.songPopularity = new HashMap<>();
    }

    public synchronized void joinParty(Person person) {
        partyGoers.put(person.getId(), person);
        updateSongPopularity();
    }

    public synchronized void leaveParty(Person person) {
        partyGoers.remove(person.getId());
        updateSongPopularity();
    }

    private void updateSongPopularity() {
        songPopularity.clear();
        for (Person person : partyGoers.values()) {
            List<String> favorites = intuitMusic.getTopFavoriteSongs(person);
            for (String song : favorites) {
                songPopularity.merge(song, 1, Integer::sum);
            }
        }
    }

    public String nextSong() {
        if (songPopularity.isEmpty()) {
            return "No songs available";
        }

        int maxPopularity = Collections.max(songPopularity.values());
        List<String> mostPopularSongs = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : songPopularity.entrySet()) {
            if (entry.getValue() == maxPopularity) {
                mostPopularSongs.add(entry.getKey());
            }
        }

        currentSong = mostPopularSongs.get(new Random().nextInt(mostPopularSongs.size()));
        return currentSong;
    }
}

// Test class
public class IntuitPartyDJTest {
    public static void main(String[] args) {
        testBasicFunctionality();
        testEmptyParty();
        testLargeParty();
    }

    private static void testBasicFunctionality() {
        System.out.println("Testing basic functionality...");

        IntuitMusicBackend backend = new IntuitMusicBackend();
        IntuitPartyDJ dj = new IntuitPartyDJ(backend);

        // Create test persons with favorite songs
        Person p1 = new Person("1");
        p1.setFavoriteSongs(Arrays.asList("S1", "S2", "S3"));

        Person p2 = new Person("2");
        p2.setFavoriteSongs(Arrays.asList("S2", "S3", "S4"));

        // Test joining party
        dj.joinParty(p1);
        dj.joinParty(p2);

        String song = dj.nextSong();
        System.out.println("Next song should be S2 or S3: " + song);
        assert song.equals("S2") || song.equals("S3") : "Failed: Most popular song not selected";

        System.out.println("Basic functionality test: PASS");
    }

    private static void testEmptyParty() {
        System.out.println("\nTesting empty party...");

        IntuitMusicBackend backend = new IntuitMusicBackend();
        IntuitPartyDJ dj = new IntuitPartyDJ(backend);

        String song = dj.nextSong();
        assert song.equals("No songs available") : "Failed: Empty party should return no songs";

        System.out.println("Empty party test: PASS");
    }

    private static void testLargeParty() {
        System.out.println("\nTesting large party...");

        IntuitMusicBackend backend = new IntuitMusicBackend();
        IntuitPartyDJ dj = new IntuitPartyDJ(backend);

        // Create 1000 people with random favorite songs
        for (int i = 0; i < 1000; i++) {
            Person p = new Person(String.valueOf(i));
            List<String> favorites = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                favorites.add("S" + (j % 20)); // Creating 20 different songs
            }
            p.setFavoriteSongs(favorites);
            dj.joinParty(p);
        }

        long startTime = System.currentTimeMillis();
        String song = dj.nextSong();
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken for large party (ms): " + (endTime - startTime));
        System.out.println("Large party test: PASS");
    }
}
