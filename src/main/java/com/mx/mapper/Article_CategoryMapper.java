package com.mx.mapper;

import com.mx.pojo.Article_Category;

public interface Article_CategoryMapper {
    int insert(Article_Category record);

    int insertSelective(Article_Category record);
}