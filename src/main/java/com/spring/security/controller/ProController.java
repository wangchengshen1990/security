package com.spring.security.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author wcs
 * @ClassName ProController
 * @description
 * @date 2020/10/19 16:26
 */
@Controller
public class ProController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello spring boot!";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/my/login")
    public String myLogin(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(principal.toString());
        System.out.println(authorities.toString());
        return "hello";
    }

    @RequestMapping("/success")
    public String success(){
        System.out.println("11111");
        return "hello";
    }

    @RequestMapping({"/logout","/login"})
    public String logout(){
        return "login";
    }

    @RequestMapping({"/db"})
    @ResponseBody
    public String dba(){
        return "dba重要信息，dba和admin可以看";
    }
    @RequestMapping({"/admin"})
    @ResponseBody
    public String admin(){
        return "admin重要信息，只有admin可以看";
    }
    //https://www.e-learn.cn/topic/3143567
}
