package com.nebula.nebula_auth.app.dao.entity;

import com.nebula.nebula_auth.app.dao.entity.embeddable.UserRoleId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_USER_ROLE", schema = "user")
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    public UserRole() {
    }

    public UserRole(UserRoleId id) {
        this.id = id;
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                '}';
    }
}
