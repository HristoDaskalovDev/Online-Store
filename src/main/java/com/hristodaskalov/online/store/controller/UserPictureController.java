package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.service.UserPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/profile/picture")
public class UserPictureController {

    private final UserPictureService userPictureService;

    @Autowired
    public UserPictureController(UserPictureService userPictureService) {
        this.userPictureService = userPictureService;
    }
}
