package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.SpringTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * PrintArgsAspectTest
 *
 * @author fantasticmao
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
