package com.mx.mapper;

import com.mx.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    List<Article>queryByTitle(@Param("title") String title);

    Article queryArticle(@Param("aId")Integer aId);

    List<Article> queryArticleByuId(@Param("uId")Integer uId);

    int add(Article article);


    int delete(@Param("aId") Integer aId);
    List<Article> myPraiseArticle(@Param(("aId")) Integer aId);
    List<Article> queryByctgr(@Param("acId") Integer acId);
    int addArticle(String title,String content,Integer uId,Integer acId);
    Integer articlePraise(@Param("aId") Integer aId);
    Integer ifPraised(@Param("aId") Integer aId,@Param("uId") Integer uId);
    void PVRaise(@Param("aId") Integer aId);
    int addAP(@Param("aId") Integer aId,@Param("uId") Integer uId);
}