package cn.fantasticmao.mundo.web.support.wechat;

import lombok.Getter;

/**
 * A WeChat message parsed from XML.
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018-12-05
 */
@Getter
public class WeChatMessage {
    /**
     * XML tag for the recipient.
     */
    public static final String TO_USER_NAME = "ToUserName";
    /**
     * XML tag for the sender.
     */
    public static final String FROM_USER_NAME = "FromUserName";
    /**
     * XML tag for the creation time, in seconds.
     */
    public static final String CREATE_TIME = "CreateTime";
    /**
     * XML tag for the message id.
     */
    public static final String MSG_ID = "MsgId";
    /**
     * XML tag for the message type.
     */
    public static final String MSG_TYPE = "MsgType";

    /**
     * Recipient user name.
     */
    private final String toUserName;
    /**
     * Sender user name.
     */
    private final String fromUserName;
    /**
     * Creation time, in seconds.
     */
    private final long createTime;
    /**
     * Message id.
     */
    private final long msgId;
    /**
     * Message type.
     */
    private final WeChatMessageType msgType;

    /**
     * Creates a message.
     *
     * @param toUserName   recipient
     * @param fromUserName sender
     * @param createTime   creation time, in seconds
     * @param msgId        message id
     * @param msgType      message type
     */
    protected WeChatMessage(String toUserName, String fromUserName, long createTime, long msgId, WeChatMessageType msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgId = msgId;
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "WeChatMessage{" +
            "toUserName='" + toUserName + '\'' +
            ", fromUserName='" + fromUserName + '\'' +
            ", createTime=" + createTime +
            ", msgId=" + msgId +
            ", msgType=" + msgType +
            "}";
    }

}
