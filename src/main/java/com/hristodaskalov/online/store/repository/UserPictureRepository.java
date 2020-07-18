package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.UserPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPictureRepository extends JpaRepository<UserPictureEntity, Long> {
}
