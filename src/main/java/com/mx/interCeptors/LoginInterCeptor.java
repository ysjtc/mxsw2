package com.mx.interCeptors;/*
@author 郭子雄
@description 用户登录验证拦截器
*/

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterCeptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uId=(String) httpServletRequest.getSession().getAttribute("USER_ID");
        if(uId!=null&&!uId.equals("")){
            return true;
        }else {
            httpServletRequest.setAttribute("msg","请先登录");
            httpServletRequest.getRequestDispatcher("/FrontForward/loginMain").forward(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
