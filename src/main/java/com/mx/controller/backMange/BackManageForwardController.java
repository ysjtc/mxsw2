package com.mx.controller.backMange;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Forward")
public class BackManageForwardController {
    //此控制器与后台视图相关
    //此控制器用作转发后台部分的视图，到时候拦截器都要拦截Forward下的路径，做登录判断
    //这些都要登录判断的！！

    //查看商品信息
    @RequestMapping("/showItems")
    public String ShowItems(){
        return "backManage/storeManage/showItem";
    }
    //添加商品
    @RequestMapping("/addItems")
    public String addItems(){
        return "backManage/storeManage/addItem";
    }
    //删除商品
    @RequestMapping("/deleteItems")
    public String deleteItems(){
        return "backManage/storeManage/addItem";
    }
    //进入后台查看所有用户页面
    @RequestMapping("showUser")
    public String showUser(){
        return "backManage/userManage/showUser";
    }

}
