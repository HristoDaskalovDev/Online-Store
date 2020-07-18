package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    Category getCategory(Long id);

    void deleteCategory(Long id);

    List<Category> getAllCategories();
}
