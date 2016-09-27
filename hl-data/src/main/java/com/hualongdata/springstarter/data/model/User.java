package com.hualongdata.springstarter.data.model;

import com.hualongdata.springstarter.common.domain.UserSex;
import com.hualongdata.springstarter.common.domain.UserStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by yangbajing on 16-9-2.
 */
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
    @Id
    private java.lang.Long id;

    private String nickname;

    @OneToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private UserSex sex = UserSex.U;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.CREATED;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
