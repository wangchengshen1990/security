package com.spring.security.common;
/**
 * @ClassName browser
 * @description
 * @author wcs
 * @date 2020/10/19 22:17
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@program: security
 *@description:
 *@author: wcshen
 *@create: 2020-10-19 22:17
 */

@ConfigurationProperties(prefix="spring.custom")
@Component
public class browser {
    private String  loginType;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "browser{" +
                "loginType='" + loginType + '\'' +
                '}';
    }
}
