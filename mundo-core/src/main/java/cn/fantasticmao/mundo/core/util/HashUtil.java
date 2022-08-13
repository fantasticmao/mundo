package cn.fantasticmao.mundo.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Operations on {@link MessageDigest}.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/3/5
 */
public enum HashUtil {
    MD5("MD5"),
    SHA_1("SHA-1"),
    SHA_256("SHA-256");

    private final MessageDigest messageDigest;

    HashUtil(String algorithm) {
        try {
            this.messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm: " + algorithm, e);
        }
    }

    public byte[] hash(byte[] bytes) {
        Objects.requireNonNull(this.messageDigest);
        return this.messageDigest.digest(bytes);
    }

}
