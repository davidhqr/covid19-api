package com.example.covid19api.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    public static String date() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York")).minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return zonedDateTime.format(formatter);
    }

}
