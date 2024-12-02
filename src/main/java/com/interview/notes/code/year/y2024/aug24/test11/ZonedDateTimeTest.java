package com.interview.notes.code.year.y2024.aug24.test11;

import java.time.*;

public class ZonedDateTimeTest {
    public static void main(String[] args) {
        ZoneId ny = ZoneId.of("America/New_York");
        ZoneId la = ZoneId.of("America/Los_Angeles");

        LocalDateTime localDT = LocalDateTime.of(2021, Month.MARCH, 14, 8, 13, 55);
        ZonedDateTime nyDT = ZonedDateTime.of(localDT, ny);
        ZonedDateTime laDT = ZonedDateTime.of(localDT, la);

        System.out.println(nyDT);
        System.out.println(laDT);

        if (nyDT.equals(laDT)) {
            System.out.println("EQUAL");
        }

        Duration d = Duration.between(nyDT, laDT);
        System.out.println("Duration=" + d);
    }

}

/*
2021-03-14T08:13:55-04:00[America/New_York]
2021-03-14T08:13:55-07:00[America/Los_Angeles]
Duration=PT3H
 */