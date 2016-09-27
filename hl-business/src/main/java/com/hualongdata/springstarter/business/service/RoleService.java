package com.hualongdata.springstarter.business.service;

import com.hualongdata.springstarter.data.model.Role;
import com.hualongdata.springstarter.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RoleService(RoleRepository roleRepository,
                       RestTemplateBuilder restTemplateBuilder) {
        this.roleRepository = roleRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 根据角色ID查找用户
     *
     * @param id 角色ID
     * @return 找到用户返回 Users，否则返回 null
     */
    public Optional<Role> findRoleById(Integer id) {
        return Optional.ofNullable(roleRepository.findOne(id));
    }

    public Optional<List<Role>> findRoleAll() {
        return Optional.ofNullable(roleRepository.findAll());
    }

    public Optional<Role> crateRole(Role role) {
        System.out.println(role);
        return Optional.ofNullable(roleRepository.save(role));}

    public void delateRole(Integer id) { roleRepository.delete(id);}
    /**
     * 角色信息更新
     * @param role
     * @return
     */

    @Transactional
    public Optional<Role> updateRole(Role role) {
        if (!roleRepository.exists(role.getId()))
            return Optional.empty();
        return Optional.of(roleRepository.save(role));
    }

}
