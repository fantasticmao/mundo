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
public final class HashUtil {

    public enum Type {
        MD5("MD5"),
        SHA("SHA"),
        SHA1("SHA1");

        private String type;

        Type(String type) {
            this.type = type;
        }

        public String value() {
            return this.type;
        }
    }

    /**
     * Base64 解码
     */
    public static String atob(String src) {
        byte[] dts = Base64.getDecoder().decode(src.getBytes());
        return new String(dts);
    }

    /**
     * Base64 编码
     */
    public static String btoa(String src) {
        byte[] dst = Base64.getEncoder().encode(src.getBytes());
        return new String(dst);
    }

    public static String digest(Type type, String src) {
        try {
            MessageDigest md = MessageDigest.getInstance(type.value());
            md.update(src.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
