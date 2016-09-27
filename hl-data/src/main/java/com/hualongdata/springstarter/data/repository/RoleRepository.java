package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangbajing on 16-9-9.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
