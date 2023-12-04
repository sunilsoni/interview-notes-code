package com.interview.notes.code.months.nov23.test7;

import java.util.ArrayList;
import java.util.List;

class Vehicle {
    int vehicleNumber;
    long lastPolledInfoEpochTime;
    int numberOfTimesFinedImposed;
    List<Long> finesEpochTimes;

    public Vehicle(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.lastPolledInfoEpochTime = 0;
        this.numberOfTimesFinedImposed = 0;
        this.finesEpochTimes = new ArrayList<>();
    }
    
    // Additional methods and logic as needed...
}
