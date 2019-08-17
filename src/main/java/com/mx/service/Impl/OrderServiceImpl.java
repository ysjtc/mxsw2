package com.mx.service.Impl;

import com.mx.mapper.OrderMapper;
import com.mx.mapper.Order_DetailMapper;
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

    @Autowired
    private Order_DetailMapper order_detailMapper;

    //生成订单号
    @Override
    public boolean createOrder(Order order) {
        System.out.println(order);
        //将订单添加到订单表
        int row=orderMapper.createOrder(order);
        if (row==0){ return false; }
        else return true;
    }

    @Override
    public boolean createOrderDet(Order_Detail order_detail) {
        System.out.println("------------"+order_detail);
        //将订单添加到订单详情表
        int row=order_detailMapper.createOrderDet(order_detail);
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
        System.out.println(uid);
        List<Order> orderlist=orderMapper.SeeAllOrder(pageSize,offset,sort,sortOrder,uid);
        System.out.println("++++++++"+orderlist);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrderCount(uid);
        String str= ConvertJson.ConvertOrder(count,orderlist);
        System.out.println("----"+str);
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
    public boolean deleteOrder(Integer o_id) {
        boolean row= orderMapper.deleteOrder(o_id);
        if (!row){
            return false;
        }else return true;
    }
}
