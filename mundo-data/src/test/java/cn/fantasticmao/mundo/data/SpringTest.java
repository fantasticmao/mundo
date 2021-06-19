package cn.fantasticmao.mundo.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * SpringTest
 *
 * @author maodh
 * @version 1.0
 * @since 02/05/2018
 */
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SpringTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void spring() {
        Assertions.assertNotNull(applicationContext);
    }
}
