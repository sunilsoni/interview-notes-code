package com.interview.notes.code.year.y2025.jan25.test1;

import java.util.*;

interface IntuitMusicBackend {
    List<String> getTopFavorites(Person person);
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}

class SimpleIntuitMusicBackend implements IntuitMusicBackend {
    private Map<String, List<String>> userFavorites = new HashMap<>();

    public void setFavorites(Person person, List<String> favorites) {
        userFavorites.put(person.name, favorites);
    }

    @Override
    public List<String> getTopFavorites(Person person) {
        return userFavorites.getOrDefault(person.name, Collections.emptyList());
    }
}

class SongEntry {
    String song;
    int frequency;

    SongEntry(String song, int frequency) {
        this.song = song;
        this.frequency = frequency;
    }
}

class IntuitPartyDJ {
    private IntuitMusicBackend intuitMusic;
    private Set<Person> currentParty;
    private Map<String, Integer> globalFreq;
    private PriorityQueue<SongEntry> maxHeap;

    public IntuitPartyDJ(IntuitMusicBackend backend) {
        this.intuitMusic = backend;
        this.currentParty = new HashSet<>();
        this.globalFreq = new HashMap<>();
        // Max-heap based on frequency
        this.maxHeap = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare(b.frequency, a.frequency);
            if (cmp == 0) {
                return a.song.compareTo(b.song);
            }
            return cmp;
        });
    }

    public void joinParty(Person person) {
        if (!currentParty.add(person)) return;  // if already present, do nothing
        List<String> favorites = intuitMusic.getTopFavorites(person);
        for (String song : favorites) {
            int newFreq = globalFreq.getOrDefault(song, 0) + 1;
            globalFreq.put(song, newFreq);
            maxHeap.offer(new SongEntry(song, newFreq));
        }
    }

    public void leaveParty(Person person) {
        if (!currentParty.remove(person)) return;  // if not present, do nothing
        List<String> favorites = intuitMusic.getTopFavorites(person);
        for (String song : favorites) {
            int freq = globalFreq.getOrDefault(song, 0);
            if (freq <= 0) continue;
            int newFreq = freq - 1;
            if (newFreq == 0) {
                globalFreq.remove(song);
            } else {
                globalFreq.put(song, newFreq);
            }
            maxHeap.offer(new SongEntry(song, newFreq));
        }
    }

    public String nextSong() {
        while (!maxHeap.isEmpty()) {
            SongEntry top = maxHeap.peek();
            int currentFreq = globalFreq.getOrDefault(top.song, 0);
            // If the frequency in the heap matches current frequency, this entry is valid
            if (top.frequency == currentFreq) {
                return top.song;
            } else {
                // Stale entry, remove and continue
                maxHeap.poll();
            }
        }
        return null;
    }
}

public class IntuitPartyDJTestPQ {
    public static void main(String[] args) {
        SimpleIntuitMusicBackend backend = new SimpleIntuitMusicBackend();
        IntuitPartyDJ partyDJ = new IntuitPartyDJ(backend);

        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person carol = new Person("Carol");

        backend.setFavorites(alice, Arrays.asList("SongA", "SongB", "SongC"));
        backend.setFavorites(bob, Arrays.asList("SongB", "SongC", "SongD"));
        backend.setFavorites(carol, Arrays.asList("SongC", "SongD", "SongE"));

        List<Boolean> tests = new ArrayList<>();

        // Test scenarios similar to before
        tests.add(partyDJ.nextSong() == null);

        partyDJ.joinParty(alice);
        String next = partyDJ.nextSong();
        tests.add(next != null && Arrays.asList("SongA", "SongB", "SongC").contains(next));

        partyDJ.joinParty(bob);
        next = partyDJ.nextSong();
        tests.add(next != null && (next.equals("SongB") || next.equals("SongC")));

        partyDJ.joinParty(carol);
        next = partyDJ.nextSong();
        tests.add("SongC".equals(next));

        partyDJ.leaveParty(carol);
        next = partyDJ.nextSong();
        tests.add(next != null && (next.equals("SongB") || next.equals("SongC")));

        partyDJ.leaveParty(alice);
        partyDJ.leaveParty(bob);
        tests.add(partyDJ.nextSong() == null);

        for (int i = 0; i < tests.size(); i++) {
            System.out.println("Test " + (i + 1) + ": " + (tests.get(i) ? "PASS" : "FAIL"));
        }
    }
}
