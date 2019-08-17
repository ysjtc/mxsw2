package com.mx.controller.frontManage;

import com.mx.pojo.Address;
import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;
import com.mx.pojo.User;
import com.mx.service.AddressService;
import com.mx.service.OrderService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/FrontManageOrder")
public class OrderFrontManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    /**
     *      -------接受的参数
     *      1.商品id  2.商品数量
     *      5.收货地址的id  6.订单名称 7.备注
     *订单状态（0表示未处理，1未支付，2待收货中，4收货完成，3退货中，，5退货完成）
     *
     1--订单名称（当前登陆id+time）<‘name’是用户登陆用的id，还有一个‘uname’，他是用户的昵称，还有一个uid，是用于用户表自增的id>
     2--商品id
     3--商品数量
     4--获取session中的userid（name）
     5--备注
     */

    //1生成订单
    @ResponseBody
    @RequestMapping("/createOrder")
    public String createOrder(Integer item_id,Integer count,String oName, String note,Integer address_id, HttpSession session){
        System.out.println("oName="+oName+"-----note="+note+"uid:"+session.getAttribute("USER_ID"));
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            int u_id = userService.getUserIdByname(String.valueOf(uname));
            //订单创建时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String create_time = dateFormat.format(new Date());
            System.out.println("订单创建时间：" + create_time);
            //订单编号(自动生成)
            StringBuilder trade_number = new StringBuilder();
            trade_number.append("mx" + create_time);
            //实例化一个Order对象和一个Order_detail对象
            Order order = new Order();
            Order_Detail order_detail = new Order_Detail();
            //实例化一个User对象
            User user = new User();
            user.setuId(u_id);
            //实例化一个Address对象
            //当前登陆的用户的地址表id
            Address address = new Address();
            address.setAddId(address_id);

            order.setuId(user.getuId());
            order.setAddress(address);
            order.setCreateTime(create_time);
            order.setNumber(String.valueOf(trade_number));
            order.setNote(note);

            order_detail.setCount(count);
            order_detail.setItemId(item_id);

            try {
                boolean createOrder = orderService.createOrder(order, order_detail);
                if (createOrder) {
                    return truejson;
                } else {
                    return falsejson;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return falsejson;
            }
        }
    }


    //查看单个订单
    @ResponseBody
    @RequestMapping("/seeOrder")
    public String seeOrder(Integer trade_number, HttpSession session, HttpServletResponse response){
        System.out.println(trade_number);
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "redirect:/frontShow/personal/login";
        }else {
            try{
                String orderList=orderService.SeeOrder(trade_number);
                return orderList;
            }catch (Exception e){
                e.printStackTrace();
                try {
                    response.sendRedirect("frontShow/errorPage/error");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return "";
        }
    }


    //查看某个用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrder")
    public String seeAllOrder(@RequestBody String param, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        System.out.println(param);
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
//            return "redirect:/FrontForward/loginMain";
            try {
                response.sendRedirect(request.getContextPath()+"/FrontForward/loginMain");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return "";
        }else {
            try{
                int u_id=userService.getUserIdByname(uname);
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                System.out.println(pageSize+offset+sort+sortOrder);
                String OrderList = orderService.SeeAllOrder(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,u_id);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                try {
                    response.sendRedirect("frontShow/errorPage/error");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return "";
        }

    }
    //查看某个用户的全部订单的状态
    @ResponseBody
    @RequestMapping("/seeAllOrderStatus")
    public String seeAllOrderStatus(@RequestBody String param,Integer status,HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "redirect:/FrontForward/loginMain";
        }else {
            try{
                int u_id=userService.getUserIdByname(uname);
                //解析json对象
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String OrderList = orderService.QueryAllOrderStatus(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,status,u_id);
                return OrderList;
            }catch (Exception e){
                e.printStackTrace();
                return "redirect:/FrontForward/error";
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
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "redirect:/FrontForward/loginMain";
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
                return "redirect:/FrontForward/error";
            }
        }
    }


    //更新订单
    @ResponseBody
    @RequestMapping("/updateOrder")
    public String updateOrder(Integer trade_number, Model model,HttpSession session){
      return null;
    }




    //查询收货人
    @ResponseBody
    @RequestMapping(value = {"/seeAddressee"},produces = "application/json;charset=utf-8")
    public String queryAddressee(HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                int uid=userService.getUserIdByname(uname);
                String uaddr=addressService.getAddress(uid);
                return uaddr;
            }catch (Exception e){
                e.printStackTrace();
                return falsejson;
            }
        }
    }

}
