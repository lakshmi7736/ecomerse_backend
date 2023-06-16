package com.userRegistration.eCommerce.dao;

import com.userRegistration.eCommerce.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
