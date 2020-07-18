package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByEmail(String email);
}
