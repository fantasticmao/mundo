package cn.fantasticmao.mundo.web.support.wechat;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Type of a WeChat message.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
public enum WeChatMessageType {
    /**
     * Text message.
     */
    TEXT,
    /**
     * Image message.
     */
    IMAGE,
    /**
     * Voice message.
     */
    VOICE,
    /**
     * Video message.
     */
    VIDEO,
    /**
     * Short video message.
     */
    SHORT_VIDEO,
    /**
     * Location message.
     */
    LOCATION,
    /**
     * Link message.
     */
    LINK,
    /**
     * Unrecognized message type.
     */
    UNKNOWN;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Returns the type whose lowercase name equals {@code type}.
     *
     * @param type lowercase type name from the XML {@code MsgType} element
     * @return matching type, or {@link #UNKNOWN} when none matches
     */
    public static WeChatMessageType of(String type) {
        return Stream.of(WeChatMessageType.values())
            .filter(messageType -> Objects.equals(messageType.toString(), type))
            .findFirst()
            .orElse(UNKNOWN);
    }
}
