package com.fanap.corepos.device.hsm.sayan.amp.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author：Paddy
 * Email：pjpsmile@126.com
 * 2016/3/22 15:51
 */
public class StringUtil2 {

    public static final String[] hexStrings;

    static {
        hexStrings = new String[256];
        for (int i = 0; i < 256; i++) {
            StringBuilder d = new StringBuilder(2);
            char ch = Character.forDigit((byte) i >> 4 & 0x0F, 16);
            d.append(Character.toUpperCase(ch));
            ch = Character.forDigit((byte) i & 0x0F, 16);
            d.append(Character.toUpperCase(ch));
            hexStrings[i] = d.toString();
        }

    }

    /**
     * 字节数组转为16进制的字符串
     *
     * @param b      - 字节数组
     * @param offset - 起始位
     * @param len    长度
     * @return 16进制的字符串
     */
    public static String hexString(byte[] b, int offset, int len) {
        String ret = "";
        try {
            if (b == null) {
                return "";
            }
            StringBuilder d = new StringBuilder(len * 2);
            len += offset;
            for (int i = offset; i < len; i++) {
                d.append(hexStrings[(int) b[i] & 0xFF]);
            }
            ret = d.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 字节数组转为16进制的字符串
     *
     * @param b
     * @return
     */
    public static String hexString(byte[] b) {
        if (b == null) {
            return "";
        }
        return hexString(b, 0, b.length);
    }


    /**
     * 16进制字符串转字节数组
     *
     * @param hexStr 16进制字符串
     * @return 转换后的字节数组
     */
    public static byte[] hex2ByteArray(String hexStr) {
        int len = hexStr.length();
        if ((len & 0x01) == 0x01) {
            throw new IllegalArgumentException("hexStr is invalid: " + hexStr);
        }

        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) (Short.valueOf(hexStr.substring(i, i + 2).toString(), 16) & 0xff);
        }
        return data;
    }

    /**
     * @param b      source byte array
     * @param offset starting offset
     * @param len    number of bytes in destination (processes len*2)
     * @return byte[len]
     */
    public static byte[] hex2byte(byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }
        return d;
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param s 原始16进制字符串
     * @return 转换后的字节数组
     */
    public static byte[] hex2byte(String s) {
        if (s.length() % 2 == 0) {
            return hex2byte(s.getBytes(), 0, s.length() >> 1);
        } else {
            // Padding left zero to make it even size #Bug raised by tommy
            return hex2byte("0" + s);
        }
    }

    /**
     * 判断传入的是否是金额
     *
     * @param amount 金额
     * @return
     */
    public static boolean isAmout(String amount) {
        String reg = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(amount);
        return m.matches();
    }


    public static void main(String[] args) {

        String hex = "9A0C425C5A2F2763B7575E9685F5E02C0CDE9CA3B47956735A52D48D21CF0C6E";
        byte[] hexBytes = hex2byte(hex);
        System.out.println(hexString(hexBytes));
    }
}
