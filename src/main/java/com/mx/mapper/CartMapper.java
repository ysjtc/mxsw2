package com.mx.mapper;

import com.mx.pojo.Cart;

public interface CartMapper {
    int insert(Cart record);

    int insertSelective(Cart record);
}