package com.mundo.web.support.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WeChatTextMessage
 *
 * @author maodh
 * @since 2018/12/5
 */
public class WeChatTextMessage extends WeChatMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatTextMessage.class);
    public static final String CONTENT = "Content";

    private final String content;

    protected WeChatTextMessage(WeChatMessage weChatMessage, String content) {
        super(weChatMessage.getToUserName(), weChatMessage.getFromUserName(), weChatMessage.getCreateTime(),
                weChatMessage.getMsgId(), weChatMessage.getMsgType());
        this.content = content;
    }

    @Override
    public String toString() {
        return "WeChatTextMessage{" +
                "content='" + content + '\'' +
                "} " + super.toString();
    }

    // getter

    public String getContent() {
        return content;
    }
}
