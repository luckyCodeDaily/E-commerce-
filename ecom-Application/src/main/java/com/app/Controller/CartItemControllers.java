package com.app.Controller;

import com.app.Service.CartItemService;
import com.app.dto.CartItemRequest;
import com.app.models.Cartitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemControllers {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                    @RequestBody CartItemRequest request){
       if (!cartItemService.addToCart(userId,request)){
           return new ResponseEntity<>("\"PRODUCT out of stock or  User not found or Product not found \"", HttpStatus.BAD_REQUEST);
       }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Boolean> removeFromCart(@RequestHeader("X-User-ID") String userId,
                                                  @PathVariable Long productId){
       boolean deleted =  cartItemService.deleteItemFromCart(userId,productId);
      return deleted ? ResponseEntity.noContent().build():ResponseEntity.notFound().build();

    }

@GetMapping
    public ResponseEntity<List<Cartitem>>FetchByUserId(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(cartItemService.fetchItemfromCartById((userId)));

    }
}

