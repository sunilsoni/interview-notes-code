package com.interview.notes.code.year.y2025.jan24.amazon.test10;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

interface OutputChannel {
    void output(String message);
}

/*
FINAL:

Amazon offers a large selection of Alexa devices. Some Alexa devices only have a speaker (Echo, Echo Dot). Some only have a screen/display (Microwave). Some have both (Echo Show, Echo Spot). Others have neither (Echo Input).

Some Alexa devices have batteries (Tablets, Echo Tap, Bose Headset). Others that do not have batteries (Echo Dot, FireTV Stick).

Design a set of classes that will report the current battery/power status to the user.

Depending on the hardware, the response may need to be spoken, or displayed on a screen, or both. Also, depending on whether there is a battery or not, the status message will differ. For example, if the device is a Tablet which has a battery, a speaker, and a display, and currently it happens to be plugged in and recharging (let's say at 75%), then your code should return the following:

- Say "Current battery level is 75% and charging" through the speaker.
- Display "Current battery level is 75% and charging" on the screen.

 */
class Battery {
    private int level;
    private boolean isCharging;

    public Battery(int level, boolean isCharging) {
        this.level = level;
        this.isCharging = isCharging;
    }

    public String getStatusMessage() {
        return String.format("Current battery level is %d%% and %s", level, isCharging ? "charging" : "not charging");
    }
}

class SpeakerOutput implements OutputChannel {
    @Override
    public void output(String message) {
        System.out.println("Say \"" + message + "\" through the speaker.");
    }
}

class DisplayOutput implements OutputChannel {
    @Override
    public void output(String message) {
        System.out.println("Display \"" + message + "\" on the screen.");
    }
}

class Device {
    private Battery battery;
    private List<OutputChannel> outputs;

    public Device(Battery battery, List<OutputChannel> outputs) {
        this.battery = battery;
        this.outputs = outputs;
    }

    public void reportStatus() {
        String message = (battery != null) ? battery.getStatusMessage() : "Device is plugged in.";
        for (OutputChannel channel : outputs) {
            channel.output(message);
        }
    }
}

public class AlexaDeviceTest {
    public static void main(String[] args) {
        runTestCases();
    }

    private static void runTestCases() {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }

    private static void testCase1() {
        Battery battery = new Battery(75, true);
        Device tablet = new Device(battery, Arrays.asList(new SpeakerOutput(), new DisplayOutput()));
        String expected = "Say \"Current battery level is 75% and charging\" through the speaker.\n" +
                "Display \"Current battery level is 75% and charging\" on the screen.";
        checkTestResult(1, expected, captureOutput(tablet::reportStatus));
    }

    private static void testCase2() {
        Device echoDot = new Device(null, Arrays.asList(new SpeakerOutput()));
        String expected = "Say \"Device is plugged in.\" through the speaker.";
        checkTestResult(2, expected, captureOutput(echoDot::reportStatus));
    }

    private static void testCase3() {
        Device microwave = new Device(null, Arrays.asList(new DisplayOutput()));
        String expected = "Display \"Device is plugged in.\" on the screen.";
        checkTestResult(3, expected, captureOutput(microwave::reportStatus));
    }

    private static void testCase4() {
        Device echoInput = new Device(null, List.of());
        String expected = "";
        checkTestResult(4, expected, captureOutput(echoInput::reportStatus));
    }

    private static void testCase5() {
        Battery battery = new Battery(50, false);
        Device device = new Device(battery, Arrays.asList(new SpeakerOutput(), new DisplayOutput()));
        String expected = "Say \"Current battery level is 50% and not charging\" through the speaker.\n" +
                "Display \"Current battery level is 50% and not charging\" on the screen.";
        checkTestResult(5, expected, captureOutput(device::reportStatus));
    }

    private static String captureOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            runnable.run();
        } finally {
            System.setOut(originalOut);
        }
        return outputStream.toString().replaceAll("\r\n", "\n").trim();
    }

    private static void checkTestResult(int testCase, String expected, String actual) {
        actual = actual.replaceAll("\r\n", "\n");
        expected = expected.replaceAll("\r\n", "\n");
        if (actual.equals(expected)) {
            System.out.println("Test Case " + testCase + ": PASS");
        } else {
            System.out.println("Test Case " + testCase + ": FAIL");
            System.out.println("Expected:\n" + expected);
            System.out.println("Actual:\n" + actual);
        }
    }
}