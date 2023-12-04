package com.interview.notes.code.months.nov23.test7;

import java.util.List;

interface IVehicleTrackingService {
    void registerVehicle(int vehicleNumber);
    Vehicle getVehicleInfo(int vehicleNumber);
    boolean polledVehicleInfo(int vehicleNumber, long distanceTraveledInKm, long epochTime);
    List<Long> fineHistory(int vehicleNumber, int K);
}
