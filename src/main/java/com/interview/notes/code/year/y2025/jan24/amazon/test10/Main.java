package com.interview.notes.code.year.y2025.jan24.amazon.test10;// Main.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Amazon offers a large selection of Alexa devices. Some Alexa devices only have a speaker (Echo, Echo Dot). Some only have a screen/display (Microwave). Some have both (Echo Show, Echo Spot). Others have neither (Echo Input).

Some Alexa devices have batteries (Tablets, Echo Tap, Bose Headset). Others that do not have batteries (Echo Dot, FireTV Stick).

Design a set of classes that will report the current battery/power status to the user.

Depending on the hardware, the response may need to be spoken, or displayed on a screen, or both. Also, depending on whether there is a battery or not, the status message will differ. For example, if the device is a Tablet which has a battery, a speaker, and a display, and currently it happens to be plugged in and recharging (let's say at 75%), then your code should return the following:

- Say "Current battery level is 75% and charging" through the speaker.
- Display "Current battery level is 75% and charging" on the screen.

 */
// Main class containing all components and test cases
public class Main {

    // Main method with test cases
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 8;

        // List to hold all test devices
        List<Device> testDevices = new ArrayList<>();

        // Adding test devices

        // Test Case 1: Tablet with battery, primary speaker, home theater speaker, small display, and TV display, charging at 75%
        List<Speaker> tabletSpeakers = new ArrayList<>();
        tabletSpeakers.add(new SimpleSpeaker("Primary"));
        tabletSpeakers.add(new SimpleSpeaker("Home Theater"));

        List<Display> tabletDisplays = new ArrayList<>();
        tabletDisplays.add(new SimpleDisplay("Small"));
        tabletDisplays.add(new SimpleDisplay("TV"));

        Battery tabletBattery = new SimpleBattery(75, true);
        testDevices.add(new Device("Tablet", tabletSpeakers, tabletDisplays, tabletBattery));

        // Test Case 2: Echo Dot with primary speaker only, plugged in
        List<Speaker> echoDotSpeakers = new ArrayList<>();
        echoDotSpeakers.add(new SimpleSpeaker("Primary"));
        testDevices.add(new Device("Echo Dot", echoDotSpeakers, null, true));

        // Test Case 3: Echo Dot with primary speaker only, not plugged in
        List<Speaker> echoDotNotPluggedSpeakers = new ArrayList<>();
        echoDotNotPluggedSpeakers.add(new SimpleSpeaker("Primary"));
        testDevices.add(new Device("Echo Dot Not Plugged", echoDotNotPluggedSpeakers, null, false));

        // Test Case 4: Microwave with display only (small display and external TV display), plugged in
        List<Display> microwaveDisplays = new ArrayList<>();
        microwaveDisplays.add(new SimpleDisplay("Small"));
        microwaveDisplays.add(new SimpleDisplay("External TV"));
        testDevices.add(new Device("Microwave", null, microwaveDisplays, true));

        // Test Case 5: EchoInput with neither speaker nor display
        testDevices.add(new Device("Echo Input"));

        // Test Case 6: Tablet with battery, primary speaker, small display, not charging at 50%
        List<Speaker> tablet2Speakers = new ArrayList<>();
        tablet2Speakers.add(new SimpleSpeaker("Primary"));

        List<Display> tablet2Displays = new ArrayList<>();
        tablet2Displays.add(new SimpleDisplay("Small"));

        Battery tablet2Battery = new SimpleBattery(50, false);
        testDevices.add(new Device("Tablet2", tablet2Speakers, tablet2Displays, tablet2Battery));

        // Test Case 7: Device with multiple speakers and displays, battery at 100%, charging
        List<Speaker> multiSpeakers = new ArrayList<>();
        multiSpeakers.add(new SimpleSpeaker("Primary"));
        multiSpeakers.add(new SimpleSpeaker("Surround"));

        List<Display> multiDisplays = new ArrayList<>();
        multiDisplays.add(new SimpleDisplay("Main"));
        multiDisplays.add(new SimpleDisplay("Secondary"));

        Battery multiBattery = new SimpleBattery(100, true);
        testDevices.add(new Device("Home Theater System", multiSpeakers, multiDisplays, multiBattery));

        // Test Case 8: Device with multiple speakers and displays, no battery, not plugged in
        List<Speaker> multiSpeakersNoBattery = new ArrayList<>();
        multiSpeakersNoBattery.add(new SimpleSpeaker("Primary"));
        multiSpeakersNoBattery.add(new SimpleSpeaker("Satellite"));

        List<Display> multiDisplaysNoBattery = new ArrayList<>();
        multiDisplaysNoBattery.add(new SimpleDisplay("Small"));
        multiDisplaysNoBattery.add(new SimpleDisplay("Projector"));

        testDevices.add(new Device("Advanced Speaker System", multiSpeakersNoBattery, multiDisplaysNoBattery, false));

        // Expected outputs for test cases 1-8
        List<List<String>> expectedOutputs = new ArrayList<>();

        // Test Case 1: Tablet with multiple speakers and displays, charging at 75%
        List<String> expectedTabletStatus = new ArrayList<>();
        expectedTabletStatus.add("Primary Speaker says: Current battery level is 75% and charging");
        expectedTabletStatus.add("Home Theater Speaker says: Current battery level is 75% and charging");
        expectedTabletStatus.add("Small Display shows: Current battery level is 75% and charging");
        expectedTabletStatus.add("TV Display shows: Current battery level is 75% and charging");
        expectedOutputs.add(expectedTabletStatus);

        // Test Case 2: Echo Dot with primary speaker only, plugged in
        List<String> expectedEchoDotStatus = new ArrayList<>();
        expectedEchoDotStatus.add("Primary Speaker says: Device is plugged in");
        expectedOutputs.add(expectedEchoDotStatus);

        // Test Case 3: Echo Dot not plugged in
        List<String> expectedEchoDotNotPluggedStatus = new ArrayList<>();
        expectedEchoDotNotPluggedStatus.add("Primary Speaker says: Device is not plugged in");
        expectedOutputs.add(expectedEchoDotNotPluggedStatus);

        // Test Case 4: Microwave with multiple displays, plugged in
        List<String> expectedMicrowaveStatus = new ArrayList<>();
        expectedMicrowaveStatus.add("Small Display shows: Device is plugged in");
        expectedMicrowaveStatus.add("External TV Display shows: Device is plugged in");
        expectedOutputs.add(expectedMicrowaveStatus);

        // Test Case 5: EchoInput with neither speaker nor display
        List<String> expectedEchoInputStatus = new ArrayList<>();
        expectedEchoInputStatus.add("No speaker or display available to report status.");
        expectedOutputs.add(expectedEchoInputStatus);

        // Test Case 6: Tablet2 with primary speaker, small display, not charging at 50%
        List<String> expectedTablet2Status = new ArrayList<>();
        expectedTablet2Status.add("Primary Speaker says: Current battery level is 50% and not charging");
        expectedTablet2Status.add("Small Display shows: Current battery level is 50% and not charging");
        expectedOutputs.add(expectedTablet2Status);

        // Test Case 7: Home Theater System with multiple speakers and displays, battery at 100%, charging
        List<String> expectedHomeTheaterStatus = new ArrayList<>();
        expectedHomeTheaterStatus.add("Primary Speaker says: Current battery level is 100% and charging");
        expectedHomeTheaterStatus.add("Surround Speaker says: Current battery level is 100% and charging");
        expectedHomeTheaterStatus.add("Main Display shows: Current battery level is 100% and charging");
        expectedHomeTheaterStatus.add("Secondary Display shows: Current battery level is 100% and charging");
        expectedOutputs.add(expectedHomeTheaterStatus);

        // Test Case 8: Advanced Speaker System with multiple speakers and displays, not plugged in
        List<String> expectedAdvancedSpeakerStatus = new ArrayList<>();
        expectedAdvancedSpeakerStatus.add("Primary Speaker says: Device is not plugged in");
        expectedAdvancedSpeakerStatus.add("Satellite Speaker says: Device is not plugged in");
        expectedAdvancedSpeakerStatus.add("Small Display shows: Device is not plugged in");
        expectedAdvancedSpeakerStatus.add("Projector Display shows: Device is not plugged in");
        expectedOutputs.add(expectedAdvancedSpeakerStatus);

        // Running Test Cases 1-8
        for (int i = 0; i < testDevices.size(); i++) {
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

        // Test Case 9: Large Number of Devices
        System.out.println("\nRunning Test Case 9: Large Number of Devices");
        boolean largeTestPassed = true;
        int largeNumber = 1000;
        for (int i = 0; i < largeNumber; i++) {
            // Alternate between battery and non-battery devices
            boolean hasBattery = i % 2 == 0;
            Battery battery = hasBattery ? new SimpleBattery(i % 100, i % 2 == 0) : null;
            boolean pluggedIn = hasBattery ? null : (i % 3 == 0);

            // Create multiple speakers and displays
            List<Speaker> speakers = new ArrayList<>();
            if (i % 5 == 0) {
                speakers.add(new SimpleSpeaker("Primary"));
                speakers.add(new SimpleSpeaker("Satellite"));
            } else {
                speakers.add(new SimpleSpeaker("Primary"));
            }

            List<Display> displays = new ArrayList<>();
            if (i % 7 == 0) {
                displays.add(new SimpleDisplay("Small"));
                displays.add(new SimpleDisplay("External TV"));
            } else {
                displays.add(new SimpleDisplay("Main"));
            }

            Device device;
            if (hasBattery) {
                device = new Device("Device_Large_" + i, speakers, displays, battery);
            } else {
                device = new Device("Device_Large_" + i, speakers, displays, pluggedIn);
            }

            List<String> status = device.reportStatus();

            // Validate status messages
            if (hasBattery) {
                String expectedStatus = "Current battery level is " + battery.getBatteryLevel() + "% and " + (battery.isCharging() ? "charging" : "not charging");
                for (Speaker speaker : speakers) {
                    String expectedMessage = speaker.getRole() + " Speaker says: " + expectedStatus;
                    if (!status.contains(expectedMessage)) {
                        largeTestPassed = false;
                        System.out.println("Test Case 9: FAIL at device " + i);
                        break;
                    }
                }
                for (Display display : displays) {
                    String expectedMessage = display.getRole() + " Display shows: " + expectedStatus;
                    if (!status.contains(expectedMessage)) {
                        largeTestPassed = false;
                        System.out.println("Test Case 9: FAIL at device " + i);
                        break;
                    }
                }
            } else {
                String expectedStatus = pluggedIn ? "Device is plugged in" : "Device is not plugged in";
                for (Speaker speaker : speakers) {
                    String expectedMessage = speaker.getRole() + " Speaker says: " + expectedStatus;
                    if (!status.contains(expectedMessage)) {
                        largeTestPassed = false;
                        System.out.println("Test Case 9: FAIL at device " + i);
                        break;
                    }
                }
                for (Display display : displays) {
                    String expectedMessage = display.getRole() + " Display shows: " + expectedStatus;
                    if (!status.contains(expectedMessage)) {
                        largeTestPassed = false;
                        System.out.println("Test Case 9: FAIL at device " + i);
                        break;
                    }
                }
            }

            if (!largeTestPassed) {
                break;
            }
        }
        if (largeTestPassed) {
            System.out.println("Test Case 9: PASS");
            testsPassed++;
        } else {
            System.out.println("Test Case 9: FAIL");
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

    // Interface for Speaker capability
    public interface Speaker {
        void speak(String message);

        String getRole(); // Optional: To identify speaker role
    }

    // Interface for Display capability
    public interface Display {
        void show(String message);

        String getRole(); // Optional: To identify display role
    }

    // Interface for Battery capability
    public interface Battery {
        int getBatteryLevel(); // Returns battery level percentage

        boolean isCharging();
    }

    // Speaker implementation with role
    public static class SimpleSpeaker implements Speaker {
        private String role;

        public SimpleSpeaker(String role) {
            this.role = role;
        }

        @Override
        public void speak(String message) {
            // Simulate speaking by printing to console
            System.out.println(role + " Speaker says: " + message);
        }

        @Override
        public String getRole() {
            return role;
        }
    }

    // Display implementation with role
    public static class SimpleDisplay implements Display {
        private String role;

        public SimpleDisplay(String role) {
            this.role = role;
        }

        @Override
        public void show(String message) {
            // Simulate displaying by printing to console
            System.out.println(role + " Display shows: " + message);
        }

        @Override
        public String getRole() {
            return role;
        }
    }

    // Battery implementation
    public static class SimpleBattery implements Battery {
        private int batteryLevel;
        private boolean charging;

        public SimpleBattery(int batteryLevel, boolean charging) {
            this.batteryLevel = batteryLevel;
            this.charging = charging;
        }

        @Override
        public int getBatteryLevel() {
            return batteryLevel;
        }

        @Override
        public boolean isCharging() {
            return charging;
        }
    }

    // PowerStatusReporter class to generate status messages
    public static class PowerStatusReporter {
        public static String generateStatus(Battery battery) {
            int level = battery.getBatteryLevel();
            boolean charging = battery.isCharging();
            String status = "Current battery level is " + level + "% and ";
            status += charging ? "charging" : "not charging";
            return status;
        }
    }

    // Device class composed of various capabilities
    public static class Device {
        private String deviceName;
        private List<Speaker> speakers;
        private List<Display> displays;
        private Battery battery;
        private Boolean pluggedIn; // For devices without battery

        // Constructor for devices with battery
        public Device(String deviceName, List<Speaker> speakers, List<Display> displays, Battery battery) {
            this.deviceName = deviceName;
            this.speakers = speakers != null ? speakers : new ArrayList<>();
            this.displays = displays != null ? displays : new ArrayList<>();
            this.battery = battery;
            this.pluggedIn = null; // Irrelevant for battery-powered devices
        }

        // Constructor for devices without battery but with pluggedIn status
        public Device(String deviceName, List<Speaker> speakers, List<Display> displays, boolean pluggedIn) {
            this.deviceName = deviceName;
            this.speakers = speakers != null ? speakers : new ArrayList<>();
            this.displays = displays != null ? displays : new ArrayList<>();
            this.battery = null; // No battery
            this.pluggedIn = pluggedIn;
        }

        // Constructor for devices without battery and without speakers/displays
        public Device(String deviceName) {
            this.deviceName = deviceName;
            this.speakers = new ArrayList<>();
            this.displays = new ArrayList<>();
            this.battery = null;
            this.pluggedIn = null;
        }

        // Method to report status based on available capabilities
        public List<String> reportStatus() {
            List<String> messages = new ArrayList<>();

            if (battery != null) {
                String status = PowerStatusReporter.generateStatus(battery);
                for (Speaker speaker : speakers) {
                    messages.add(speaker.getRole() + " Speaker says: " + status);
                    speaker.speak(status);
                }
                for (Display display : displays) {
                    messages.add(display.getRole() + " Display shows: " + status);
                    display.show(status);
                }
            } else if (pluggedIn != null) {
                String status = pluggedIn ? "Device is plugged in" : "Device is not plugged in";
                if (speakers.isEmpty() && displays.isEmpty()) {
                    messages.add("No speaker or display available to report status.");
                } else {
                    for (Speaker speaker : speakers) {
                        messages.add(speaker.getRole() + " Speaker says: " + status);
                        speaker.speak(status);
                    }
                    for (Display display : displays) {
                        messages.add(display.getRole() + " Display shows: " + status);
                        display.show(status);
                    }
                }
            } else {
                messages.add("No speaker or display available to report status.");
            }

            return messages;
        }
    }
}
