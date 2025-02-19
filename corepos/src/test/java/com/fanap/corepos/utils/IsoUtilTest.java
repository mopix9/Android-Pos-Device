package com.fanap.corepos.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class IsoUtilTest {

    @Test
    public void binary_to_hex() {

        String hex = IsoUtil.binaryToHex("1111000111001010");
        assertEquals(hex, "f1");
    }

    @Test
    public void string_to_hex() {

        String hex = IsoUtil.stringToHexAscii("10P190000009");
        assertEquals(hex, "313050313930303030303039");
    }
    @Test
    public void getMacKey() {

        byte[] macKey = IsoUtil.getDefaultMacKey();
        assertNull(macKey);
    }

    @Test
    public void hexToAscii() {

        String data = "3038";
        String result = IsoUtil.hexToAscii(data);
        assertEquals(result, "08");
    }


}