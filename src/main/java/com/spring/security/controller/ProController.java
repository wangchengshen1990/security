package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("11111");
        return "hello";
    }

    @RequestMapping("/success")
    public String success(){
        System.out.println("11111");
        return "success";
    }
    //https://www.e-learn.cn/topic/3143567
}
