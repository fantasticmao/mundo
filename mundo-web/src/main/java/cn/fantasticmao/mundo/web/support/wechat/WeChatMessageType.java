package cn.fantasticmao.mundo.web.support.wechat;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * WeChatMessageType
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
public enum WeChatMessageType {
    TEXT, IMAGE, VOICE, VIDEO, SHORT_VIDEO, LOCATION, LINK, UNKNOWN;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static WeChatMessageType of(String type) {
        return Stream.of(WeChatMessageType.values())
            .filter(messageType -> Objects.equals(messageType.toString(), type))
            .findFirst()
            .orElse(UNKNOWN);
    }
}
