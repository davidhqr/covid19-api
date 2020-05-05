package com.example.covid19api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Helper {

    public static String dateNow() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York")).minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return zonedDateTime.format(formatter);
    }

    public static String timeSeriesDate(String inputDate) {
        DateFormat originalFormat = new SimpleDateFormat("MM/dd/yy");
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = originalFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetFormat.format(date);
    }

    public static <K, V> TreeMap<K, V> getTreeMap(Map<K, V> hashMap) {
        return hashMap.entrySet()
                      .stream()
                      .collect(Collectors.toMap(Map.Entry::getKey,
                                                Map.Entry::getValue,
                                                (oldValue, newValue) -> newValue,
                                                TreeMap::new));
    }
}
