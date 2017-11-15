package com.mundo.aop;

import com.mundo.annotation.MethodLogger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * MethodLoggerCompont
 *
 * @author maodh
 * @since 2017/11/15
 */
@Component
public class MethodLoggerComponent {

    @MethodLogger
    public void method() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("AOP Logger");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
