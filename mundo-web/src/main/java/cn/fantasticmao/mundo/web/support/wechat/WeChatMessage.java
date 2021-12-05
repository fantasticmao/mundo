package cn.fantasticmao.mundo.web.support.wechat;

/**
 * WeChatMessage
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/12/5
 */
public class WeChatMessage {
    public static final String TO_USER_NAME = "ToUserName";
    public static final String FROM_USER_NAME = "FromUserName";
    public static final String CREATE_TIME = "CreateTime";
    public static final String MSG_ID = "MsgId";
    public static final String MSG_TYPE = "MsgType";

    private final String toUserName;
    private final String fromUserName;
    private final long createTime;
    private final long msgId;
    private final WeChatMessageType msgType;

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
            '}';
    }

    // getter

    public String getToUserName() {
        return toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getMsgId() {
        return msgId;
    }

    public WeChatMessageType getMsgType() {
        return msgType;
    }
}
