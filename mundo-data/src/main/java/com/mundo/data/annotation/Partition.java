package com.mundo.data.annotation;

import java.lang.annotation.*;

/**
 * Partition
 * 1. 仅支持获取方法的第一个 <code>@Partition</code> 注解标志的参数，用以计算 PartitionSeed 数值
 * 2. 支持参数类型为 ? extends AbstractPartitionDomain || Byte || Short || Integer || Long
 *
 * @author maodh
 * @since 2017/11/14
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Partition {

}
