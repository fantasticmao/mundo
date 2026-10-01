package cn.fantasticmao.mundo.web.support.wechat;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.mundo.core.util.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Verifies a WeChat server configuration request.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
public abstract class WeChatServerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerConfig.class);

    /**
     * Verifies the signature on a WeChat server configuration request.
     *
     * @param request HTTP request carrying {@code signature}, {@code timestamp}, {@code nonce}, and {@code echostr}
     * @return {@code echostr} when verification succeeds, otherwise an empty string
     */
    public String config(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("验证微信服务器请求 signature={} timestamp={} nonce={} echostr={}",
                signature, timestamp, nonce, echoStr);
        }

        if (this.verifyParameters(signature, timestamp, nonce)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("验证微信服务器请求成功");
            }
            return echoStr;
        } else {
            LOGGER.error("验证微信服务器请求失败");
            return Constant.Strings.EMPTY;
        }
    }

    /**
     * Checks that the signature matches {@code SHA-1(sort(token, timestamp, nonce))}.
     *
     * @param signature expected signature
     * @param timestamp request timestamp
     * @param nonce     request nonce
     * @return {@code true} when the parameters are present and the signature matches
     */
    protected boolean verifyParameters(final String signature, final String timestamp, final String nonce) {
        if (StringUtils.isAnyEmpty(signature, timestamp, nonce)) {
            return false;
        }

        List<String> list = Arrays.asList(getToken(), timestamp, nonce);
        Collections.sort(list);
        final String str = String.join(Constant.Strings.EMPTY, list);
        final byte[] bytes = HashUtil.SHA_1.hash(str.getBytes(StandardCharsets.UTF_8));
        final String hashStr = Hex.encodeHexString(bytes);
        return Objects.equals(signature, hashStr);
    }

    /**
     * Loads the server token from the first {@link TokenProvider} on the classpath.
     *
     * @return server token
     * @throws IllegalArgumentException if no provider is available
     */
    protected String getToken() {
        ServiceLoader<TokenProvider> serviceLoader = ServiceLoader.load(TokenProvider.class);
        for (TokenProvider provider : serviceLoader) {
            return provider.token();
        }
        throw new IllegalArgumentException("获取微信服务器配置令牌异常");
    }

    public interface TokenProvider {

        /**
         * Returns the WeChat server configuration token.
         *
         * @return configuration token
         */
        @NonNull
        String token();
    }
}
