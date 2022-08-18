package cn.fantasticmao.mundo.data.jdbc;

import cn.fantasticmao.mundo.data.SpringTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * RoutingDataSourceTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/7/25
 */
@Disabled
public class RoutingDataSourceTest extends SpringTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void findUserById() {
        UserRepository.User user = userRepository.findUserById(3);
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Annie", user.getName());
    }

    @Test
    public void findBob() {
        UserRepository.User user = userRepository.findBob();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Bob", user.getName());
    }

    @Test
    public void findFirst() {
        UserRepository.User user = userRepository.findFirst();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Tom", user.getName());
    }

}
