package com.mx.controller.frontManage;

import com.mx.pojo.Cart;
import com.mx.pojo.Item;
import com.mx.pojo.User;
import com.mx.service.CartService;
import com.mx.service.CategoryService;
import com.mx.service.ItemsService;
import com.mx.service.UserService;
import com.mx.utils.ConvertJson.JsonToJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/CartFrontManage")
public class CartFrontController {

    //自动注入items
    @Autowired
    private ItemsService itemsService;

    //自动注入User
    @Autowired
    private UserService userService;

    //自动注入Category
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;



    //加入购物车
    @ResponseBody
    @RequestMapping("/addItemToCart")
    public String addItemToCart(Cart cart, Integer item_id,HttpSession session){
        System.out.println("count:"+cart.getCount()+"ItemID::::"+item_id);
        String truejson="{\"result\":true}";
        String falsejson="{\"result\":false}";
        //1.获取uid
        String name=(String)session.getAttribute("USER_ID");
        if (name==null||name.equals("")){
            return "{\"result\":false,\"isLogin\":false}";
        }else {
//            if (cart.getCount()!=null&&cart.getItem().getItemId()!=null){
            if (cart.getCount()!=null&&item_id!=null){
                try{
                    int uid=userService.getUserIdByname(name);
                    if (uid==0){
                        return falsejson;
                    }
                    //实例化一个user
                    User user=new User();
                    user.setuId(uid);
//                    //实例化一个item
                    Item item=new Item();
                    item.setItemId(item_id);

                    cart.setUser(user);
                    cart.setItem(item);

                    System.out.println("cart:"+cart);
                    //由于数据库中uid和itemid没有作为联合主键，所以这里进行判断是否存在同一个用户对同一个商品重复下单
                    boolean repeat=cartService.repeatToCart(uid,item_id);
                    if (repeat){
                        boolean addItemToCart=cartService.addItemToCart(cart);
                        if (addItemToCart){
                            return truejson;
                        }else {
                            return falsejson;
                        }
                    }else return falsejson;
                }catch (Exception e){
                    e.printStackTrace();
                    //重定向到404
                    return falsejson;
                }
            }else {
                return falsejson;
            }
        }
    }

    //查看购物车
    @ResponseBody
    @RequestMapping("/SeeCart")
    public String SeeCart(@RequestBody String param,HttpSession session) {
//        System.out.println("---------"+item_id);
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                String pageSize=String.valueOf(JsonToJsonObject.ToJsonObject(param,"pageSize"));
                String offset=String.valueOf(JsonToJsonObject.ToJsonObject(param,"offset"));
                String sort=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sort"));
                String sortOrder=String.valueOf(JsonToJsonObject.ToJsonObject(param,"sortOrder"));
                String cart=cartService.queryCart(Integer.parseInt(pageSize),Integer.parseInt(offset),sort,sortOrder,uid);
                if (cart==null){
                    return falsejson;
                }else {
                    return cart;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return "{\"result\":false}";
            }
        }
    }

    //修改商品数量
    @ResponseBody
    @RequestMapping("/UpdateItemCount")
    public String UpdateItemCount(Integer cart_id,Integer count,HttpSession session) {
//        System.out.println("---------"+item_id);
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                boolean edit=cartService.editCount(cart_id,count);
                if (edit){
                    return truejson;
                }else return falsejson;
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return falsejson;
            }
        }
    }

    //批量删除
    @ResponseBody
    @RequestMapping("/deleteCartItem")
    public String deleteCartItem(Integer[] cart_id,HttpSession session) {
        String truejson = "{\"result\":true}";
        String falsejson = "{\"result\":false}";
        System.out.println(cart_id.length);
        //1.获取uid
        String name = (String) session.getAttribute("USER_ID");
        if (name == null || name.equals("")) {
            return "{\"result\":false,\"isLogin\":false}";
        } else {
            try {
                int uid=userService.getUserIdByname(name);
                if (uid==0){
                    return falsejson;
                }
                boolean delItem=false;
                for (int i=0;i<cart_id.length;i++){
                    delItem=cartService.deleteCartItem(cart_id[i]);
                }
                if (delItem){
                    return truejson;
                }else return falsejson;
            } catch (Exception e) {
                e.printStackTrace();
                //重定向到404
                return falsejson;
            }
        }
    }
}
