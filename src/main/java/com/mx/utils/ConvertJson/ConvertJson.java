package com.mx.utils.ConvertJson;
import com.mx.pojo.Item;
import com.mx.pojo.Item_Pic;

import java.util.List;

public class ConvertJson {

    public static String Convert(int count,List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
//        for( Item obj : list){
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).toJson() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
//        System.out.println(str);
        return str;
    }

    //获取item表中除itemid外的所有字段
    public static String UpdateConvertItem(List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).UpdateToJson() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1);
        System.out.println("UpdateConvertItem----"+str);
        return str;
    }

    //将上面的item数据与获取的itempic拼接成json字符串
    public static String UpdateConvertPath(String item,List<Item_Pic> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder();
        String str=null;
        jsonStrAll.append(item+"\""+ "item_pic"+"\""+":[");
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+list.get(i).getPicPath() +"\""+",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
        System.out.println("item+path:"+str);
        return str;
    }


  //将获取的itempic拼接成json字符串
    public static String ConvertPicPath(List<Item_Pic> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
//        jsonStrAll.append();
            for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append("\""+ list.get(i).getIpId()+"\""+":"+"\""+list.get(i).getPicPath() +"\""+",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"}";
//        System.out.println("itemPath:"+str);
        return str;
    }


    //分页
    public static int getIndex(int pageNum,int pageSize){
        return (pageNum>0)? (pageNum-1)*pageSize:0;
    }


    //获取item表中除itemid外的所有字段
    public static String FrontConvertItem(List<Item> list) {
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str=null;
        for (int i=0;i<list.size();i++){
            //把你要拼接的字段放进去
            jsonStrAll.append(list.get(i).FrontToJson() +",");
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1);
        System.out.println("ConvertItem----"+str);
        return str;
    }

}
