package com.mundo.aop;

import com.mundo.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * TimeoutAspectTest
 *
 * @author maodh
 * @since 2017/11/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TimeoutAspectTest {
    @Resource
    private TimeoutComponent timeoutComponent;

    @Test
    public void testTimeoutAspect() {
        timeoutComponent.hello("", null, null);
    }
}