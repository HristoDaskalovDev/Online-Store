package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
