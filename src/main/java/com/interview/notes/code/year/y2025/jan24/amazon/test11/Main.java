package com.interview.notes.code.year.y2025.jan24.amazon.test11;// Main.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    // Main method with test cases
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 7;

        // List to hold all test devices
        List<Device> testDevices = new ArrayList<>();

        // Adding test devices
        testDevices.add(new Tablet("Tablet", 75, true));        // Test Case 1
        testDevices.add(new EchoDot("Echo Dot", true));        // Test Case 2
        testDevices.add(new EchoDot("Echo Dot", false));       // Test Case 3
        testDevices.add(new Microwave("Microwave", true));     // Test Case 4
        testDevices.add(new EchoInput("Echo Input"));          // Test Case 5
        testDevices.add(new Tablet("Tablet2", 50, false));      // Test Case 6

        // Expected outputs for test cases 1-6
        List<List<String>> expectedOutputs = new ArrayList<>();

        // Test Case 1: Tablet with battery, speaker, and display, charging at 75%
        List<String> expectedTabletStatus = new ArrayList<>();
        expectedTabletStatus.add("Speaker says: Current battery level is 75% and charging");
        expectedTabletStatus.add("Display shows: Current battery level is 75% and charging");
        expectedOutputs.add(expectedTabletStatus);

        // Test Case 2: Echo Dot without battery, plugged in
        List<String> expectedEchoDotStatus = new ArrayList<>();
        expectedEchoDotStatus.add("Speaker says: Device is plugged in");
        expectedOutputs.add(expectedEchoDotStatus);

        // Test Case 3: Echo Dot without battery, not plugged in
        List<String> expectedEchoDotStatus2 = new ArrayList<>();
        expectedEchoDotStatus2.add("Speaker says: Device is not plugged in");
        expectedOutputs.add(expectedEchoDotStatus2);

        // Test Case 4: Microwave with display only, plugged in
        List<String> expectedMicrowaveStatus = new ArrayList<>();
        expectedMicrowaveStatus.add("Display shows: Device is plugged in");
        expectedOutputs.add(expectedMicrowaveStatus);

        // Test Case 5: EchoInput with neither speaker nor display
        List<String> expectedEchoInputStatus = new ArrayList<>();
        expectedEchoInputStatus.add("No speaker or display available to report status.");
        expectedOutputs.add(expectedEchoInputStatus);

        // Test Case 6: Tablet with battery, speaker, and display, not charging at 50%
        List<String> expectedTabletStatus2 = new ArrayList<>();
        expectedTabletStatus2.add("Speaker says: Current battery level is 50% and not charging");
        expectedTabletStatus2.add("Display shows: Current battery level is 50% and not charging");
        expectedOutputs.add(expectedTabletStatus2);

        // Running Test Cases 1-6
        for (int i = 0; i < 6; i++) {
            Device device = testDevices.get(i);
            List<String> expected = expectedOutputs.get(i);
            List<String> actual = device.reportStatus();
            if (compareLists(expected, actual)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                testsPassed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                printTestDetails(expected, actual);
            }
        }

        // Test Case 7: Large Number of Devices
        System.out.println("\nRunning Test Case 7: Large Number of Devices");
        boolean largeTestPassed = true;
        int largeNumber = 1000;
        for (int i = 0; i < largeNumber; i++) {
            // Alternate between charging and not charging
            Tablet tabletLarge = new Tablet("Tablet_Large_" + i, i % 100, i % 2 == 0);
            List<String> status = tabletLarge.reportStatus();
            // Check if two messages are returned
            if (status.size() != 2) {
                largeTestPassed = false;
                System.out.println("Test Case 7: FAIL at device " + i);
                break;
            }
        }
        if (largeTestPassed) {
            System.out.println("Test Case 7: PASS");
            testsPassed++;
        } else {
            System.out.println("Test Case 7: FAIL");
        }

        // Summary of Test Results
        System.out.println("\nTests Passed: " + testsPassed + "/" + totalTests);
    }

    // Helper method to compare two lists of strings
    private static boolean compareLists(List<String> expected, List<String> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!Objects.equals(expected.get(i), actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Helper method to print test details when a test fails
    private static void printTestDetails(List<String> expected, List<String> actual) {
        System.out.println("Expected:");
        for (String msg : expected) {
            System.out.println("  " + msg);
        }
        System.out.println("Actual:");
        for (String msg : actual) {
            System.out.println("  " + msg);
        }
    }

    // Interface for speaker capability
    public interface Speaker {
        void speak(String message);
    }

    // Interface for display capability
    public interface Display {
        void show(String message);
    }

    // Interface for battery-powered devices
    public interface BatteryPowered {
        int getBatteryLevel(); // Returns battery level percentage

        boolean isCharging();
    }

    // Abstract base class for all devices
    public static abstract class Device {
        protected String deviceName;

        public Device(String deviceName) {
            this.deviceName = deviceName;
        }

        // Abstract method to report status, to be implemented by subclasses
        public abstract List<String> reportStatus();
    }

    // Class to generate power status messages
    public static class PowerStatusReporter {
        public static String generateStatus(BatteryPowered device) {
            int level = device.getBatteryLevel();
            boolean charging = device.isCharging();
            String status = "Current battery level is " + level + "% and ";
            status += charging ? "charging" : "not charging";
            return status;
        }
    }

    // EchoShow device with speaker, display, and battery
    public static class EchoShow extends Device implements Speaker, Display, BatteryPowered {
        private int batteryLevel;
        private boolean charging;

        public EchoShow(String deviceName, int batteryLevel, boolean charging) {
            super(deviceName);
            this.batteryLevel = batteryLevel;
            this.charging = charging;
        }

        @Override
        public void speak(String message) {
            // In a real device, this would interface with the speaker hardware
            // For simulation, we'll just return the message
            // System.out.println("Speaker says: " + message);
        }

        @Override
        public void show(String message) {
            // In a real device, this would interface with the display hardware
            // For simulation, we'll just return the message
            // System.out.println("Display shows: " + message);
        }

        @Override
        public int getBatteryLevel() {
            return batteryLevel;
        }

        @Override
        public boolean isCharging() {
            return charging;
        }

        @Override
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();
            String status = PowerStatusReporter.generateStatus(this);
            messages.add("Speaker says: " + status);
            messages.add("Display shows: " + status);
            return messages;
        }
    }

    // Tablet device with speaker, display, and battery
    public static class Tablet extends Device implements Speaker, Display, BatteryPowered {
        private int batteryLevel;
        private boolean charging;

        public Tablet(String deviceName, int batteryLevel, boolean charging) {
            super(deviceName);
            this.batteryLevel = batteryLevel;
            this.charging = charging;
        }

        @Override
        public void speak(String message) {
            // Simulate speaking
            // System.out.println("Speaker says: " + message);
        }

        @Override
        public void show(String message) {
            // Simulate displaying
            // System.out.println("Display shows: " + message);
        }

        @Override
        public int getBatteryLevel() {
            return batteryLevel;
        }

        @Override
        public boolean isCharging() {
            return charging;
        }

        @Override
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();
            String status = PowerStatusReporter.generateStatus(this);
            messages.add("Speaker says: " + status);
            messages.add("Display shows: " + status);
            return messages;
        }
    }

    // EchoDot device with speaker only, no battery
    public static class EchoDot extends Device implements Speaker {
        private boolean pluggedIn;

        public EchoDot(String deviceName, boolean pluggedIn) {
            super(deviceName);
            this.pluggedIn = pluggedIn;
        }

        @Override
        public void speak(String message) {
            // Simulate speaking
            // System.out.println("Speaker says: " + message);
        }

        @Override
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();
            String status = pluggedIn ? "Device is plugged in" : "Device is not plugged in";
            messages.add("Speaker says: " + status);
            return messages;
        }
    }

    // Microwave device with display only, no battery
    public static class Microwave extends Device implements Display {
        private boolean pluggedIn;

        public Microwave(String deviceName, boolean pluggedIn) {
            super(deviceName);
            this.pluggedIn = pluggedIn;
        }

        @Override
        public void show(String message) {
            // Simulate displaying
            // System.out.println("Display shows: " + message);
        }

        @Override
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();
            String status = pluggedIn ? "Device is plugged in" : "Device is not plugged in";
            messages.add("Display shows: " + status);
            return messages;
        }
    }

    // EchoInput device with neither speaker nor display
    public static class EchoInput extends Device {

        public EchoInput(String deviceName) {
            super(deviceName);
        }

        @Override
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();
            messages.add("No speaker or display available to report status.");
            return messages;
        }
    }
}
