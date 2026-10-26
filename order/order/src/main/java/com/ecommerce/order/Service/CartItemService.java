package com.ecommerce.order.Service;


import com.ecommerce.order.Dto.CartItemRequest;
import com.ecommerce.order.Models.Cartitem;
import com.ecommerce.order.Repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemService {
    //@Autowired
 //   private ProductRepository productRepository;
   // @Autowired
   // private UserRepo userRepo;
    @Autowired
    private CartRepository cartRepository;

    public boolean addToCart(Long userID, CartItemRequest cartItemRequest) {
      //  Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductID());
        //if (optionalProduct.isEmpty()) {
          //  return false;
        //}
        //Product product = optionalProduct.get();
        //if (product.getStockQuality() < cartItemRequest.getQuantity()) {
           // return false;
        //}
        //Optional<Users> usersOptional = userRepo.findById(Long.valueOf(userID));
        //if (usersOptional.isEmpty()) {
          //  return false;
        //}
        //Users users = usersOptional.get();

        Cartitem ExistingCartItem = cartRepository.findByUserIdAndProductId(userID,cartItemRequest.getProductID());
        if (ExistingCartItem != null) {
            //update in cart
            ExistingCartItem.setQuantity(ExistingCartItem.getQuantity() + cartItemRequest.getQuantity());
            ExistingCartItem.setPrice(BigDecimal.valueOf(1000.0));
        } else {
            //new cartItem
            Cartitem newCartItem = new Cartitem();
            newCartItem.setProductId(Long.valueOf(cartItemRequest.getProductID()));
            newCartItem.setUserId(Long.valueOf(userID));
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(BigDecimal.valueOf(1000.0));
            cartRepository.save(newCartItem);

        }
        return true;
    }

    public boolean deleteItemFromCart(Long userId, Long productId) {
        Cartitem cartitem = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cartitem !=null){
            cartRepository.delete(cartitem);
            return true;
        }
        return false;
    }

    public List<Cartitem> fetchItemfromCartById(Long userId) {
       return cartRepository.findByUserId(userId);

    }

    public void clearCart(Long userID) {
        cartRepository.deleteByUserId(userID);
    }
}
