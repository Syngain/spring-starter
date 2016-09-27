package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.domain.SignRequest;
import com.hualongdata.springstarter.data.model.User;

/**
 * Created by yangbajing on 16-9-5.
 */
public interface UserRepositoryCustom {
    /**
     * 注册用户
     *
     * @param signRequest 注册请求
     * @return 注册成功返回 User
     * @throws com.hualongdata.exception.HlBaseException 注册返回抛出异常
     */
    User signup(SignRequest signRequest);

    /**
     * 用户登录
     *
     * @param signRequest 登录请求
     * @return 登录成功返回 User, 失败返回null
     */
    User signin(SignRequest signRequest);
}
