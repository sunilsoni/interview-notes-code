package com.interview.notes.code.year.y2026.august.karat.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Solution {

    static void test(String name, boolean pass) {
        System.out.println(
                name + ": " + (pass ? "PASS" : "FAIL")
        );
    }

    public static void main(String[] args) {

        // Original Test 1
        FacilityManager manager1 = new FacilityManager();

        Reservation res =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                );

        test(
                "Make reservation",
                manager1.makeReservation(res)
                        && manager1.reservations.size() == 1
        );


        // Original Test 2
        FacilityManager manager2 = new FacilityManager();

        Reservation res1 =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                );

        Reservation res2 =
                new Reservation(
                        102,
                        "Bob Smith",
                        1,
                        540,
                        660
                );

        manager2.makeReservation(res1);

        test(
                "Conflict detected",
                !manager2.makeReservation(res2)
        );


        // Original Test 3
        FacilityManager manager3 = new FacilityManager();

        Reservation r1 =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                );

        Reservation r2 =
                new Reservation(
                        102,
                        "Bob Smith",
                        2,
                        480,
                        600
                );

        test(
                "Different equipment",
                manager3.makeReservation(r1)
                        && manager3.makeReservation(r2)
        );


        // Original Test 4
        FacilityManager manager4 = new FacilityManager();

        Reservation cancel1 =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                );

        manager4.makeReservation(cancel1);
        manager4.cancelReservation(101);

        Reservation cancel2 =
                new Reservation(
                        102,
                        "Bob Smith",
                        1,
                        480,
                        600
                );

        test(
                "Cancelled reservation frees slot",
                manager4.makeReservation(cancel2)
                        && manager4.reservations.size() == 2
                        && cancel1.status
                        == ReservationStatus.CANCELLED
        );


        // TODO 1 TEST
        FacilityManager memberManager =
                new FacilityManager();

        Reservation m1 =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                );

        Reservation m2 =
                new Reservation(
                        102,
                        "Bob Smith",
                        1,
                        660,
                        720
                );

        Reservation m3 =
                new Reservation(
                        103,
                        "Alice Johnson",
                        2,
                        720,
                        840
                );

        Reservation m4 =
                new Reservation(
                        104,
                        "Carol White",
                        2,
                        480,
                        540
                );

        memberManager.makeReservation(m1);
        memberManager.makeReservation(m2);
        memberManager.makeReservation(m3);
        memberManager.makeReservation(m4);

        List<Reservation> alice =
                memberManager
                        .getReservationsForMember(
                                "Alice Johnson"
                        );

        test(
                "TODO 1 - Alice",
                alice.size() == 2
                        && alice.contains(m1)
                        && alice.contains(m3)
        );


        // new String test
        List<Reservation> alice2 =
                memberManager
                        .getReservationsForMember(
                                "Alice Johnson"
                        );

        test(
                "TODO 1 - String equals",
                alice2.size() == 2
        );


        test(
                "TODO 1 - No member",
                memberManager
                        .getReservationsForMember(
                                "David Brown"
                        )
                        .isEmpty()
        );


        memberManager.cancelReservation(101);

        List<Reservation> aliceAfter =
                memberManager
                        .getReservationsForMember(
                                "Alice Johnson"
                        );

        test(
                "TODO 1 - Ignore cancelled",
                aliceAfter.size() == 1
                        && aliceAfter.contains(m3)
        );


        // TODO 2 TEST
        FacilityManager summaryManager =
                new FacilityManager();

        Reservation s1 =
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        540
                );

        Reservation s2 =
                new Reservation(
                        102,
                        "Bob Smith",
                        1,
                        660,
                        720
                );

        Reservation s3 =
                new Reservation(
                        103,
                        "Carol White",
                        1,
                        720,
                        780
                );

        Reservation s4 =
                new Reservation(
                        104,
                        "David Brown",
                        2,
                        480,
                        600
                );

        summaryManager.makeReservation(s1);
        summaryManager.makeReservation(s2);
        summaryManager.makeReservation(s3);
        summaryManager.makeReservation(s4);

        Map<String, Integer> summary1 =
                summaryManager
                        .getEquipmentSummary(1);

        test(
                "TODO 2 - Equipment 1",
                summary1.get("total_reservations") == 3
                        && summary1.get("total_minutes") == 180
        );


        Map<String, Integer> summary2 =
                summaryManager
                        .getEquipmentSummary(2);

        test(
                "TODO 2 - Equipment 2",
                summary2.get("total_reservations") == 1
                        && summary2.get("total_minutes") == 120
        );


        Map<String, Integer> summary3 =
                summaryManager
                        .getEquipmentSummary(3);

        test(
                "TODO 2 - No reservations",
                summary3.get("total_reservations") == 0
                        && summary3.get("total_minutes") == 0
        );


        summaryManager.cancelReservation(101);

        Map<String, Integer> summaryAfter =
                summaryManager
                        .getEquipmentSummary(1);

        test(
                "TODO 2 - Ignore cancelled",
                summaryAfter.get("total_reservations") == 2
                        && summaryAfter.get("total_minutes") == 120
        );


        // TODO 3 TEST
        FacilityManager availableManager =
                new FacilityManager();

        availableManager.addEquipment(
                new Equipment(1, "Treadmill")
        );

        availableManager.addEquipment(
                new Equipment(2, "Rowing Machine")
        );

        availableManager.addEquipment(
                new Equipment(3, "Exercise Bike")
        );

        availableManager.addEquipment(
                new Equipment(4, "Elliptical Trainer")
        );

        availableManager.makeReservation(
                new Reservation(
                        101,
                        "Alice Johnson",
                        1,
                        480,
                        600
                )
        );

        availableManager.makeReservation(
                new Reservation(
                        102,
                        "Bob Smith",
                        2,
                        300,
                        420
                )
        );

        availableManager.makeReservation(
                new Reservation(
                        103,
                        "Carol White",
                        3,
                        700,
                        780
                )
        );


        // Exactly 30 minutes after equipment 1
        test(
                "TODO 3 - Exactly 30 min",
                availableManager
                        .getAvailableEquipment(
                                630,
                                720
                        )
                        .equals(
                                Arrays.asList(1, 2, 4)
                        )
        );


        // Only 20 minutes after equipment 1
        test(
                "TODO 3 - Under 30 min",
                availableManager
                        .getAvailableEquipment(
                                620,
                                720
                        )
                        .equals(
                                Arrays.asList(2, 4)
                        )
        );


        // Cancelled reservation should not block
        availableManager
                .cancelReservation(101);

        test(
                "TODO 3 - Cancelled ignored",
                availableManager
                        .getAvailableEquipment(
                                620,
                                720
                        )
                        .contains(1)
        );


        // No equipment available
        FacilityManager noEquipment =
                new FacilityManager();

        noEquipment.addEquipment(
                new Equipment(
                        5,
                        "Weight Bench"
                )
        );

        noEquipment.makeReservation(
                new Reservation(
                        201,
                        "Alice Johnson",
                        5,
                        480,
                        600
                )
        );

        test(
                "TODO 3 - None available",
                noEquipment
                        .getAvailableEquipment(
                                610,
                                700
                        )
                        .isEmpty()
        );


        // Buffer BEFORE existing reservation
        FacilityManager beforeManager =
                new FacilityManager();

        beforeManager.addEquipment(
                new Equipment(
                        6,
                        "Spin Bike"
                )
        );

        beforeManager.makeReservation(
                new Reservation(
                        301,
                        "Nina Patel",
                        6,
                        600,
                        720
                )
        );


        // Gap 20 minutes -> blocked
        test(
                "TODO 3 - 20 min before",
                beforeManager
                        .getAvailableEquipment(
                                500,
                                580
                        )
                        .isEmpty()
        );


        // Exactly 30 minutes -> allowed
        test(
                "TODO 3 - 30 min before",
                beforeManager
                        .getAvailableEquipment(
                                500,
                                570
                        )
                        .equals(
                                List.of(6)
                        )
        );


        // Large data test
        FacilityManager large =
                new FacilityManager();

        for (int i = 1; i <= 5000; i++) {
            large.addEquipment(
                    new Equipment(
                            i,
                            "Equipment " + i
                    )
            );
        }

        List<Integer> result =
                large.getAvailableEquipment(
                        480,
                        600
                );

        test(
                "Large data",
                result.size() == 5000
                        && result.get(0) == 1
                        && result.get(4999) == 5000
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

        @Override
        public String toString() {
            return "Reservation ID: " + reservationId
                    + ", Member: " + memberName
                    + ", Equipment ID: " + equipmentId
                    + ", Start: " + startTime
                    + ", End: " + endTime
                    + ", Status: " + status;
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
                if (res.status != ReservationStatus.CANCELLED
                        && res.equipmentId == equipmentId) {

                    if (startTime < res.endTime
                            && endTime > res.startTime) {
                        return false;
                    }
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

        // TODO 1
        List<Reservation> getReservationsForMember(String name) {
            return reservations.stream()
                    .filter(r ->
                            r.status == ReservationStatus.ACTIVE
                                    && r.memberName.equals(name))
                    .toList();
        }

        // TODO 2
        Map<String, Integer> getEquipmentSummary(int id) {
            var list = reservations.stream()
                    .filter(r ->
                            r.status == ReservationStatus.ACTIVE
                                    && r.equipmentId == id)
                    .toList();

            int sum = list.stream()
                    .mapToInt(Reservation::getDuration)
                    .sum();

            return Map.of(
                    "total_reservations", list.size(),
                    "total_minutes", sum
            );
        }

        // TODO 3
        List<Integer> getAvailableEquipment(int startTime, int endTime) {
            return equipmentList.stream()

                    .filter(e -> reservations.stream()

                            .filter(r ->
                                    r.status == ReservationStatus.ACTIVE
                                            && r.equipmentId == e.equipmentId)

                            .noneMatch(r ->
                                    startTime < r.endTime + 30
                                            && endTime > r.startTime - 30))

                    .map(e -> e.equipmentId)

                    .sorted()

                    .toList();
        }
    }
}