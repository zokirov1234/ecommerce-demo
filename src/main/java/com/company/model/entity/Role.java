package com.company.model.entity;

import com.company.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    public Role(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + roleEnum.name();
    }
}
