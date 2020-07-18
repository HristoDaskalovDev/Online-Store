package com.hristodaskalov.online.store.dto;

import com.hristodaskalov.online.store.model.Role;

public class UserDto {

    //TODO remove password
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Role role;

    public UserDto(String username, String email, String password, String phone, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
