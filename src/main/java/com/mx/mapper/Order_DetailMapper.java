package com.mx.mapper;

import com.mx.pojo.Order_Detail;

public interface Order_DetailMapper {
    int insert(Order_Detail record);

    int insertSelective(Order_Detail record);
}