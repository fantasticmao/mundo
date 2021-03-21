package cn.fantasticmao.mundo.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * RegexUtilTest
 *
 * @author maomao
 * @version 1.0
 * @since 17-4-23
 */
public class RegexUtilTest {
    @Test
    public void isHost() throws Exception {
        String str = "www.google.com";
        Assert.assertTrue(RegexUtil.isHost(str));
    }

    @Test
    public void isUrl() throws Exception {
        String str = "HTTPS://www.google.com";
        Assert.assertTrue(RegexUtil.isUrl(str));
    }

    @Test
    public void isTelephone() throws Exception {
        String str = "13291297303";
        Assert.assertTrue(RegexUtil.isTelephone(str));
    }

    @Test
    public void isEmail() throws Exception {
        String str = "maomao8017@gmail.com";
        Assert.assertTrue(RegexUtil.isEmail(str));
    }

    @Test
    public void isUsername() throws Exception {
        String str = "猫猫233";
        Assert.assertTrue(RegexUtil.isUsername(str));
    }

    @Test
    public void isPassword() throws Exception {
        String str = "mao.122333";
        Assert.assertTrue(RegexUtil.isPassword(str));
    }

}