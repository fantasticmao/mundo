package cn.fantasticmao.mundo.core.util;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * HashUtilTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 17-4-24
 */
public class HashUtilTest {

    @Test
    public void digestBytes_md5() {
        byte[] output = HashUtil.MD5.hash("helloworld".getBytes(StandardCharsets.UTF_8));
        Assertions.assertEquals("fc5e038d38a57032085441e7fe7010b0", Hex.encodeHexString(output));
        Assertions.assertEquals("/F4DjTilcDIIVEHn/nAQsA==", Base64.getEncoder().encodeToString(output));
    }

    @Test
    public void digestBytes_sha1() {
        byte[] output = HashUtil.SHA_1.hash("helloworld".getBytes(StandardCharsets.UTF_8));
        Assertions.assertEquals("6adfb183a4a2c94a2f92dab5ade762a47889a5a1", Hex.encodeHexString(output));
        Assertions.assertEquals("at+xg6SiyUovktq1redipHiJpaE=", Base64.getEncoder().encodeToString(output));
    }

    @Test
    public void digestBytes_sha256() {
        byte[] output = HashUtil.SHA_256.hash("helloworld".getBytes(StandardCharsets.UTF_8));
        Assertions.assertEquals("936a185caaa266bb9cbe981e9e05cb78cd732b0b3280eb944412bb6f8f8f07af", Hex.encodeHexString(output));
        Assertions.assertEquals("k2oYXKqiZrucvpgengXLeM1zKwsygOuURBK7b4+PB68=", Base64.getEncoder().encodeToString(output));
    }

}
