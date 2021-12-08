package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import cn.fantasticmao.mundo.core.util.HashUtil;
import cn.fantasticmao.mundo.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    private static final Cipher CIPHER;

    static {
        final String aesCbcPadding = "AES/CBC/PKCS5Padding";
        try {
            CIPHER = Cipher.getInstance(aesCbcPadding);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error("can not find such algorithm: {}", aesCbcPadding);
            throw new UserInfoParserException(e);
        }
    }

    /**
     * 校验数据签名
     *
     * @param sessionKey 用户的会话密钥
     * @param rawData    不包括敏感信息的原始数据字符串，用于计算签名
     * @param signature  使用 sha1( rawData + sessionKey ) 得到字符串，用于校验用户信息
     * @return true 校验成功；false 校验失败
     * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html">数据签名校验</a>
     */
    public static boolean checkSignature(final String sessionKey, final String rawData, final String signature) {
        LOGGER.debug("rawData: {}", rawData);
        LOGGER.debug("signature: {}", signature);
        String hash = HashUtil.SHA1.hash(rawData + sessionKey);
        LOGGER.debug("hash: {}", hash);
        return Objects.equals(signature, hash);
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
    public static UserInfo decryptData(final String sessionKey, final String encryptedData, final String iv) {
        final byte[] sessionKeyBase64 = HashUtil.decode(sessionKey);
        final byte[] encryptedDataBase64 = HashUtil.decode(encryptedData);
        final byte[] ivBase64 = HashUtil.decode(iv);

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(sessionKeyBase64, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBase64);
            CIPHER.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] userInfoBytes = CIPHER.doFinal(encryptedDataBase64);
            String userInfoJson = new String(userInfoBytes);
            LOGGER.debug("decrypt user info data: {}", userInfoJson);
            return JsonUtil.toClass(userInfoJson, UserInfo.class);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new UserInfoParserException(e);
        }
    }
}
