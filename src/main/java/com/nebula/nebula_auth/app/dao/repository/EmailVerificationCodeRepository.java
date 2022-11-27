package com.nebula.nebula_auth.app.dao.repository;

import com.nebula.nebula_auth.app.dao.entity.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationCodeRepository extends JpaRepository<EmailVerificationCode, String> {
    int deleteByEmail(String email);
    EmailVerificationCode findByEmail(String email);
}
