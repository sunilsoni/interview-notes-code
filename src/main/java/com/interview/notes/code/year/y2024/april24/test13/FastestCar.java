package com.interview.notes.code.year.y2024.april24.test13;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FastestCar {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("OUTPUT_FILE_PATH")));

        int arCount = Integer.parseInt(bufferedReader.readLine().trim());
        String[] arTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < arCount; i++) {
            carList.add(new Car(Integer.parseInt(arTempItems[i])));
        }

        Car fastestCar = findFastestCar(carList);
        int outcome = fastestCar.getSpeed();
        bufferedWriter.write(outcome + "\n");

        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static Car findFastestCar(List<Car> carList) {
        return Collections.max(carList, Comparator.comparing(Car::getSpeed));
    }
}