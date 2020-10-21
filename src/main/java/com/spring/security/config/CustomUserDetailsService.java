package com.spring.security.config;

import com.spring.security.dao.SysFunctionDao;
import com.spring.security.dao.SysRoleDao;
import com.spring.security.dao.SysUserDao;
import com.spring.security.entity.SysRole;
import com.spring.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcs
 * @ClassName CustomUserDetailsService
 * @description
 * @date 2020/10/19 15:41
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    SysRoleDao sysRoleDao;
    @Autowired
    BCryptPasswordEncoder bryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.findByUserName(username);
        if (!StringUtils.isEmpty(sysUser)){
            List<SysRole> roles = sysRoleDao.findByUserId(sysUser.getId());
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (SysRole role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new User(sysUser.getUserName(),bryptPasswordEncoder.encode(sysUser.getPassWord()),authorities);
        }else {
            throw  new UsernameNotFoundException("用户: " + username + " do not exist!");
        }

    }
}
