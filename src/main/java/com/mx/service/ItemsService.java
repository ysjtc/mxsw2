package com.mx.service;

import com.mx.pojo.Item;

/**
 * 商品管理
 */

public interface ItemsService {

    //添加商品
    boolean addItems(Item item);

    //查询全部商品
    String queryAll(int pageSize,int offset,String sort,String sortOrder);

    //查询商品总记录数
    int queryItemsCount();

    //通过name查询商品
    String  queryItemsByName(int pageSize,int offset,String sort,String sortOrder,String name);

    //通过ISBN查询商品
    String queryItemsByISBN(int pageSize,int offset,String sort,String sortOrder,String isbn);

    //通过价格区间查询商品
    String queryItemsByPrice(int pageSize,int offset,String sort,String sortOrder,Integer lowPrice, Integer highPrice);

    //通过商品类别查询商品
    String queryItemsByItemCate(int pageSize,int offset,String sort,String sortOrder,Integer itemCate);

    //修改商品信息
    boolean updateItemsInfo(Item item);

    //删除商品
    boolean deleteItem(Integer id);

    //通过itemid查询商品
    String queryItemById(Integer itemid);

}
