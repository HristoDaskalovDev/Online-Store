package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    //TODO see if email or username
    User retrieveLoggedUser();

    User registerUser(User user);
}
