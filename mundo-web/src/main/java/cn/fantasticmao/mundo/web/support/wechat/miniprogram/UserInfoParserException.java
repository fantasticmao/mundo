package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

/**
 * UserInfoParserException
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2019-03-31
 */
public class UserInfoParserException extends RuntimeException {

    public UserInfoParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoParserException(Throwable cause) {
        super(cause);
    }
}