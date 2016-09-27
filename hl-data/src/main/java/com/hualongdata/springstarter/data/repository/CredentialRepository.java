package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangbajing on 16-9-8.
 */
public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findByEmail(String phone);

    Credential findByPhone(String phone);
}
