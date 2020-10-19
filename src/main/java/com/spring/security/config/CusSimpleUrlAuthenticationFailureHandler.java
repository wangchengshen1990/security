package com.spring.security.config;
/**
 * @ClassName CusSimpleUrlAuthenticationFailureHandler
 * @description
 * @author wcs
 * @date 2020/10/19 22:46
 */

import com.google.gson.GsonBuilder;
import com.spring.security.common.browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@program: security
 *@description:
 *@author: wcshen
 *@create: 2020-10-19 22:46
 */
public class CusSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private browser browser;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        if("redirect".equals(browser.getLoginType())){
            logger.info("登陆失败");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new GsonBuilder().create().toJson(exception));
        }
        else{
            super.onAuthenticationFailure(request, response, exception);
        }

    }
}
