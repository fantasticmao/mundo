package com.mundo.data.temp;

import com.mundo.core.util.RandomUtil;
import com.mundo.data.ApplicationTest;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * UserRepositoryTest
 *
 * @author maodh
 * @since 15/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    static String randomUsername() {
        StringBuilder randomUsername = new StringBuilder();
        for (int i = 0; i < RandomUtil.nextInt(1, 33); i++) {
            randomUsername.append(RandomUtil.nextString());
        }
        return randomUsername.toString();
    }

    static String md5(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(bytes);
            return MD5Encoder.encode(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String randomPassword() {
        StringBuilder randomPassword = new StringBuilder();
        for (int i = 0; i < RandomUtil.nextInt(8, 32); i++) {
            randomPassword.append(RandomUtil.nextString());
        }
        return randomPassword.toString();
    }

    static String randomEmail() {
        String suffix = "@qq.com";
        StringBuilder randomEmail = new StringBuilder();
        for (int i = 0; i < RandomUtil.nextInt(6, 33 - suffix.length()); i++) {
            randomEmail.append(RandomUtil.nextString());
        }
        return randomEmail.append(suffix).toString();
    }

    static String randomTelephone() {
        StringBuilder randomTelephone = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            randomTelephone.append(RandomUtil.nextInt(0, 10));
        }
        return randomTelephone.toString();
    }

    void saveBatch() {
        for (; ; ) {
            User user = new User();
            user.setUsername(randomUsername());
            user.setNickname(randomUsername());
            user.setPassword(md5(randomPassword().getBytes()));
            user.setEmail(randomEmail());
            user.setTelephone(randomTelephone());
            System.out.println(Thread.currentThread().getName() + " " + userRepository.save(user));
        }
    }


    @Test
    public void testSaveBatch() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
        }
        new Thread(this::saveBatch).start();
        TimeUnit.HOURS.sleep(3);
    }
}
