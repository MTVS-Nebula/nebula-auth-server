package com.nebula.nebula_auth.app.dao.repository;

import com.nebula.nebula_auth.app.dao.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {

}
