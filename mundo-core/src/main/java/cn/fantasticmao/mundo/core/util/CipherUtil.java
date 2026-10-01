package cn.fantasticmao.mundo.core.util;

import org.jspecify.annotations.Nullable;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Operations on {@link Cipher}.
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-13
 */
public enum CipherUtil {
    /**
     * AES cipher with ECB mode and PKCS #5 padding.
     */
    AES_ECB_PKCS5("AES/ECB/PKCS5Padding"),
    /**
     * AES cipher with CBC mode and PKCS #5 padding.
     */
    AES_CBC_PKCS5("AES/CBC/PKCS5Padding");

    private final Cipher cipher;

    /**
     * Constructor for creating {@link Cipher} instance.
     *
     * @param transformation transforms in format: algorithm/mode/padding
     */
    CipherUtil(String transformation) {
        try {
            this.cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm: " + transformation, e);
        } catch (NoSuchPaddingException e) {
            throw new IllegalArgumentException("No such padding: " + transformation, e);
        }
    }

    /**
     * Encrypts the given data.
     *
     * @param key  encryption key
     * @param data plaintext
     * @return ciphertext
     * @throws IllegalArgumentException if the key, block size, or padding is invalid
     */
    public byte[] encrypt(Key key, byte[] data) {
        return encrypt(key, data, null);
    }

    /**
     * Encrypts the given data.
     * <p>
     * {@code params} is not used.
     *
     * @param key    encryption key
     * @param data   plaintext
     * @param params ignored
     * @return ciphertext
     * @throws IllegalArgumentException if the key, block size, or padding is invalid
     */
    public byte[] encrypt(Key key, byte[] data, @Nullable AlgorithmParameterSpec params) {
        return encrypt(key, data, null, null);
    }

    /**
     * Encrypts the given data.
     *
     * @param key    encryption key
     * @param data   plaintext
     * @param params algorithm parameters, or {@code null}
     * @param random source of randomness, or {@code null}
     * @return ciphertext
     * @throws IllegalArgumentException if the key, algorithm parameters, block size, or padding is invalid
     */
    public byte[] encrypt(Key key, byte[] data, @Nullable AlgorithmParameterSpec params,
                          @Nullable SecureRandom random) {
        init(Cipher.ENCRYPT_MODE, key, params, random);
        return doFinal(data);
    }

    /**
     * Decrypts the given data.
     *
     * @param key  decryption key
     * @param data ciphertext
     * @return plaintext
     * @throws IllegalArgumentException if the key, block size, or padding is invalid
     */
    public byte[] decrypt(Key key, byte[] data) {
        return decrypt(key, data, null);
    }

    /**
     * Decrypts the given data.
     *
     * @param key    decryption key
     * @param data   ciphertext
     * @param params algorithm parameters, or {@code null}
     * @return plaintext
     * @throws IllegalArgumentException if the key, algorithm parameters, block size, or padding is invalid
     */
    public byte[] decrypt(Key key, byte[] data, @Nullable AlgorithmParameterSpec params) {
        return decrypt(key, data, params, null);
    }

    /**
     * Decrypts the given data.
     *
     * @param key    decryption key
     * @param data   ciphertext
     * @param params algorithm parameters, or {@code null}
     * @param random source of randomness, or {@code null}
     * @return plaintext
     * @throws IllegalArgumentException if the key, algorithm parameters, block size, or padding is invalid
     */
    public byte[] decrypt(Key key, byte[] data, @Nullable AlgorithmParameterSpec params,
                          @Nullable SecureRandom random) {
        init(Cipher.DECRYPT_MODE, key, params, random);
        return doFinal(data);
    }

    private void init(int mode, Key key, @Nullable AlgorithmParameterSpec params,
                      @Nullable SecureRandom random) {
        try {
            if (params != null && random != null) {
                cipher.init(mode, key, params, random);
            } else if (params != null) {
                cipher.init(mode, key, params);
            } else if (random != null) {
                cipher.init(mode, key, random);
            } else {
                cipher.init(mode, key);
            }
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("Invalid key: " + key, e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("Invalid algorithm parameter: " + params, e);
        }
    }

    private byte[] doFinal(byte[] data) {
        try {
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalArgumentException("Illegal block size", e);
        } catch (BadPaddingException e) {
            throw new IllegalArgumentException("Bad padding", e);
        }
    }
}
