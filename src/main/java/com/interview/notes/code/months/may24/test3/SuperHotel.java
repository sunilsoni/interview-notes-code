package com.interview.notes.code.months.may24.test3;

public class SuperHotel extends Hotel {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        // hotel.book(2);
        System.out.print(hotel.bookings);
    }

    public void book() {
        bookings--;
    }

    public void book(int size) {
        book();
        super.book();
        bookings += size;
    }
}