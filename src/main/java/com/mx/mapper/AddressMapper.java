package com.mx.mapper;

import com.mx.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AddressMapper {
    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectAddress(@Param("offset")int offset, @Param("pageSize")int pageSize,
                                @Param("sort")String sort, @Param("sortOrder")String sortOrder,
                                @Param("uId")int uId);

    int getAlladdress(int uId);

    int updateAddress(Address address);

    int deleteAddress(Address address);


}