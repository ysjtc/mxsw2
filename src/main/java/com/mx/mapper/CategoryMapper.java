package com.mx.mapper;

import com.mx.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int insert(Category record);

    int insertSelective(Category record);

    List<Integer> queryCid();

    List<Category> queryAllCategory();

    List<Category> queryItemsItemCateName();
}