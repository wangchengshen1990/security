package com.spring.security.dao;

import com.spring.security.entity.SysFunction;

import java.util.List;

/**
 * @author wcs
 * @ClassName FunctionDao
 * @description
 * @date 2020/10/21 14:56
 */
public interface SysFunctionDao {
    public List<SysFunction> findAll();

    public List<SysFunction> findByUserId(int userId);
}
