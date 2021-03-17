package cn.fantasticmao.mundo.web.support.wechat.miniprogram;

import org.junit.Assert;
import org.junit.Test;

/**
 * UserInfoParserTest
 *
 * @author maomao
 * @since 2019-03-31
 */
public class UserInfoParserTest {

    @Test
    public void checkSignature() {
        String sessionKey = "HyVFkGl5F5OQWJZZaNzBBg==";
        String rawData = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}";
        String signature = "75e81ceda165f4ffa64f4068af58c64b8f54b88c";
        boolean actual = UserInfoParser.checkSignature(sessionKey, rawData, signature);
        Assert.assertTrue(actual);
    }

    @Test
    public void decryptData() {
        String appId = "wx4f4bc4dec97d474b";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
        String iv = "r7BXXKkLb8qrSNn05n0qiA==";
        UserInfo userInfo = UserInfoParser.decryptData(sessionKey, encryptedData, iv);
        Assert.assertNotNull(userInfo);
        Assert.assertEquals(appId, userInfo.getWatermark().getAppid());
    }
}