package com.mundo.data.aop;

import com.mundo.data.annotation.Partition;
import com.mundo.data.datasource.PartitionSupport;
import com.mundo.data.domain.AbstractPartitionDomain;

/**
 * BusinessRepository
 *
 * @author maodh
 * @since 2017/11/17
 */
public class BusinessRepository implements PartitionSupport {

    public void test1(@Partition PartitionBean bean) {
    }

    public void test2(@Partition Integer i) {
    }

    public void test3(@Partition Long l) {
    }

    public void test4(@Partition String s) {
    }

    public static class PartitionBean extends AbstractPartitionDomain<Integer, Integer> {
        private Integer id;

        public PartitionBean(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getSeed() {
            return id;
        }
    }
}
