package com.hristodaskalov.online.store.entity;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;


//TODO FIX to extend base entity
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDateTime createdTs;

    public RoleEntity(String name) {
        this.name = name;
        this.createdTs = LocalDateTime.now(Clock.systemUTC());
    }

    public RoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(LocalDateTime createdTs) {
        this.createdTs = createdTs;
    }
}
