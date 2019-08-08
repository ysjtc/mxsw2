package com.mx.mapper;

import com.mx.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

    //添加商品
    int addItems(Item item);

    //通过name查询商品
    List<Item> queryItemsByName(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("name") String name);

    //通过name查询商品记录数
    int queryItemsByNameCount(@Param("name") String name);

    //通过ISBN查询商品
    List<Item> queryItemsByISBN(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("isbn") String isbn);

    //通过ISBN查询商品记录数
    int queryItemsByISBNCount(@Param("isbn") String isbn);

    //查询全部商品
    List<Item>  queryAll(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder);

    //查询商品总记录数
    int queryItemsCount();

    //删除单个商品
    boolean deleteItem(@Param("itemId") Integer itemId);

    //更新商品信息
    boolean updateItemsInfo(Item item);

    //通过价格区间查询商品信息
    List<Item> queryItemsByPrice(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice);

    //通过价格区间查询商品信息个数
    int queryItemsByPriceCount(@Param("lowPrice") Integer lowPrice, @Param("highPrice") Integer highPrice);

    //通过商品类别查询商品
    List<Item> queryItemsByItemCate(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("itemCate")Integer itemCate);

    //通过商品类别查询商品个数
    int queryItemsByItemCateCount(@Param("itemCate")Integer itemCate);

    //通过itemId查询商品
    List<Item> queryItemsById(@Param("itemId") Integer itemId);


}