package com.interview.notes.code.year.y2025.jan.amazon.test13;

// Device.java
public abstract class Device {
    protected String deviceName;

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }

    public abstract void reportStatus();
}
