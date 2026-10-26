package com.ecommerce.order.Repository;


import com.ecommerce.order.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
