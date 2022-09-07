package cn.fantasticmao.mundo.web.support.wechat;

import cn.fantasticmao.mundo.core.support.Constant;
import cn.fantasticmao.mundo.core.util.HashUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * WeChatServerConfig
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
public abstract class WeChatServerConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServerConfig.class);

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

    protected String getToken() {
        ServiceLoader<TokenProvider> serviceLoader = ServiceLoader.load(TokenProvider.class);
        for (TokenProvider provider : serviceLoader) {
            return provider.token();
        }
        throw new IllegalArgumentException("获取微信服务器配置令牌异常");
    }

    public interface TokenProvider {

        /**
         * 获取微信服务器配置令牌
         *
         * @return 配置令牌
         */
        @Nonnull
        String token();
    }
}
