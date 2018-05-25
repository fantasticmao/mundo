package com.mundo.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * SpringTest
 *
 * @author maodh
 * @since 02/05/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SpringTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void spring() {
        Assert.assertNotNull(applicationContext);
    }
}
