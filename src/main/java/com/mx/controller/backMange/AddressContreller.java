package com.mx.controller.backMange;/*
@author 郭子雄
@description 
*/

import com.mx.pojo.Address;
import com.mx.pojo.Page;
import com.mx.service.AddressService;
import com.mx.service.UserService;
import com.mx.utils.Encoding.userCheck;
import com.mx.utils.Validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(value="/Address")
public class AddressContreller {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    /*用户管理下的收货人模块*/
    @RequestMapping (value="/addAddr")
    public String addAddress(
            @Valid Address address,
            BindingResult result,
            Model model,
            HttpSession session
    ){
        try{
            if (UserValidator.checkError(result,session)) {
                model.addAttribute("info","请勿非法测试！");
                return "frontShow/personal/login";
            }else{
                int uId=userService.getUserIdByname((String)session.getAttribute("USER_ID"));
                address.setuId(uId);
                /*入库前的检验*/
                if(userCheck.CheckAddaddr(address)){
                    model.addAttribute("info","数据输入格式错误或未输入数据！");
                    return "frontShow/personal/addAddr";
                }else {
                    /*入库*/
                    addressService.Addaddress(address);
                }
                return "frontShow/personal/addAddr";
        }}catch (Exception e){
            e.printStackTrace();
            return "frontShow/personal/login";
        }
    }

    @ResponseBody
    @RequestMapping (value="/getAlladdress")
    public Object getAlladdress(
            @RequestBody Page page,
            HttpSession session
    ){
        int uId=userService.getUserIdByname((String)session.getAttribute("USER_ID"));
        Map map=addressService.getAlladdress(uId,page);
        return map;
    }

}
