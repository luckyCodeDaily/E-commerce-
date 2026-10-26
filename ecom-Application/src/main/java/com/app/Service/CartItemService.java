package com.app.Service;

import com.app.Repository.CartRepository;
import com.app.Repository.ProductRepository;
import com.app.Repository.UserRepo;
import com.app.dto.CartItemRequest;
import com.app.models.Cartitem;
import com.app.models.Product;
import com.app.models.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartRepository cartRepository;

    public boolean addToCart(String userID, CartItemRequest cartItemRequest) {
        Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductID());
        if (optionalProduct.isEmpty()) {
            return false;
        }
        Product product = optionalProduct.get();
        if (product.getStockQuality() < cartItemRequest.getQuantity()) {
            return false;
        }
        Optional<Users> usersOptional = userRepo.findById(Long.valueOf(userID));
        if (usersOptional.isEmpty()) {
            return false;
        }
        Users users = usersOptional.get();

        Cartitem ExistingCartItem = cartRepository.findByUsersAndProduct(users, product);
        if (ExistingCartItem != null) {
            //update in cart
            ExistingCartItem.setQuantity(ExistingCartItem.getQuantity() + cartItemRequest.getQuantity());
            ExistingCartItem.setPrice(product.getPrice().multiply(ExistingCartItem.getPrice()));
        } else {
            //new cartItem
            Cartitem newCartItem = new Cartitem();
            newCartItem.setProduct(product);
            newCartItem.setUsers(users);
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartRepository.save(newCartItem);

        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Users> usersOptional = userRepo.findById(Long.valueOf(userId));
        if (optionalProduct.isPresent()&&usersOptional.isPresent()){
            cartRepository.deleteByUsersAndProduct(usersOptional,optionalProduct);
            return true;
        }
        return false;
    }

    public List<Cartitem> fetchItemfromCartById(String userId) {
       return userRepo.findById(Long.valueOf(userId)).
               map(cartRepository::findByUsers)
               .orElseGet(List::of);




    }

    public void clearCart(String userID) {
        userRepo.findById(Long.valueOf(userID)).ifPresent(users -> {
            cartRepository.deleteByUsers(users);
        });
    }
}
