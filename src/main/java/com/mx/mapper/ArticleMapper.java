package com.mx.mapper;

import com.mx.pojo.Article;

public interface ArticleMapper {
    int insert(Article record);

    int insertSelective(Article record);
}