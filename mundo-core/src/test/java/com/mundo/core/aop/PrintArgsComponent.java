package com.mundo.core.aop;

import com.mundo.core.annotation.PrintArgs;

/**
 * PrintArgsComponent
 *
 * @author maodh
 * @since 2018/6/24
 */
public class PrintArgsComponent {

    @PrintArgs
    void hello(String str, int i) {

    }
}
