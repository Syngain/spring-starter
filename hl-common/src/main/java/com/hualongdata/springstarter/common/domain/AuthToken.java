package com.hualongdata.springstarter.common.domain;


import com.hualongdata.exception.HlUnauthorizedException;
import com.hualongdata.util.TimeUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auth Token
 * Immutable对象，当前提供 copyOnTimestamp() 函数来复制并重置创建时间戳
 * Created by yangbajing on 16-9-8.
 */
public class AuthToken {
    private static final int TOKEN_ITEM_LEN = 4;

    private static ThreadLocal<AuthToken> authTokenThreadLocal = new ThreadLocal<>();

    /**
     * 登录用户ID
     */
    public final Long ownerId;

    /**
     * 单位（秒），token创建时时间戳
     */
    public final Long timestamp;

    /**
     * 登录用户源IP
     */
    public final String ip;

    /**
     * 登录用户角色
     */
    public final Set<Integer> roles;

    private AuthToken(Long ownerId, Long timestamp, String ip, Set<Integer> roles) {
        this.ownerId = ownerId;
        this.timestamp = timestamp;
        this.ip = ip;
        this.roles = roles;
    }

    /**
     * 创建AutoTOken
     *
     * @param ownerId 登录ID
     * @param ip      用户访问源IP
     * @param roles   用户角色列表
     * @return 返回新创建 AuthToken
     */
    static public AuthToken create(Long ownerId, String ip, Set<Integer> roles) {
        return new AuthToken(ownerId, TimeUtils.nowSeconds(), ip, roles);
    }

    @Override
    public String toString() {
        return ownerId.toString() +
                ';' +
                timestamp +
                ';' +
                ip +
                ';' +
                roles.stream().map(i -> Integer.toString(i)).reduce((x, y) -> x + ',' + y).orElse("");
    }

    /**
     * 复制并重置创建时间戳
     *
     * @return 新AuthToken实例
     */
    public AuthToken copyOnTimestamp() {
        return new AuthToken(this.ownerId, TimeUtils.nowSeconds(), this.ip, this.roles);
    }

    /**
     * 从字符串中解析AuthToken
     *
     * @param str 待解析字符串，此寡不敌众串要求以英文分号分隔，格式如下：[ownerId]:[timestamp]:[ip]:[roles]。
     *            其中roles以英文逗号分隔字符串
     * @return 新创建AuthToken实例
     */
    public static AuthToken fromString(String str) {
        if (StringUtils.isEmpty(str))
            throw new HlUnauthorizedException("认证Token不存在");

        String[] items = str.split(";", TOKEN_ITEM_LEN);
        if (items.length != TOKEN_ITEM_LEN)
            throw new HlUnauthorizedException("认证Token无效");

        Long ownerId = Long.valueOf(items[0]);
        Long timestamp = Long.valueOf(items[1]);
        String ip = items[2];
        Set<Integer> roles = Stream.of(items[3].split(",")).filter(s -> !s.isEmpty()).map(Integer::valueOf).collect(Collectors.toSet());
        return new AuthToken(ownerId, timestamp, ip, roles);
    }

    /**
     * 从ThreadLocal中获取AuthToken实例
     *
     * @return 返回AuthToken的Optional包装对象
     */
    public static Optional<AuthToken> getFromThreadLocal() {
        return Optional.ofNullable(authTokenThreadLocal.get());
    }

    /**
     * 从ThreadLocal中获取或设置AuthToken
     *
     * @param token AuthToken实例
     * @return 当ThreadLocal中为空是，将token存入并返回；否则直接返回ThreadLocal中已存在的
     */
    public static AuthToken getAndSetFromThreadLocal(AuthToken token) {
        AuthToken authToken = authTokenThreadLocal.get();
        if (authToken == null) {
            authTokenThreadLocal.set(token);
            authToken = token;
        }
        return authToken;
    }

    /**
     * 从ThreadLocal中删除AuthToken实例
     */
    public static void removeOnThreadLocal() {
        authTokenThreadLocal.remove();
    }
}
