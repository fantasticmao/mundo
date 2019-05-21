package com.mundo.core.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
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
    SHA1("SHA1");

    private final String type;

    HashUtil(String type) {
        this.type = type;
    }

    public String hash(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(type);
            byte[] newBytes = md.digest(bytes);
            return new BigInteger(1, newBytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String hash(String str) {
        return hash(str.getBytes());
    }

    public String hash(Path path) throws IOException {
        return hash(Files.readAllBytes(path));
    }

    public static byte[] encode(String str) {
        return Base64.getEncoder().encode(str.getBytes());
    }

    public static byte[] decode(String str) {
        return Base64.getDecoder().decode(str.getBytes());
    }

    public static String encodeToString(String str) {
        return new String(encode(str));
    }

    public static String decodeToString(String str) {
        return new String(decode(str));
    }

}
