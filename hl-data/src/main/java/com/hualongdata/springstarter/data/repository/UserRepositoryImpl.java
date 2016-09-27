package com.hualongdata.springstarter.data.repository;

import com.hualongdata.exception.HlBadRequestException;
import com.hualongdata.exception.HlUnauthorizedException;
import com.hualongdata.springstarter.data.domain.SignRequest;
import com.hualongdata.springstarter.data.model.Credential;
import com.hualongdata.springstarter.data.model.User;
import com.hualongdata.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import scala.Tuple2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

/**
 * Created by yangbajing on 16-9-5.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CredentialRepository credentialRepository;  //认证信息

    @Override
    @Transactional
    public User signup(SignRequest signRequest) {
        Credential credential = new Credential();
        if (signRequest.isEmail()) {
            credential.setEmail(signRequest.getAccount());
        } else if (signRequest.isPhone()) {
            credential.setPhone(signRequest.getAccount());
        } else {
            throw new HlBadRequestException("注册账号需要为邮箱或手机号");
        }

        Tuple2<String, String> saltAndPassword = Utils.generateSaltAndPassword(signRequest.password());
        credential.setSalt(saltAndPassword._1());
        credential.setPassword(saltAndPassword._2());
        credential.setCreatedAt(LocalDateTime.now());
        credential.setUpdatedAt(LocalDateTime.now());
        em.persist(credential);

        User user = new User();
        user.setId(credential.getId());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        em.persist(user);

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User signin(SignRequest signRequest) {
        Credential c;
        if (signRequest.isEmail()) {
            c = credentialRepository.findByEmail(signRequest.getAccount());
        } else if (signRequest.isPhone()) {
            c = credentialRepository.findByPhone(signRequest.getAccount());
        } else {
            throw new HlBadRequestException("登录账号需要为邮箱或手机号");
        }

        if (!Utils.matchPassword(c.getSalt(), c.getPassword(), signRequest.password())) {
            throw new HlUnauthorizedException("账号：" + signRequest.getAccount() + " 用户认证失败");
        }

        return em.find(User.class, c.getId());

    }


}
