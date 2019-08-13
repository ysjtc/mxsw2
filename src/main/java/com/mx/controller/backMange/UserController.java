package com.mx.controller.backMange;

import com.mx.pojo.*;
import com.mx.service.UserPicService;
import com.mx.service.UserService;
import com.mx.service.VipService;
import com.mx.utils.Anno.PreventRepeat;
import com.mx.utils.RandomUser.RandomUser;
import com.mx.utils.UpLoad.UserUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping(value="/User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPicService userPicService;

    @Autowired
    private VipService vipService;


    /*用户登录的handle*/
    @PreventRepeat
    @RequestMapping(value="/login")
    public String login(
            @Valid @ModelAttribute User user,
            Errors error,
            Model model,
            HttpSession session){
        if (!error.hasErrors()) {
            /*判断用户名是否合法*/
            if(user.getName()==null||
                    user.getName().equals("")||
                    user.getName().length()>12||
                    user.getName().length()<6){
                return "frontShow/errorPage/error";
            }
            /*若用户账号与密码都正确，则从数据库拿到用户记录recorduser*/
            User recorduser = userService.login(user);

            /*当用户账号与密码都正确时进入登录成功页面*/
            if (recorduser != null) {
               User_Pic userPic=userPicService.queryById(recorduser.getuId());
                model.addAttribute("user",recorduser);
                model.addAttribute("userpic",userPic);
                session.setAttribute("USER_SESSION", recorduser);
                session.setAttribute("USE_ID", recorduser.getName());
                return "frontShow/personal/personalMain";
            } else {
                return "frontShow/errorPage/error";
            }
        }else{
            return "frontShow/personal/login";
        }
    }

    /*用户注册的handle*/
    @RequestMapping(value="/register",method = RequestMethod.POST)
    @PreventRepeat
    public String register(
            @Valid @ModelAttribute User user,
            Errors error,
            HttpSession session
            ){
        if (error.hasErrors()) {
            return "frontShow/errorPage/error";
        }else{
            /*随机生成用户名*/
            user.setName(RandomUser.RandomName());
            System.out.println(user.getName());
            while(userService.queryUserByname(user.getName())!=null){
                user.setName(RandomUser.RandomName());
            }
            /*开始创建用户*/
            if (userService.addUser(user)) {
                try {
                   /* *//*添加vip信息*//*
                    Vip vip=new Vip();
                    vip.setVipId(userService.getUserIdByname(user.getName()));
                    vip.setScore(0);
                    vipService.AddVip(vip);*/

                    /*添加用户头像信息*/
                    User_Pic userPic=new User_Pic();
                    userPic.setuId(userService.getUserIdByname(user.getName()));
                    userPic.setUserPath("static/upload/userPic/timg.jpg");
                    userPicService.addUserPic(userPic);
                }catch(Exception e){
                    e.printStackTrace();
                }
                return "frontShow/personal/personalMain";
            } else {
                session.setAttribute("STATUE", "账号已存在");
                return "frontShow/errorPage/error";
            }
        }
    }

    /*用户登出的handle*/
    @RequestMapping("/loginout")
    @PreventRepeat
    public String loginout(HttpSession session){
        session.removeAttribute("USER_SESSION");
        session.invalidate();
        return "frontShow/navigation/nav";
    }

    /*删除用户的handle*/
    @RequestMapping("/delateUser")
    @PreventRepeat
    public String deleteuser(String name){
        if(userService.deleteUser(name)){
            return "";
        }else {
            return "";
        }
    }

    /*修改用户信息的handle*/
    @RequestMapping("/updateUser")
    @PreventRepeat
    public String updateuser(
        @Valid @ModelAttribute User user,
        HttpServletRequest request,
        @RequestParam("file") CommonsMultipartFile file,
        Errors error
        )throws Exception{
        if(error.hasErrors()){
            return "frontShow/errorPage/error";
        }else {
            if (userService.updateUser(user)) {

                User_Pic userPic= UserUpload.imgUpload(file,request,user,userService,userPicService);
                UserData useraccept=userService.queryUserByname(user.getName());
                return "";
            } else {
                return "";
            }
        }
    }

    /*查找所有用户的handle*/
    @ResponseBody
    @RequestMapping("/getAlluser")
    public Object getAlluser(
            @RequestBody Page page
    ){
        Map users=userService.queryAllUser(page);
        System.out.println(users);
        return users;
    }

    /*查找特定用户的handle*/
    @ResponseBody
    @RequestMapping("/queryUser")
    @PreventRepeat
    public Object queryUser(String name){
        UserData user=userService.queryUserByname(name);
        return user;
    }






   /* @RequestMapping(value="/regtest",method = RequestMethod.POST)
    public String regtest(
            @Valid @ModelAttribute User user,
            HttpSession session,
            HttpServletRequest request,
            @RequestParam("file") CommonsMultipartFile file
    ){
            *//*开始创建用户*//*
            if (userService.addUser(user)) {
                try {
                    User_Pic userPic= UserUpload.imgUpload(file,request,user,userService,userPicService);
                    session.setAttribute("userPic",userPic.getUserPath());
                }catch(Exception e){
                    e.printStackTrace();
                }
                return "redirect:/jsp/show.jsp";
            } else {
                session.setAttribute("STATUE", "账号已存在");
                return "";
            }
        }*/



}

