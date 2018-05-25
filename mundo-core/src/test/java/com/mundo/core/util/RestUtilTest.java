package com.mundo.core.util;

import com.mundo.core.MundoCoreAutoConfiguration;
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
    public void getForObject1() {
        RestUtil.getForObject("http://www.dxy.com", String.class).ifPresent(System.out::println);
    }

    @Test
    public void getForObject2() {
        RestUtil.getForObject("https://www.bing.com", String.class).ifPresent(System.out::println);
    }

    @Test
    public void getForEntity1() {
        RestUtil.getForEntity("http://www.dxy.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
    }

    @Test
    public void getForEntity2() {
        RestUtil.getForEntity("https://www.bing.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
    }

    /**
     * 同 {@link RestAsyncUtilTest#ansycGetForEntity3()} 方法作比较
     */
    @Test
    public void getForEntity3() {
        long start = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            RestUtil.getForEntity("http://www.dxy.com", String.class).ifPresent(entity -> System.out.println(entity.getStatusCode()));
        }
        long end = System.nanoTime();
        System.out.println("RestTemplate 同步调用费时：" + (end - start) * Math.pow(10, -9));
    }

}
