package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import cn.fantasticmao.mundo.core.util.CipherUtil;
import cn.fantasticmao.mundo.core.util.HashUtil;
import cn.fantasticmao.mundo.core.util.JsonUtil;
import com.fasterxml.jackson.core.JacksonException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.Objects;

/**
 * UserInfoParser
 *
 * @author fantasticmao
 * @version 1.0
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">开放数据校验与解密</a>
 * @since 2019-03-30
 */
public class UserInfoParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoParser.class);

    /**
     * 校验数据签名
     *
     * @param sessionKey 用户的会话密钥
     * @param rawData    不包括敏感信息的原始数据字符串，用于计算签名
     * @param signature  使用 sha1( rawData + sessionKey ) 得到字符串，用于校验用户信息
     * @return true 校验成功；false 校验失败
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">数据签名校验</a>
     */
    public static boolean checkSignature(final String sessionKey, final String rawData,
                                         final String signature) {
        byte[] bytes = HashUtil.SHA_1.hash((rawData + sessionKey).getBytes(StandardCharsets.UTF_8));
        return Objects.equals(signature, Hex.encodeHexString(bytes));
    }

    /**
     * 解密敏感数据
     *
     * @param sessionKey    用户的会话密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return 小程序用户信息 {@link UserInfo}
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">加密数据解密算法</a>
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
