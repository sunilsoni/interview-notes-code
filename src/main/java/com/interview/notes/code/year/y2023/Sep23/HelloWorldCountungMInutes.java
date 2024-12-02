package com.interview.notes.code.year.y2023.Sep23;

public class HelloWorldCountungMInutes {

    public static int CountingMinutesI(String str) {
        String[] times = str.split("-");
        int start = convertToMinutes(times[0]);
        int end = convertToMinutes(times[1]);

        if (end < start) {
            end += 1440; // add 24 hours in minutes
        }

        return end - start;
    }

    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1].substring(0, 2));
        boolean isPm = parts[1].substring(2).equalsIgnoreCase("pm");

        // Convert to 24-hour format
        if (hours == 12) {
            hours = 0;
        }
        if (isPm) {
            hours += 12;
        }

        return hours * 60 + minutes;
    }

    public static void main(String[] args) {
        System.out.println(CountingMinutesI("10:30am-11:30am")); // 60
        System.out.println(CountingMinutesI("12:30pm-12:00am")); // 690
        System.out.println(CountingMinutesI("1:23am-1:08am")); // 1425
        System.out.println(CountingMinutesI("1:00pm-11:00am")); // 1320
    }
}
