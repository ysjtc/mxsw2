package com.mx.mapper;

import com.mx.pojo.Vip;

public interface VipMapper {
    int insert(Vip record);

    int insertSelective(Vip record);
}