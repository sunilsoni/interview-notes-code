package com.interview.notes.code.months.nov23.test7;

/**
 * Consider the input integer as seat number. Based on the seat number, we need to return the type of the seat.
 *  In flights, the seat number will be like
 *
 * 1  2  3     4  5  6
 * 12 11 10    9  8  7
 * 13 14 15    16 17 18
 * ....
 * 97 98 99    100
 *
 * Input : 15
 * Output : Asile Seat
 *
 * Input : 1
 * Output : Window Seat
 *
 * Input : 2
 * Output : Middle Seat
 */
public class FlightSeatType {

    // Main method for example execution
    public static void main(String[] args) {
        System.out.println(getSeatType(15)); // Output: Aisle Seat
        System.out.println(getSeatType(1));  // Output: Window Seat
        System.out.println(getSeatType(2));  // Output: Middle Seat
    }

    // Method to determine the seat type
    public static String getSeatType(int seatNumber) {
        int row = (seatNumber - 1) / 6;  // Determine the row of the seat
        int posInRow = (seatNumber - 1) % 6; // Position in the row (0 to 5)

        // Window seat conditions
        if (posInRow == 0 || posInRow == 5) {
            return "Window Seat";
        }
        // Aisle seat conditions
        else if (posInRow == 2 || posInRow == 3) {
            return "Aisle Seat";
        }
        // Middle seat condition
        else {
            return "Middle Seat";
        }
    }
}
