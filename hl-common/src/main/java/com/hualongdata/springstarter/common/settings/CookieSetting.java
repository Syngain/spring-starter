package com.hualongdata.springstarter.common.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Web Cookie
 * Created by yangbajing on 16-9-8.
 */
@Component
@ConfigurationProperties(prefix = "com.hualongdata.springstarter.web.cookie")
public class CookieSetting {

    /**
     * 域名
     */
    private String domain;

    /**
     * 路径
     */
    private String path;

    /**
     * 只允许HTTP访问，客户端JS不可读
     */
    private boolean httpOnly;

    /**
     * Cookie存活时间，单位（秒）
     */
    private int maxAge;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
