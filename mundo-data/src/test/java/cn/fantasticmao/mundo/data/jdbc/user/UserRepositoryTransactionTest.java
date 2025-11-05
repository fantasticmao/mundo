package cn.fantasticmao.mundo.data.jdbc.user;

import cn.fantasticmao.mundo.data.jdbc.RoutingTransactionTemplate;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * UserRepositoryTransactionTest
 *
 * @author fantasticmao
 * @version 1.0.7
 * @since 2022-09-06
 */
@SpringBootTest(classes = UserDataSourceConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTransactionTest {
    @Resource
    private UserRepository<Integer> userRepository;
    @Resource
    private RoutingTransactionTemplate transactionTemplate;

    @Test
    public void save() {
        final int userId = 4;
        transactionTemplate.executeWithoutResult(userId, status -> {
            Optional<UserRepository.User> userOptional = userRepository.findById(userId);
            Assertions.assertTrue(userOptional.isPresent());

            UserRepository.User user = userOptional.get();
            Assertions.assertEquals("Jason", user.getName());

            user.setName("Jack");
            userRepository.save(user);

            userOptional = userRepository.findById(userId);
            Assertions.assertTrue(userOptional.isPresent());
            Assertions.assertEquals("Jack", userOptional.get().getName());

            status.setRollbackOnly();
        });
    }

    @Test
    public void update() {
        final int userId = 3;
        UserRepository.User user = transactionTemplate.execute(userId, status -> {
            Optional<UserRepository.User> userOptional = userRepository.findById(userId);
            Assertions.assertTrue(userOptional.isPresent());

            UserRepository.User tempUser = userOptional.get();
            Assertions.assertEquals("Annie", tempUser.getName());

            tempUser.setName("Anna");
            userRepository.save(tempUser);
            return tempUser;
        });

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Anna", user.getName());

        // rollback manually
        user.setName("Annie");
        userRepository.save(user);
    }
}
