package com.nebula.nebula_auth.app.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_LOGIN_LOG", schema = "user")
@SequenceGenerator(
        name = "SEQ_LOGIN_LOG_ID_GENERATOR",
        sequenceName = "SEQ_LOGIN_LOG",
        initialValue = 1,
        allocationSize = 1
)
public class LoginLog {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_LOGIN_LOG_ID_GENERATOR"
    )
    @Column(name = "LOGIN_LOG_ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "LOGIN_DATE")
    private Date loginDate;

    public LoginLog() {
    }

    public LoginLog(int id, User user, Date loginDate) {
        this.id = id;
        this.user = user;
        this.loginDate = loginDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id=" + id +
                ", user=" + user +
                ", loginDate=" + loginDate +
                '}';
    }
}
