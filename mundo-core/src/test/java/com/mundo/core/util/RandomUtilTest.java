package com.mundo.core.util;

import com.mundo.core.SpringTest;
import org.junit.Test;

/**
 * RandomUtilTest
 *
 * @author maodh
 * @since 2019/2/18
 */
public class RandomUtilTest extends SpringTest {

    @Test
    public void nextNumber() {
        System.out.println(RandomUtil.nextNumber());
        System.out.println(RandomUtil.nextNumber());
        System.out.println(RandomUtil.nextNumber());
        System.out.println(RandomUtil.nextNumber());
        System.out.println(RandomUtil.nextNumber());
    }

    @Test
    public void nextString() {
        System.out.println(RandomUtil.nextString());
        System.out.println(RandomUtil.nextString());
        System.out.println(RandomUtil.nextString());
        System.out.println(RandomUtil.nextString());
        System.out.println(RandomUtil.nextString());
    }
}