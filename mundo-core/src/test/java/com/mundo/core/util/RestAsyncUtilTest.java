package com.mundo.core.util;

import com.mundo.core.MundoCoreAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * RestAsyncUtilTest
 *
 * @author maodh
 * @since 2018/1/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MundoCoreAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RestAsyncUtilTest {

    @Test
    public void ansycGetForEntity1() throws InterruptedException {
        RestAsyncUtil.ansycGetForEntity("http://www.dxy.com", String.class, entity -> System.out.println(entity.getStatusCode()));
        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void ansycGetForEntity2() throws InterruptedException {
        RestAsyncUtil.ansycGetForEntity("https://www.bing.com", String.class, entity -> System.out.println(entity.getStatusCode()));
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 同 {@link RestUtilTest#getForEntity3()} 方法作比较
     */
    @Test
    public void ansycGetForEntity3() throws InterruptedException {
        long start = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            RestAsyncUtil.ansycGetForEntity("http://www.dxy.com", String.class, entity -> System.out.println(entity.getStatusCode()));
        }
        long end = System.nanoTime();
        System.out.println("AsyncRestTemplate 异步调用费时：" + (end - start) * Math.pow(10, -9));
        TimeUnit.SECONDS.sleep(3);
    }
}
