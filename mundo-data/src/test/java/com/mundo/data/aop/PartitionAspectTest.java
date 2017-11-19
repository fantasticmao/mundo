package com.mundo.data.aop;

import com.mundo.data.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * PartitionAspectTest
 *
 * @author maodh
 * @since 2017/11/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PartitionAspectTest {
    @Resource
    private BusinessRepository repository;

    @Test
    public void testPartitionAspect() {
        repository.test1(new BusinessRepository.PartitionBean(1));
        repository.test2(2);
        repository.test3(3L);
    }
}
