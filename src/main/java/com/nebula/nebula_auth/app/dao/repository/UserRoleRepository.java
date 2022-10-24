package com.nebula.nebula_auth.app.dao.repository;

import com.nebula.nebula_auth.app.dao.entity.UserRole;
import com.nebula.nebula_auth.app.dao.entity.embeddable.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository <UserRole, UserRoleId>{
    List<UserRole> findByIdUserUsername(String username);
}
