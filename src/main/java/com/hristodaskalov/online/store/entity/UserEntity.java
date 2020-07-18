package com.hristodaskalov.online.store.entity;

import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToOne
    @JoinColumn(name = "profile_picture_id")
    private UserPictureEntity userPicture;

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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UserPictureEntity getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(UserPictureEntity userPicture) {
        this.userPicture = userPicture;
    }
}
