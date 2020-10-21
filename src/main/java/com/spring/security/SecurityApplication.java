package com.spring.security;

import com.spring.security.common.browser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={"com.spring.security"})
@MapperScan("com.spring.security.dao")
public class SecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        browser bean = context.getBean(browser.class);
        System.out.println(bean);
    }


}
