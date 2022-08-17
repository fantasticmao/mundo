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
    private UserRepository userRepository;

    @Test
    public void findById() {
        Optional<UserRepository.User> userOptional = userRepository.findById(1);
        Assertions.assertTrue(userOptional.isPresent());
        Assertions.assertEquals("Tom", userOptional.get().getName());
    }

}
