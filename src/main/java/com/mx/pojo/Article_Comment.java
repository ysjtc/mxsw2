package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 文章评论表实体类
 *
 */
public class Article_Comment implements Serializable {
    private Integer acoId;

    private String content;

    private Integer uId;

    private Integer aId;

    private static final long serialVersionUID = 1L;

    public Integer getAcoId() {
        return acoId;
    }

    public void setAcoId(Integer acoId) {
        this.acoId = acoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }
}