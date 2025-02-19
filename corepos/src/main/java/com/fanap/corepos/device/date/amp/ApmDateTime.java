package com.fanap.corepos.device.date.amp;

import android.util.Log;

import com.fanap.corepos.device.date.DateTimeInterface;
import com.fanap.corepos.device.date.DateTimeUtils;
import com.pos.device.SDKException;

import java.util.List;

public class ApmDateTime implements DateTimeInterface {

    public ApmDateTime(){

    }

    @Override
    public void setDateTime(int stamp) {
        List<Integer> time = DateTimeUtils.getFullTime(stamp);
        setTerminalDateTime(time.get(0), time.get(1), time.get(2), time.get(3), time.get(4), time.get(5));
        Log.d("SetupFragment", time.get(0)+time.get(1)+time.get(2)+time.get(3)+time.get(4)+time.get(5) +"");

    }

    @Override
    public void setDateTime(String stamp) {

    }

    public static void setTerminalDateTime(int year, int month, int day,int hour, int minute, int second) {

        // format date and time as yyyyMMddHHmmss

        String yyyy = String.valueOf(year);

        // Format month
        // We don't plus one here because we assume that the caller already fixed the 0=January.
        // Unlike in setTerminalDate(), we need to fix it from inside the function
        String MM = String.valueOf(month);
        if(MM.length() == 1) {
            MM = "0" + MM;
        }

        String dd = String.valueOf(day);
        if(dd.length() == 1) {
            dd = "0" + dd;
        }

        String HH = String.valueOf(hour);
        if(HH.length() == 1) {
            HH = "0" + HH;
        }

        String mm = String.valueOf(minute);
        if(mm.length() == 1) {
            mm = "0" + mm;
        }


        String ss = String.valueOf(second);
        if(ss.length() == 1) {
            ss = "0" + ss;
        }

        try {
            com.pos.device.rtc.RealTimeClock.set(yyyy+MM+dd+HH+mm+ss);
        } catch (SDKException e) {
            e.printStackTrace();
        }

    }
}
