package com.interview.notes.code.year.y2025.June.microsoft.test1;

import java.util.concurrent.locks.ReentrantLock;

public class Theater {
    private final int rows;
    private final int columns;
    private final Seat[][] seats;

    public Theater(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new Seat[rows][columns];

        // Initialize all seats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    // Test the implementation
    public static void main(String[] args) {
        Theater theater = new Theater(3, 4);  // 3x4 theater

        // Print initial layout
        System.out.println("Initial theater layout:");
        theater.printTheaterLayout();

        // Test booking some seats
        theater.bookSeat(0, 0);
        theater.bookSeat(1, 2);

        // Try to book an already booked seat
        boolean result = theater.bookSeat(0, 0);
        System.out.println("Trying to book already booked seat: " + result);

        // Print final layout
        System.out.println("\nFinal theater layout:");
        theater.printTheaterLayout();
    }

    public boolean bookSeat(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IllegalArgumentException("Invalid seat position");
        }

        Seat seat = seats[row][column];
        seat.lock.lock();
        try {
            if (!seat.isBooked) {
                seat.isBooked = true;
                System.out.println("Successfully booked seat: Row " + row + ", Column " + column);
                return true;
            }
            return false;
        } finally {
            seat.lock.unlock();
        }
    }

    // Optional: Method to view theater layout
    public void printTheaterLayout() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(seats[i][j].isBooked ? "[X] " : "[ ] ");
            }
            System.out.println();
        }
    }

    // Inner class representing a seat
    private static class Seat {
        private final ReentrantLock lock = new ReentrantLock();
        private final int row;
        private final int column;
        private boolean isBooked = false;

        public Seat(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
