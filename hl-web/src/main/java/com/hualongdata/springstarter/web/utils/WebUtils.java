package com.hualongdata.springstarter.web.utils;

import com.hualongdata.springstarter.common.HlConstants;
import com.hualongdata.springstarter.common.settings.CookieSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Created by yangbajing on 16-9-9.
 */
@Component
public class WebUtils {

    @Autowired
    private CookieSetting cookieSetting;

    public String getIpFromRequest(HttpServletRequest request) {
        String ip = request.getHeader(HlConstants.CC_REAL_IP);
        return ip == null ? request.getRemoteAddr() : ip;
    }

    public String findFromHeader(HttpServletRequest request, String headerString) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerString.equalsIgnoreCase(headerName)) {
                return request.getHeader(headerName);
            }
        }
        return "";
    }

    public void addCookie(HttpServletResponse response, String name, String value) {
        Cookie domainCookie = new Cookie(name, value);
        if (!StringUtils.isEmpty(cookieSetting.getDomain())) {
            domainCookie.setDomain(cookieSetting.getDomain());
        }
        domainCookie.setPath(cookieSetting.getPath());
        domainCookie.setMaxAge(cookieSetting.getMaxAge());
        domainCookie.setHttpOnly(cookieSetting.isHttpOnly());
        response.addCookie(domainCookie);
    }

    public void addCookieDisableTimeOut(HttpServletResponse response, String name, String value) {
        Cookie domainCookie = new Cookie(name, value);
        if (!StringUtils.isEmpty(cookieSetting.getDomain())) {
            domainCookie.setDomain(cookieSetting.getDomain());
        }
        domainCookie.setPath(cookieSetting.getPath());
        response.addCookie(domainCookie);
    }

    public void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setDomain(cookieSetting.getDomain());
                    cookie.setPath(cookieSetting.getPath());
                    cookie.setHttpOnly(cookieSetting.isHttpOnly());
                    response.addCookie(cookie);
                }
            }
        }

    }

    /**
     * just return the first result, when exists multiple
     */
    public String getValueByRequest(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
