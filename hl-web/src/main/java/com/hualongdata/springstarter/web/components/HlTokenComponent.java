package com.hualongdata.springstarter.web.components;

import com.hualongdata.aes.HLBizMsgCrypt;
import com.hualongdata.exception.HlUnauthorizedException;
import com.hualongdata.util.TimeUtils;
import com.hualongdata.springstarter.common.HlConstants;
import com.hualongdata.springstarter.common.domain.AuthToken;
import com.hualongdata.springstarter.common.settings.CookieSetting;
import com.hualongdata.springstarter.common.settings.SessionSetting;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by yangbajing on 16-9-8.
 */
@Component
public class HlTokenComponent {

    private final CookieSetting cookieSetting;
    private final SessionSetting sessionSetting;
    private final HLBizMsgCrypt hlBizMsgCrypt;

    @Autowired
    public HlTokenComponent(CookieSetting cookieSetting, SessionSetting sessionSetting) {
        this.cookieSetting = cookieSetting;
        this.sessionSetting = sessionSetting;
        this.hlBizMsgCrypt = new HLBizMsgCrypt(sessionSetting.getToken(), sessionSetting.getEncodingAesKey(), sessionSetting.getAppid());
    }

    public Cookie discardCookieFromToken() {
        Cookie cookie = new Cookie(HlConstants.CC_OWNER_TOKEN, "");
        cookie.setDomain(cookieSetting.getDomain());
        cookie.setPath(cookieSetting.getPath());
        cookie.setHttpOnly(cookieSetting.isHttpOnly());
        cookie.setMaxAge(-1);
        return cookie;
    }

    public Cookie createCookieFromToken(AuthToken token) {
        String encryptStr = hlBizMsgCrypt.encrypt(hlBizMsgCrypt.getRandomStr(), token.toString());
        encryptStr = Base64.encodeBase64URLSafeString(encryptStr.getBytes());
        return createCookieFromString(encryptStr);
    }

    private Cookie createCookieFromString(String encryptTokenString) {
        Cookie cookie = new Cookie(HlConstants.CC_OWNER_TOKEN, encryptTokenString);
        if (!StringUtils.isEmpty(cookieSetting.getDomain())) {
            cookie.setDomain(cookieSetting.getDomain());
        }
        cookie.setPath(cookieSetting.getPath());
        cookie.setHttpOnly(cookieSetting.isHttpOnly());
        if (cookieSetting.getMaxAge() > 0) {
            cookie.setMaxAge(cookieSetting.getMaxAge());
        }
        return cookie;
    }

    public AuthToken getAuthTokenFromHttpRequest(HttpServletRequest request) {
        String str = getCookieValue(request, HlConstants.CC_OWNER_TOKEN).orElseThrow(() -> new HlUnauthorizedException("获取Token值失败"));
        str = new String(Base64.decodeBase64(str));
        String decryptMsg = hlBizMsgCrypt.decrypt(str);
        return AuthToken.fromString(decryptMsg);
    }

    private Optional<String> getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return Optional.empty();
        return Stream.of(cookies).filter(cookie -> name.equals(cookie.getName())).findFirst().map(Cookie::getValue);
    }

    public void validationToken(AuthToken token) {
        long dueSeconds = token.timestamp + sessionSetting.getTimeout();
        long nowSeconds = TimeUtils.nowSeconds();
        if (nowSeconds > dueSeconds) {
            throw new HlUnauthorizedException("校验Token: " + HlConstants.CC_OWNER_TOKEN + " 超期");
        }
    }

    public void storeAuthTokenToHttpResponse(HttpServletResponse response, AuthToken authToken) {
        Cookie cookie = createCookieFromToken(authToken.copyOnTimestamp());
        response.addCookie(cookie);
        response.addHeader(HlConstants.CC_OWNER_TOKEN, cookie.getValue());
    }
}
