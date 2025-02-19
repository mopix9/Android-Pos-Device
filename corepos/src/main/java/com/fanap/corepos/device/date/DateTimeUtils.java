package com.fanap.corepos.device.date;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import saman.zamani.persiandate.PersianDate;

public class DateTimeUtils {
    public static List<Integer> getFullTime(int stamp) {
        try {
            List<Integer> time = new ArrayList<>();
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            PersianDate pdate = new PersianDate((long) (stamp + 1388534400) * 1000);

            time.add(pdate.getGrgYear());
            time.add(pdate.getGrgMonth());
            time.add(pdate.getGrgDay());
            time.add(pdate.getHour());
            time.add(pdate.getMinute());
            time.add(pdate.getSecond());
            return time;

        } finally {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tehran"));
        }

    }
}
