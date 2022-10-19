package com.interview.notes.code.amazon;

import java.util.ArrayList;
import java.util.List;

public class NumberofAirplanesSkyTest {
    public static void main(String[] args) {
        List<Interval> airplanes = new ArrayList<>();

        //[[1,10],[2,3],[5,8],[4,7]], return 3
        airplanes.add(new Interval(1, 10));
        airplanes.add(new Interval(2, 3));
        airplanes.add(new Interval(5, 8));
        airplanes.add(new Interval(4, 7));

        NumberofAirplanesSky sky = new NumberofAirplanesSky();
        System.out.println(sky.countOfAirplanes(airplanes));
    }
}
