package com.nebula.nebula_auth.app.dao.entity.embeddable;

import com.nebula.nebula_auth.app.dao.entity.Role;
import com.nebula.nebula_auth.app.dao.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    Role role;

    public UserRoleId() {
    }

    public UserRoleId(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoleId{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
