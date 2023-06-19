//package com.userRegistration.eCommerce.entity;
//
//import com.sun.istack.NotNull;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//@Entity
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long categoryId;
//    @NotNull
//    private String categoryName;
//
//    @ManyToMany
//    private List<Product> products;
//
//    public Category() {
//    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
//}