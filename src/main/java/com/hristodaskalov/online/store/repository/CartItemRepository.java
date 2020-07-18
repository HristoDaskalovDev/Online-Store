package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    List<CartItemEntity> findAllByCartIdAndItemId(Long cartId, Long itemId);
}
