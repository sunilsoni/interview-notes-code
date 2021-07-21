package com.interview.notes.code.anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintAnagramTogetherTest {

    PrintAnagramTogether printAnagramTogether;
    @BeforeEach
    void setUp() {
        printAnagramTogether = new PrintAnagramTogether();
    }

    @Test
    void print() {
        String str[] = {"cat","dog","tac","god","act"};
        printAnagramTogether.print(str);
    }
}