package com.spring.security.dao;

import com.spring.security.entity.SysUser;

/**
 * @author wcs
 * @ClassName SysUserDao
 * @description
 * @date 2020/10/21 14:31
 */
public interface SysUserDao {
    public SysUser findByUserName(String userName);
}
