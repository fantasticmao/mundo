package com.mundo.data.datasource;

import com.mundo.data.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * PartitionDataSourceTest
 *
 * @author maodh
 * @since 2018/7/25
 */
public class PartitionDataSourceTest extends SpringTest {
    @Autowired(required = false)
    PartitionDataSource partitionDataSource;

    @Test
    public void inject() {
        System.out.println(partitionDataSource);
        Assert.assertNotNull(partitionDataSource);
    }
}