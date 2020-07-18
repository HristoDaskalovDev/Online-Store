package com.hristodaskalov.online.store.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {

    private Long id;
    private String name;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonCreator
    public RoleDto(@JsonProperty("name")String name) {
        this.name = name;
    }

    public RoleDto() {
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
}
