package com.mx.controller;

import com.mx.pojo.User;
import com.mx.pojo.User_Pic;
import com.mx.service.UserPicService;
import com.mx.service.UserService;
import com.mx.utils.Anno.PreventRepeat;
import com.mx.utils.RandomUser.RandomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value="/User")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserPicService userPicService;



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
                session.setAttribute("USER_ID", recorduser.getuId());
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
            Model model,
            HttpSession session,
            @RequestParam("file") CommonsMultipartFile file
            ){
        if (error.hasErrors()) {
            return "frontShow/errorPage/error";
        }else{
            /*随机生成用户名*/
            user.setName(RandomUser.RandomName());
            while(userService.queryUserByname(user.getName())!=null){
                user.setName(RandomUser.RandomName());
            }
            /*开始创建用户*/
            if (userService.addUser(user)) {
                try {
                   // User_Pic userPic=this.imgUpload(file, request, user);
                    model.addAttribute("user", user);
                    //model.addAttribute("userPic",userPic);
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
    @RequestMapping("/delateuser")
    @PreventRepeat
    public String deleteuser(String name){
        if(userService.deleteUser(name)){
            return "";
        }else {
            return "";
        }
    }

    /*修改用户信息的handle*/
    @RequestMapping("/updateuser")
    @PreventRepeat
    public String updateuser(
        @Valid @ModelAttribute User user,
        Errors error,
        Model model){
        if(error.hasErrors()){
            return "redirect:/jsp/test/update.jsp";
        }else {
            if (userService.updateUser(user)) {
                User useraccept=userService.queryUserByname(user.getName());
                model.addAttribute("user",useraccept);
                return "redirect:/jsp/test/success.jsp";
            } else {
                return "redirect:/jsp/test/update.jsp";
            }
        }
    }

    /*查找所有用户的handle*/
    @ResponseBody
    @RequestMapping("/getAlluser")
    @PreventRepeat
    public Object getAlluser(){

        List<User> users=userService.queryAllUser();
        return users;
    }

    /*查找特定用户的handle*/
    @RequestMapping("/queryuser")
    @PreventRepeat
    public String queryUser(String name,Model model){
        User user=userService.queryUserByname(name);
        model.addAttribute("user",user);
        return "";
    }

    /*注册时图片上传*/

    public User_Pic imgUpload(
                CommonsMultipartFile file,
                HttpServletRequest request,
                User user) throws IOException {
        if(!file.isEmpty()) {
            User_Pic userPic=new User_Pic();
            //文件后缀名
            String suffixName=file.getContentType().substring(file.getContentType().indexOf("/")+1);
            // 文件名
            String newFileName = user.getName()+"."+suffixName;
            // 获得项目的路径
            ServletContext sc = request.getSession().getServletContext();
            // 上传位置
            String path = sc.getRealPath("/user"); // 设定文件保存的目录
            File f = new File(path);
            File f2 = new File(path + newFileName);
        /*判断目录是否存在，不存在则创建*/
            if (!f.exists())
                f.mkdirs();
            if (f2.exists())
                f2.delete();
            userPic.setuId(userService.queryUserByname(user.getName()).getuId());
            if (!file.isEmpty()) {
                /*将图片路径存入数据库*/
                userPic.setUserPath(path + "\\"+newFileName);
                userPicService.addUserPic(userPic);
                /*将图片存入在服务器下的制定路径（虚拟的）*/
                file.transferTo(new File(path + newFileName));
                return userPic;
            }
    }
        return null;
}
}
