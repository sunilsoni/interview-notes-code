package com.interview.notes.code.amazon;

import java.util.Arrays;
import java.util.List;

//https://github.com/awangdev/leet-code/blob/master/Java/Number%20of%20Airplane%20in%20the%20sky.java
//https://www.cnblogs.com/lz87/p/7181301.html
//[LintCode] Number of Airplanes in the Sky
public class NumberofAirplanesSky {
    /**
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {


        if (airplanes == null) {
            return 0;
        }
        if (airplanes.size() <= 1) {
            return airplanes.size();
        }
        int n = airplanes.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = airplanes.get(i).start;
            ends[i] = airplanes.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int startIdx = 0, endIdx = 0, curr = 0, max = 0;
        while (startIdx < n) {
            if (starts[startIdx] < ends[endIdx]) {
                curr++;
                startIdx++;
            } else {
                curr--;
                endIdx++;
            }
            max = Math.max(max, curr);
        }
        return max;
    }
}

