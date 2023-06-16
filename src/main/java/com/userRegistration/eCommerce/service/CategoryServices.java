package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.dao.CategoryDao;
import com.userRegistration.eCommerce.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryServices {
    @Autowired
    private CategoryDao categoryDao;

    public Category addNewCategory(Category category){return categoryDao.save(category);}


    public ArrayList<Category> getAllCategories(){return (ArrayList<Category>) categoryDao.findAll();}
    public void deleteCategoryDetails(Long categoryId){categoryDao.deleteById(categoryId);}
    public Category getCategoryDetailsById(Long categoryId){return  categoryDao.findById(categoryId).get();}

}