package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.UserEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.exception.OperationFailedException;
import com.hristodaskalov.online.store.model.Role;
import com.hristodaskalov.online.store.model.User;
import com.hristodaskalov.online.store.repository.UserRepository;
import com.hristodaskalov.online.store.service.RoleService;
import com.hristodaskalov.online.store.service.UserService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import com.hristodaskalov.online.store.utils.Validation;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User retrieveLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName;

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            loggedUserName = authentication.getName();
            return ObjectConverter.convertObject(
                    loadUserByUsername(loggedUserName),
                    User.class
            );
            //return new org.springframework.security.core.userdetails.User(curr.getUsername(), curr.getPassword(),
           //         mapRolesToAuthorities(curr.getRole()));

        }

        throw new OperationFailedException("Could not retrieve logged user.");
    }

    @Override
    @Transactional
    public User registerUser(User user) {

        validateFields(user);
        user.setPassword(encodePassword(user.getPassword()));
        UserEntity userEntity = ObjectConverter.convertObject(user, UserEntity.class);
        userEntity = userRepository.save(userEntity);

        return ObjectConverter.convertObject(userEntity, User.class);
    }

    private void validateFields(User user) {
        Validation.fieldNotEmptyOrNull(user.getUsername(), "username");
        Validation.fieldHasValidLength(user.getUsername(), Constants.NAME_FIELD_MAX_LENGTH, "username");
        Validation.fieldNotEmptyOrNull(user.getEmail(), "email");
        Validation.fieldHasValidLength(user.getEmail(), Constants.EMAIL_FIELD_MAX_LENGTH, "email");
        Validation.validateEmail(user.getEmail());
        Validation.fieldNotEmptyOrNull(user.getPassword(), "password");
        Validation.validatePassword(user.getPassword());
        Validation.fieldNotEmptyOrNull(user.getPhone(), "phone number");
        Validation.fieldHasValidLength(user.getPhone(), Constants.PHONE_FIELD_MAX_LENGTH, "phone number");
        checkIfRoleExists(user.getRole());
    }

    private void checkIfRoleExists(Role role) {

        if (role == null) {
            throw new InvalidInputException("Role cannot be null");
        }
        if (role.getId() == null) {
            throw new InvalidInputException("Role id cannot be null");
        }

        roleService.getRoleById(role.getId());
    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ObjectConverter.convertObject(
                userRepository.findFirstByEmail(username), User.class
        );
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
}

