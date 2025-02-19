package com.fanap.corepos.iso.utils;

import android.util.Log;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SimpleDES {
    private byte[] IV = new byte[8];
    private byte[] mKey;

    public SimpleDES(byte[] aKey) {
        if (aKey.length != 8) {
            throw new IllegalArgumentException("Key size must be 8 bytes");
        }
        this.mKey = aKey;
    }


    public byte[] Encrypt(byte[] data) {
        try {
            KeySpec keySpec = new DESKeySpec(mKey);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Exception .. " + e.getMessage());
            return null;
        }
//        return new String(Base64.encodeBase64(bout.toByteArray()),"ASCII");
    }

    public byte[] encrypt(byte[] data) {
        try {
            KeySpec keySpec = new DESKeySpec(mKey);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.out.println("Exception .. " + e.getMessage());
            return null;
        }
//        return new String(Base64.encodeBase64(bout.toByteArray()),"ASCII");
    }

    public byte[] GetMacBytes(byte[] input) {

        try {
            KeySpec keySpec = new DESKeySpec(mKey);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] mac = cipher.doFinal(input);

            return mac;

        } catch (Exception e) {
            Log.d("Exception",e.getMessage());
        }
        return null;
    }

    public byte[] decrypt(byte[] input) throws Exception {
        byte[] decrypted = new byte[8];
        try {
            KeySpec keySpec = new DESKeySpec(mKey);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            //IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,key);
            decrypted = cipher.doFinal(input);
        } catch(Exception e) {
            Log.d("Exception",e.getMessage());
        }
        return decrypted;
    }

    public byte[] decryptCbc(byte[] input) {
        byte[] decrypted = new byte[8];
        try {
            KeySpec keySpec = new DESKeySpec(mKey);
            SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(keySpec);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,key,iv);
            decrypted = cipher.doFinal(input);
        } catch(Exception e) {
            Log.d("Exception",e.getMessage());
        }
        return decrypted;
    }

}

