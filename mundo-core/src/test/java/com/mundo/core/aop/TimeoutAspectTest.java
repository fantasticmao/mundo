package com.mundo.core.aop;

import com.mundo.core.ApplicationTest;
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
    public void testTimeoutAspect1() {
        timeoutComponent.hello1();
    }

    @Test
    public void testTimeoutAspect2() {
        timeoutComponent.hello2("");
    }

    @Test
    public void testTimeoutAspect3() {
        timeoutComponent.hello3("", null, null);
    }
}