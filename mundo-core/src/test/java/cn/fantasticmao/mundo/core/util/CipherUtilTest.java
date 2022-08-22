package cn.fantasticmao.mundo.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

/**
 * CipherUtilTest
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-14
 */
public class CipherUtilTest {

    @Test
    public void encrypt() {
        Key key = new SecretKeySpec("12345678abcdefgh".getBytes(StandardCharsets.UTF_8), "AES");
        byte[] input = "helloworld".getBytes(StandardCharsets.UTF_8);
        byte[] output = CipherUtil.AES_ECB_PKCS5.encrypt(key, input);
        String data = Base64.getEncoder().encodeToString(output);
        Assertions.assertEquals("MREmyq/t1VoZqXcb2xs5GQ==", data);
    }

    @Test
    public void decrypt() {
        Key key = new SecretKeySpec("12345678abcdefgh".getBytes(StandardCharsets.UTF_8), "AES");
        byte[] input = Base64.getDecoder().decode("MREmyq/t1VoZqXcb2xs5GQ==");
        byte[] output = CipherUtil.AES_ECB_PKCS5.decrypt(key, input);
        String message = new String(output, StandardCharsets.UTF_8);
        Assertions.assertEquals("helloworld", message);
    }
}
