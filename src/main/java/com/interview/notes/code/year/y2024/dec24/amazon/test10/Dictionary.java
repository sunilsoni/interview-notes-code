package com.interview.notes.code.year.y2024.dec24.amazon.test10;

import java.util.HashSet;
import java.util.Set;

/*

The **game of Boggle** consists of an M x N board, where each cell is a letter from the English alphabet. The aim of the game is to find all possible words that can be formed by a sequence of adjacent characters.

Starting from any cell on the board, you can move to one of the **4 adjacent cells** as part of the sequence. But a word cannot consist of multiple instances of the same cell. *Diagonal cells are not considered adjacent.*

For example, consider the following 4 x 4 board:

```
    0   1   2   3
0 {'o','t','s','y'},
1 {'p','m','z','d'},
2 {'t','a','i','r'},
3 {'l','u','r','e'}
```

**Legal**:
```
0,1 -> 0,0 -> 1,0 = "pot"
1,2 -> 2,2 -> 3,2 = "air"
```

**Illegal**:
```
0,1 -> 0,0 -> 0,1 = "pop"  (the cell 0,1 was used more than once)
```

You can assume that you have access to a **Dictionary** with the methods defined as below:

```java
interface Dictionary {
    boolean isWord(String word);
}
```

The following class is used to implement the Boggle word search:

```java
class BoggleSearch {

    private char[][] board;
    private int numRows;
    private int numCols;
    private Dictionary dictionary;

    // Assume numRows > 0, numCols > 0.
    // Assume all cells in board are lower-case ascii english letters
    Set<String> findAllWords(char[][] board, int numRows, int numCols, Dictionary dictionary) {
        // implement
    }
}
```

 */
interface Dictionary {
    boolean isWord(String word);

    boolean isPrefix(String prefix);
}

class BoggleSearch {

    private char[][] board;
    private int numRows;
    private int numCols;
    private Dictionary dictionary;

    public BoggleSearch(char[][] board, int numRows, int numCols, Dictionary dictionary) {
        this.board = board;
        this.numRows = numRows;
        this.numCols = numCols;
        this.dictionary = dictionary;
    }

    public static void main(String[] args) {
        testFindAllWords();
    }

    private static void testFindAllWords() {
        char[][] board = {
                {'o', 't', 's', 'y'},
                {'p', 'm', 'z', 'd'},
                {'t', 'a', 'i', 'r'},
                {'l', 'u', 'r', 'e'}
        };

        Dictionary dictionary = new SimpleDictionary(); // Assuming isWord and isPrefix both exist

        BoggleSearch boggle = new BoggleSearch(board, 4, 4, dictionary);
        Set<String> foundWords = boggle.findAllWords();

        if (foundWords.contains("pot") && foundWords.contains("air") && foundWords.size() == 2) {
            System.out.println("Test Case: PASS");
        } else {
            System.out.println("Test Case: FAIL");
        }
    }

    public Set<String> findAllWords() {
        Set<String> validWords = new HashSet<>();
        boolean[][] visited = new boolean[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                searchWords(i, j, "", visited, validWords);
            }
        }
        return validWords;
    }

    private void searchWords(int row, int col, String prefix, boolean[][] visited, Set<String> validWords) {
        if (!dictionary.isPrefix(prefix)) return; // Early termination for invalid prefixes

        if (row < 0 || row >= numRows || col < 0 || col >= numCols || visited[row][col]) {
            return;
        }

        prefix += board[row][col];
        visited[row][col] = true;

        if (dictionary.isWord(prefix)) {
            validWords.add(prefix);
        }

        searchWords(row - 1, col, prefix, visited, validWords);
        searchWords(row + 1, col, prefix, visited, validWords);
        searchWords(row, col - 1, prefix, visited, validWords);
        searchWords(row, col + 1, prefix, visited, validWords);

        visited[row][col] = false; // Backtrack
    }
}

class SimpleDictionary implements Dictionary {
    private Set<String> words = new HashSet<>();

    public SimpleDictionary() {
        words.add("pot");
        words.add("air");
        // Additional words and prefix logic here
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public boolean isPrefix(String prefix) {
        for (String word : words) {
            if (word.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}