package com.mx.mapper;

import com.mx.pojo.Order;

public interface OrderMapper {
    int insert(Order record);

    int insertSelective(Order record);
}