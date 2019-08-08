package com.mx.mapper;

import com.mx.pojo.Address;


public interface AddressMapper {
    int insert(Address record);

    int insertSelective(Address record);
}