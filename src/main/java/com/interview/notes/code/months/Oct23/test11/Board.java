package com.interview.notes.code.months.Oct23.test11;

class Board {
    // private int[] squares = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6};

    private int[] squares = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6};

    public int getNextPosition(int currentPosition, int dice1, int dice2) {
        int targetNumber = (dice1 == dice2) ? dice1 : Math.max(dice1, dice2);
        int occurrences = (dice1 == dice2) ? 2 : 1;

        for (int i = currentPosition + 1; i < squares.length; i++) {
            if (squares[i] == targetNumber) {
                occurrences--;
                if (occurrences == 0) return i;
            }
        }
        return squares.length; // If not found, move to end
    }

    public boolean hasReachedEnd(int position) {
        return position >= squares.length;
    }
}
