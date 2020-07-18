package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.repository.UserPictureRepository;
import com.hristodaskalov.online.store.service.UserPictureService;

public class UserPictureServiceImpl implements UserPictureService {

    private final UserPictureRepository userPictureRepository;

    public UserPictureServiceImpl(UserPictureRepository userPictureRepository) {
        this.userPictureRepository = userPictureRepository;
    }
}
