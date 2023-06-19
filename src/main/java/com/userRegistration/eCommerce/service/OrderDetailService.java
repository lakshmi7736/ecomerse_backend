package com.userRegistration.eCommerce.service;

import com.userRegistration.eCommerce.configuration.JwtRequestFilter;
import com.userRegistration.eCommerce.dao.OrderDetailDao;
import com.userRegistration.eCommerce.dao.ProductDao;
import com.userRegistration.eCommerce.dao.UserDao;
import com.userRegistration.eCommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private static final String ORDER_PLACED="Placed";

    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ProductDao productDao;
    public void placeOrder(OrderInput orderInput){
      List<OrderProductQuantity> productQuantityList=  orderInput.getOrderProductQuantityList();
        Long userIdEntity= JwtRequestFilter.userId;
        User user= userDao.findById(userIdEntity).get();
          for(OrderProductQuantity o: productQuantityList){
              Product product= productDao.findById(o.getProductId()).get();
              OrderDetail orderDetail=new OrderDetail(
                      orderInput.getFullName(),
                      orderInput.getFullAddress(),
                      orderInput.getContactNumber(),
                      orderInput.getAlternativeContactNumber(),
                      ORDER_PLACED,
                      product.getProductDiscountedPrice()*o.getQuantity(),
                      product,
                      user
              );
              orderDetailDao.save(orderDetail);
          }
      }
    }
