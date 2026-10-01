package cn.fantasticmao.mundo.web.support.wechat;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A WeChat text message.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
@Getter
public class WeChatTextMessage extends WeChatMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatTextMessage.class);
    /**
     * XML tag for the text content.
     */
    public static final String CONTENT = "Content";

    /**
     * Text content.
     */
    private final String content;

    /**
     * Creates a text message.
     *
     * @param weChatMessage common message fields
     * @param content       text content
     */
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

}
