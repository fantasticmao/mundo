package com.mundo.data.aop;

import com.mundo.annotation.Timeout;
import com.mundo.data.annotation.Partition;

import java.util.concurrent.TimeUnit;

/**
 * PartitionComponent
 *
 * @author maodh
 * @since 2017/11/17
 */
public class PartitionComponent implements PartitionInterface {

    @Timeout
    public String find(@Partition Integer id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "maomao";
    }
}
