package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.dao.ProductDao;
import com.userRegistration.eCommerce.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    public Product addNewProduct(Product product){
       return productDao.save(product);
    }

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) productDao.findAll();
    }

    public void deleteProductDetails(Integer productId){
        productDao.deleteById(productId);
    }

    public Product getProductDetailsById(Integer productId){
       return productDao.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout,Integer productId){
        if(isSingleProductCheckout){
            List<Product> list=new ArrayList<>();
            Product product=productDao.findById(productId).get();
             list.add(product);
             return list;
        }else {

        }
        return new ArrayList<>();

    }

}
