package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.CategoryEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.Category;
import com.hristodaskalov.online.store.repository.CategoryRepository;
import com.hristodaskalov.online.store.service.CategoryService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;

import javax.transaction.Transactional;
import java.util.List;

import static com.hristodaskalov.online.store.utils.Validation.*;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        validateFields(category);
        CategoryEntity categoryEntity = ObjectConverter.convertObject(category, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);

        return ObjectConverter.convertObject(categoryEntity, Category.class);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {

        Category existingCategory = getCategory(id);

        validateFields(category);
        updateFieldsIfValid(existingCategory, category);

        CategoryEntity categoryEntity = categoryRepository.save(
                ObjectConverter.convertObject(existingCategory, CategoryEntity.class)
        );

        return ObjectConverter.convertObject(categoryEntity, Category.class);
    }

    @Override
    public Category getCategory(Long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                () -> new InvalidInputException(String.format("Category with id: %d does not exist", id))
        );

        return ObjectConverter.convertObject(categoryEntity, Category.class);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {

        Category category = getCategory(id);
        categoryRepository.delete(ObjectConverter.convertObject(category, CategoryEntity.class));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return ObjectConverter.convertList(categoryEntityList, Category.class);
    }

    private void updateFieldsIfValid(Category existingCategory, Category newCategory) {

       if (!isSameAsExisting(existingCategory.getName(), newCategory.getName())) {
           existingCategory.setName(newCategory.getName());
       }
    }

    private void validateFields(Category category) {

        fieldNotEmptyOrNull(category.getName(), "category name");
        fieldHasValidLength(category.getName(), Constants.NAME_FIELD_MAX_LENGTH, "category name");
    }
}
