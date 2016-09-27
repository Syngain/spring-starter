package com.hualongdata.springstarter.web.utils;

import com.hualongdata.springstarter.common.domain.AuthToken;
import com.hualongdata.springstarter.web.components.HlTokenComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AuthToken认证拦截器
 * Created by yangbajing on 16-9-8.
 */
public class HlTokenAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private HlTokenComponent hlTokenComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthToken authToken = hlTokenComponent.getAuthTokenFromHttpRequest(request);
        hlTokenComponent.validationToken(authToken);
        AuthToken.getAndSetFromThreadLocal(authToken);
        hlTokenComponent.storeAuthTokenToHttpResponse(response, authToken.copyOnTimestamp());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthToken.removeOnThreadLocal();
    }
}
