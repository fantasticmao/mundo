package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.SpringTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * AssertFalseAspectTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 02/05/2018
 */
public class AssertFalseAspectTest extends SpringTest {
    @Resource
    private AssertFalseComponent assertFalseComponent;

    @Test
    public void testAssertFalse1() {
        assertFalseComponent.test1();
    }

    @Test
    public void testAssertFalse2() {
        assertFalseComponent.test2(99, "Java");
    }

}