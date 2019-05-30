package com.mundo.core.util;

import java.io.IOException;
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
            return getFormattedText(md.digest(bytes));
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

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

}
