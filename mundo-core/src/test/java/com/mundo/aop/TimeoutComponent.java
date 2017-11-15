package com.mundo.aop;

import com.mundo.annotation.Timeout;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TimeoutComponent
 *
 * @author maodh
 * @since 2017/11/15
 */
@Component
public class TimeoutComponent {

    @Timeout
    public void hello(String str, Map<String, Integer> map, int... ints) {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Timeout AOP");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
