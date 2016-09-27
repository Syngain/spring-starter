package com.hualongdata.springstarter.common.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Web Session
 * Created by yangbajing on 16-9-9.
 */
@Component
@ConfigurationProperties(prefix = "com.hualongdata.springstarter.web.session")
public class SessionSetting {

    /**
     * Session超时时间，单位（秒）
     */
    private int timeout;

    /**
     * 访问Token
     */
    private String token;

    /**
     * 加密KEY
     */
    private String encodingAesKey;

    /**
     * 应用ID
     */
    private String appid;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public String toString() {
        return "SessionSetting{" +
                "timeout=" + timeout +
                ", token='" + token + '\'' +
                ", encodingAesKey='" + encodingAesKey + '\'' +
                ", appid='" + appid + '\'' +
                '}';
    }
}
