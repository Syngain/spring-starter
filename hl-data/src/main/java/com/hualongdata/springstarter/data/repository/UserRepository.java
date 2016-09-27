package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangbajing on 16-9-2.
 */
public interface UserRepository extends JpaRepository<User, java.lang.Long>, UserRepositoryCustom {
    User findByNickname(String name);

}
