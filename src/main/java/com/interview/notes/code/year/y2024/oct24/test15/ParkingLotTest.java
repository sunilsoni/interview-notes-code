package com.interview.notes.code.year.y2024.oct24.test15;

public class ParkingLotTest {
    public static void main(String[] args) {
        runAllTests();
    }

    private static void runAllTests() {
        testBasicFunctionality();
        testMultiLevelFunctionality();
        testLargeDataInput();
    }

    private static void testBasicFunctionality() {
        ParkingLot parkingLot = new ParkingLot(1, 5, 3, 2);

        assert parkingLot.getTotalSpots() == 10 : "Total spots test failed";
        assert parkingLot.getRemainingSpots() == 10 : "Remaining spots test failed";

        parkingLot.parkVehicle(VehicleType.CAR);
        assert parkingLot.getRemainingSpots() == 9 : "Parking car test failed";

        parkingLot.parkVehicle(VehicleType.MOTORCYCLE);
        assert parkingLot.getRemainingSpots() == 8 : "Parking motorcycle test failed";

        parkingLot.parkVehicle(VehicleType.VAN);
        assert parkingLot.getRemainingSpots() == 5 : "Parking van test failed";

        assert parkingLot.isParkingLotFull() == false : "Parking lot full test failed";
        assert parkingLot.isParkingLotEmpty() == false : "Parking lot empty test failed";

        System.out.println("Basic functionality tests passed");
    }

    private static void testMultiLevelFunctionality() {
        ParkingLot parkingLot = new ParkingLot(3, 5, 3, 2);

        // Fill the first level
        for (int i = 0; i < 10; i++) {
            parkingLot.parkVehicle(VehicleType.CAR);
        }

        assert parkingLot.getCurrentLevel() == 1 : "Multi-level filling test failed";

        // Fill the second level
        for (int i = 0; i < 10; i++) {
            parkingLot.parkVehicle(VehicleType.CAR);
        }

        assert parkingLot.getCurrentLevel() == 2 : "Multi-level filling test failed";

        System.out.println("Multi-level functionality tests passed");
    }

    private static void testLargeDataInput() {
        ParkingLot parkingLot = new ParkingLot(100, 1000, 500, 200);

        // Park a large number of vehicles
        for (int i = 0; i < 10000; i++) {
            parkingLot.parkVehicle(VehicleType.CAR);
        }

        assert parkingLot.getRemainingSpots() == 70000 : "Large data input test failed";

        // Park more vehicles of different types
        for (int i = 0; i < 5000; i++) {
            parkingLot.parkVehicle(VehicleType.MOTORCYCLE);
        }

        for (int i = 0; i < 1000; i++) {
            parkingLot.parkVehicle(VehicleType.VAN);
        }

        assert parkingLot.getRemainingSpots() == 62000 : "Large data input test failed";

        System.out.println("Large data input tests passed");
    }

}
