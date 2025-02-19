package com.fanap.corepos.device.date.smartpeak;

import android.app.AlarmManager;
import android.content.Context;
import android.util.Log;

import com.fanap.corepos.device.date.DateTimeInterface;
import com.fanap.corepos.device.date.DateTimeUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class SmartPeakDateTime implements DateTimeInterface {

    Context context;

    public SmartPeakDateTime(Context context) {
        this.context = context;
    }

    @Override
    public void setDateTime(int stamp) {
        try {
            List<Integer> time = DateTimeUtils.getFullTime(stamp);
            Calendar c = Calendar.getInstance();
            c.set(time.get(0), time.get(1)-1, time.get(2), time.get(3), time.get(4), time.get(5));
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.setTime(c.getTimeInMillis());
        } catch (Exception e) {
            Log.d("TIME", Objects.requireNonNull(e.getLocalizedMessage()));
        }
    }

    @Override
    public void setDateTime(String stamp) {

    }
}
