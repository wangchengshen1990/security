package com.spring.security.config;

import com.sun.tracing.ProbeName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcs
 * @ClassName WebSecurityConfig
 * @description
 * @date 2020/10/15 17:57
 */
/***
 *
 * --This is only used if the AuthenticationManagerBuilder has not been populated
 * --and no AuthenticationProviderBean is defined.
 *
 * AuthenticationManagerBuilder
 * AuthenticationProvider 尝试登陆用户，实现该接口自定义authentication
 * UserDetailsService 实现该接口,根据用户名来自定义authentication
 *
 * */

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
//                .antMatchers("/db/**").access("hasAnyRole('ADMIN','DBA')")
                .antMatchers("/db/**").hasAnyRole("ADMIN","DBA")
                .anyRequest().authenticated()//如何请求都需要鉴权
                .and()
                .formLogin()
                    .loginPage("/login")
//
                   //login请求任何请求都可以访问
                .loginProcessingUrl("/authentication/form")
                //配置了successHandler这个就失效了
//                .successForwardUrl("/my/login")
                //与successForwardUrl选一个
//                .defaultSuccessUrl("/success")
                .failureUrl("/login.html")
                .successHandler(cusSavedRequestAwareAuthenticationSuccessHandler())
                .failureHandler(cusSimpleUrlAuthenticationFailureHandler())
                .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandlerImpl())
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login.html")
                    //如果指定了logoutSuccessHandler，logoutSuccessUrl就会失效
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    //注销HttpSession
                    .invalidateHttpSession(true)
                    //退出时执行的，SecurityContextLogoutHandler 是默认最后一个执行的，
                    //使用logoutSuccessHandler和logoutUrl来创建LogoutFilter
                    .addLogoutHandler(new CustomLogoutHandler())
                    //清除cookie
                    .deleteCookies("a")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //ROLE默认是ROLE_开头 可以通过 AccessDecisionManager 实现类AffirmativeBased
        // 中的AccessDecisionVoter的实现类 RoleVoter 的 rolePrefix = "ROLE_" 修改成 NULL;

       auth.inMemoryAuthentication().withUser("user").password(bCryptPasswordEncoder().encode("1234")).roles("USER");
       auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder().encode("1234")).roles("ADMIN");
       auth.inMemoryAuthentication().withUser("dba").password(bCryptPasswordEncoder().encode("1234")).roles("DBA");
       //       auth.jdbcAuthentication().dataSource(null).usersByUsernameQuery("sql").authoritiesByUsernameQuery("sql").passwordEncoder(new BCryptPasswordEncoder());
//       auth.userDetailsService(cusUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }


    @Bean
    public AccessDeniedHandlerImpl accessDeniedHandlerImpl(){
        AccessDeniedHandlerImpl deniedHandler = new AccessDeniedHandlerImpl();
        //设置403页面获取重写AccessDeniedHandler，自定义AccessDeniedHandler返回错误信息给前台
        deniedHandler.setErrorPage("/403.html");
        return deniedHandler;
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){

        GrantedAuthorityDefaults defaults = new GrantedAuthorityDefaults("");
        return defaults;
    }

    @Bean
    public RoleVoter roleVoter(){
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        return roleVoter;
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler cusSimpleUrlAuthenticationFailureHandler(){
        return new CusSimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler cusSavedRequestAwareAuthenticationSuccessHandler(){
        return new CusSavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler cusAuthenticationFailureHandler(){
        return  new CusAuthenticationFailureHandler();
    }


    @Bean
    public AuthenticationSuccessHandler cuserAuthenticationSuccessHandler(){
        return new CuserAuthenticationSuccessHandler();
    }


    @Bean
    public UserDetailsService  cusUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
