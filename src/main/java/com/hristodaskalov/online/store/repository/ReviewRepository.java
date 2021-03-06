package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findAllByItemId(Long id);

    Optional<ReviewEntity> findByIdAndItemId(Long id, Long itemId);
}
