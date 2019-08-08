package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 文章类型表实体类
 *
 */
public class Article_Category implements Serializable {
    private Integer acId;

    private String name;

    private static final long serialVersionUID = 1L;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}