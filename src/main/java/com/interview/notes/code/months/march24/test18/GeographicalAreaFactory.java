package com.interview.notes.code.months.march24.test18;

public class GeographicalAreaFactory {
    public static GeographicalArea createZipcodeRangeArea(int start, int end) {
        return new ZipcodeRange(start, end);
    }
    
    public static GeographicalArea createCountryArea(String country) {
        return new CountryArea(country);
    }
    
    // Additional factory methods for states, cities, etc.
}