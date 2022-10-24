package com.nebula.nebula_auth.app.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ROLE", schema = "users")
@SequenceGenerator(
        name = "SEQ_ROLE_ID_GENERATOR",
        sequenceName = "SEQ_ROLE_ID",
        allocationSize = 1,
        initialValue = 1
)
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private int id;
    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String roleName;
    @Column(name = "ROLE_DESC")
    private String roleDesc;

    public Role() {
    }

    public Role(int id, String roleName, String roleDesc) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
