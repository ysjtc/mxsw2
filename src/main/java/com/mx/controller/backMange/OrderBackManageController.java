package com.mx.controller.backMange;

import com.mx.service.OrderService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/BackManageOrder")
public class OrderBackManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    /**
     *      -------接受的参数
     *      1.商品id  2.商品数量
     *      5.收货地址的id  6.订单名称 7.备注
     *
     *
     1--订单名称（当前登陆id+time）<‘name’是用户登陆用的id，还有一个‘uname’，他是用户的昵称，还有一个uid，是用于用户表自增的id>
     2--商品id
     3--商品数量
     4--获取session中的userid（name）
     5--备注
     */



    //查看单个订单
    @ResponseBody
    @RequestMapping("/seeOrder")
    public String seeOrder(Integer trade_number,HttpSession session){
        System.out.println(trade_number);
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "redirect:/SuperAdmin/ToLogin";
        }else {
            try{
                String orderList=orderService.SeeOrder(trade_number);
                return orderList;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/frontShow/errorPage/error";
            }
        }
    }


    //查看某个用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrder")
    public String seeAllOrder(@RequestBody String param,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "redirect:/SuperAdmin/ToLogin";
        }else {
            try{
                int u_id=userService.getUserIdByname((String)session.getAttribute("SUPERADMIN_ID"));
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String OrderList = orderService.SeeAllOrder(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,u_id);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/frontShow/errorPage/error";
            }
        }

    }


    //查看某个用户的全部订单的状态
    @ResponseBody
    @RequestMapping("/seeAllOrderStatus")
    public String seeAllOrderStatus(@RequestBody String param,Integer status,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "redirect:/SuperAdmin/ToLogin";
        }else {
            try{
                int u_id=userService.getUserIdByname((String)session.getAttribute("SUPERADMIN_ID"));
                //解析json对象
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String OrderList = orderService.QueryAllOrderStatus(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,status,u_id);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/frontShow/errorPage/error";
            }
        }
    }

    //删除订单（取消订单）
    @ResponseBody
    @RequestMapping("/cancelOrder")
    public String cancelOrder(Integer trade_number, Model model,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "redirect:/SuperAdmin/ToLogin";
        }else {
            try{
            if (trade_number != null) {
                boolean is = orderService.deleteOrder(trade_number);
                if (is) {
                    model.addAttribute("deleteInfo", "删除成功!");
                    return truejson;
                } else {
                    model.addAttribute("deleteInfo", "删除失败!");
                    return falsejson;
                }
            } else return falsejson;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/frontShow/errorPage/error";
            }
        }
    }


    //更新订单
    @ResponseBody
    @RequestMapping("/updateOrder")
    public String updateOrder(Integer trade_number, Model model,HttpSession session){
      return null;
    }
}
