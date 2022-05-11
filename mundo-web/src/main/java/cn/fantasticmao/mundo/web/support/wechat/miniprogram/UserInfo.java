package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import lombok.Getter;
import lombok.Setter;

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

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "openId='" + openId + '\'' +
            ", nickName='" + nickName + '\'' +
            ", gender=" + gender +
            ", language='" + language + '\'' +
            ", city='" + city + '\'' +
            ", province='" + province + '\'' +
            ", country='" + country + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", unionId='" + unionId + '\'' +
            ", watermark=" + watermark +
            '}';
    }

    public enum GenderEnum {
        UNKNOWN(0), MALE(1), FEMALE(2);

        public final int gender;

        GenderEnum(int gender) {
            this.gender = gender;
        }
    }

    public enum Language {
        EN("en"),
        ZH_CN("zh_CN"),
        ZH_TW("zh_TW");

        private final String language;

        Language(String language) {
            this.language = language;
        }
    }

    @Getter
    @Setter
    public static class Watermark {
        private String appid;
        private String timestamp;

        public Watermark() {
        }

        @Override
        public String toString() {
            return "Watermark{" +
                "appid='" + appid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
        }
    }
}