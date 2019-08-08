package com.mx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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




}
