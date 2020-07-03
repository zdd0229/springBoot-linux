package com.z;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.z.dao")
public class SpringBootLinux {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootLinux.class,args);
    }

}
