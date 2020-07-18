package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPictureRepository extends JpaRepository<ItemEntity, Long> {
}
