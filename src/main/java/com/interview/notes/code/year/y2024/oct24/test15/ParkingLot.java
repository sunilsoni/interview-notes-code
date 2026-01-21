package com.interview.notes.code.year.y2024.oct24.test15;

class ParkingLot {
    private final int levels;
    private final int motorcycleSpots;
    private final int carSpots;
    private final int largeSpots;
    private final int[][] spots;
    private int currentLevel;

    public ParkingLot(int levels, int motorcycleSpots, int carSpots, int largeSpots) {
        this.levels = levels;
        this.motorcycleSpots = motorcycleSpots;
        this.carSpots = carSpots;
        this.largeSpots = largeSpots;
        this.currentLevel = 0;
        this.spots = new int[levels][3]; // 0: motorcycle, 1: car, 2: large
    }

    public int getTotalSpots() {
        return levels * (motorcycleSpots + carSpots + largeSpots);
    }

    public int getRemainingSpots() {
        int total = 0;
        for (int i = 0; i < levels; i++) {
            total += motorcycleSpots - spots[i][0];
            total += carSpots - spots[i][1];
            total += largeSpots - spots[i][2];
        }
        return total;
    }

    public boolean parkVehicle(VehicleType type) {
        for (int i = currentLevel; i < levels; i++) {
            if (parkOnLevel(i, type)) {
                if (i > currentLevel) {
                    currentLevel = i;
                }
                return true;
            }
        }
        return false;
    }

    private boolean parkOnLevel(int level, VehicleType type) {
        switch (type) {
            case MOTORCYCLE:
                if (spots[level][0] < motorcycleSpots) {
                    spots[level][0]++;
                    return true;
                }
                break;
            case CAR:
                if (spots[level][1] < carSpots) {
                    spots[level][1]++;
                    return true;
                } else if (spots[level][2] < largeSpots) {
                    spots[level][2]++;
                    return true;
                }
                break;
            case VAN:
                if (spots[level][2] < largeSpots) {
                    spots[level][2]++;
                    return true;
                } else if (carSpots - spots[level][1] >= 3) {
                    spots[level][1] += 3;
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean isParkingLotFull() {
        return getRemainingSpots() == 0;
    }

    public boolean isParkingLotEmpty() {
        return getRemainingSpots() == getTotalSpots();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
