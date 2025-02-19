package com.fanap.corepos.iso.utils;

import java.io.UnsupportedEncodingException;

public class Encoding {

    public static String convertToWindows1256(byte[] originalBytes) throws UnsupportedEncodingException {
        return new String(originalBytes, "windows-1256");
    }
}
