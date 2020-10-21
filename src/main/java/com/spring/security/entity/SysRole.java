package com.spring.security.entity;

import java.io.Serializable;

/**
 * @author wcs
 * @ClassName SysRole
 * @description
 * @date 2020/10/21 11:26
 */
public class SysRole implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
