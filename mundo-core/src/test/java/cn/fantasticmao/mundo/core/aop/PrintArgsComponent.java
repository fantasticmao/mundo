package cn.fantasticmao.mundo.core.aop;

import cn.fantasticmao.mundo.core.annotation.PrintArgs;

/**
 * PrintArgsComponent
 *
 * @author maodh
 * @version 1.0
 * @since 2018/6/24
 */
public class PrintArgsComponent {

    @PrintArgs
    void hello(String str, int i) {

    }
}
