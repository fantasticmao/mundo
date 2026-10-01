package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import lombok.Getter;
import lombok.Setter;

/**
 * User information returned by a WeChat mini program.
 *
 * @author fantasticmao
 * @version 1.0
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/api/UserInfo.html">Mini program user information</a>
 * @since 2019-03-31
 */
@Getter
@Setter
public class UserInfo {
    /**
     * Open id of the user.
     */
    private String openId;
    /**
     * Nickname.
     */
    private String nickName;
    /**
     * Gender.
     */
    private GenderEnum gender;
    /**
     * Language code.
     */
    private String language;
    /**
     * City.
     */
    private String city;
    /**
     * Province.
     */
    private String province;
    /**
     * Country.
     */
    private String country;
    /**
     * Avatar URL.
     */
    private String avatarUrl;
    /**
     * Union id, when the user is bound to an open platform account.
     */
    private String unionId;
    /**
     * Watermark of the decrypted payload.
     */
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
            "}";
    }

    /**
     * Gender of a mini program user.
     */
    public enum GenderEnum {
        /**
         * Unknown.
         */
        UNKNOWN(0),
        /**
         * Male.
         */
        MALE(1),
        /**
         * Female.
         */
        FEMALE(2);

        /**
         * Numeric gender code from the WeChat payload.
         */
        public final int gender;

        GenderEnum(int gender) {
            this.gender = gender;
        }
    }

    /**
     * Language of a mini program user.
     */
    public enum Language {
        /**
         * English.
         */
        EN("en"),
        /**
         * Simplified Chinese.
         */
        ZH_CN("zh_CN"),
        /**
         * Traditional Chinese.
         */
        ZH_TW("zh_TW");

        private final String language;

        Language(String language) {
            this.language = language;
        }
    }

    /**
     * Watermark attached to decrypted user information.
     */
    @Getter
    @Setter
    public static class Watermark {
        /**
         * App id of the mini program.
         */
        private String appid;
        /**
         * Timestamp of the watermark.
         */
        private String timestamp;

        public Watermark() {
        }

        @Override
        public String toString() {
            return "Watermark{" +
                "appid='" + appid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                "}";
        }
    }
}
