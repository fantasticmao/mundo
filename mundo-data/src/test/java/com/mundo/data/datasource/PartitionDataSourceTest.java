package com.mundo.data.datasource;

import com.mundo.data.SpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

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
    public void inject() throws SQLException {
        Assert.assertNotNull(partitionDataSource);
        System.out.println(partitionDataSource);
        PartitionSeedContext.push(1);
        Connection connection = partitionDataSource.getConnection();
        System.out.println(connection);
    }
}