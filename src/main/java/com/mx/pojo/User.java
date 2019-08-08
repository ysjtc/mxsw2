package com.mx.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 *
 * User表实体类
 */
public class User implements Serializable {

    private Integer uId;


    private String name;

    @NotBlank(message="密码不能为空")
    @Length(min=6,max =18,message = "密码长度必须在6到18位之间")
    private String password;


    @Pattern(regexp = "^[A-Za-z0-9][\\w\\-\\.]{3,12}@([\\w\\-]+\\.)+[\\w]{2,3}$", message = "该邮箱不合法")
    private String email;

    //@NotBlank(message="用户昵称不能为空")
    private String uName;//昵称

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email == null ? null : email.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uName='" + uName + '\'' +
                '}';
    }
}