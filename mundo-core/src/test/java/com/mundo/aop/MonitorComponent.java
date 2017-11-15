package com.mundo.aop;

import com.mundo.annotation.Monitor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * MonitorComponent
 *
 * @author maodh
 * @since 2017/11/15
 */
@Component
public class MonitorComponent {

    @Monitor
    public void method() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Monitor AOP");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
