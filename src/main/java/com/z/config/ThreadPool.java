package com.z.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@Async
public class ThreadPool {

    @Bean(name = "asyncServiceExecutor")
    public ThreadPoolExecutor asyncServiceExecutor() {

        return new ThreadPoolExecutor(
                10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

}
