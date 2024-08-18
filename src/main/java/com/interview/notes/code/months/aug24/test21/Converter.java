package com.interview.notes.code.months.aug24.test21;

class Converter {
    public long convert(long value, ConvertType type) throws Exception {
        if (value < 0) {
            throw new Exception("Invalid inputs.");
        }

        double result;
        switch (type) {
            case DTH_TO_GJ:
                result = value * 1.055056;
                break;
            case GJ_TO_DTH:
                result = value / 1.055056;
                break;
            default:
                throw new Exception("Invalid inputs.");
        }

        return Math.round(result);
    }
}
