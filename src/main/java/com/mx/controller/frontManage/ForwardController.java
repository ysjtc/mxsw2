package com.mx.controller.frontManage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/FrontForward")
public class ForwardController {
    //直接与视图映射的控制器单独放在一个地方，不要写在处理功能请求的控制器那里
    //此控制器与前台展示视图相关

    /*进入登录页面*/
    @RequestMapping(value="/loginMain")
    public String loginMain(){
        return "frontShow/personal/login";
    }

    /*进入个人信息页面*/
    @RequestMapping(value="/personalMain")
    public String personalMain(HttpSession session,Model model){
        return "frontShow/personal/personalMain";
    }
    /*进入Store.jsp页面*/
    @RequestMapping("/store")
    public String store(){
        return "frontShow/store/store";
    }

    /*进入添加收货人页*/
    @RequestMapping("/addAddr")
    public String addAddr(){
        return "frontShow/personal/addAddr";
    }




}
