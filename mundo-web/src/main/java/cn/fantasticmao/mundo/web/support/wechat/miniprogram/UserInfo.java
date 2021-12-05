package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import lombok.*;

/**
 * UserInfo
 *
 * @author fantasticmao
 * @version 1.0
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/api/UserInfo.html">小程序用户信息</a>
 * @since 2019-03-31
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserInfo {
    private String openId;
    private String nickName;
    private GenderEnum gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private Watermark watermark;

    @AllArgsConstructor
    public enum GenderEnum {
        UNKNOW(0), MALE(1), FEMALE(2);

        public final int gender;
    }

    @AllArgsConstructor
    public enum Language {
        EN("en"),
        ZH_CN("zh_CN"),
        ZH_TW("zh_TW");

        private final String language;

    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Watermark {
        private String appid;
        private String timestamp;
    }
}