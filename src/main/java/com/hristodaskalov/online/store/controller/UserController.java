package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.UserDto;
import com.hristodaskalov.online.store.model.User;
import com.hristodaskalov.online.store.service.UserService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO do not return user password
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {

        User user = ObjectConverter.convertObject(userDto, User.class);
        user = userService.registerUser(user);

        return new ResponseEntity<>(ObjectConverter.convertObject(user, UserDto.class), HttpStatus.CREATED);
    }

//    @GetMapping("/profile")
//    public ResponseEntity<UserProfileDto> getProfile() {
//        UserProfileDto userProfileDto =
//    }
}
