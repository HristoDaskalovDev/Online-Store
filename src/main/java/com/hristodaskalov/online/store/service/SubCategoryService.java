package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory createSubCategory(Long categoryId, SubCategory subCategory);

    SubCategory updateSubCategory(Long categoryId, Long existingSubCategoryId, SubCategory newSubCategory);

    List<SubCategory> getAllSubsForCategory(Long categoryId);

    SubCategory getSubCategoryByIdAndCategoryId(Long categoryId, Long id);

    void deleteSubCategory(Long categoryId, Long subCategoryId);
}
