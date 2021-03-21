package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.SpringTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * PrintArgsAspectTest
 *
 * @author maodh
 * @version 1.0
 * @since 2018/6/24
 */
public class PrintArgsAspectTest extends SpringTest {
    @Resource
    private PrintArgsComponent printArgsComponent;

    @Test
    public void testPrintArgs() {
        printArgsComponent.hello("XuYao", 1314);
    }
}
