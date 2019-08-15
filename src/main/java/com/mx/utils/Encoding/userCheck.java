package com.mx.utils.Encoding;/*
@author 郭子雄
@description 入库前对用户数据的验证
*/

import com.mx.pojo.Address;
import com.mx.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public class userCheck {
    public static boolean CheckregisterUser(User userData){
        if(userData.getuName()==null||userData.getuName().equals("")||
                userData.getPassword()==null||userData.getPassword().equals("")||
                userData.getEmail()==null||userData.getEmail().equals("")
        )return true;
        String regex="(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~]|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+(?:\\.(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~])|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+)*)@(?:(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+(?:(?:[A-Za-z0-9]*[A-Za-z][A-Za-z0-9]*)(?:[A-Za-z0-9-]*[A-Za-z0-9])?))";
       if(!userData.getEmail().matches(regex))
           return true;
       return false;
    }

    public static boolean CheckupdateUser(User userData, MultipartFile file){

        if(userData.getuName()==null||userData.getuName().equals("")||
                userData.getTel()==null||userData.getTel().equals("")||
                userData.getSex()==null||userData.getSex().equals("")||
                userData.getEmail()==null||userData.getEmail().equals("")||
                file==null
        ){
            return true;
        }

        /*检验邮箱*/
        String regex="(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~]|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+(?:\\.(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~])|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+)*)@(?:(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+(?:(?:[A-Za-z0-9]*[A-Za-z][A-Za-z0-9]*)(?:[A-Za-z0-9-]*[A-Za-z0-9])?))";
        if(!userData.getEmail().matches(regex)) {
            return true;
        }

        /*检验电话号码*/
        String regexNumber = "^1[3|4|5|8][0-9]\\d{8}$";
        if(userData.getTel().length()!=11||!userData.getTel().matches(regexNumber)){
            if(userData.getTel().equals("none")) return false;
            return true;
        }
        return false;
    }

    public static boolean CheckAddaddr(Address address){
        if(address.getName()==null||address.getName().equals("")||
                address.getProvince()==null||address.getProvince().equals("")||
                address.getPostcode()==null||address.getPostcode().equals("")||
                address.getAddr()==null||address.getAddr().equals(""))
            return true;

        /*检验电话号码*/
        String regexNumber = "^1[3|4|5|8][0-9]\\d{8}$";
        if(address.getTel().length()!=11||!address.getTel().matches(regexNumber)){
            return true;
        }
        return false;
    }
}
