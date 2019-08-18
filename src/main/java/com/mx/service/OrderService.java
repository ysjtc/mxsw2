package com.mx.service;

import com.mx.pojo.Logistics;
import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;

public interface OrderService {

    //生成订单
    boolean createOrder(Order order);
    boolean createOrderDet(Order_Detail order_detail);

    //查询单个订单
    String  SeeOrder(String trade_number);

    //查看某个用户的全部订单(前台)
    String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid);

    //取消订单
    boolean deleteOrder(Integer o_id);

    /*
    后台
     */


    //查看某个用户的全部订单(后台)
    String SeeAllOrderBackManage(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid);

    //查询所有订单(后台)
    String SeeAllOrders(Integer pageSize, Integer offset, String sort, String sortOrder);

    //查询所有订单中的状态
    String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder,Integer[] status);

    //更新订单状态
    boolean updateOrderStatus(Logistics logistics, Integer status);
}
