package com.spring.security.config;

import com.google.gson.GsonBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wcs
 * @ClassName CustomLogoutHandler
 * @description  //自定义退出处理器
 * @date 2020/10/16 11:22
 */
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            System.out.println("CustomLogoutHandler-->注销后的处理逻辑："+new GsonBuilder().create().toJson(authentication));
            request.getRequestDispatcher("/logout").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
