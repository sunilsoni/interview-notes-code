package com.interview.notes.code.months.nov23.test7;

import java.util.*;

class VehicleTrackingService implements IVehicleTrackingService {
    private Map<Integer, Vehicle> vehicles;
    private final long speedLimit; // in km/h

    public VehicleTrackingService(long M) {
        this.speedLimit = M;
        this.vehicles = new HashMap<>();
    }

    @Override
    public void registerVehicle(int vehicleNumber) {
        vehicles.put(vehicleNumber, new Vehicle(vehicleNumber));
    }

    @Override
    public Vehicle getVehicleInfo(int vehicleNumber) {
        return vehicles.get(vehicleNumber);
    }

    @Override
    public boolean polledVehicleInfo(int vehicleNumber, long distanceTraveledInKm, long epochTime) {
        Vehicle vehicle = vehicles.get(vehicleNumber);
        if (vehicle == null) return false;

        long timeDiff = epochTime - vehicle.lastPolledInfoEpochTime;
        double hours = timeDiff / 3600.0;
        double speed = distanceTraveledInKm / hours;

        vehicle.lastPolledInfoEpochTime = epochTime;

        if (speed > this.speedLimit) {
            vehicle.numberOfTimesFinedImposed++;
            vehicle.finesEpochTimes.add(epochTime);
            return true;
        }

        return false;
    }

    @Override
    public List<Long> fineHistory(int vehicleNumber, int K) {
        Vehicle vehicle = vehicles.get(vehicleNumber);
        if (vehicle == null) return new ArrayList<>();

        // Return the latest K fines, need to sort the list in descending order first
        List<Long> sortedFines = new ArrayList<>(vehicle.finesEpochTimes);
        sortedFines.sort(Collections.reverseOrder());
        return sortedFines.subList(0, Math.min(K, sortedFines.size()));
    }
}
