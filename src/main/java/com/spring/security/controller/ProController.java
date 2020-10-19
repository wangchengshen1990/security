package com.spring.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wcs
 * @ClassName ProController
 * @description
 * @date 2020/10/19 16:26
 */
@RestController
public class ProController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }

    @RequestMapping("/my/login")
    public String str(){
        return null;
    }
    //https://www.e-learn.cn/topic/3143567
}
