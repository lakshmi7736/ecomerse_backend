package com.userRegistration.eCommerce.controller;

import com.userRegistration.eCommerce.entity.Category;
import com.userRegistration.eCommerce.service.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

//
//    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewCategory"})
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category){
        return new ResponseEntity<Category>( categoryServices.addNewCategory(category), HttpStatus.CREATED );
    }

    @GetMapping({"/getAllCategories"})
    public List<Category>getAllCategories(){
        return categoryServices.getAllCategories();
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteCategoryDetails/{categoryId}"})
    public void deleteCategoryDetails(@PathVariable("categoryId")Long categoryId){
        categoryServices.deleteCategoryDetails(categoryId);
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getCategoryDetailsById/{categoryId}"})
    public ResponseEntity<Category> getCategoryDetailsById(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<Category>(categoryServices.getCategoryDetailsById(categoryId),HttpStatus.OK) ;
    }
}