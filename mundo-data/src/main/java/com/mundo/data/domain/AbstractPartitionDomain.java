package com.mundo.data.domain;

import java.io.Serializable;

/**
 * AbstractPartitionDomain
 * 计算是数据源时，SEED 将被强制转换成 Long 类型
 *
 * @author maodh
 * @since 2017/11/15
 */
public abstract class AbstractPartitionDomain<ID extends Serializable> extends AbstractDomain<ID> {
    private static final long serialVersionUID = 6018123367595471771L;

    public abstract Number getSeed();
}
