package com.hristodaskalov.online.store.repository;

import com.hristodaskalov.online.store.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    List<SubCategoryEntity> findAllByCategoryId(Long categoryId);

    Optional<SubCategoryEntity> findByIdAndCategoryId(Long id, Long categoryId);
}
