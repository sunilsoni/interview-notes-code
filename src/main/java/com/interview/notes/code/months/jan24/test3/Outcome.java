package com.interview.notes.code.months.jan24.test3;


import java.util.*;
import java.text.*;

public class Outcome {

    public static String solve(String S, List<String> meeting) {
        String[] currentDate = S.split("/");
        int currentMonth = Integer.parseInt(currentDate[0]);
        int currentDay = Integer.parseInt(currentDate[1]);
        int minMonth = 13;
        int minDay = 32;
        for (String meet : meeting) {
            String[] meetDate = meet.split("_")[0].split("/");
            int meetMonth = Integer.parseInt(meetDate[0]);
            int meetDay = Integer.parseInt(meetDate[1]);
            if (meetMonth > currentMonth || (meetMonth == currentMonth && meetDay >= currentDay)) {
                if (meetMonth < minMonth || (meetMonth == minMonth && meetDay < minDay)) {
                    minMonth = meetMonth;
                    minDay = meetDay;
                }
            }
        }
        return String.format("%02d/%02d", minMonth, minDay);
    }

    public static String solve2(String currentDate, List<String> meetings) {
        String[] currentDateParts = currentDate.split("/");
        int currentMonth = Integer.parseInt(currentDateParts[0]);
        int currentDay = Integer.parseInt(currentDateParts[1]);

        String earliestMeetingDate = "";
        boolean foundEarliest = false;

        for (String meeting : meetings) {
            String[] meetingParts = meeting.split("_");
            String meetingDate = meetingParts[0];
            String[] meetingDateParts = meetingDate.split("/");
            int meetingMonth = Integer.parseInt(meetingDateParts[0]);
            int meetingDay = Integer.parseInt(meetingDateParts[1]);

            // Check if meeting is later than the current date
            if (meetingMonth > currentMonth || (meetingMonth == currentMonth && meetingDay > currentDay)) {
                if (!foundEarliest || compareDates(meetingDate, earliestMeetingDate) < 0) {
                    earliestMeetingDate = meetingDate;
                    foundEarliest = true;
                }
            }
        }

        return earliestMeetingDate;
    }

    // Helper function to compare two dates in "MM/DD" format
    private static int compareDates(String date1, String date2) {
        String[] date1Parts = date1.split("/");
        String[] date2Parts = date2.split("/");

        int month1 = Integer.parseInt(date1Parts[0]);
        int day1 = Integer.parseInt(date1Parts[1]);
        int month2 = Integer.parseInt(date2Parts[0]);
        int day2 = Integer.parseInt(date2Parts[1]);

        if (month1 != month2) {
            return month1 - month2;
        } else {
            return day1 - day2;
        }
    }

    public static String solve1(String S, List<String> meeting) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        Date currentDate = null;
        try {
            currentDate = sdf.parse(S);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Date firstMeetingDate = null;
        boolean firstMeetingFound = false;
        
        for (String meet : meeting) {
            String[] parts = meet.split("_");
            String meetDateStr = parts[0];
            String meetName = parts[1];
            
            try {
                Date meetDate = sdf.parse(meetDateStr);
                if (meetDate.compareTo(currentDate) > 0) {
                    if (!firstMeetingFound || meetDate.before(firstMeetingDate)) {
                        firstMeetingDate = meetDate;
                        firstMeetingFound = true;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        if (firstMeetingFound) {
            return sdf.format(firstMeetingDate);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
//        String S = "01/25";
//        List<String> meeting = new ArrayList<>();
//        meeting.add("02/02_John");
//        meeting.add("02/03_Jack");
        String S = "04/16";
        List<String> meeting = new ArrayList<>();
       // meeting.add("1");
        meeting.add("09/04_Ann");
        String result1 = solve1(S, meeting);
        String result2 = solve2(S, meeting);
        String result = solve(S, meeting);
        System.out.println(result1); // Output: 02/02
        System.out.println(result2); // Output: 02/02
        System.out.println(result); // Output: 02/02
    }
}
