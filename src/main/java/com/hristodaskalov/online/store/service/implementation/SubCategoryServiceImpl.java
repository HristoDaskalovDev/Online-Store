package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.SubCategoryEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.SubCategory;
import com.hristodaskalov.online.store.repository.SubCategoryRepository;
import com.hristodaskalov.online.store.service.CategoryService;
import com.hristodaskalov.online.store.service.SubCategoryService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;

import javax.transaction.Transactional;
import java.util.List;

import static com.hristodaskalov.online.store.utils.Validation.fieldHasValidLength;
import static com.hristodaskalov.online.store.utils.Validation.fieldNotEmptyOrNull;
import static com.hristodaskalov.online.store.utils.Validation.isSameAsExisting;

public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public SubCategory createSubCategory(Long categoryId, SubCategory subCategory) {

        validateFields(subCategory);
        subCategory.setCategory(
                categoryService.getCategory(categoryId)
        );

        SubCategoryEntity subCategoryEntity = ObjectConverter.convertObject(subCategory, SubCategoryEntity.class);
        subCategoryEntity = subCategoryRepository.save(subCategoryEntity);

        return ObjectConverter.convertObject(subCategoryEntity, SubCategory.class);
    }

    @Override
    @Transactional
    public SubCategory updateSubCategory(Long categoryId, Long existingSubCategoryId, SubCategory newSubCategory) {

        SubCategory existingSubCategory = getSubCategoryByIdAndCategoryId(categoryId, existingSubCategoryId);

        validateFields(newSubCategory);
        updateFieldsIfValid(existingSubCategory, newSubCategory);

        SubCategoryEntity updatedSubCategory = ObjectConverter.convertObject(existingSubCategory, SubCategoryEntity.class);
        updatedSubCategory = subCategoryRepository.save(updatedSubCategory);

        return ObjectConverter.convertObject(updatedSubCategory, SubCategory.class);
    }

    @Override
    public List<SubCategory> getAllSubsForCategory(Long categoryId) {
        List<SubCategoryEntity> subCategoryEntityList = subCategoryRepository.findAllByCategoryId(categoryId);
        return ObjectConverter.convertList(subCategoryEntityList, SubCategory.class);
    }

    //TODO add item check and not delete
    @Override
    @Transactional
    public void deleteSubCategory(Long categoryId, Long subCategoryId) {
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(subCategoryId).orElseThrow(
                () -> new InvalidInputException(String.format("Sub category with id: %d does not exist.", subCategoryId))
        );
        subCategoryRepository.delete(subCategoryEntity);
    }

    @Override
    public SubCategory getSubCategoryByIdAndCategoryId(Long categoryId, Long id) {
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findByIdAndCategoryId(id, categoryId).orElseThrow(
                ()-> new InvalidInputException(String.format("Subcategory with id: %d does not exist.", id))
        );

        return ObjectConverter.convertObject(subCategoryEntity, SubCategory.class);
    }

    private void validateFields(SubCategory subCategory) {
        fieldNotEmptyOrNull(subCategory.getName(), "sub category name");
        fieldHasValidLength(subCategory.getName(), Constants.NAME_FIELD_MAX_LENGTH, "sub category name");
    }

    private void updateFieldsIfValid(SubCategory existing, SubCategory newSubCategory) {
        if (!isSameAsExisting(existing.getName(), newSubCategory.getName())) {
            existing.setName(newSubCategory.getName());
        }
    }
}
