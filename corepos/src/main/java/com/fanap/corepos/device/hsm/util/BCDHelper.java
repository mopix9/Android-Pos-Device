package com.fanap.corepos.device.hsm.util;

import android.annotation.SuppressLint;
import android.text.format.Time;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class BCDHelper {

	public static int bytes2Int(byte[] b, int start, int len) {
		int sum = 0;
		int end = start + len;
		for (int i = start; i < end; i++) {
			int n = b[i] & 0xff;
			n <<= (--len) * 8;
			sum += n;
		}
		return sum;
	}

	//
	public static byte[] int2Bytes(int value, int len) {
		byte[] b = new byte[len];
		Arrays.fill(b, (byte) 0);
		for (int i = 0; i < len; i++) {
			// b[len - i - 1] = (byte)((value >> 8 * i) & 0xff);
			b[len - i - 1] = (byte) ((value >> 8 * i));
		}
		return b;
	}

	public static byte[] StrToBCD(String str) {

		return StrToBCD(str, str.length());

	}

	public static byte[] StrToBCD(String str, int numlen) {

		if (numlen % 2 != 0)

			numlen++;

		while (str.length() < numlen) {

			str = "0" + str;

		}

		byte[] bStr = new byte[numlen / 2];

		char[] cs = str.toCharArray();

		int i = 0;

		int iNum = 0;

		for (i = 0; i < numlen; i += 2) {

			int iTemp = 0;

			if (cs[i] >= '0' && cs[i] <= '9') {

				iTemp = (cs[i] - '0') << 4;

			} else {

				// 判断是否为a~f

				if (cs[i] >= 'a' && cs[i] <= 'f') {

					cs[i] -= 32;

				}

				iTemp = (cs[i] - '0' - 7) << 4;

			}

			// 处理低位

			if (cs[i + 1] >= '0' && cs[i + 1] <= '9') {

				iTemp += cs[i + 1] - '0';

			} else {

				// 判断是否为a~f

				if (cs[i + 1] >= 'a' && cs[i + 1] <= 'f') {

					cs[i + 1] -= 32;

				}

				iTemp += cs[i + 1] - '0' - 7;

			}

			bStr[iNum] = (byte) iTemp;

			iNum++;

		}

		return bStr;

	}

	public static int bcdToInt(byte[] bcdNum, int offset, int numlen) {

		return Integer.parseInt(bcdToString(bcdNum, offset, numlen));

	}

	@SuppressLint("DefaultLocale")
	public static String bcdToString(byte[] bcdNum, int offset, int numlen) {
		if (numlen <= 0 || offset < 0 || bcdNum == null)
			return null;
		int len = numlen;
		// if (numlen % 2 != 0) //zhanghw,2015-09-01,to del
		// len++;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(Integer.toHexString((bcdNum[i + offset] & 0xf0) >> 4));
			sb.append(Integer.toHexString(bcdNum[i + offset] & 0xf));
		}
		return sb.toString().toUpperCase();
	}

	public static Time Bcd3ToDateTime(byte[] data, int offset) {

		int year = 0, month = 0, monthDay = 0;

		year = Integer.parseInt("20" + bcdToString(data, offset, 2));

		month = bcdToInt(data, offset + 1, 2);

		monthDay = bcdToInt(data, offset + 2, 2);

		Time time = new Time("GMT+8");

		time.set(monthDay, month, year);

		return time;

	}

	public static Time Bcd5ToDateTime(byte[] data, int offset) {

		int year = 0, month = 0, monthDay = 0, hour = 0, minute = 0, second = 0;

		year = Integer.parseInt("20" + bcdToString(data, offset, 2));

		month = bcdToInt(data, offset + 1, 2);

		monthDay = bcdToInt(data, offset + 2, 2);

		hour = bcdToInt(data, offset + 3, 2);

		minute = bcdToInt(data, offset + 4, 2);

		Time time = new Time("GMT+8");

		time.set(second, minute, hour, monthDay, month, year);

		return time;

	}

	public static Time Bcd6ToDateTime(byte[] data, int offset) {

		int year = 0, month = 0, monthDay = 0, hour = 0, minute = 0, second = 0;

		year = Integer.parseInt("20" + bcdToString(data, offset, 2));

		month = bcdToInt(data, offset + 1, 2);

		monthDay = bcdToInt(data, offset + 2, 2);

		hour = bcdToInt(data, offset + 3, 2);

		minute = bcdToInt(data, offset + 4, 2);

		second = bcdToInt(data, offset + 5, 2);

		Time time = new Time("GMT+8");

		time.set(second, minute, hour, monthDay, month, year);

		return time;

	}

	public static Time Bcd7ToDateTime(byte[] data, int offset) {

		int year = 0, month = 0, monthDay = 0, hour = 0, minute = 0, second = 0;

		year = bcdToInt(data, offset, 4);

		month = bcdToInt(data, offset + 2, 2);

		monthDay = bcdToInt(data, offset + 3, 2);

		hour = bcdToInt(data, offset + 4, 2);

		minute = bcdToInt(data, offset + 5, 2);

		second = bcdToInt(data, offset + 6, 2);

		Time time = new Time("GMT+8");

		time.set(second, minute, hour, monthDay, month, year);

		return time;

	}

	public static byte[] DateTimeToBcd3(Time dt)

			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();

		sb.append(FStrLen(String.valueOf((dt.year - 2000)), 2));

		sb.append(FStrLen(String.valueOf((dt.month)), 2));

		sb.append(FStrLen(String.valueOf((dt.monthDay)), 2));

		return StrToBCD(sb.toString());

	}

	public static byte[] DateTimeToBcd5(Time dt)

			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();

		sb.append(FStrLen(String.valueOf((dt.year - 2000)), 2));

		sb.append(FStrLen(String.valueOf(dt.month), 2));

		sb.append(FStrLen(String.valueOf(dt.monthDay), 2));

		sb.append(FStrLen(String.valueOf(dt.hour), 2));

		sb.append(FStrLen(String.valueOf(dt.minute), 2));

		return StrToBCD(sb.toString());

	}

	public static byte[] DateTimeToBcd6(Time dt)

			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();

		sb.append(FStrLen(String.valueOf((dt.year - 2000)), 2));

		sb.append(FStrLen(String.valueOf(dt.month), 2));

		sb.append(FStrLen(String.valueOf(dt.monthDay), 2));

		sb.append(FStrLen(String.valueOf(dt.hour), 2));

		sb.append(FStrLen(String.valueOf(dt.minute), 2));

		sb.append(FStrLen(String.valueOf(dt.second), 2));

		return StrToBCD(sb.toString());

	}

	public static byte[] DateTimeToBcd7(Time dt)

			throws UnsupportedEncodingException {

		StringBuilder sb = new StringBuilder();

		sb.append(FStrLen(String.valueOf(dt.year), 4));

		sb.append(FStrLen(String.valueOf(dt.month), 2));

		sb.append(FStrLen(String.valueOf(dt.monthDay), 2));

		sb.append(FStrLen(String.valueOf(dt.hour), 2));

		sb.append(FStrLen(String.valueOf(dt.minute), 2));

		sb.append(FStrLen(String.valueOf(dt.second), 2));

		return StrToBCD(sb.toString());

	}

	public static String FStrLen(String str, int len) {

		if (str.length() < len) {

			str = "0" + str;

		}

		return str;

	}

	public static Time GetNowTime() {

		Time t = GetNewTime();

		t.setToNow();

		return t;

	}

	public static Time GetNewTime() {

		return new Time("GMT+8");

	}

	public static long TimeDiff(Time tm1, Time tm2) {

		return (tm1.toMillis(true) - tm2.toMillis(true));

	}

	public static byte[] intToBytes2(int value) {
		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 用于调试 16进制数组转化成调试用字符串(大写字母)，比如[0x03][0x3f]转化成"03 3F"
	 *
	 * @param b
	 * @return
	 */
	public static String hex2DebugHexString(byte[] b, int len) {
		int[] x = new int[len];
		String[] y = new String[len];
		StringBuilder str = new StringBuilder();
		// 转换成Int数组,然后转换成String数组
		int j = 0;
		for (; j < len; j++) {
			x[j] = b[j] & 0xff;
			y[j] = Integer.toHexString(x[j]);
			while (y[j].length() < 2) {
				y[j] = "0" + y[j];
			}
			str.append(y[j]);
			str.append(" ");
		}
		return new String(str).toUpperCase();
		// toUpperCase()方法 转化成大写
	}

	public static String toHexString(byte[] ba) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < ba.length; i++)
			str.append(String.format("%x", ba[i]));
		return str.toString();
	}

	public static String fromHexString(String hex) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < hex.length(); i+=2) {
			str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
		}
		return str.toString();
	}
}