package cn.fantasticmao.mundo.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RegexUtilTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 17-4-23
 */
public class RegexUtilTest {

    @Test
    public void isHost() {
        String str = "www.google.com";
        Assertions.assertTrue(RegexUtil.isHost(str));
    }

    @Test
    public void isUrl() {
        String str = "HTTPS://www.google.com";
        Assertions.assertTrue(RegexUtil.isUrl(str));
    }

    @Test
    public void isTelephone() {
        String str = "13291297303";
        Assertions.assertTrue(RegexUtil.isTelephone(str));
    }

    @Test
    public void isEmail() {
        String str = "maomao8017@gmail.com";
        Assertions.assertTrue(RegexUtil.isEmail(str));
    }

    @Test
    public void isUsername() {
        String str = "猫猫233";
        Assertions.assertTrue(RegexUtil.isUsername(str));
    }

    @Test
    public void isPassword() {
        String str = "mao.122333";
        Assertions.assertTrue(RegexUtil.isPassword(str));
    }

}