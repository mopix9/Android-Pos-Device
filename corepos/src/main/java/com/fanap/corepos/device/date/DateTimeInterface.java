package com.fanap.corepos.device.date;

public interface DateTimeInterface {
    /*
     * param beepType : Utils.BEEP_TYPE_SUCCESS
     */
    void setDateTime(int stamp);

    void setDateTime(String stamp);
}
