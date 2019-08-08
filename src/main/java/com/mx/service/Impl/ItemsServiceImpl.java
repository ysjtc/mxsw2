package com.mx.service.Impl;

import com.mx.mapper.ItemMapper;
import com.mx.mapper.Item_PicMapper;
import com.mx.pojo.Item;
import com.mx.pojo.Item_Pic;
import com.mx.service.ItemsService;
import com.mx.utils.ConvertJson.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private  ItemMapper itemMapper;

    @Autowired
    private Item_PicMapper item_picMapper;

    @Override
    public boolean addItems(Item item) {
        int row=itemMapper.addItems(item);
        if (row==0){
            return false;
        }else return true;
    }

    @Override
    public String queryItemsByName(int pageSize,int offset,String sort,String sortOrder,String name) {
        List<Item> list=itemMapper.queryItemsByName(pageSize,offset,sort,sortOrder,name);
        //将查询结果转换成json数组
        int count=itemMapper.queryItemsByNameCount(name);
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryItemsByISBN(int pageSize,int offset,String sort,String sortOrder,String isbn) {
        List<Item> list=itemMapper.queryItemsByISBN(pageSize,offset,sort,sortOrder,isbn);
        int count=itemMapper.queryItemsByISBNCount(isbn);
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryAll(int pageSize,int offset,String sort,String sortOrder) {
        List<Item> itemList=itemMapper.queryAll(pageSize,offset,sort,sortOrder);
        System.out.println("lust："+itemList);
//        System.out.println(itemList.size());
        int count=itemMapper.queryItemsCount();
        String str=ConvertJson.Convert(count,itemList);
        return str;
    }

    //查询商品总记录数
    @Override
    public int queryItemsCount() {
        return itemMapper.queryItemsCount();
    }

    @Override
    public boolean updateItemsInfo(Item item) {
        boolean row=itemMapper.updateItemsInfo(item);
        if (!row){
            return false;
        }else return true;
    }

    @Override
    public boolean deleteItem(Integer id) {
        return itemMapper.deleteItem(id);

    }

    @Override
    public String queryItemById(Integer  itemId) {
       System.out.println("传入的itemid:"+itemId);
        List<Item> list=itemMapper.queryItemsById(itemId);
        String item=ConvertJson.UpdateConvertItem(list);
        List<Item_Pic> picPath=item_picMapper.queryByItemID(itemId);
        String itemPath=ConvertJson.UpdateConvertPath(item,picPath);
        return itemPath;
    }



    @Override
    public String queryItemsByPrice(int pageSize,int offset,String sort,String sortOrder,Integer lowPrice, Integer highPrice) {
        List<Item> list=itemMapper.queryItemsByPrice(pageSize,offset,sort,sortOrder,lowPrice,highPrice);
        int count=itemMapper.queryItemsByPriceCount(lowPrice,highPrice);
        String str=ConvertJson.Convert(count,list);
        return str;
    }

    @Override
    public String queryItemsByItemCate(int pageSize,int offset,String sort,String sortOrder,Integer itemCate) {
        List<Item> list=itemMapper.queryItemsByItemCate(pageSize,offset,sort,sortOrder,itemCate);
        int count=itemMapper.queryItemsByItemCateCount(itemCate);
        //将查询结果转换成json数组
        String str=ConvertJson.Convert(count,list);
        return str;
    }
}
