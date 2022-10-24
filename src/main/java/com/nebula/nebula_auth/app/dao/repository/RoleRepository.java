package com.nebula.nebula_auth.app.dao.repository;

import com.nebula.nebula_auth.app.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String role);
}
