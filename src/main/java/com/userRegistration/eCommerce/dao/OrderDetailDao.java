package com.userRegistration.eCommerce.dao;

import com.userRegistration.eCommerce.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetail,Integer> {
}
