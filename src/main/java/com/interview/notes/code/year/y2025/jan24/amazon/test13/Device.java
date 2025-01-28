package com.interview.notes.code.year.y2025.jan24.amazon.test13;

// Device.java
public abstract class Device {
    protected String deviceName;

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract void reportStatus();
}
