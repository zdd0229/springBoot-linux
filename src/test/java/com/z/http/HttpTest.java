package com.z.http;

import com.z.SpringBootLinux;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringBootLinux.class})
public class HttpTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRpc(){
        String baiduHtml = restTemplate.getForObject("http://localhost:31001/student/getall/1/2", String.class);
        System.out.println(baiduHtml);
    }

}
