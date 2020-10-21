package com.spring.security.dao;

import com.spring.security.entity.SysRole;

import java.util.List;

/**
 * @author wcs
 * @ClassName SysRoleDao
 * @description
 * @date 2020/10/21 15:38
 */
public interface SysRoleDao {
     public List<SysRole> findByUserId(int userId);
}
