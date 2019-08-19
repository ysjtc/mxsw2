package com.mx.service.Impl;

import com.mx.mapper.Apply_returnMapper;
import com.mx.mapper.LogisticsMapper;
import com.mx.mapper.OrderMapper;
import com.mx.mapper.Order_DetailMapper;
import com.mx.pojo.*;
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

    @Autowired
    private LogisticsMapper logisticsMapper;

    @Autowired
    private Apply_returnMapper apply_returnMapper;

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
//        System.out.println("------------"+order_detail);
        //将订单添加到订单详情表
        int row=order_detailMapper.createOrderDet(order_detail);
        if (row==0){ return false; }
        else return true;
    }


    @Override
    public String SeeAllOrder(Integer pageSize, Integer offset, String sort, String sortOrder,Integer uid) {
        System.out.println(uid);
        List<Order> orderlist=orderMapper.SeeAllOrder(pageSize,offset,sort,sortOrder,uid);
//        System.out.println("++++++++"+orderlist);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrderCount(uid);
        if (count==0){
            return "\"result\":false";
        }else {
            String str = ConvertJson.ConvertOrder(count, orderlist);
            System.out.println("----" + str);
            return str;
        }
    }

    @Override
    public boolean deleteOrder(Integer o_id) {
        boolean row= orderMapper.deleteOrder(o_id);
        if (!row){
            return false;
        }else return true;
    }

    @Override
    public boolean updateFrontOrderStatus(Integer oId, Integer oStatus) {
        try{
            boolean Orderstatus=orderMapper.updateOrderStatus(oId,oStatus);
            if (Orderstatus){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean applyOrder(Integer oId, Integer status, String reason) {
        try{
            //实例化一个apply—return对象和order对象
            Apply_return app=new Apply_return();
            Order order=new Order();
            order.setoId(oId);
            app.setOrder(order);
            app.setApplyStatus(0);
            app.setReason(reason);

            //判断用户是否是第一次提交
            //判断oid是不是在表中
            Apply_return repeat= apply_returnMapper.repeatApply(oId);
            System.out.println("rep:"+repeat);
            if (repeat==null){
                //不存在
                int apply=apply_returnMapper.insertApply(app);
                boolean Orderstatus=orderMapper.updateOrderStatus(oId,status);
                if (apply==0&&!Orderstatus){ return false; }
                else return true;
            }else {
                //存在,拒绝重复申请
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            //异常(查不到数据)
            return false;
        }
    }


    /**
     * 后台管理
     */

    @Override
    public String SeeAllOrderBackManage(Integer pageSize, Integer offset, String sort, String sortOrder, Integer uid) {
        System.out.println(uid);
        List<Order> orderlist=orderMapper.SeeAllOrderBackManage(pageSize,offset,sort,sortOrder,uid);
//        System.out.println("++++++++"+orderlist);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrderCount(uid);
        if (count==0){
            return "\"result\":false";
        }else {
            String str = ConvertJson.ConvertOrderBackManage(count, orderlist);
            System.out.println("----" + str);
            return str;
        }
    }


    @Override
    public String SeeAllOrders(Integer pageSize, Integer offset, String sort, String sortOrder) {
        List<Order> orderlist=orderMapper.SeeAllOrders(pageSize,offset,sort,sortOrder);
        System.out.println("++++++++"+orderlist);
        //将查询结果转换成json数组
        int count=orderMapper.AllOrdersCount();
        if (count==0){
            return "\"result\":false";
        }else {
//            int num=orderMapper.queryItemsCountByOrder();
            String str= ConvertJson.ConvertOrderBackManage(count,orderlist);
            System.out.println("----"+str);
            return str;
        }
    }

    @Override
    public String QueryAllOrderStatus(Integer pageSize, Integer offset, String sort, String sortOrder, Integer[] oStatus) {
        List<Order> orderlist=null;
        int count=0;
        //定义一个StringBuilder
        StringBuilder jsonStrAll = new StringBuilder("{");
        String str="";
        for (int i=0;i<oStatus.length;i++)
            //将查询结果转换成json数组
        count=count+orderMapper.AllOrderStatusCount(oStatus[i]);
        jsonStrAll.append("\""+"total"+"\""+":"+count+","+"\""+
                "rows"+"\""+":[");
        for (int i=0;i<oStatus.length;i++) {
            System.out.println("ostatus:::"+oStatus[i]);
            orderlist= orderMapper.QueryAllOrderStatus(pageSize, offset, sort, sortOrder, oStatus[i]);
            for (int j=0;j<orderlist.size();j++){
                //把你要拼接的字段放进去
//                int num=orderMapper.queryItemsCountByOrder(orderlist.get(j).getoId());
                jsonStrAll.append(orderlist.get(j).TOJSONBackManage() +",");
            }
        }
        //把最后的，（逗号）截取掉
        str = jsonStrAll.substring(0, jsonStrAll.length()-1)+"]}";
        System.out.println("QueryAllOrderStatus:"+str);
        return str;
    }

    @Override
    public boolean updateOrderStatus(Logistics logistics, Integer oStatus) {
        try{
            int log=logisticsMapper.addLogistics(logistics);
            boolean Orderstatus=orderMapper.updateOrderStatus(logistics.getoId(),oStatus);
            if (log!=0&& Orderstatus==true){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateApplyStatus(Integer oId, Integer status) {
        try{
            boolean Appstatus=apply_returnMapper.updateApplyStatus(oId,status);
            if (Appstatus){ return true; }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String SeeOrder(String trade_number) {
        try{
            List<Order> orderlist=orderMapper.SeeOrder(trade_number);
            int SeeOrderCount=orderMapper.SeeOrderCount(trade_number);
            if (SeeOrderCount==0){
                return "\"result\":false";
            }else {
                //将查询结果转换成json数组
                String str = ConvertJson.ConvertOrderBackManage(1, orderlist);
                return str;
            }
        }catch (Exception e){
            e.printStackTrace();
            return "\"result\":false";
        }

    }
}
