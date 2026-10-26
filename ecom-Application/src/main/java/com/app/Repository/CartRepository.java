package com.app.Repository;

import com.app.models.Cartitem;
import com.app.models.Product;
import com.app.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cartitem, Long> {

    Cartitem findByUsersAndProduct(Users users, Product product);

    void deleteByUsersAndProduct(Optional<Users> users, Optional<Product> product);
    List<Cartitem> findByUsers(Users users);   //

    void deleteByUsers(Users users);
}

