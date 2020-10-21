package com.spring.security.entity;

import java.io.Serializable;

/**
 * @author wcs
 * @ClassName SysFunction
 * @description
 * @date 2020/10/21 13:57
 */
public class SysFunction implements Serializable {
    private int id;
    private String name;
    private String description;
    private String url;
    private int pid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
