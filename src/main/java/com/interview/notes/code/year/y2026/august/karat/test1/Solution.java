package com.interview.notes.code.year.y2026.august.karat.test1;

import java.util.ArrayList;
import java.util.List;

public class Solution { // Main class containing the complete program.

    static void test(String name, boolean condition) { // Simple replacement for JUnit assertions.

        System.out.println(name + ": " + (condition ? "PASS" : "FAIL")); // Prints test result.

    } // Ends test method.

    public static void main(String[] args) { // Starts our manual tests.

        FacilityManager m1 = new FacilityManager(); // Creates manager for test 1.

        Reservation r1 =
                new Reservation(101, "Alice Johnson", 1, 480, 600); // Creates first reservation.

        test(
                "Make reservation",
                m1.makeReservation(r1) && m1.reservations.size() == 1
        ); // Verifies a valid reservation is added.

        FacilityManager m2 = new FacilityManager(); // Creates manager for conflict test.

        Reservation r2a =
                new Reservation(101, "Alice Johnson", 1, 480, 600); // Creates first overlapping reservation.

        Reservation r2b =
                new Reservation(102, "Bob Smith", 1, 540, 660); // Creates second overlapping reservation.

        m2.makeReservation(r2a); // Adds the first reservation.

        test(
                "Conflict detected",
                !m2.makeReservation(r2b)
        ); // Verifies overlapping reservation for same equipment is rejected.

        FacilityManager m3 = new FacilityManager(); // Creates manager for equipment test.

        Reservation r3a =
                new Reservation(101, "Alice Johnson", 1, 480, 600); // Creates reservation for equipment 1.

        Reservation r3b =
                new Reservation(102, "Bob Smith", 2, 480, 600); // Creates same-time reservation for equipment 2.

        test(
                "Different equipment",
                m3.makeReservation(r3a) && m3.makeReservation(r3b)
        ); // Verifies different equipment can be booked at the same time.

        FacilityManager m4 = new FacilityManager(); // Creates manager for cancellation test.

        Reservation r4a =
                new Reservation(101, "Alice Johnson", 1, 480, 600); // Creates original reservation.

        m4.makeReservation(r4a); // Books the original slot.

        boolean cancelled = m4.cancelReservation(101); // Cancels the original reservation.

        Reservation r4b =
                new Reservation(102, "Bob Smith", 1, 480, 600); // Requests the exact same slot.

        boolean bookedAgain = m4.makeReservation(r4b); // Attempts booking after cancellation.

        test(
                "Cancelled reservation frees slot",
                cancelled
                        && r4a.status == ReservationStatus.CANCELLED
                        && bookedAgain
                        && m4.reservations.size() == 2
        ); // Verifies the bug is fixed.

        FacilityManager m5 = new FacilityManager(); // Creates manager for adjacent-time test.

        m5.makeReservation(
                new Reservation(101, "Alice", 1, 480, 600)
        ); // Books equipment until minute 600.

        test(
                "Back-to-back reservation",
                m5.makeReservation(
                        new Reservation(102, "Bob", 1, 600, 660)
                )
        ); // Verifies starting exactly when another reservation ends is allowed.

        FacilityManager m6 = new FacilityManager(); // Creates manager for missing-ID test.

        test(
                "Cancel unknown reservation",
                !m6.cancelReservation(999)
        ); // Verifies cancelling a nonexistent reservation returns false.

        Reservation duration =
                new Reservation(1, "Alice", 1, 480, 600); // Creates reservation for duration test.

        test(
                "Duration",
                duration.getDuration() == 120
        ); // Verifies duration calculation.

        FacilityManager large = new FacilityManager(); // Creates manager for large-data testing.

        boolean largePass = true; // Tracks whether every large-data booking succeeds.

        for (int i = 0; i < 5000; i++) { // Creates 5,000 reservations.

            if (!large.makeReservation(
                    new Reservation(
                            i,
                            "User" + i,
                            i,
                            480,
                            600))) { // Uses different equipment IDs to avoid conflicts.

                largePass = false; // Records failure if any valid booking is rejected.

                break; // Stops because the large-data test already failed.

            } // Ends failure check.

        } // Ends large-data loop.

        test(
                "Large data 5000 reservations",
                largePass && large.reservations.size() == 5000
        ); // Verifies the brute-force implementation handles a larger input.

    } // Ends main method.

    enum ReservationStatus { // Defines the possible states of a reservation.
        ACTIVE, // Means the reservation currently occupies the time slot.
        CANCELLED // Means the reservation no longer occupies the time slot.
    } // Ends ReservationStatus enum.

    /**
     * @param equipmentId Stores the unique equipment ID.
     * @param name        Stores the equipment name.
     */
    record Equipment(int equipmentId, String name) { // Represents equipment that members can reserve.

        // Creates an equipment object.
        // Saves the given equipment ID.
        // Saves the given equipment name.
        // Ends constructor.

    } // Ends Equipment class.

    static class Reservation { // Represents one equipment reservation.

        final int reservationId; // Stores the unique reservation ID.

        final String memberName; // Stores the member making the reservation.

        final int equipmentId; // Stores which equipment is being reserved.

        final int startTime; // Stores reservation starting time in minutes.

        final int endTime; // Stores reservation ending time in minutes.

        ReservationStatus status; // Stores whether reservation is active or cancelled.

        Reservation(int reservationId, String memberName,
                    int equipmentId, int startTime, int endTime) { // Creates a reservation.

            this.reservationId = reservationId; // Saves reservation ID.

            this.memberName = memberName; // Saves member name.

            this.equipmentId = equipmentId; // Saves equipment ID.

            this.startTime = startTime; // Saves starting time.

            this.endTime = endTime; // Saves ending time.

            this.status = ReservationStatus.ACTIVE; // Every new reservation starts as active.

        } // Ends constructor.

        int getDuration() { // Returns how long the reservation lasts.

            return endTime - startTime; // Calculates end minus start.

        } // Ends getDuration method.

    } // Ends Reservation class.

    static class FacilityManager { // Manages equipment and reservations.

        final List<Equipment> equipmentList = new ArrayList<>(); // Stores facility equipment.

        final List<Reservation> reservations = new ArrayList<>(); // Stores all reservations.

        void addEquipment(Equipment equipment) { // Adds equipment to the inventory.

            equipmentList.add(equipment); // Stores the equipment.

        } // Ends addEquipment method.

        boolean makeReservation(Reservation reservation) { // Tries to create a reservation.

            if (!isAvailable(
                    reservation.equipmentId,
                    reservation.startTime,
                    reservation.endTime)) { // Checks whether requested equipment and time are free.

                return false; // Rejects the reservation when a conflict exists.

            } // Ends availability condition.

            reservations.add(reservation); // Stores the reservation when no conflict exists.

            return true; // Reports successful reservation.

        } // Ends makeReservation method.

        boolean isAvailable(int equipmentId, int startTime, int endTime) { // Checks the requested slot.

            for (Reservation res : reservations) { // Brute-force scans every existing reservation.

                if (res.status == ReservationStatus.CANCELLED) { // Checks whether this reservation was cancelled.

                    continue; // Ignores cancelled reservations because they no longer block the slot.

                } // Ends cancelled-status check.

                if (res.equipmentId == equipmentId) { // Checks only reservations for the same equipment.

                    if (startTime < res.endTime && endTime > res.startTime) { // Detects time overlap.

                        return false; // Equipment is unavailable when an active overlap exists.

                    } // Ends overlap check.

                } // Ends equipment check.

            } // Ends reservation scan.

            return true; // No active conflicting reservation was found.

        } // Ends isAvailable method.

        boolean cancelReservation(int reservationId) { // Cancels a reservation using its ID.

            for (Reservation res : reservations) { // Searches every stored reservation.

                if (res.reservationId == reservationId) { // Checks for the requested reservation ID.

                    res.status = ReservationStatus.CANCELLED; // Marks the matching reservation cancelled.

                    return true; // Reports successful cancellation.

                } // Ends ID check.

            } // Ends reservation search.

            return false; // Reports false when the reservation does not exist.

        } // Ends cancelReservation method.

    } // Ends FacilityManager class.

} // Ends Solution class.