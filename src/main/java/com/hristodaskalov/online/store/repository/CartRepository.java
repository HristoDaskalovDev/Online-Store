package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findOneByUserId(Long id);
}
