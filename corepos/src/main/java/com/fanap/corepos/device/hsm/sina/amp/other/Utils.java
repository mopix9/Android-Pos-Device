package com.fanap.corepos.device.hsm.sina.amp.other;

import java.text.DecimalFormat;

public class Utils {
    public static String padleft(String s, int len, char c) {
        s = s.trim();
        if (s.length() > len)
            return null;
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        while (fill-- > 0)
            d.append(c);
        d.append(s);
        return d.toString();
    }

    public static String TwoWei(double s){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(s);
    }

    /**
     * 金额存的时候*100  所有取的时候要/100   格式转换
     * @param amount
     * @return  0.00格式
     */
    public static String TwoWei(String amount){
        DecimalFormat df = new DecimalFormat("0.00");
        double d = 0;
        if(!isNullWithTrim(amount))
            d = Double.parseDouble(amount)/100;
        return df.format(d);
    }

    public static boolean isNullWithTrim(String str) {
        return str == null || str.trim().equals("")||str.trim().equals("null");
    }
}
