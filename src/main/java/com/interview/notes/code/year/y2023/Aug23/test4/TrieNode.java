package com.interview.notes.code.year.y2023.Aug23.test4;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;

    TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}
