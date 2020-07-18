package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.RoleDto;
import com.hristodaskalov.online.store.model.Role;
import com.hristodaskalov.online.store.service.RoleService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        return new ResponseEntity<>(ObjectConverter.convertObject(role, RoleDto.class), HttpStatus.OK);
    }
}
