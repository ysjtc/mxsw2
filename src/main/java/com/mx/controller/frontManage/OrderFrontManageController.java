package com.mx.controller.frontManage;

import com.mx.pojo.Address;
import com.mx.pojo.Order;
import com.mx.pojo.Order_Detail;
import com.mx.pojo.User;
import com.mx.service.AddressService;
import com.mx.service.ItemsService;
import com.mx.service.OrderService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private  ItemsService itemsService;

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
        System.out.println("itemid:"+item_id+"count:"+count+"address_id:"+address_id+"oName="+oName+"-----note="+note+"uid:"+session.getAttribute("USER_ID"));
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            if (count!=null&&count>0&&oName!=null&&!oName.equals("")&&address_id!=null&&address_id>0) {
                int u_id = userService.getUserIdByname(uname);
                //订单创建时间
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String create_time = dateFormat.format(new Date());
                System.out.println("订单创建时间：" + create_time);
                //订单编号(自动生成)
                StringBuilder trade_number = new StringBuilder();
                trade_number.append("mx" + new Date().getTime());
//            System.out.println("订单创建时间:::::::"+trade_number);  //mx1566050992765//mx1566051018661
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

                order.setuId(user);
                order.setoName(oName);
                order.setAddress(address);
                order.setCreateTime(create_time);
                order.setNumber(String.valueOf(trade_number));
                order.setoStatus("0");
                order.setNote(note);


                try {
                    //数据插入订单表
                    boolean createOrder = orderService.createOrder(order);

                    order_detail.setoId(order.getoId());
                    System.out.println(order.getoId());
                    order_detail.setCount(count);
                    order_detail.setItemId(item_id);
                    Float price = itemsService.queryItemsPriceByItemId(item_id);
//                System.out.println("price:"+price);
                    Float totalPrice = count * price;
//                System.out.println("totalPrice:"+totalPrice);
                    order_detail.setTotalPrice(totalPrice);
                    //数据插入订单详情表
                    boolean createOrderDet = orderService.createOrderDet(order_detail);

                    if (createOrder && createOrderDet) {
                        return truejson;
                    } else {
                        return falsejson;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return falsejson;
                }
            }else {
                System.out.println("非法的传入数据");
                return falsejson;
            }
        }
    }


    //查看单个订单
    @ResponseBody
    @RequestMapping("/seeOrder")
    public String seeOrder(String trade_number, HttpSession session, HttpServletResponse response){
        System.out.println(trade_number);
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
                String orderList=orderService.SeeOrder(trade_number);
                return orderList;
            }catch (Exception e){
                e.printStackTrace();
                return "{\"result\":false,\"isLogin\":false}";
            }
        }
    }


    //查看某个用户的全部订单
    @ResponseBody
    @RequestMapping("/seeAllOrder")
    public String seeAllOrder(@RequestBody String param, HttpSession session){
        System.out.println(param);
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
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
                //重定向到404
                return "{\"result\":false,\"isLogin\":false}";
            }

        }

    }


    //删除订单（取消订单）
    @ResponseBody
    @RequestMapping("/cancelOrder")
    public String cancelOrder(Integer oId, Model model,HttpSession session){
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //当前登陆的用户id
        String uname=(String)session.getAttribute("USER_ID");
        if (uname==null||uname.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
            try{
            if (oId != null) {
                boolean is = orderService.deleteOrder(oId);
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


    //更新订单状态
    @ResponseBody
    @RequestMapping("/updateOrder")
    public String updateOrder(Integer oId, String what,HttpSession session){
        System.out.println(oId+what);
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
