package com.mx.mapper;

import com.mx.pojo.Article_Comment;

public interface Article_CommentMapper {
    int insert(Article_Comment record);

    int insertSelective(Article_Comment record);
}