package cn.fantasticmao.mundo.data.jdbc;

import cn.fantasticmao.mundo.data.SpringTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Optional;

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
    private UserRepository<Integer> userRepository;

    @Test
    public void findById() {
        Optional<UserRepository.User> userOptional = userRepository.findById(1);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals("Tom", userOptional.get().getName());

        userOptional = userRepository.findById(2);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals("Bob", userOptional.get().getName());

        userOptional = userRepository.findById(3);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals("Annie", userOptional.get().getName());

        userOptional = userRepository.findById(4);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals("Jason", userOptional.get().getName());
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
