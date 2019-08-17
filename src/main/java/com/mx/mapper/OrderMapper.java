package com.mx.mapper;

import com.mx.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    //生成订单
    int createOrder(Order order);

    //查询单个订单
    List<Order>  SeeOrder(@Param("trade_number") Integer trade_number);

    //查询某个人的全部订单
    List<Order> SeeAllOrder(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("uid") Integer uid);

    //查询某个人的全部订单的数量
    int AllOrderCount(@Param("uid") Integer uid);

    //查询某个人的全部订单中的状态
    List<Order>  QueryAllOrderStatus(@Param("pageSize")int pageSize,@Param("offset") int offset,@Param("sort") String sort,@Param("sortOrder") String sortOrder,@Param("status") Integer status, @Param("uid") Integer uid);

    //查询某个人的全部订单中的状态的数量
    int AllOrderStatusCount(@Param("status") Integer status, @Param("uid") Integer uid);

    //取消订单
    boolean deleteOrder(@Param("o_id") Integer o_id);
}