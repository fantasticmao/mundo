package com.mundo.util;

import com.mundo.MundoCoreAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RestUtilTest
 *
 * @author maodh
 * @since 2017/12/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MundoCoreAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RestUtilTest {

    @Test
    public void rest() throws InterruptedException {
        String str = RestUtil.getForObject("https://www.bing.com", String.class);
        System.out.println(str);
    }
}
