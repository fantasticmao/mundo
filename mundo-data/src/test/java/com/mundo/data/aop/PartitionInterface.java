package com.mundo.data.aop;

import com.mundo.data.annotation.Partition;

/**
 * PartitionInterface
 *
 * @author maodh
 * @since 2017/11/17
 */
public interface PartitionInterface {

    String find(@Partition Integer id);
}
