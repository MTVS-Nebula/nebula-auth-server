package com.nebula.nebula_auth.app.dto.info;

import java.util.Date;

public class InfoDTO {
    private int id;
    private String username;
    private String email;
    private Date joinDate;
    private String isDeleted;
    private Date deletedDate;

    public InfoDTO() {
    }
    public InfoDTO(int id, String username, String email, Date joinDate, String isDeleted, Date deletedDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.joinDate = joinDate;
        this.isDeleted = isDeleted;
        this.deletedDate = deletedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return "InfoDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", joinDate=" + joinDate +
                ", isDeleted='" + isDeleted + '\'' +
                ", deletedDate=" + deletedDate +
                '}';
    }
}
