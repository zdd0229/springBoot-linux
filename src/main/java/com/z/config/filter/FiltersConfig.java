package com.z.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.setName("HttpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }

}
