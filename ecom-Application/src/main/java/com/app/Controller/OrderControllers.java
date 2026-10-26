package com.app.Controller;

import com.app.Service.OrderService;
import com.app.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/order")
public class OrderControllers {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse>createOrder(@RequestHeader("X-User-ID") String userID){
       return orderService.addOrder(userID).map(OrderResponse->new ResponseEntity(OrderResponse,HttpStatus.CREATED))
               .orElseGet(()->ResponseEntity.badRequest().build());
    }
}
