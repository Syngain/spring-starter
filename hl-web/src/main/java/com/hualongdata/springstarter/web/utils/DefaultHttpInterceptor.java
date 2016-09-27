package com.hualongdata.springstarter.web.utils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认HTTP拦截器
 * Created by yangbajing on 16-9-23.
 */
public class DefaultHttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Expires", "-1");
        response.addHeader("Cache-Control", "no-cache");
        return true;
    }
}
