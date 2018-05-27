package com.mundo.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * HashUtil
 *
 * @author maomao
 * @since 2017/3/5
 */
public enum HashUtil {
    MD5("MD5"),
    SHA("SHA"),
    SHA1("SHA1");

    private final String type;

    HashUtil(String type) {
        this.type = type;
    }

    public String encode(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            md.update(text.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Base64 解码
     */
    public static String a2b(String text) {
        byte[] dts = Base64.getDecoder().decode(text.getBytes());
        return new String(dts);
    }

    /**
     * Base64 编码
     */
    public static String b2a(String text) {
        byte[] dst = Base64.getEncoder().encode(text.getBytes());
        return new String(dst);
    }

}
