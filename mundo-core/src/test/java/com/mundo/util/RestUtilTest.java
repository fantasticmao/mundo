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
    public void testGetForObject1() {
        RestUtil.getForObject("http://www.dxy.com", String.class).ifPresent(System.out::println);
    }

    @Test
    public void testGetForObject2() {
        RestUtil.getForObject("https://www.bing.com", String.class).ifPresent(System.out::println);
    }

    @Test
    public void testGetForEntity1() {
        RestUtil.getForEntity("http://www.dxy.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
    }

    @Test
    public void testGetForEntity2() {
        RestUtil.getForEntity("https://www.bing.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
    }

    /**
     * 同 {@link RestAsyncUtilTest#testAnsycGetForEntity3()} 方法作比较
     */
    @Test
    public void testGetForEntity3() {
        long start = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            RestUtil.getForEntity("http://www.dxy.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
        }
        long end = System.nanoTime();
        System.out.println("RestTemplate 同步调用费时：" + (end - start) * Math.pow(10, -9));
    }

}
