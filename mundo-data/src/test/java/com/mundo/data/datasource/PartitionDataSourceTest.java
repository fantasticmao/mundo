package com.mundo.data.datasource;

import com.mundo.data.SpringTest;
import com.mundo.data.UserRepository;
import com.mundo.data.partition.PartitionDataSource;
import com.mundo.data.partition.PartitionSeedContext;
import org.junit.Test;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PartitionDataSourceTest
 *
 * @author maodh
 * @since 2018/7/25
 */
public class PartitionDataSourceTest extends SpringTest {
    @Resource
    private PartitionDataSource partitionDataSource;

    @Test
    public void test1() throws SQLException {
        PartitionSeedContext.push(2);

        final String sql = "SELECT * FROM test_user";
        try (Connection connection = partitionDataSource.getConnection();
             PreparedStatement prep = connection.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
            }
        }
    }

    @Resource
    private UserRepository userRepository;

    @Test
    public void test2() {
        System.out.println(userRepository.findTop(1));
        System.out.println(userRepository.findTop(2));
    }
}