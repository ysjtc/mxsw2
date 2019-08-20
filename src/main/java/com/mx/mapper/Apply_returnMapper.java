package com.mx.mapper;

import com.mx.pojo.Apply_return;
import org.apache.ibatis.annotations.Param;

public interface Apply_returnMapper {

    //退换货申请提交
    int insertApply(Apply_return apply_return);

    //判断表中是否存在相同的oid
    Apply_return repeatApply(@Param("oid") Integer oid);

    //更新申请退换货表的状态
    boolean updateApplyStatus(@Param("oId")Integer oId,@Param("status") Integer status);


}