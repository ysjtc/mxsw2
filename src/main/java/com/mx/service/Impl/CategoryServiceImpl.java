package com.mx.service.Impl;

import com.mx.mapper.CategoryMapper;
import com.mx.pojo.Category;
import com.mx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Integer> querycid() {
        return categoryMapper.queryCid();
    }

    @Override
    public List<Category> queryAllCategory() {
        List<Category> categoryList=categoryMapper.queryAllCategory();
//        //new一个只有cid的集合
//        List<Category> categories=new ArrayList<>();
//        //new一个Category对象
//        Category cg=new Category();
//        //遍历
//        for (Category category:categoryList) {
//            cg.setcId(category.getcId());
//            cg.setCateName(category.getCateName());
//            categories.add(cg);
//        }
        return categoryList;
    }


}
