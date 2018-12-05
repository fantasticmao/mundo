package com.mundo.web.mvc;

import com.mundo.core.support.Constant;
import com.mundo.core.util.HashUtil;
import com.mundo.core.util.SpringUtil;
import com.mundo.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * WeChatConfigController
 *
 * @author maodh
 * @since 2018/12/5
 */
public abstract class WeChatConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatConfigController.class);

    public static final String TOKEN_BEAN_NAME = "weChatToken";

    public String configServer(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("验证微信服务器请求");
            LOGGER.debug("signature={}", signature);
            LOGGER.debug("timestamp={}", timestamp);
            LOGGER.debug("nonce={}", nonce);
            LOGGER.debug("echostr={}", echostr);
        }

        if (this.verifyParameters(signature, timestamp, nonce)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("验证微信服务器请求成功");
            }
            return echostr;
        } else {
            LOGGER.error("验证微信服务器请求失败");
            return Constant.Strings.EMPTY;
        }
    }

    protected boolean verifyParameters(final String signature, final String timestamp, final String nonce) {
        if (StringUtil.isAnyEmpty(signature, timestamp, nonce)) {
            return false;
        }

        List<String> list = Arrays.asList(getToken(), timestamp, nonce);
        Collections.sort(list);
        final String str = list.stream().collect(Collectors.joining());
        final String strAfterHash = HashUtil.SHA1.hash(str);
        return Objects.equals(signature, strAfterHash);
    }

    protected String getToken() {
        final String weChatToken = SpringUtil.getBean(TOKEN_BEAN_NAME);
        if (StringUtil.isEmpty(weChatToken)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("微信服务器 Token 配置异常，weChatToken={}", weChatToken);
            }
            throw new IllegalArgumentException("微信服务器 Token 配置异常");
        } else {
            return weChatToken;
        }
    }
}
