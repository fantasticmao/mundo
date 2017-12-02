package com.mundo.data.aop;

import com.mundo.data.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * UserRepositoryTest
 *
 * @author maodh
 * @since 2017/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void userSave1() {
        User user = new User("mao1", "mao.122333");
        userRepository.save(user);
    }

    @Test
    public void userSave2() {
        User user = new User("mao2", "mao.122333");
        userRepository.save(user);
    }
}
