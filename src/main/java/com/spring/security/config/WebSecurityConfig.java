package com.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wcs
 * @ClassName WebSecurityConfig
 * @description
 * @date 2020/10/15 17:57
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                 //自定义访问权限
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()//如何请求都需要鉴权
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()//login请求任何请求都可以访问
                .and()
                .logout()
                    .logoutUrl("/my/logout")
                    .logoutSuccessUrl("/my/index")
                    //如果指定了logoutSuccessHandler，logoutSuccessUrl就会失效
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    //注销HttpSession
                    .invalidateHttpSession(true)
                    //退出时执行的，SecurityContextLogoutHandler 是默认最后一个执行的，
                    //使用logoutSuccessHandler和logoutUrl来创建LogoutFilter
                    .addLogoutHandler(new CustomLogoutHandler())
                    //清除cookie
                    .deleteCookies("")
                    .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ROLE默认是ROLE_开头 可以通过 AccessDecisionManager 实现类AffirmativeBased
        // 中的AccessDecisionVoter的实现类 RoleVoter 的 rolePrefix = "ROLE_" 修改成 NULL;
       auth.inMemoryAuthentication().withUser("user").password("password").roles("user");
    }
}
