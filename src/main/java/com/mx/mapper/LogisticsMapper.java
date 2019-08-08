package com.mx.mapper;

import com.mx.pojo.Logistics;

public interface LogisticsMapper {
    int insert(Logistics record);

    int insertSelective(Logistics record);
}