package com.interview.notes.code.anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramOfFirstAsSubstringTest {
    AnagramOfFirstAsSubstring ana ;
    @BeforeEach
    void setUp() {
        ana = new AnagramOfFirstAsSubstring();
    }

    @Test
    void isSubString() {
        char str1[] = "aaabccde".toCharArray();
        char str2[] = "tbcdaacaaecbd".toCharArray();

        System.out.println(ana.isSubString(str1, str2));
    }
}