package com.mx.controller.backMange;

import com.mx.pojo.Logistics;
import com.mx.service.OrderService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import com.mx.utils.Validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String seeOrder(@RequestBody String param,HttpSession session){
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                System.out.println(param);
                String trade_number=String.valueOf(JsonToJsonObject.ToJsonObject(param,"trade_number"));
                String orderList="";
                if (trade_number!=null&&!trade_number.equals("")){
                    orderList=orderService.SeeOrder(trade_number);
                    return orderList;
                }else
                return "{\"result\":false}";
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false}";
            }
        }
    }


    //查看某个用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrderByuId")
    public String seeAllOrder(@RequestBody String param,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                //解析json对象
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String name=String.valueOf(JsonToJsonObject.ToJsonObject(param,"name"));
                String OrderList = "";
                if (name!=null&&!name.equals("")){
                    int uid=userService.getUserIdByname(name);
                    if (uid==0){
                        return "{\"result\":false}";
                    }else {
                        OrderList=orderService.SeeAllOrderBackManage(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,uid);
                        return OrderList;
                    }
                }else
                    return "{\"result\":false}";
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false}";
            }
        }

    }


    //查看全部用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrders")
    public String seeAllOrders(@RequestBody String param,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                //解析json对象
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String OrderList = orderService.SeeAllOrders(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false}";
            }
        }
    }


    //查看全部用户的全部订单的状态
    @ResponseBody
    @RequestMapping("/seeAllOrderStatus")
    public String seeAllOrderStatus(@RequestBody String param,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                //解析json对象
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String Status=String.valueOf(JsonToJsonObject.ToJsonObject(param,"status"));
                System.out.println(Status);//String a="[1,2]"   String b=a.split("[")[1].split();
//                JSONArray status = new JSONArray(Status);
//                String OrderList = orderService.QueryAllOrderStatus(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,status);
//                return OrderList;
                return null;
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false}";
            }
        }
    }


    //管理员更新订单的状态
    @ResponseBody
    @RequestMapping("/updateOrderStatus")
    public String updateOrderStatus(@Valid Logistics logistics, BindingResult result, Integer status, HttpSession session){
        System.out.println(logistics+"-----"+status);
        String truejson="{\"result\": true }";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        if (session.getAttribute("SUPERADMIN_ID")==null||session.getAttribute("SUPERADMIN_ID").equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                //判断传入的数据是否存在null
                if (Validator.checkErrors(result, session)) {
                    boolean Order = orderService.updateOrderStatus(logistics,status);
                    if (Order){
                        return truejson;
                    }else
                        return falsejson;
                }else return falsejson;
            }catch (Exception e){
                e.printStackTrace();
                return falsejson;
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
