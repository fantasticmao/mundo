package com.mundo.web.mvc;

import com.mundo.web.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JsonpControllerAdviceTest
 *
 * @author maodh
 * @since 16/01/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonpControllerAdviceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testJsonpController() {
        String url = "http://localhost:" + port + "/jsonp?callback=handler";
        String res = template.getForObject(url, String.class);
        System.out.println(res);
    }
}