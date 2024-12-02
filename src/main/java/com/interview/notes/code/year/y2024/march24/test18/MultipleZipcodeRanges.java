package com.interview.notes.code.year.y2024.march24.test18;

import java.util.ArrayList;
import java.util.List;

public class MultipleZipcodeRanges implements GeographicalArea {
    private final List<ZipcodeRange> zipcodeRanges;

    public MultipleZipcodeRanges() {
        this.zipcodeRanges = new ArrayList<>();
    }

    public void addZipcodeRange(ZipcodeRange range) {
        zipcodeRanges.add(range);
    }

    @Override
    public boolean containsAddress(Address address) {
        for (ZipcodeRange range : zipcodeRanges) {
            if (range.containsAddress(address)) {
                return true;
            }
        }
        return false;
    }
}