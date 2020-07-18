package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.RoleEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.Role;
import com.hristodaskalov.online.store.repository.RoleRepository;
import com.hristodaskalov.online.store.service.RoleService;
import com.hristodaskalov.online.store.utils.ObjectConverter;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long roleId) {

        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(
                ()-> new InvalidInputException(String.format("Role with id: %d does not exist.", roleId))
        );

        return ObjectConverter.convertObject(roleEntity, Role.class);
    }
}
