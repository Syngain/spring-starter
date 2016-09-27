package com.hualongdata.springstarter.business.service;

import com.hualongdata.exception.HlBadRequestException;
import com.hualongdata.springstarter.data.domain.SignRequest;
import com.hualongdata.springstarter.data.model.User;
import com.hualongdata.springstarter.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by yangbajing on 16-9-6.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public UserService(UserRepository userRepository,
                       RestTemplateBuilder restTemplateBuilder) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 找到用户返回 User，否则返回 null
     */
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }


    public User signup(SignRequest signRequest) {
        if (!signRequest.isEmail() && !signRequest.isPhone())
            throw new HlBadRequestException("注册账号必需为邮箱或手机号");


        return userRepository.signup(signRequest);
    }


    public User signin(SignRequest signRequest) {
        if (!signRequest.isEmail() && !signRequest.isPhone())
            throw new HlBadRequestException("登录账号必需为邮箱或手机号");


        return userRepository.signin(signRequest);
    }

    @Transactional
    public Optional<User> updateUser(User user) {
        if (!userRepository.exists(user.getId()))
            return Optional.empty();

        return Optional.of(userRepository.save(user));
    }

}
