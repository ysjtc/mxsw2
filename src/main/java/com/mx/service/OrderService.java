package com.mx.service;

import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;

public interface OrderService {

    //生成订单号
    boolean createOrder(Order order, Order_Detail order_detail);

    //查询单个订单
    String  SeeOrder(Integer trade_number);

    //查询所有订单
    String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid);

    //查询所有订单中的状态
    String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder,Integer status,Integer uid);

    //取消订单
    boolean deleteOrder(Integer trade_number);
}
