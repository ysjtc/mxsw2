package com.mx.pojo;

import java.io.Serializable;
/**
 *
 * 购物车表实体类
 *
 */
public class Cart implements Serializable {
    private Integer userId;

    private Integer itemId;

    private Integer count;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}