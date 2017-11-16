package com.mundo.data.domain;

import java.io.Serializable;

/**
 * AbstractPartitionDomain
 *
 * @author maodh
 * @since 2017/11/15
 */
public abstract class AbstractPartitionDomain<PK extends Serializable, SEED extends Number> extends AbstractDomain<PK> {

    abstract SEED getSeed();
}
