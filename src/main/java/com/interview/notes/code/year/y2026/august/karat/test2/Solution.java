package com.interview.notes.code.year.y2026.august.karat.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

    static void test(String name, boolean pass) {
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {

        // Test 1
        FacilityManager m1 = new FacilityManager();

        Reservation r1 =
                new Reservation(101, "Alice Johnson", 1, 480, 600);

        test(
                "Make reservation",
                m1.makeReservation(r1)
                        && m1.reservations.size() == 1
        );


        // Test 2: conflict
        FacilityManager m2 = new FacilityManager();

        Reservation r2a =
                new Reservation(101, "Alice Johnson", 1, 480, 600);

        Reservation r2b =
                new Reservation(102, "Bob Smith", 1, 540, 660);

        m2.makeReservation(r2a);

        test(
                "Conflict detected",
                !m2.makeReservation(r2b)
        );


        // Test 3: different equipment
        FacilityManager m3 = new FacilityManager();

        Reservation r3a =
                new Reservation(101, "Alice Johnson", 1, 480, 600);

        Reservation r3b =
                new Reservation(102, "Bob Smith", 2, 480, 600);

        test(
                "Different equipment",
                m3.makeReservation(r3a)
                        && m3.makeReservation(r3b)
        );


        // Test 4: cancellation should free slot
        FacilityManager m4 = new FacilityManager();

        Reservation r4a =
                new Reservation(101, "Alice Johnson", 1, 480, 600);

        m4.makeReservation(r4a);

        boolean cancelled = m4.cancelReservation(101);

        Reservation r4b =
                new Reservation(102, "Bob Smith", 1, 480, 600);

        boolean bookedAgain = m4.makeReservation(r4b);

        test(
                "Cancelled reservation frees slot",
                cancelled
                        && r4a.status == ReservationStatus.CANCELLED
                        && bookedAgain
                        && m4.reservations.size() == 2
        );


        // TODO 1 TEST
        FacilityManager m5 = new FacilityManager();

        Reservation a1 =
                new Reservation(101, "Alice Johnson", 1, 480, 600);

        Reservation b1 =
                new Reservation(102, "Bob Smith", 1, 660, 720);

        Reservation a2 =
                new Reservation(103, "Alice Johnson", 2, 720, 840);

        Reservation c1 =
                new Reservation(104, "Carol White", 2, 480, 540);

        m5.makeReservation(a1);
        m5.makeReservation(b1);
        m5.makeReservation(a2);
        m5.makeReservation(c1);

        List<Reservation> alice =
                m5.getReservationsForMember("Alice Johnson");

        test(
                "TODO 1 - Alice has 2 reservations",
                alice.size() == 2
                        && alice.contains(a1)
                        && alice.contains(a2)
        );


        // Important String equals test
        List<Reservation> aliceNewString =
                m5.getReservationsForMember(
                        "Alice Johnson");

        test(
                "TODO 1 - String equals",
                aliceNewString.size() == 2
        );


        // Member with no reservation
        test(
                "TODO 1 - No reservations",
                m5.getReservationsForMember(
                        "David Brown").isEmpty()
        );


        // Cancel Alice reservation
        m5.cancelReservation(101);

        List<Reservation> aliceAfter =
                m5.getReservationsForMember("Alice Johnson");

        test(
                "TODO 1 - Cancelled ignored",
                aliceAfter.size() == 1
                        && aliceAfter.contains(a2)
        );


        // TODO 2 TEST
        FacilityManager m6 = new FacilityManager();

        Reservation e1 =
                new Reservation(101, "Alice Johnson", 1, 480, 540);

        Reservation e2 =
                new Reservation(102, "Bob Smith", 1, 660, 720);

        Reservation e3 =
                new Reservation(103, "Carol White", 1, 720, 780);

        Reservation e4 =
                new Reservation(104, "David Brown", 2, 480, 600);

        m6.makeReservation(e1);
        m6.makeReservation(e2);
        m6.makeReservation(e3);
        m6.makeReservation(e4);

        Map<String, Integer> summary1 =
                m6.getEquipmentSummary(1);

        test(
                "TODO 2 - Equipment 1",
                summary1.get("total_reservations") == 3
                        && summary1.get("total_minutes") == 180
        );


        Map<String, Integer> summary2 =
                m6.getEquipmentSummary(2);

        test(
                "TODO 2 - Equipment 2",
                summary2.get("total_reservations") == 1
                        && summary2.get("total_minutes") == 120
        );


        Map<String, Integer> summary3 =
                m6.getEquipmentSummary(3);

        test(
                "TODO 2 - Empty equipment",
                summary3.get("total_reservations") == 0
                        && summary3.get("total_minutes") == 0
        );


        m6.cancelReservation(101);

        Map<String, Integer> afterCancel =
                m6.getEquipmentSummary(1);

        test(
                "TODO 2 - Cancelled ignored",
                afterCancel.get("total_reservations") == 2
                        && afterCancel.get("total_minutes") == 120
        );


        // Large data test
        FacilityManager large = new FacilityManager();

        for (int i = 0; i < 10000; i++) {
            large.makeReservation(
                    new Reservation(
                            i,
                            "User" + i,
                            i,
                            480,
                            540
                    )
            );
        }

        test(
                "Large data",
                large.reservations.size() == 10000
        );
    }

    enum ReservationStatus {
        ACTIVE,
        CANCELLED
    }

    record Equipment(int equipmentId, String name) {

        @Override
            public String toString() {
                return "Equipment ID: " + equipmentId + ", Name: " + name;
            }
        }

    static class Reservation {
        final int reservationId;
        final String memberName;
        final int equipmentId;
        final int startTime;
        final int endTime;
        ReservationStatus status;

        Reservation(int reservationId, String memberName,
                    int equipmentId, int startTime, int endTime) {
            this.reservationId = reservationId;
            this.memberName = memberName;
            this.equipmentId = equipmentId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.status = ReservationStatus.ACTIVE;
        }

        int getDuration() {
            return endTime - startTime;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Reservation r))
                return false;
            return reservationId == r.reservationId;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(reservationId);
        }
    }

    static class FacilityManager {

        final ArrayList<Equipment> equipmentList = new ArrayList<>();
        final ArrayList<Reservation> reservations = new ArrayList<>();

        void addEquipment(Equipment equipment) {
            equipmentList.add(equipment);
        }

        boolean makeReservation(Reservation reservation) {
            if (!isAvailable(
                    reservation.equipmentId,
                    reservation.startTime,
                    reservation.endTime)) {
                return false;
            }

            reservations.add(reservation);
            return true;
        }

        boolean isAvailable(int equipmentId, int startTime, int endTime) {
            for (Reservation res : reservations) {

                // TODO 0: Previous bug fix
                if (res.status == ReservationStatus.ACTIVE
                        && res.equipmentId == equipmentId
                        && startTime < res.endTime
                        && endTime > res.startTime) {
                    return false;
                }
            }

            return true;
        }

        boolean cancelReservation(int reservationId) {
            for (Reservation res : reservations) {
                if (res.reservationId == reservationId) {
                    res.status = ReservationStatus.CANCELLED;
                    return true;
                }
            }

            return false;
        }

        // TODO 1: Get all ACTIVE reservations for a member
        List<Reservation> getReservationsForMember(String name) {
            return reservations.stream()
                    .filter(r -> r.status == ReservationStatus.ACTIVE
                            && r.memberName.equals(name))
                    .toList();
        }

        // TODO 2: Get ACTIVE reservation count and total minutes
        Map<String, Integer> getEquipmentSummary(int id) {
            var list = reservations.stream()
                    .filter(r -> r.status == ReservationStatus.ACTIVE
                            && r.equipmentId == id)
                    .toList();

            return Map.of(
                    "total_reservations", list.size(),
                    "total_minutes",
                    list.stream()
                            .mapToInt(Reservation::getDuration)
                            .sum()
            );
        }
    }
}