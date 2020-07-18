package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.SubCategoryDto;
import com.hristodaskalov.online.store.model.SubCategory;
import com.hristodaskalov.online.store.service.SubCategoryService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/categories/{categoryId}/sub")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<SubCategoryDto> createSubCategory(@PathVariable("categoryId") Long categoryId,
                                                            @RequestBody SubCategoryDto subCategoryDto) {
        SubCategory subCategory = ObjectConverter.convertObject(subCategoryDto, SubCategory.class);
        subCategory = subCategoryService.createSubCategory(categoryId, subCategory);
        return new ResponseEntity<>(ObjectConverter.convertObject(subCategory, SubCategoryDto.class), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDto> updateSubCategory(@PathVariable("categoryId") Long categoryId,
                                                            @PathVariable("id") Long id,
                                                            @RequestBody SubCategoryDto subCategoryDto){

        SubCategory subCategory = ObjectConverter.convertObject(subCategoryDto, SubCategory.class);
        subCategory = subCategoryService.updateSubCategory(categoryId, id, subCategory);
        return new ResponseEntity<>(ObjectConverter.convertObject(subCategory, SubCategoryDto.class), HttpStatus.OK);
    }

    @GetMapping
    public List<SubCategoryDto> getAllSubsForCategory(@PathVariable("categoryId") Long categoryId) {
        List<SubCategory> subCategoryList = subCategoryService.getAllSubsForCategory(categoryId);
        return ObjectConverter.convertList(subCategoryList, SubCategoryDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubCategory(@PathVariable("categoryId") Long categoryId,
                                                        @PathVariable("id") Long id) {

        subCategoryService.deleteSubCategory(categoryId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
