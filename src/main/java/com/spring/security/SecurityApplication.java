package com.spring.security;

import com.spring.security.common.browser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages={"com.spring.security"})
public class SecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        browser bean = context.getBean(browser.class);
        System.out.println(bean);
    }


}
