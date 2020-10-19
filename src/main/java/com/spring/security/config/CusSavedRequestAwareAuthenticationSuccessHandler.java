package com.spring.security.config;
/**
 * @ClassName CusSavedRequestAwareAuthenticationSuccessHandler
 * @description
 * @author wcs
 * @date 2020/10/19 21:43
 */

import com.google.gson.GsonBuilder;
import com.spring.security.common.browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: security
 * @description:
 * @author: wcshen
 * @create: 2020-10-19 21:43
 */
public class CusSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private browser browser;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //Authentication接口封装认证信息

        logger.info("登录成功");

        if ("redirect".equals(browser.getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");

            //将authentication认证信息转换为json格式的字符串写到response里面去
            response.getWriter().write(new GsonBuilder().create().toJson(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
