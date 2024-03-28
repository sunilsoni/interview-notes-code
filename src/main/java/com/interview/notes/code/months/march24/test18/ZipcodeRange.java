package com.interview.notes.code.months.march24.test18;

// Assuming you have the ZipcodeRange class defined as before
public class ZipcodeRange implements GeographicalArea {
    private final int startZipcode;
    private final int endZipcode;

    public ZipcodeRange(int startZipcode, int endZipcode) {
        this.startZipcode = startZipcode;
        this.endZipcode = endZipcode;
    }

    @Override
    public boolean containsAddress(Address address) {
        int addressZip = address.getZipcode();
        return addressZip >= startZipcode && addressZip <= endZipcode;
    }

    // Getters for startZipcode and endZipcode could be added here.
}