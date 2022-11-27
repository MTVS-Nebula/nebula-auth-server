package com.nebula.nebula_auth.app.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMAIL_VC", schema = "verification")
public class EmailVerificationCode {
    @Id
    @Column(name = "EMAIL")
    String email;
    @Column(name = "VERIFICATION_CODE")
    int code;

    public EmailVerificationCode() {
    }

    public EmailVerificationCode(String email, int code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "EmailVerificationCode{" +
                "email='" + email + '\'' +
                ", code=" + code +
                '}';
    }
}
