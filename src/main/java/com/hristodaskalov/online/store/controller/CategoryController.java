package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.CategoryDto;
import com.hristodaskalov.online.store.model.Category;
import com.hristodaskalov.online.store.service.CategoryService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = ObjectConverter.convertObject(categoryDto, Category.class);
        category = categoryService.createCategory(category);
        return new ResponseEntity<>(ObjectConverter.convertObject(category, CategoryDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CategoryDto categoryDto) {
        Category category = ObjectConverter.convertObject(categoryDto, Category.class);
        category = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(ObjectConverter.convertObject(category, CategoryDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                ObjectConverter.convertObject(categoryService.getCategory(id), CategoryDto.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return ObjectConverter.convertList(categoryService.getAllCategories(), CategoryDto.class);
    }
}
