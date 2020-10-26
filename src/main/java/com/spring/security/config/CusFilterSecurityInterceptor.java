package com.spring.security.config;

import org.apache.ibatis.jdbc.Null;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wcs
 * @ClassName CusFilterSecurityInterceptor
 * @description 自定义资源拦截filter
 * @date 2020/10/26 15:06
 */
public class CusFilterSecurityInterceptor extends AbstractSecurityInterceptor  implements Filter {
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return  null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
