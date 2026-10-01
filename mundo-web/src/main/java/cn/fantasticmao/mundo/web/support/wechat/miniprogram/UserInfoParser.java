package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import cn.fantasticmao.mundo.core.util.CipherUtil;
import cn.fantasticmao.mundo.core.util.HashUtil;
import cn.fantasticmao.mundo.core.util.JsonUtil;
import org.apache.commons.codec.binary.Hex;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Objects;

/**
 * Checks and decrypts user information from a WeChat mini program.
 *
 * @author fantasticmao
 * @version 1.0
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">Signature verification and decryption</a>
 * @since 2019-03-30
 */
public class UserInfoParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoParser.class);

    /**
     * Checks the data signature.
     *
     * @param sessionKey session key of the user
     * @param rawData    raw data string without sensitive information, used to compute the signature
     * @param signature  expected signature, {@code sha1(rawData + sessionKey)}
     * @return {@code true} when the signature matches
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">Data signature verification</a>
     */
    public static boolean checkSignature(final String sessionKey, final String rawData,
                                         final String signature) {
        byte[] bytes = HashUtil.SHA_1.hash((rawData + sessionKey).getBytes(StandardCharsets.UTF_8));
        return Objects.equals(signature, Hex.encodeHexString(bytes));
    }

    /**
     * Decrypts sensitive user data.
     *
     * @param sessionKey    session key of the user
     * @param encryptedData encrypted user information, including sensitive data
     * @param iv            initialization vector
     * @return mini program {@linkplain UserInfo user information}, or {@code null} when parsing fails
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">Decryption algorithm</a>
     */
    @Nullable
    public static UserInfo decryptData(final String sessionKey, final String encryptedData,
                                       final String iv) {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Key key = new SecretKeySpec(decoder.decode(sessionKey), "AES");
        final byte[] input = decoder.decode(encryptedData);
        final AlgorithmParameterSpec params = new IvParameterSpec(decoder.decode(iv));

        byte[] output = CipherUtil.AES_CBC_PKCS5.decrypt(key, input, params);
        String userInfo = new String(output, StandardCharsets.UTF_8);
        try {
            return JsonUtil.fromJson(userInfo, UserInfo.class);
        } catch (JacksonException e) {
            LOGGER.error("Parse user info error", e);
            return null;
        }
    }
}
