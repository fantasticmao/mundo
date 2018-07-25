package com.mundo.data;

import com.mundo.data.datasource.PartitionDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringTest
 *
 * @author maodh
 * @since 02/05/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SpringTest {
    @Autowired(required = false)
    PartitionDataSource partitionDataSource;

    @Test
    public void spring() {
        System.out.println(partitionDataSource);
    }
}
