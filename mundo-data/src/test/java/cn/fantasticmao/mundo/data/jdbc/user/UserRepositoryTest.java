package cn.fantasticmao.mundo.data.jdbc.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * UserRepositoryTest
 *
 * @author fantasticmao
 * @version 1.0
 * @since 2018/7/25
 */
@SpringBootTest(classes = UserDataSourceConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTest {
    @Resource
    private UserRepository<Integer> userRepository;

    @Test
    @Rollback
    @Transactional
    public void save() {
        Optional<UserRepository.User> userOptional = userRepository.findById(4);
        Assertions.assertTrue(userOptional.isPresent());

        UserRepository.User user = userOptional.get();
        Assertions.assertEquals("Jason", user.getName());

        user.setName("Jack");
        user = userRepository.save(user);
        Assertions.assertEquals("Jack", user.getName());
    }

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
