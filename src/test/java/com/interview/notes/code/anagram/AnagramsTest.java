package com.interview.notes.code.anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramsTest {

    Anagrams anagrams;

    @BeforeEach
    void setUp() {
        anagrams = new Anagrams();
    }

    @Test
    void isAnagram() {

        boolean isAnagram = Anagrams.isAnagram("Anagrams", "Anagrams");
        assertTrue(isAnagram);
    }
}