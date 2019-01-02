package com.mundo.data.partition;

import com.mundo.data.SpringTest;
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
    @Resource
    private UserRepository userRepository;

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

    @Test
    public void test2() {
        System.out.println(userRepository.findTop(1));
        System.out.println(userRepository.findTop(2));
        System.out.println(userRepository.findTop(3));
        System.out.println(userRepository.findTop(4));
        System.out.println(userRepository.findTop(null));
    }
}