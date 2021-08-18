package com.z;

import com.z.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.z.dao")
public class SpringBootLinux {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootLinux.class, args);
        SpringContextUtil.setApplicationContext(applicationContext);
    }
}
