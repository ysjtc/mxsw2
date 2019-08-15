package com.mx.service.Impl;

import com.mx.mapper.OrderMapper;
import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;
import com.mx.service.OrderService;
import com.mx.utils.ConvertJson.ConvertJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    //生成订单号
    @Override
    public boolean createOrder(Order order, Order_Detail order_detail) {
        //将订单添加到订单表
        int row=orderMapper.createOrder(order,order_detail);
        if (row==0){ return false; }
        else return true;
    }

    @Override
    public String SeeOrder(Integer trade_number) {
        List orderlist=orderMapper.SeeOrder(trade_number);
        //将查询结果转换成json数组
        String str= ConvertJson.ConvertOrder(1,orderlist);
        return str;
    }

    @Override
    public String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid) {
        List orderlist=orderMapper.SeeAllOrder(pageSize,offset,sort,sortOrder,uid);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrderCount(uid);
        String str= ConvertJson.ConvertOrder(count,orderlist);
        return str;
    }

    @Override
    public String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder, Integer status, Integer uid) {
        List orderlist=orderMapper.QueryAllOrderStatus(pageSize,offset,sort,sortOrder,status,uid);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrderStatusCount(status,uid);
        String str= ConvertJson.ConvertOrder(count,orderlist);
        return str;
    }

    @Override
    public boolean deleteOrder(Integer trade_number) {
        boolean row= orderMapper.deleteOrder(trade_number);
        if (!row){
            return false;
        }else return true;
    }
}
