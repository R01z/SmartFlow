package com.smartflow.smartflow.dto.userdto;

import java.sql.Timestamp;
import java.util.List;

import com.smartflow.smartflow.model.Roles;

public class UserDTO {

    private Integer userId;

    private String name;

    private String email;

    private String password;

    private Timestamp registrationDate;

    private List<Roles> roles;

    public UserDTO(Integer userId, String name, String email, String password, Timestamp registrationDate,
            List<Roles> roles) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.roles = roles;
    }

    public UserDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
                + ", registrationDate=" + registrationDate + "]";
    }

}
