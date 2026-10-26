package com.ecommerce.order.Repository;

import com.ecommerce.order.Models.Cartitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cartitem, Long> {

    Cartitem findByUserIdAndProductId(Long userId, Long productId);

    void deleteByUserIdAndProductId(Long userId, Long productId);

    List<Cartitem> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}