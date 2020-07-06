package com.z.util;

import org.springframework.context.ApplicationContext;

public class SpringContextUtil {

    /**
     * 上下文对象
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Object getBean(String beanId){
        return applicationContext.getBean(beanId);
    }

}
