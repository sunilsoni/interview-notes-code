package com.interview.notes.code.year.y2024.april24.test13;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FastCar {
    private int speed;

    public FastCar(int speed) {
        this.speed = speed;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("OUTPUT_FILE_PATH")));

        int arCount = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<FastCar> carList = new ArrayList<>();
        for (int i = 0; i < arCount; i++) {
            carList.add(new FastCar(Integer.parseInt(arTempItems[i])));
        }
        FastCar fastestCar = findFastestCar(carList);

        int outcome = fastestCar.getSpeed();
        bufferedWriter.write(outcome + "\n");

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static FastCar findFastestCar(List<FastCar> carList) {
        return Collections.max(carList, Comparator.comparing(FastCar::getSpeed));
    }

    public int getSpeed() {
        return speed;
    }
}
