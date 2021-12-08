package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.SpringTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * TimeoutAspectTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2017/11/15
 */

public class TimeoutAspectTest extends SpringTest {
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