package com.mundo.aop;

import com.mundo.annotation.Timeout;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * TimeoutComponent
 *
 * @author maodh
 * @since 2017/11/15
 */
public class TimeoutComponent {

    @Timeout
    public void hello1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Timeout
    public void hello2(String str) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Timeout
    public void hello3(String str, Map<String, Integer> map, int... ints) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
