package com.spring.security;

import com.spring.security.dao.SysFunctionDao;
import com.spring.security.dao.SysUserDao;
import com.spring.security.entity.SysFunction;
import com.spring.security.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysFunctionDao sysFunctionDao;

    @Test
    public void test() {
        List<SysFunction> all = sysFunctionDao.findAll();
        System.out.println(all);
    }

}
