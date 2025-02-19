package com.fanap.corepos.utils;

import com.fanap.corepos.device.DeviceSDKManager;
import com.fanap.corepos.iso.utils.SimpleDES;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.macs.ISO9797Alg3Mac;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IsoUtil {

    public static String SERIAL = "001234567890";
    //public static String SERIAL = "1398101114";
    public static String MASTERKEY = "BA05AC442A54770F";
    public static String MACKEY = "E77563AAED25539D";
    public static String PINKEY = "9FF7A1226006C381";
    public static final String[] hexStrings = new String[256];

    public static String getStandardTrack2(String track2){

        String tr2 = "";

        if (track2.split("=")[1].length()%2==0)
            tr2 = track2.replace("=","AA");
        else
            tr2 = track2.replace("=","AAB");

        if (tr2.length()<38)
            tr2 = IsoUtil.padright(tr2,38,'0');

        return tr2;


    }

    public static String asciiToText(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(n / 2);
        for (int i = 0; i < n; i += 2) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
        }
        return sb.toString();
    }


    public static String stringToHexAscii(String input){
        return String.format("%x", new BigInteger(1, input.getBytes(/*YOUR_CHARSET?*/)));
    }

    public static String getSerial(String input){
        return "FF"+ stringToHexAscii(input);
    }

//    public static String decode(byte[] bytes){
//        StringBuilder sb = new StringBuilder();
//        for(byte b : bytes){
//            int w = b;
//            if(w<0)
//                w += 256;
//            sb.append(FanapDecoder.getWord(w));
//        }
//        return sb.toString();
//    }

//    public static String decodeName(byte[] bytes){
//        StringBuilder sb = new StringBuilder();
//        List<String> temp = new ArrayList<>();
//        for(byte b : bytes){
//            int w = b;
//            if(w<0)
//                w += 256;
//
//            if (w>=46 && w<=65){
//                temp.add(FanapDecoder.getWord(w));
//            }else{
//                if (!temp.isEmpty()){
//                    Collections.reverse(temp);
//                    for (String word:temp) {
//                        sb.append(word);
//                    }
//                    temp.clear();
//                }
//                sb.append(FanapDecoder.getWord(w));
//            }
//        }
//
//        if (!temp.isEmpty()) {
//            Collections.reverse(temp);
//            for (String word : temp) {
//                sb.append(word);
//            }
//        }
//
//        return sb.toString();
//    }

//    public static String decodePhone(byte[] bytes){
//        StringBuilder sb = new StringBuilder();
//        for(byte b : bytes){
//            int w = b;
//            if(w<0)
//                w += 255;
//            sb.append(FanapDecoder.getWord(w));
//        }
//        return sb.reverse().toString();
//    }

    public static String cardMask(String track2){

        String cardNumber = track2.split("=")[0];

        StringBuilder builder = new StringBuilder();
        builder.append(cardNumber.substring(0,6));
        builder.append("_x_");
        builder.append(cardNumber.substring(12));
        return builder.toString();
    }

    public static String cardMaskForReceipt(String track2){

        String cardNumber = track2.split("=")[0];

        StringBuilder builder = new StringBuilder();
        builder.append(cardNumber.substring(0,6));
        builder.append("*");
        builder.append(cardNumber.substring(12));
        return builder.toString();
    }

    public static String ibanMask(String iban){
        try {
            return iban.substring(0, 4) + "_x_" + iban.substring(iban.length() - 4);
        }catch (Exception e){
            return "";
        }
    }

    public static String deMask(String mask){
        return mask.replace("_x_","000000")+"=";
    }

    public static String hexLength(String input, int type){
        long length = input.length()/2;
        String hex = Long.toString(length,16);
        return type==256? padleft(hex,2,'0') : padleft(hex,4,'0');
    }

    public static int hexToDecimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    public static ArrayList<String> getIbans(String ibans){
        ArrayList<String> list = new ArrayList<>();
        String [] arrIbans = ibans.split("2C");
        for (String item : arrIbans)
            list.add(IsoUtil.hexToAscii(item.substring(4)));
        return list;
    }

    public static String hexToAscii(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(n / 2);
        for (int i = 0; i < n; i += 2) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
        }
        return sb.toString();
    }

    private static int hexToInt(char ch) {
        if ('a' <= ch && ch <= 'f') { return ch - 'a' + 10; }
        if ('A' <= ch && ch <= 'F') { return ch - 'A' + 10; }
        if ('0' <= ch && ch <= '9') { return ch - '0'; }
        throw new IllegalArgumentException(String.valueOf(ch));
    }

    public static String longStringToHexString(String longNumber){
       return Long.toString(Long.parseLong(longNumber),16);
    }

    public static String padright(String s, int len, char c) throws IllegalArgumentException {
        s = s.trim();
        if (s.length() > len)
            throw new IllegalArgumentException("invalid len " + s.length() + "/" + len);
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        d.append(s);
        while (fill-- > 0)
            d.append(c);
        return d.toString();
    }


    public static String padleft(String s, int len, char c) throws IllegalArgumentException
    {
        s = s.trim();
        if (s.length() > len)
            throw new IllegalArgumentException("invalid len " +s.length() + "/" +len);
        StringBuilder d = new StringBuilder (len);
        int fill = len - s.length();
        while (fill-- > 0)
            d.append (c);
        d.append(s);
        return d.toString();
    }

    public static String getTerminalTime(){

        //Log.d("TIME",String.valueOf(System.currentTimeMillis()/1000 - 1388534400 + 12600));

        return String.valueOf(System.currentTimeMillis()/1000 - 1388534400 + 12600);//current time minus timestamp from 2014/01/01
    }

    public static String makeEvenLength(String input){
        return input.length()%2==0? input : input+"0";
    }


    public static String makeEvenLength2(String input){
        return input.length()%2==0? input : input+"F";
    }

    public static byte[] getDefaultMacKey()
    {
        String serialNumber = DeviceSDKManager.INSTANCE.getSerialInterface().getSerial();
        if (serialNumber.length()>15)
            serialNumber = serialNumber.substring(serialNumber.length()-15);

        byte[] serial = serialNumber.getBytes();
        byte[] serialPadded = new byte[16];
        Arrays.fill(serialPadded, (byte) 0);

        int j = serial.length - 1;
        for (int i = serialPadded.length - 2; i >= 0 && j >= 0; i--, j--)
            serialPadded[i] = serial[j];
        serialPadded[15] = 0;
        byte[] masterKey = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};

        byte[] xorResult = new byte[16];
        for (int i = 0; i < 16; i++)
        {
            xorResult[i] = (byte) (serialPadded[i] ^ masterKey[i]);
        }

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] newBigKey = md.digest(xorResult);

            byte[] logonMacKey = new byte[16];
            for (int i = 0; i < 8; i++) {
                logonMacKey[i] = newBigKey[2 * i];
                logonMacKey[8 + i] = newBigKey[2 * i];
            }

            return logonMacKey;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] createMac(byte[] packetMessage, byte[] key)
    {
        try {

            byte[] zeroIv = new byte[8];
            byte[] nonZeroIv = new byte[]{0x5, 0x6, 0x7, 0x8, 0x1, 0x2, 0x3, 0x4};

            ParametersWithIV zeroIvParameter = new ParametersWithIV(new KeyParameter(key), nonZeroIv);

            int len = packetMessage.length;
            int d = packetMessage.length % 8;
            if (d != 0) {
                len = ((packetMessage.length / 8) * 8) + 8;
            }
            if (len != packetMessage.length) {
                packetMessage = Arrays.copyOf(packetMessage, len);
            }
            BlockCipher cipher = new DESEngine();
            Mac mac = new ISO9797Alg3Mac(cipher, 64, new ISO7816d4Padding());

            KeyParameter keyP = new KeyParameter(key);
            mac.init(zeroIvParameter);
            mac.update(packetMessage, 0, packetMessage.length);

            byte[] out = new byte[8];

            mac.doFinal(out, 0);

            return out;
        }
        catch (Exception e){
            return null;
        }
    }

    public static byte[] DoMac(byte[] data , byte[] key)
    {
        try{
            byte[] IV = new byte[8]; //empty byte array

            byte[] leftKey =  Arrays.copyOfRange(key, 0, 8);
            byte[] rightKey = Arrays.copyOfRange(key, 8, 16);

            SimpleDES encryptor = new SimpleDES(leftKey);
            SimpleDES decryptor = new SimpleDES(rightKey);

            byte[] result = new byte[8];
            byte[] datablock = new byte[8];
            int remain = data.length % 8;
            int LoopCount = data.length / 8;

            if (remain == 0)
            {
                LoopCount--;
                remain = 8;
            }

            System.arraycopy(data,0,datablock, 0, 8);
            result = encryptor.encrypt(datablock);

            for (int i = 1; i < LoopCount; i++)
            {
                datablock = new byte[8];
                System.arraycopy(data, i * 8, datablock, 0, 8);
                datablock = xorArray(datablock, result);
                result = encryptor.encrypt(datablock);
            }

            byte[] LastBlock = new byte[8];

            System.arraycopy(data, data.length - remain, LastBlock, 0, remain);
            LastBlock = xorArray(LastBlock, result);

            result = encryptor.encrypt(LastBlock);

            result = decryptor.decryptCbc(result);

            result = encryptor.encrypt(result);

            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] xorArray(byte[] buffer1, byte[] buffer2)
    {
        byte[] xorResult = new byte[buffer1.length];
        for (int i = 0; i < buffer1.length; i++)
            xorResult[i] = (byte) (buffer1[i] ^ buffer2[i]);

        return xorResult;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String createBitmap(Set<Integer> fields){

        List<String> bitmap = new ArrayList<>(Collections.nCopies(64, "0"));

        for(int item : fields) {
            if(item>1)
                bitmap.set(item - 1, "1");
        }
        bitmap.set(63,"1"); // set for MAC

        StringBuilder binaryBitmap = new StringBuilder();
        for(String item:bitmap)
            binaryBitmap.append(item);

       // Log.d("BITMAP",binaryBitmap.toString());
        return binaryBitmap.toString();
    }

    public static String binaryToHex(String binaryStr){
        try {
            long decimal = Long.parseLong(binaryStr, 2);
            return Long.toString(decimal, 16);
        }catch (Exception e){
            //Log.e("TAG", e.getMessage());
            return null;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String hexString(byte[] var0) {
        if (var0 != null && var0.length != 0) {
            StringBuilder var1;
            var1 = new StringBuilder(var0.length * 2);
            int var2 = var0.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                var1.append(hexStrings[var0[var3] & 255]);
            }

            return var1.toString();
        } else {
            return "";
        }
    }


    public static String bcdToString(byte[] bcdNum, int offset, int numlen) {
        int len = numlen;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < len; ++i) {
            sb.append(String.format("%02X", bcdNum[i + offset]));
        }

        return sb.toString();
    }



    public static List<Integer> unpackBitmap(String bitmap){

        List<Integer> list = new ArrayList<>(Collections.nCopies(64, 0));
        String binary = padleft(new BigInteger(bitmap, 16).toString(2),64,'0');
        for (int i =0; i< binary.length(); i++)
            if(binary.charAt(i)=='1')
                list.set(i,1);

        return list;
    }

    public static List<Byte> bcdToByte(String p)
    {
        List<Byte> myField = new ArrayList<>();
        for (int i = 0; i < p.length(); i += 2)
            myField.add(GetByte(p.substring(i, i+2)));
        return myField;
    }

    private static byte GetByte(String value)
    {
        return (byte) ((Character.digit(value.charAt(0), 16) << 4) + Character.digit(value.charAt(1), 16));
    }

    public static List<Byte> intToHexByte(int value, int count)
    {
        if(count==2) {
            List<Byte> ListByte = new ArrayList<>();

            byte[] MyBytes = GetBytesV2(value);
            int i = MyBytes.length - 1;
            while (i >= MyBytes.length - count) {
                ListByte.add(MyBytes[i]);
                i = i - 1;
            }
            return ListByte;
        }
        else if(count==4){
            List<Byte> ListByte = new ArrayList<>();

            byte[] MyBytes = GetBytesV4(value);
            int i = MyBytes.length - 1;
            while (i >= MyBytes.length - count)
            {
                ListByte.add(MyBytes[i]);
                i = i - 1;
            }
            return ListByte;
        }
        return null;
    }

    public static byte[] listToByteArray(List<Byte> byteList){
        byte[] byteArray = new byte[byteList.size()];
        for (int index = 0; index < byteList.size(); index++) {
            byteArray[index] = byteList.get(index);
        }
        return byteArray;
    }

    public static List<Byte> byteArrayToList(byte[] byteArray){
        List<Byte> list = new ArrayList<>();
        for (byte b : byteArray)
            list.add(b);
        return list;
    }

     static byte[] GetBytesV2(int value)
    {
        byte[] result = new byte[2];
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
        buffer.putInt(value);
        result[0] = buffer.get(0);
        result[1] = buffer.get(1);
        return result;
    }

     static byte[] GetBytesV4(int value)
    {
        ByteBuffer buffer = ByteBuffer.allocate(4).order(ByteOrder.nativeOrder());
        buffer.putInt(value);
        return buffer.array();
    }
}
