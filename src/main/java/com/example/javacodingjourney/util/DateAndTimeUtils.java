package com.example.javacodingjourney.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeUtils {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println("localDate="+localDate);
        System.out.println("localTime="+localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime="+localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
        String isoDateTime = LocalDateTime.now().format(formatter);
        System.out.println("isoDateTime="+isoDateTime);

        ZonedDateTime currentZone = ZonedDateTime.now();
        ZonedDateTime utcZone = currentZone.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime estZone = currentZone.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("utcZone="+utcZone);
        System.out.println("estZone="+estZone);

        DateTimeFormatter formatter1 = DateTimeFormatter.ISO_DATE_TIME;
        String isoDateTime1 = LocalDateTime.now().format(formatter);
        System.out.println("isoDateTime1="+isoDateTime1);

    }
}
