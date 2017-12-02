package com.mundo.data.aop;

import com.mundo.data.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * UserLogRepositoryTest
 *
 * @author maodh
 * @since 2017/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserLogRepositoryTest {
    @Resource
    private UserLogRepository userLogRepository;

    @Test
    public void testUserLogSave1() {
        UserLog userLog = new UserLog(1, 1, "login");
        userLogRepository.save(userLog);
    }

    @Test
    public void testUserLogSave2() {
        UserLog userLog = new UserLog(2, 1, "login");
        userLogRepository.save(userLog);
    }
}
